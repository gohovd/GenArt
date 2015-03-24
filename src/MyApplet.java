import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;

import processing.core.*;

import javax.swing.*;

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
    public static Application appInit = new Application();
    boolean pause = false;
    // Variables related to the "bouncing ball".
    ArrayList<Ball> ballList;
    boolean ballbutton = false;
    boolean varBubblesButton = false;
    boolean pulseButton = false;
    boolean starzButton = false;
    boolean squarezButton = false;
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
    PVector mouse;
    boolean randomLineButton = false;
    int pulseAngle = 0;
    //Declare the robot.
    aRobot Tormod;
    //Button for making random (auto generated art)
    boolean randomize;

    boolean crossDotsButton = false;
    int i = 0; //variabler for crossdots
    boolean q = false;//variabler for crossdots

    public void setup() {
        size(screenSize.width - 200, screenSize.height);
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
        //Instantiate the robot.
        Tormod = new aRobot();

    }

    public void collectButtonData(){
        try {
            JButton VB = appInit.getVectorButton();
            System.out.println("VBX: " + VB.getX());
        } catch (NullPointerException e) {
            System.out.println("NPE"); }
    }

    public void draw() {
        // Draw method "never" ends, here we iterate through all
        // the objects we want to display. Whether or not we display
        // them, is decided by the push of the button.
        if (!pause) {
            if (ballbutton) {
                for (int i = 0; i < ballList.size(); i++) {
                    Ball ball = ballList.get(i);
                    ball.move();
                    display(ball);
                }
            }

            if (vectorButton && mousePressed) {
                //Choice determines motion pattern (1: circular,  2: linear)
                int choice = 1;
                for (int i = 0; i < movers.size(); i++) {
                    Mover mover = movers.get(i);
                    mouse = new PVector(mouseX, mouseY);
                    mover.setVecLocation(mouse);
                    if (circular) {
                        choice = 1;
                    }
                    if (linear) {
                        choice = 2;
                    }
                    mover.update(choice);
                    //mover.checkEdges();
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

            if (crossDotsButton) {

                if (mousePressed && (mouseButton == LEFT)) {
                    if (i >= 0 && q == false) {
                        i += 1;
                    }

                    ellipse(mouseX + i, mouseY, 10, 10);
                    ellipse(mouseX - i, mouseY, 10, 10);
                    ellipse(mouseX, mouseY + i, 10, 10);
                    ellipse(mouseX, mouseY - i, 10, 10);

                    for (int i = 0; i < 100; i++) {
                        float r = random(0, 255);
                        float g = random(0, 255);
                        float b = random(0, 255);

                        noStroke();
                        fill(r, g, b);

                    }

                    if (i == 100) {
                        q = true;

                    }
                    if (q == true) {
                        i -= 1;
                    }

                    if (i == 0) {
                        q = false;
                    }
                }


            }

            if (starzButton) {

                if (mousePressed == true) {

                    strokeWeight((float)0.1);
                    fill(random(255), random(255), random(255), 127);
                    beginShape();
                    vertex(mouseX, mouseY - 50);
                    vertex(mouseX+14, mouseY-20);
                    vertex(mouseX+47, mouseY-15);
                    vertex(mouseX+23, mouseY+7);
                    vertex(mouseX+29, mouseY+40);
                    vertex(mouseX, mouseY+25);
                    vertex(mouseX-29, mouseY+40);
                    vertex(mouseX-23, mouseY+7);
                    vertex(mouseX-47, mouseY-15);
                    vertex(mouseX-14, mouseY-20);
                    endShape(CLOSE);

                }

            }

            if (squarezButton) {

                if (mousePressed == true && mouseButton == LEFT){
                    strokeWeight((float)0.1);
                    fill(random(255), random(255), random(255), 127);
                    rect(mouseX - 25, mouseY - 25, 100, 100);
                }

                if (mousePressed == true && mouseButton == RIGHT){
                    strokeWeight((float)0.1);
                    fill(random(255), random(255), random(255), 127);
                    rect(mouseX, mouseY,100,100);
                    rect();
                }

            }

        }
    }

    void rect(){
        fill(255,255,255,255);
        rect(mouseX+10, mouseY+10,80,80);

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
        starzButton = false;
        squarezButton = false;
        randomize = false;

        String vColor = appInit.getVColor();
        if (evt.getActionCommand().equals("create ball")) {
            createNewBall();
            pause = false;
        } else if (evt.getActionCommand().equals("create vector")) {
            createNewMover();
            pause = false;
        } else if (evt.getActionCommand().equals("randomize")) {
            collectButtonData();
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
            randomclr = false;
        } else if (evt.getActionCommand().equals("randomLines")) {
            randomLineButton = true;
            pause = false;

        } else if (evt.getActionCommand().equals("varBubbles")) {
            varBubblesButton = true;
            pause = false;

        } else if (evt.getActionCommand().equals("pulse")) {
            pulseButton = true;
            pause = false;

        } else if (evt.getActionCommand().equals("crossDots")) {
            crossDotsButton = true;
            pause = false;

        } else if (evt.getActionCommand().equals("starz")) {
            starzButton = true;
            pause = false;

        } else if (evt.getActionCommand().equals("squarez")) {
            squarezButton = true;
            pause = false;

        } else {
            println("actionPerformed(): can't handle " + evt.getActionCommand());
        }
    }

    public void itemStateChanged(ItemEvent e) {
        if (appInit.getCircularState() == true) {
            //If the linear state is already checked, we have to deselect first..
            if (linear) {
                appInit.setLinearState(false);
                linear = false;
            }
            appInit.setCircularState(true);
            circular = true;
        }
        if (appInit.getLinearState() == true) {
            //If the circular state is already checked, we have to deselect first..
            if (circular) {
                appInit.setCircularState(false);
                circular = false;
            }
            appInit.setLinearState(true);
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
        // X and Y speed to make the ball go in desired direction.
        float xDir, yDir;
        Ball up, down, left, right, left45, negleft45, right45, negright45;
        // UP
        xDir = 0;
        yDir = -10;
        up = new Ball(xDir, yDir);

        // DOWN
        xDir = 0;
        yDir = 5;
        down = new Ball(xDir, yDir);

        // LEFT
        xDir = -5;
        yDir = 0;
        left = new Ball(xDir, yDir);

        // RIGHT
        xDir = 5;
        yDir = 0;
        right = new Ball(xDir, yDir);

        // 45 degrees left
        xDir = -5;
        yDir = -5;
        left45 = new Ball(xDir, yDir);

        // negative 45 degrees left
        xDir = -5;
        yDir = 5;
        negleft45 = new Ball(xDir, yDir);

        // 45 degrees right
        xDir = 5;
        yDir = -5;
        right45 = new Ball(xDir, yDir);

        // negative 45 degrees right
        xDir = 5;
        yDir = 5;
        negright45 = new Ball(xDir, yDir);

        ballList.add(up);
        ballList.add(down);
        ballList.add(left);
        ballList.add(right);
        ballList.add(left45);
        ballList.add(negleft45);
        ballList.add(right45);
        ballList.add(negright45);

        // Let class ball know what width and height we're working with.
        for(Ball b : ballList){
            b.setWidth(width);
            b.setHeight(height);
        }
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