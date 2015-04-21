import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;


import java.awt.Robot;
import java.awt.event.InputEvent;


import javafx.application.*;
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
    Border border;

    // Variables related to the "bouncing ball".
    Brush brush = new Brush(this);
    Brush pu = new PulseShape(this);
    Brush cr = new CrossShape(this);
    Brush st = new StarShape(this);
    Brush li = new LineShape(this);
    Brush bu = new BubbleShape(this);
    Brush sq = new SquareShape(this);
    Brush tr = new TriangleShape(this);
    Brush mover = new Mover(this);
    Brush moverInstance = new Mover(this);

    ArrayList<Brush> brushes = new ArrayList();

    boolean ballbutton = false;
    Ball ballInstance;
    boolean varBubblesButton = false;
    boolean pulseButton = false;
    boolean starzButton = false;
    boolean squarezButton = false;
    boolean trianglezButton = false;
    boolean strokeNColourButton = false;
    boolean heartButton = false;
    boolean signatureButton = false;
    
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
    // Color and stroke manager
    JColorChooser colors = new JColorChooser();
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
    //////////////////////////////////////////// filterButton
    boolean filterButton = false;
    PGraphics pg2;
    boolean nr = false;
    boolean nr2 = false;
    Robot robot; //deklarering av robot
    PImage d;
    ////////////////////////////////////////////////// funkyvectors bytter ut hearts
    ArrayList history;   // Define the history for pattern3
    boolean switcher = false;
    public Color col;
    //The colors we want to pass to all brushes.
    //Either chosen by the robot, or the user.
    int reds, greens, blues, alphas;


///////////////////////////////////////////////////////////////////////////

    public void setup() {
        //Put all brushes into an array.
        brushes.add(pu); brushes.add(st); brushes.add(cr); brushes.add(li); brushes.add(bu);
        brushes.add(sq); brushes.add(tr); brushes.add(moverInstance);
        // Set up the movers/vectors.
        moverInstance = new Mover(this);
        for(int i = 0; i < 50; i++) {
            //Circle radius is determined through the rTopSpeed. Great value => Great circle.
            float rTopSpeed = random(150);
            //The speed at which the circle rotates. Should be between 0.1 and 0.4.
            float TorqueIncrement = (float) 0.19;
            ((Mover)moverInstance).createNewMover(rTopSpeed, TorqueIncrement);
            //moverInstance.setPapp(this, appInit);
        }
        size(screenSize.width - 200, screenSize.height);
        // Set up the bouncing balls.
        ballInstance = new Ball();
        background(255);
        Tormod = new aRobot(); // Instantiate the robot.
        Tormod.setPapp(this); // "Export" PApplet instance (from this class).

        ////////////setup for save funksjon///////////////////////////////
        pg = createGraphics(200, 200);//størrelse på boxa
        pg2 = createGraphics(200, 200); //filterbox
        f = createFont("Arial", 16, true);//gir f en font og størrelse
        // Set the font and fill for text
        textFont(f);
        fill(0);

        //////////////////setup for save funksjon slutt////////////////////
        //////////////////setup for filters/////////////////////////////
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        ///////////////////setup for filters slutt/////////////////
        history  = new ArrayList();
        border = new Border(this, appInit);
    }

    public void draw() {
        // Draw method "never" ends, here we iterate through all
        // the objects we want to display. Whether or not we display
        // them, is decided by the push of the button.
        if (randomize && !killTormod) {
            try {
                if(Tormod.getFilterSelection() == true) {
                    Tormod.selectRandomFilter(Tormod.getRandFilter());
                    Tormod.setFilterSelection(false);
                }
                count++;
                if(count % 400 == 0) { switcher = true; }
                if(count % 500 == 0) {
                    System.out.println("Let's look for colors! :D");
                    String split[] = Tormod.getColorString().split(" ");
                    reds = Integer.parseInt(split[0]);
                    greens = Integer.parseInt(split[1]);
                    blues = Integer.parseInt(split[2]);
                    alphas = Integer.parseInt(split[3]);

                }
                if(count % 810 == 0) { switcher = false; count = 0; }
                if(switcher) { Tormod.oMotion(); }
                if(!switcher) { Tormod.rMotion(); }
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
                for (int i = 0; i < ((Mover)moverInstance).getMovers().size(); i++) {
                    mover = ((Mover)moverInstance).getMovers().get(i);
                    mouse = new PVector(mouseX, mouseY);
                    ((Mover)mover).setVecLocation(mouse);
                    if (circular) {
                        choice = 1;
                    }
                    if (linear) {
                        choice = 2;
                    }
                    ((Mover)mover).update(choice);
                    ((Mover)mover).display();
                }
            }
            if (randomLineButton) {
                ((LineShape)li).drawLines();
            }
            if (varBubblesButton) {
                ((BubbleShape)bu).drawBubbles();
            }
            if (pulseButton) {
                ((PulseShape)pu).drawPulse();
            }
            if (crossDotsButton) {
                ((CrossShape)cr).drawCross();
            }
            if (starzButton) {
                ((StarShape)st).drawStars();
            }
            if (heartButton && mousePressed){
                //st.drawHearts(); denne skal da vekk, fått ny funksjonalitet

                int extra = 3;
                // Randomise the colours during each frame
                stroke(random(0,255), random(0,255), random(0,255));
                strokeWeight((float) 0.2);
                line(mouseX, mouseY, pmouseX, pmouseY);
                line(width-mouseX, mouseY, width-pmouseX, mouseY); // Mirror

                for(int i = 0; i < history.size(); i++){
                    PVector p = (PVector) history.get(i);

                    // Draw a line from the current mouse point to
                    // the historical point if the distance is less
                    // than 50
                    if(dist(mouseX, mouseY, p.x, p.y) < 50){
                        line(mouseX, mouseY, p.x + extra, p.y + extra);
                    }
                    // repeat for the mirror line
                    if(dist(width-mouseX, mouseY, p.x, p.y) < 50){
                        line(width-mouseX, mouseY, p.x + extra, p.y + extra);
                    }
                }

                // Add the current point to the history
                history.add(new PVector(mouseX, mouseY));
                history.add(new PVector(width-mouseX, mouseY));

            }
            if (squarezButton) {
                ((SquareShape)sq).drawSquares();
            }
            if (trianglezButton) {
                ((TriangleShape)tr).drawTriangles();
            }
            if (appInit.getBorderState() == true) {

                border.drawBorder();




            }
            if (appInit.getBorderState() == false) {

                border.state = 0;
            }

            if (saveButton) {


                if (kukk == false) {

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
                    pg.text("Filen blir lagret på skrivebordet", indent, 150);

                    pg.endDraw();


                    image(pg, width - 200, height - 200);

                }

            }
            if (filterButton) {

                if(nr == false){
                    nr = true;
                    nr2 = true;
                }

                pg2.beginDraw();

                d = get(0,0, 200, 200);

                pg2.background(255, 255, 0);
                pg2.stroke(255);
                pg2.fill(0, 0, 0);
                pg2.text("for filters, bruk nr tastene: \n 1: Black and white \n 2: Grayscale \n 3: Opaque \n 4: Invert \n 5: Posterize \n 6: Blur \n 7: Erode \n 8: Dilate \n" ,0,0);

                pg2.endDraw();
                image(pg2, 0, 0);
                filterButton =  false;
                robot.mouseMove(width / 2, height/2);
                leftClick();

            }
        }
    }

    ///////////////////Funksjoner for save funksjon/////////////////////////////////
    //////Kan legge til keyPressed events generelt. If key == 'x', do whatever//////
    public void keyPressed() {
        if(key == '1' && nr2 == true) {
            image(d, 0, 0);
            d.filter(THRESHOLD);
            filter(THRESHOLD);
            image(d, 0, 0);
            filterButton = false;
            nr = false;
            nr2 = false;
        }

        if(key == '2' && nr2 == true) {
            image(d, 0, 0);
            d.filter(GRAY);
            filter(GRAY);
            image(d, 0, 0);
            filterButton = false;
            nr = false;
            nr2 = false;
        }

        if(key == '3' && nr2 == true) {
            image(d, 0, 0);
            d.filter(OPAQUE );
            filter(OPAQUE);
            image(d, 0, 0);
            filterButton = false;
            nr = false;
            nr2 = false;
        }
///////////////////////////////KAN FIXE VERDIEN TIL POSTERIZE TIL Å TA INPUT EVT//////////////////
        if(key == '4' && nr2 == true) {
            image(d, 0, 0);
            d.filter(INVERT);
            filter(INVERT);
            image(d, 0, 0);
            filterButton = false;
            nr = false;
            nr2 = false;
        }

        if(key == '5' && nr2 == true) {
            image(d, 0, 0);
            d.filter(POSTERIZE, 4);
            image(d, 0, 0);
            filter(POSTERIZE, 4);
            //image(d, 0, 0);
            filterButton = false;
            nr = false;
            nr2 = false;
        }

        if(key == '6' && nr2 == true) {
            image(d, 0, 0);
            d.filter(BLUR, 6);
            image(d, 0, 0);
            filter(BLUR, 6);
            //image(d, 0, 0);
            filterButton = false;
            nr = false;
            nr2 = false;
        }

        if(key == '7' && nr2 == true) {
            image(d, 0, 0);
            d.filter(ERODE);
            image(d, 0, 0);
            filter(ERODE);
            //image(d, 0, 0);
            filterButton = false;
            nr = false;
            nr2 = false;
        }

        if(key == '8' && nr2 == true) {
            image(d, 0, 0);
            d.filter(DILATE);
            image(d, 0, 0);
            filter(DILATE);
            //image(d, 0, 0);
            filterButton = false;
            nr = false;
            nr2 = false;
        }



        if (key == 'q') {
            killTormod = true;
            Tormod.end();
            randomize = false;
        }
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
        heartButton = false;
        signatureButton = false;

        if (evt.getActionCommand().equals("create ball")) {
            ballInstance.initializeBalls();
            ballInstance.setPapp(this, appInit); // "Export" PApplet instance (from this class).
            ballbutton = true;

        } else if (evt.getActionCommand().equals("create vector")) {
            vectorButton = true;

        } else if (evt.getActionCommand().equals("randomize")) {
            /*try {
                Tormod.displayInstructions();
                Tormod.reset();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (AWTException e) {
                e.printStackTrace();
            }*/
            Tormod.reset();
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

        }else if (evt.getActionCommand().equals("heartz")) {
            heartButton = true;

        } else if (evt.getActionCommand().equals("squarez")) {
            squarezButton = true;

        } else if (evt.getActionCommand().equals("trianglez")) {
            trianglezButton = true;

        } else if (evt.getActionCommand().equals("strokencolour")) {
            /*strokeNColourButton = true;
            appInit.setStrokeSize(2);
            System.out.println(appInit.getStrokeSize());
            colors.setVisible(true);*/
            strokeNColourButton = true;
            col = colors.showDialog(null, "Choose Stroke Color", Color.GREEN);
            appInit.setRandomclrState(false);
            System.out.println("Color String: " + col.getRed() + " " + col.getGreen() + " " + col.getBlue() + " " + col.getAlpha());

            reds = col.getRed(); greens = col.getGreen(); blues = col.getBlue(); alphas = col.getAlpha();

            for(Brush b : brushes){
                b.setCC(true);
                b.setColor(reds, greens, blues, alphas);
            }
            for(Mover m : ((Mover)moverInstance).getMovers()) {
                m.setCC(true);
                m.setColor(reds, greens, blues, alphas);
            }
        }
        else if (evt.getActionCommand().equals("filter")) {
            filterButton = true;
            pause = false;
        }
        else if (evt.getActionCommand().equals("signature")){
            System.out.println("Knappen fungerer");
        }


        else {
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
            for(Brush b : brushes){
                b.setCC(false);
            }
            for(Mover m : ((Mover)moverInstance).getMovers()) {
                m.setCC(false);
            }
        } else randomclr = false;
    }

    /**
     * Clear the screen.
     */
    public void clear() {
        background(255);
        ((Mover)moverInstance).clearMovers();
        ballInstance.clearBallList();
    }


    private void leftClick() // funksjon for filters
    {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(200);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(200);
    }


}
