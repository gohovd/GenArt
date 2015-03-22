import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;

import processing.core.*;

/**
 * A once, simple demonstration Applet.
 * kake
 *
 * @author georg munkel
 *         Seeing as implementing actions across files/classes,
 *         was difficult and/or impossible. I'll try to do everything
 *         in a singular file.
 */

public class MyApplet extends PApplet implements ActionListener, ItemListener {
    Application appInit = new Application();
    boolean pause = false;
    // Variables related to the "bouncing ball".
    ArrayList<Ball> ballList;
    boolean ballbutton = false;
    boolean varBubblesButton = false;
    boolean pulseButton = false;
    // Variables related to the mover/vector.
    ArrayList<Mover> movers;
    boolean vectorButton = false;
    int vStep = 0; // When steps, do something different.
    // Variable holding the background image.
    PImage bgImg = null;
    // Defining the applets screen size (-300 to make room for toolbar).
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    //RGB and Opacity for vector.
    float vR, vG, vB, vO;
    //Boolean controlling whether the vectors move linearly or circularly.
    //Also controlling whether the user wants random colored vectors.
    boolean circular, linear, randomclr;
    int steps = 0;
    PVector tmp;
    boolean randomLineButton = false;
    int pulseAngle = 0;

    boolean crossDotsButton = false;
    int i = 0; //variabler for crossdots
    boolean q = false;//variabler for crossdots

    public void setup() {
        size(screenSize.width - 300, screenSize.height);
        // Set up the bouncing balls.
        ballList = new ArrayList<Ball>();
        // Set up the movers/vectors.
        movers = new ArrayList<Mover>();
        background(255);
        // Default vector color (black).
        vR = 0;
        vG = 0;
        vB = 0;
        vO = 255;

    }

    public void draw() {
        // Draw method "never" ends, here we iterate through all
        // the objects we want to display. Whether or not we display
        // them or not, is decided by the push of the button.
        if (!pause) {
            if (ballbutton) {
                for (int i = 0; i < ballList.size(); i++) {
                    Ball ball = ballList.get(i);
                    ball.move();
                    display(ball);
                }
            }

            if (vectorButton && mousePressed) {
                for (int i = 0; i < movers.size(); i++) {
                    Mover mover = movers.get(i);
                    tmp = new PVector(mouseX, mouseY);
                    mover.setVecLocation(tmp);
                    mover.update();
                    mover.checkEdges();
                    vDisplay(mover);
                }
            }
            if (randomLineButton) {

                if (mousePressed && (mouseButton == LEFT)) {
                    strokeWeight(random(3, 8));
                    stroke(random(0, 255), random(0, 255), random(0, 255), random(0, 255));
                    line(mouseX - random(-100, 100), mouseY, mouseX + random(-100, 100), mouseY);
                } else if (mousePressed && (mouseButton == RIGHT)) {
                    strokeWeight(random(3, 8));
                    stroke(random(0, 255), random(0, 255), random(0, 255), random(0, 255));
                    line(mouseX, mouseY - random(-100, 100), mouseX, mouseY + random(-100, 100));


                }


            }

            if (varBubblesButton) {
                if (mousePressed == true) {
                    int x = mouseX;
                    int y = mouseY;
                    int px = pmouseX;
                    int py = pmouseY;

                    float speed = abs(x - px) + abs(y - py);
                    stroke(speed);
                    ellipse(x, y, speed, speed);
                }

            }
            if (pulseButton) {

                if (mousePressed == true) {

                   pulseAngle += 5;

                    float val = (float) (cos(radians(pulseAngle)) * 12.0);
                    for (int a = 0; a < 360; a += 75) {
                        float xoff = cos(radians(a)) * val;
                        float yoff = sin(radians(a)) * val;
                        fill(random(0, 255), random(0, 255), random(0, 255), random(0, 255));
                        ellipse(mouseX + xoff, mouseY + yoff, val, val);
                    }
                    fill(255);
                    ellipse(mouseX, mouseY, 2, 2);

                }


                }



            if(crossDotsButton){

                if(mousePressed && (mouseButton == LEFT)){
                    if(i>=0 && q == false){
                        i+=1;
                    }

                    ellipse(mouseX+i, mouseY, 10, 10);
                    ellipse(mouseX-i, mouseY, 10, 10);
                    ellipse(mouseX, mouseY+i, 10, 10);
                    ellipse(mouseX, mouseY-i, 10, 10);

                    for (int i = 0; i < 100; i++) {
                        float r = random(0, 255);
                        float g = random(0, 255);
                        float b = random(0, 255);

                        noStroke();
                        fill(r,g,b);

                    }

                    if(i ==100){
                        q = true;

                    }
                    if (q == true){
                        i-=1;
                    }

                    if(i == 0){
                        q = false;
                    }
                }


            }

        }
    }

    /**
     * implementation from interface ActionListener
     * method is called from the Application
     * the String being compared is the ActionCommand from the button
     */
    public void actionPerformed(ActionEvent evt) {

        vectorButton = false;
        randomLineButton = false;
        ballbutton = false;
        varBubblesButton = false;
        pulseButton = false;
        crossDotsButton = false;

        String vColor = appInit.getVColor();
        if (evt.getActionCommand().equals("create ball")) {
            createNewBall();
            pause = false;
        } else if (evt.getActionCommand().equals("create vector")) {
            createNewMover();
            pause = false;
        } else if (evt.getActionCommand().equals("clear")) {
            clear();
            pause = true;
        } else if (!vColor.isEmpty()) {
            ArrayList<String> clrs = new ArrayList<String>();
            if (!clrs.isEmpty()) {
                clrs.clear();
            }
            if (clrs.isEmpty()) {
                String[] separate = vColor.split(" ");
                for (String c : separate) {
                    clrs.add(c);
                }
                vR = Float.parseFloat(clrs.get(0));
                vG = Float.parseFloat(clrs.get(1));
                vB = Float.parseFloat(clrs.get(2));
                vO = Float.parseFloat(clrs.get(3));
            }

        } else if (evt.getActionCommand().equals("randomLines")) {
            randomLineButton = true;
            pause = false;

        } else if (evt.getActionCommand().equals("varBubbles")) {
            varBubblesButton = true;
             pause = false;

        } else if (evt.getActionCommand().equals("pulse")) {
            pulseButton = true;
            pause = false;

        }

        else if (evt.getActionCommand().equals("crossDots")) {
            crossDotsButton = true;
            pause = false;

        }

        else {
            println("actionPerformed(): can't handle " + evt.getActionCommand());
        }
    }

    public void itemStateChanged(ItemEvent e) {
        if (appInit.getCircularState() == true) {
            //If the linear state is already checked, we have to deselect first..
            if (linear) {
                appInit.setLinearState(false);
            }
            circular = true;
        }
        if (appInit.getLinearState() == true) {
            //If the circular state is already checked, we have to deselect first..
            if (circular) {
                appInit.setCircularState(false);
            }
            linear = true;
        }
        if (appInit.getRandomColorState() == true) {
            randomclr = true;
        } else randomclr = false;
    }

    /**
     * this method is called by the ActionListener assigned to
     * the JButton buttonLoad in Application
     */
    public void loadBgImage(File selectedFile) {
        bgImg = loadImage(selectedFile.getAbsolutePath());
    }

    /*
 * creates a new ball object
 */
    private void createNewBall() {
        Ball nBall = new Ball();
        ballList.add(nBall);
        // Let class ball know what width and height we're working with.
        nBall.setWidth(width);
        nBall.setHeight(height);
        ballbutton = true; // Set to true, when button (create ball) is pressed.
    }

    /**
     * Creates a new instance of mover, adds it to array.
     */
    public void createNewMover() {
        Mover nMov = new Mover();
        // Let class mover know what width and height we're working with.
        nMov.setWidth(width);
        nMov.setHeight(height);
        movers.add(nMov);
        vectorButton = true; // Set to true, when button (create vector) is pressed.
    }

    /**
     * Clear the screen.
     */
    public void clear() {
        background(255);
        movers.clear();
        ballList.clear();
    }

    // Display methods!
    public void display(Ball ball) {
        stroke(ball.getBallColor().getRGB());
        fill(ball.getBallColor().getRGB(), 120);
        ellipse(ball.getLocX(), ball.getLocY(), ball.getBallSize(), ball.getBallSize());
    }

    void vDisplay(Mover mov) {
        noStroke();
        if (randomclr) {
            vR = random(255);
            vG = random(255);
            vB = random(255);
            vO = random(255);
        }

        fill(vR, vG, vB, vO);
        //fill(255, 0, 0, 255);
        ellipse(mov.getVecLocation().x, mov.getVecLocation().y, random(15, 20), random(15, 20));
    }
}