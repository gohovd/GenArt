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
    static Application appInit = new Application();
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
    PVector mouse;
    boolean randomLineButton = false;
    int pulseAngle = 0;
    //Declare the robot.
    aRobot Tormod;
/////////////////////////////////////////////////////////////////////////
    boolean crossDotsButton = false;
    int i = 0; //variabler for crossdots
    boolean q = false;//variabler for crossdots
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

///////////////////////////////////////////////////////////////////////////

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

        ////////////setup for save funksjon///////////////////////////////
        pg = createGraphics(200, 200);//størrelse på boxa
        f = createFont("Arial",16,true);//gir f en font og størrelse


        // Set the font and fill for text
        textFont(f);
        fill(0);



        //////////////////setup for save funksjon slutt////////////////////
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

            if(saveButton){

/*
                if(win == false) {
                    JFrame aWindow = new JFrame("This is the Window Title");
                    int windowWidth = 200;           // Window width in pixels
                    int windowHeight = 200;          // Window height in pixels
                    aWindow.setBounds(width/2, height/2-100,       // Set position
                            windowWidth, windowHeight);  // and size
                    /////////////////////////////////////////////////////////////

                    /////////////////////////////////////////////////////////////

                    aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    aWindow.setVisible(true);        // Display the window
                win = true;
                }




                //putte dette i en jframe!!!!!!!!!!!!!!!!
  */





                    pg.beginDraw();


                if(takepic==false) {

                    c=get(width - 200, height - 200, 200, 200);
                    //c.save("temp.jpg");
                    takepic = true;

                    //if pause == true/false
                    //set takepic false
                }

                    pg.background(255, 255, 0);

                    pg.stroke(255);

                    pg.text("skriv ditt ønskede filnavn. \nF.EKS: \n'minFil.jpg' \neller \n'mittBilde.PNG'", indent, 40);
                    pg.fill(0, 0, 0);

                    pg.text(typing, indent, 120);

                    //if(saved.contains(".jpg")||saved.contains(".png")){
                    //pg.text(saved+" er nå lagret",indent,130);
                    //}

                    pg.endDraw();

                    //image(pg, pg.width/0.17, pg.height/0.4);

                    image(pg, width - 200, height - 200);
//image(c,mouseX,mouseY);

                    //image(c ,mouseX, mouseY);


            }




        }


    }

    ///////////////////funksjoner for save funksjon/////////////////////////////////



    public void  keyPressed() {



            // If the return key is pressed, save the String and clear it
            if (key == '\n') {
                saved = typing;
                // A String can be cleared by setting it equal to ""
                typing = "";
                if (saved.contains("jpg") || saved.contains("JPG") || saved.contains("Jpg") || saved.contains("jpeg") || saved.contains("JPEG") || saved.contains("Jpeg")) {

                    int dotPos = saved.lastIndexOf(".");
                    if (dotPos > 0)
                        saved = saved.substring(0, dotPos);

                    pg.beginDraw();

                    //pg.background(255, 255, 255);


                    pg.stroke(255);
                    image(c, width - 200, height - 200);
                    pg.endDraw();



                    save();


                }
                if (saved.contains("PNG") || saved.contains("Png") || saved.contains("png")) {

                    int dotPos = saved.lastIndexOf(".");
                    if (dotPos > 0)
                        saved = saved.substring(0, dotPos);
                    save2();

                }


            } else {
                // Otherwise, concatenate the String
                // Each character typed by the user is added to the end of the String variable.
                typing = typing + key;
            }
        }

    void save(){

        String desktopPath = System.getProperty("user.home") + "/Desktop/";
        save(desktopPath + saved + ".jpg");

    }

    void save2(){

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

        }
        else if(evt.getActionCommand().equals("save")){
            saveButton = true;
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