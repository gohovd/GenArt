import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

import processing.core.*;

import javax.swing.*;

/**
 * A once, simple demonstration Applet.
 */

public class MyApplet extends PApplet implements ActionListener, ItemListener {
    //An instance of class Application, used to utilize methods/attributes from class Application.
    //Can be sent to other classes as parameter.
    public Application appInit = new Application();
    boolean pause = false;
    // Variables related to the "bouncing ball".


    PulseShape pu = new PulseShape(this);
    CrossShape cr = new CrossShape(this);

    boolean ballbutton = false;
    Ball ballInstance;
    Mover moverInstance;
    boolean varBubblesButton = false;
    boolean pulseButton = false;
    boolean starzButton = false;
    boolean squarezButton = false;
    boolean trianglezButton = false;
    boolean strokeNColourButton = false;
    // Variables related to the mover/vector.
    boolean vectorButton = false;
    int vStep = 0; // When steps, do something different.
    // Variable holding the background image.
    PImage bgImg = null;
    // Defining the applets screen size (-300 to make room for toolbar).
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    //Boolean controlling whether the vectors move linearly or circularly.
    //Also controlling whether the user wants random colored vectors.
    boolean circular, linear, randomclr;
    boolean beenHereBefore = false;
    PVector mouse;
    boolean randomLineButton = false;
    //Declare the robot.
    aRobot Tormod;
    boolean killTormod;
    //Button for making random (auto generated art)
    boolean randomize;
    /////////////////////////////////////////////////////////////////////////
    boolean crossDotsButton = false;

    /////////////////////////////////////////////////////////////////////////
    boolean saveButton = false;
    PGraphics pg;//SaveBox
    PImage c;
    PFont f; //variabler for text input
    // Variable to store text currently being typed
    String typing = "";
    // Variable to store saved text when return is hit
    String saved = "";
    int indent = 25;
    String desktopPath = System.getProperty("user.home") + "/Desktop";
    boolean win = false;
    boolean takepic = false;
    boolean kukk = false;
    int count = 0;
///////////////////////////////////////////////////////////////////////////

    public void setup() {
        size(screenSize.width - 200, screenSize.height);
        // Set up the bouncing balls.
        ballInstance = new Ball();
        // Set up the movers/vectors.
        moverInstance = new Mover();
        background(255);
        Tormod = new aRobot(); // Instantiate the robot.
        Tormod.setPapp(this); // "Export" PApplet instance (from this class).
        ////////////setup for save funksjon///////////////////////////////
        pg = createGraphics(200, 200);//størrelse på boxa
        f = createFont("Arial", 16, true);//gir f en font og størrelse
        // Set the font and fill for text
        textFont(f);
        fill(0);

        //////////////////setup for save funksjon slutt////////////////////
    }

    public void draw() {
        // Draw method "never" ends, here we iterate through all
        // the objects we want to display. Whether or not we display
        // them, is decided by the push of the button.
        if (randomize && !killTormod) {
            try {
                Tormod.rMotion();
            } catch (AWTException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!pause) {
            if (ballbutton) {
                for (int i = 0; i < ballInstance.getBallList().size(); i++) {
                    Ball ball = ballInstance.getBallList().get(i);
                    ball.move();
                    ball.display();
                }
            }

            if (vectorButton && mousePressed) {
                //Choice determines motion pattern (1: circular,  2: linear)
                int choice = 1;
                for (int i = 0; i < moverInstance.getMovers().size(); i++) {
                    Mover mover = moverInstance.getMovers().get(i);
                    mouse = new PVector(mouseX, mouseY);
                    mover.setVecLocation(mouse);
                    if (circular) {
                        choice = 1;
                    }
                    if (linear) {
                        choice = 2;
                    }
                    mover.update(choice);
                    mover.display();
                }
            }
            if (randomLineButton) {
                LineShape li = new LineShape(this);
                li.drawLines();
            }
            if (varBubblesButton) {
                BubbleShape bu = new BubbleShape(this);
                bu.drawBubbles();
            }
            if (pulseButton) {
                pu.drawPulse();
            }
            if (crossDotsButton) {
                cr.drawCross();
            }
            if (starzButton) {
                StarShape st = new StarShape(this);
                st.drawStars();
                //st.drawHearts();

            }
            if (squarezButton) {
                SquareShape sq = new SquareShape(this);
                sq.drawSquares();
            }
            if (trianglezButton) {
                TriangleShape tr = new TriangleShape(this);
                tr.drawTriangles();
            }
            if (strokeNColourButton) {
            }
            if (saveButton) {


                if(kukk == false ) {

                    pg.beginDraw();
                    if (takepic == false) {
                        c = get(width - 200, height - 200, 200, 200);

                        takepic = true;

                    }
                    pg.background(255, 255, 0);
                    pg.stroke(255);
                    pg.text("skriv ditt ønskede filnavn. \nF.EKS: \n'minFil.jpg' \neller \n'mittBilde.PNG'", indent, 40);
                    pg.fill(0, 0, 0);
                    pg.text(typing, indent, 120);

                    pg.endDraw();


                    image(pg, width - 200, height - 200);

                }

            }
        }
    }

    ///////////////////Funksjoner for save funksjon/////////////////////////////////
    //////Kan legge til keyPressed events generelt. If key == 'x', do whatever//////
    public void keyPressed() {
        if(key == 'q') { killTormod = true; Tormod.end(); randomize = false; }
        // If the return key is pressed, save the String and clear it
        if (key == '\n') {
            saved = typing;
            // A String can be cleared by setting it equal to ""
            typing = "";
            if (saved.contains("jpg") || saved.contains("JPG") || saved.contains("Jpg") || saved.contains("jpeg") || saved.contains("JPEG") || saved.contains("Jpeg")) {
                int dotPos = saved.lastIndexOf(".");
                if (dotPos > 0)
                    saved = saved.substring(0, dotPos);

                image(c, width - 200, height - 200);

                save();

                pg.beginDraw();
                pg.image(c, 0, 0);
                pg.endDraw();
                takepic = false;
                saveButton = false;



            }
            if (saved.contains("PNG") || saved.contains("Png") || saved.contains("png")) {
                int dotPos = saved.lastIndexOf(".");
                if (dotPos > 0)
                    saved = saved.substring(0, dotPos);
                image(c, width - 200, height - 200);

                save2();

                pg.beginDraw();
                pg.image(c, 0, 0);
                pg.endDraw();
                takepic = false;
                saveButton = false;
            }
        } else {
            // Otherwise, concatenate the String
            // Each character typed by the user is added to the end of the String variable.
            typing = typing + key;
        }
    }

    void save() {
        String desktopPath = System.getProperty("user.home") + "/Desktop/";
        save(desktopPath + saved + ".jpg");
    }

    void save2() {
        String desktopPath = System.getProperty("user.home") + "/Desktop/";
        save(desktopPath + saved + ".png");
    }
    ///////////////////funksjoner for save funksjon SLUTT/////////////////////////////////

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
        saveButton = false;
        starzButton = false;
        squarezButton = false;
        trianglezButton = false;

        if (evt.getActionCommand().equals("create ball")) {
            ballInstance.initializeBalls();
            ballInstance.setPapp(this, appInit); // "Export" PApplet instance (from this class).
            ballbutton = true;
            
        } else if (evt.getActionCommand().equals("create vector")) {
            moverInstance.createNewMover();
            moverInstance.setPapp(this, appInit);
            vectorButton = true;
            
        } else if (evt.getActionCommand().equals("randomize")) {
            try {
                Tormod.displayInstructions();
                Tormod.reset();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (AWTException e) {
                e.printStackTrace();
            }
            randomize = true;
            killTormod = false;
            
        } else if (evt.getActionCommand().equals("clear")) {
            clear();

        } else if (evt.getActionCommand().equals("randomLines")) {
            randomLineButton = true;

        } else if (evt.getActionCommand().equals("varBubbles")) {
            varBubblesButton = true;

        } else if (evt.getActionCommand().equals("pulse")) {
            pulseButton = true;

        } else if (evt.getActionCommand().equals("crossDots")) {
            crossDotsButton = true;

        } else if (evt.getActionCommand().equals("save")) {
            saveButton = true;

        } else if (evt.getActionCommand().equals("starz")) {
            starzButton = true;

        } else if (evt.getActionCommand().equals("squarez")) {
            squarezButton = true;

        } else if (evt.getActionCommand().equals("trianglez")) {
            trianglezButton = true;

        } else if (evt.getActionCommand().equals("strokencolour")) {
            strokeNColourButton = true;
            appInit.setStrokeSize(2);
            System.out.println(appInit.getStrokeSize());
            ColorChooser color = new ColorChooser();
        } else {
            println("actionPerformed(): can't handle " + evt.getActionCommand());
        }
        pause = false;
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
     * Clear the screen.
     */
    public void clear() {
        background(255);
        moverInstance.clearMovers();
        ballInstance.clearBallList();
    }
}