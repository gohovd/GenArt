import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import processing.core.*;
/**
 * A once, simple demonstration Applet.
 * @author georg munkel
 * Seeing as implementing actions across files/classes,
 * was difficult and/or impossible. I'll try to do everything
 * in a singular file.
 */
public class MyApplet extends PApplet implements ActionListener{
    boolean pause = false;
    // Variables related to the "bouncing ball".
    ArrayList <Ball> ballList;
    boolean ballbutton = false;
    // Variables related to the mover/vector.
    ArrayList <Mover> movers;
    boolean vectorButton = false;
    int vStep = 0; // When steps, do something different.
    // Variable holding the background image.
    PImage bgImg = null;
    // Defining the applets screen size (-300 to make room for toolbar).
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public void setup() {
        size(screenSize.width-300, screenSize.height);
        // Set up the bouncing balls.
        ballList = new ArrayList<Ball>();
        // Set up the movers/vectors.
        movers = new ArrayList<Mover>();
        background(255);
    }

    public void draw() {
        // Checks whether or not a background image is loaded.
        /*
        if (bgImg == null) {
            background(255);
        } else {
            image(bgImg,0,0, width, height);
        }
        */

        // Draw method "never" ends, here we iterate through all
        // the objects we want to display. Whether or not we display
        // them or not, is decided by the push of the button.
        if(ballbutton && !pause) {
            for (int i = 0; i < ballList.size(); i++) {
                Ball ball = ballList.get(i);
                ball.move();
                ball.display();
            }
        }
        if(vectorButton && !pause && mousePressed){
            for (int i = 0; i < movers.size(); i++) {
                Mover mover = movers.get(i);
                mover.update();
                mover.checkEdges();
                mover.display();
            }
        }
    }
    /**
     * implementation from interface ActionListener
     * method is called from the Application
     * the String being compared is the ActionCommand from the button
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("create ball")) {
            createNewBall();
            pause = false;
        }
        else if(evt.getActionCommand().equals("create vector")){
            createNewMover();
            pause = false;
        }
        else if(evt.getActionCommand().equals("clear")){
            clear();
            pause = true;
        }
        else {
            println("actionPerformed(): can't handle " +evt.getActionCommand());
        }
    }

    /**
     * this method is called by the ActionListener assigned to
     * the JButton buttonLoad in Application
     */
    public void loadBgImage(File selectedFile) {
        bgImg = loadImage(selectedFile.getAbsolutePath());
    }
    /*
    * creates a new Ball instance and adds it to ballList
    */
    private void createNewBall() {
        Ball nBall = new Ball();
        ballList.add(nBall);
        ballbutton = true; // Set to true, when button (create ball) is pressed.
    }

    /**
     * Creates a new instance of mover, adds it to array.
     */
    public void createNewMover(){
        Mover nMov = new Mover();
        movers.add(nMov);
        vectorButton = true; // Set to true, when button (create vector) is pressed.
    }

    /**
     * Clear the screen.
     */
    public void clear(){
        background(255);
        movers.clear();
        ballList.clear();
    }
    /*
    * simple inner class Ball
    * balls have a position, speed, size and color
    * the basic constructor assign random values to all properties
    *
    * balls can move
    * balls can display themselves
    */
    private class Ball {
        float x;
        float y;
        float size;
        float speedX;
        float speedY;
        Color color;
        private Ball() {
            this.size = random(10, 40);
            this.x = random(this.size, width-this.size);
            this.y = random(this.size, height-this.size);
            this.speedX = random(-2, 2) *3;
            this.speedY = random(-2, 2) *3;
            this.color = new Color(random(1), random(1), random(1));
        }
        private void move() {
            if (x+size/2f > width || x-size/2f < 0) speedX = -speedX;
            if (y+size/2f > height || y-size/2f < 0) speedY = -speedY;
            x += speedX;
            y += speedY;
        }
        private void display() {
            stroke(color.getRGB());
            fill(color.getRGB(), 120);
            ellipse(x, y, size, size);
        }
    }

    /**
     * Second inner class 'Mover'. Makes something
     * similar to the bouncing balls, really. Supposed to be several vectors
     * moving in a circle or another pattern of motion.
     */
    class Mover {

        PVector location;
        PVector velocity;
        PVector acceleration;
        float topspeed;
        float r = 10;

        Mover() {
            location = new PVector(width/2, height/2);
            velocity = new PVector(0, 0);
            topspeed = 35;

        }

        void update() {
            // Our algorithm for calculating acceleration:
            //topspeed += .01;

            r += .19;
            float xx = cos(r)*60;
            float yy = sin(r)*60;
            PVector aim = new PVector(xx, yy);
            location = new PVector(mouseX, mouseY);

            PVector dir = PVector.sub(aim, location);  // Find vector pointing towards aim.

            dir.normalize();     // Normalize
            dir.mult((float) 0.5);       // Scale
            acceleration = aim;

            // Motion 101!  Velocity changes by acceleration.  Location changes by velocity.
            velocity.add(acceleration);
            velocity.limit(topspeed);
            location.add(velocity);

        }

        void display() {
            noStroke();

            fill(random(255), random(255), random(255), random(255));
            ellipse(location.x, location.y, random(15, 25), random(15, 20));
        }

        void checkEdges() {

            if (location.x > width) {
                location.x = 0;
            } else if (location.x < 0) {
                location.x = width;
            }

            if (location.y > height) {
                location.y = 0;
            } else if (location.y < 0) {
                location.y = height;
            }
        }
    }
}