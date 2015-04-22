import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Random;

import processing.core.*;

import javax.swing.*;

/**
 * Class MyApplet extends Processing core functionality, handles drawing functionality
 *
 * @author Gruppe 6
 * @version 1.0, April 2015
 */

public class MyApplet extends PApplet implements ActionListener, ItemListener {
    //An instance of class Application, used to utilize methods/attributes from class Application. Can be sent to other classes as parameter.
    public Application appInit = new Application();

    boolean pause = false;
    Border border;

    //Random generator.
    Random rand = new Random();

    // Variables related to the "bouncing ball".
    Brush brush = new Brush(this);
    Brush pu = new PulseShape(this);
    Brush cr = new CrossShape(this);
    Brush st = new StarShape(this);
    Brush li = new LineShape(this);
    Brush bu = new BubbleShape(this);
    Brush sq = new SquareShape(this);
    Brush tr = new TriangleShape(this);
    Brush sym = new SymShape(this);
    Brush moverInstance = new Mover(this);
    Brush dr = new DrunkShape(this);

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
    boolean printButton = false;
    boolean symButton = false;
    boolean drunkButton = false;

    // Variables related to the mover/vector.
    boolean vectorButton = false;

    // Defining the applets screen size (-300 to make room for toolbar).
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    //Boolean controlling whether the vectors move linearly or circularly.
    //Also controlling whether the user wants random colored vectors.
    boolean circular, linear, randomclr;
    PVector mouse;
    boolean randomLineButton = false;

    //Declare the robot.
    aRobot Tormod;
    boolean killTormod;

    //Button for making random (auto generated art)
    boolean randomize;

    // Color and stroke manager
    JColorChooser colors = new JColorChooser();

    // Booleans to control which button is active
    boolean crossDotsButton = false;
    boolean saveButton = false;
    boolean bgButton = false;
    boolean closeButton = false;
    boolean filterButton = false;
    boolean panelActivated = false; // Settes til true hvis robot har klikket i tegning for å aktivere "panel"

    PImage screenshot;

    PFont f; //variabler for text input

    // Variable to store text currently being typed
    String typing = "";

    // Variable to store saved text when return is hit
    String saved = "";

    int count = 10;

    //variable for background
    int state = 0;

    // Declaring "Tormod", automatic drawing robot
    Robot robot;

    ArrayList history;   // Define the history for pattern3
    int switcher = 3; //Int to control which motion-pattern the robot uses.

    public Color col;
    //The colors we want to pass to all brushes.
    //Either chosen by the robot, or the user.
    int reds, greens, blues, alphas;

    public void setup() {
        //Put all brushes into an array.
        brushes.add(pu);
        brushes.add(st);
        brushes.add(cr);
        brushes.add(li);
        brushes.add(bu);
        brushes.add(sq);
        brushes.add(tr);
        brushes.add(dr);
        brushes.add(sym);
        brushes.add(moverInstance);

        // Set up the movers/vectors.
        moverInstance = new Mover(this);

        size(screenSize.width - 200, screenSize.height);

        // Set up the bouncing balls.
        ballInstance = new Ball();

        background(255);
        Tormod = new aRobot(); // Instantiate the robot.
        Tormod.setPapp(this); // "Export" PApplet instance (from this class).

        f = createFont("Arial", 16, true);//gir f en font og størrelse
        // Set the font and fill for text
        textFont(f);
        fill(0);

        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        history = new ArrayList();
        border = new Border(this, appInit);
    }

    /**
     * Draws actual content to screen
     */
    public void draw() {
        // Draw method "never" ends, here we iterate through all
        // the objects we want to display. Whether or not we display
        // them, is decided by the push of the button.
        if (randomize && !killTormod) {
            try {
                if (Tormod.getFilterSelection() == true) {
                    Tormod.selectRandomFilter(Tormod.getRandFilter());
                    Tormod.setFilterSelection(false);
                }
                count++;
                if (count % 500 == 0) {
                    String split[] = Tormod.getColorString().split(" ");
                    System.out.println(Tormod.getColorString());
                    reds = Integer.parseInt(split[0]);
                    greens = Integer.parseInt(split[1]);
                    blues = Integer.parseInt(split[2]);
                    alphas = Integer.parseInt(split[3]);
                    setColorForAllBrushes();
                }
                int one = rand.nextInt(501) + 200;
                int two = rand.nextInt(911) + 400;
                int three = rand.nextInt(1330) + 60;
                if (count % one == 0) {
                    switcher = 1;
                }
                if (count % two == 0) {
                    switcher = 2;
                }
                if (count % three == 0) {
                    switcher = 3;
                }
                //if(count % 2403 == 0) { switcher = 4; count = 0; }
                if (switcher == 1) {
                    Tormod.Motion(1);
                }
                if (switcher == 2) {
                    Tormod.Motion(2);
                }
                if (switcher == 3) {
                    Tormod.Motion(3);
                }
                //if(switcher == 4) { Tormod.Motion(4); }
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
                if(((Mover) moverInstance).getMovers().size() != 50) {
                    for (int i = 0; i < 50; i++) {
                        //Circle radius is determined through the rTopSpeed. Great value => Great circle!
                        float rTopSpeed = random(150);
                        //The speed at which the circle rotates. Should be between 0.1 and 0.4.
                        float TorqueIncrement = (float) 0.19;
                        ((Mover) moverInstance).createNewMover(rTopSpeed, TorqueIncrement);
                    }
                }
                //Choice determines motion pattern (1: circular,  2: linear)
                int choice = 1;
                for (int i = 0; i < ((Mover) moverInstance).getMovers().size(); i++) {
                    Brush mover = ((Mover) moverInstance).getMovers().get(i);
                    mouse = new PVector(mouseX, mouseY);
                    ((Mover) mover).setVecLocation(mouse);
                    if (circular) {
                        choice = 1;
                    }
                    if (linear) {
                        choice = 2;
                    }
                    ((Mover) mover).update(choice);
                    ((Mover) mover).display();
                }
            }
            if (randomLineButton) {
                ((LineShape) li).drawLines();
            }
            if (symButton) {
                if (mousePressed) {
                    ((SymShape) sym).display();
                }
            }
            if (varBubblesButton) {
                ((BubbleShape) bu).drawBubbles();
            }
            if (drunkButton) {
                if (mousePressed) {
                    ((DrunkShape) dr).setX1(mouseX);
                    ((DrunkShape) dr).setY1(mouseY);
                }
                ((DrunkShape) dr).display();
            }
            if (pulseButton) {
                ((PulseShape) pu).drawPulse();
            }
            if (crossDotsButton) {
                ((CrossShape) cr).drawCross();
            }
            if (starzButton) {
                ((StarShape) st).drawStars();

            }
            if (printButton) {
                saveToPrint();
                TestPrint tp = new TestPrint();
                tp.printolini();
                printButton = false;
            }
            if (heartButton && mousePressed) {
                //st.drawHearts(); denne skal da vekk, fått ny funksjonalitet

                int extra = 3;
                // Randomise the colours during each frame
                stroke(random(0, 255), random(0, 255), random(0, 255));
                strokeWeight((float) 0.2);
                line(mouseX, mouseY, pmouseX, pmouseY);
                line(width - mouseX, mouseY, width - pmouseX, mouseY); // Mirror

                for (int i = 0; i < history.size(); i++) {
                    PVector p = (PVector) history.get(i);

                    // Draw a line from the current mouse point to
                    // the historical point if the distance is less
                    // than 50
                    if (dist(mouseX, mouseY, p.x, p.y) < 50) {
                        line(mouseX, mouseY, p.x + extra, p.y + extra);
                    }
                    // repeat for the mirror line
                    if (dist(width - mouseX, mouseY, p.x, p.y) < 50) {
                        line(width - mouseX, mouseY, p.x + extra, p.y + extra);
                    }
                }

                // Add the current point to the history
                history.add(new PVector(mouseX, mouseY));
                history.add(new PVector(width - mouseX, mouseY));

            }
            if (squarezButton) {
                ((SquareShape) sq).drawSquares();
            }
            if (trianglezButton) {
                ((TriangleShape) tr).drawTriangles();
            }
            if (appInit.getBorderState() == true) {
                border.drawBorder();

            }
            if (appInit.getBorderState() == false) {
                border.state = 0;
            }

            if (saveButton) {

                strokeWeight(2);
                stroke(102, 102, 255);
                fill(245, 245, 245);
                rect((width / 2) - 150, (height / 2) - 100, 300, 300, 3);
                fill(0, 0, 0);
                textAlign(CENTER);

                PFont signatureFont;
                signatureFont = loadFont("fonts/Purisa-Oblique-16.vlw");
                textFont(signatureFont);

                text("Skriv inn ønsket filnavn \n" +
                        "F.eks. 'minfil.jpg'", width / 2, height / 2 - 50);


                text(typing, width / 2, height / 2 + 60);

                text("Filen blir lagret på skrivebordet", width / 2, height / 2 + 150);
                text("Trykk 'ESC' for å avbryte", width / 2, height / 2 + 175);

                if (panelActivated) {
                    // Do nothing
                } else {
                    robot.mouseMove(width / 2, height / 2);
                    leftClick();
                    panelActivated = true;
                }

            }

            if (bgButton) {
                ColorPicker c1 = new ColorPicker();

                if (state == 0) {

                    c1.color();
                    if (c1.getC() != null) {

                        background(c1.getR(), c1.getG(), c1.getB());
                    }

                }
                state = 1;

            }
            if (closeButton) {
                System.exit(0);
            }
            if (filterButton) {


                strokeWeight(2);
                stroke(102, 102, 255);
                fill(245, 245, 245);
                rect((width / 2) - 150, (height / 2) - 100, 300, 300, 3);
                fill(0, 0, 0);
                textAlign(CENTER);

                PFont signatureFont;
                signatureFont = loadFont("fonts/Purisa-Oblique-16.vlw");
                textFont(signatureFont);

                text("for filters, bruk nr tastene: \n" +
                        " 1: Black and white \n" +
                        " 2: Grayscale \n" +
                        " 3: Opaque \n" +
                        " 4: Invert \n" +
                        " 5: Posterize \n" +
                        " 6: Blur \n" +
                        " 7: Erode \n" +
                        " 8: Dilate \n", width / 2, height / 2 - 60);

                text("Trykk 'ESC' for å avbryte", width / 2, height / 2 +175);

                if (panelActivated) {
                    // Do nothing
                } else {
                    robot.mouseMove(width / 2, height / 2);
                    leftClick();
                    panelActivated = true;
                }


            }
            if (signatureButton) {

                strokeWeight(2);
                stroke(102, 102, 255);
                fill(245, 245, 245);
                rect((width / 2) - 150, (height / 2) - 100, 300, 200, 3);
                fill(0, 0, 0);
                textAlign(CENTER);

                PFont signatureFont;
                signatureFont = loadFont("fonts/Purisa-Oblique-16.vlw");
                textFont(signatureFont);

                text("Signatur og trykk 'enter'", width / 2, height / 2 - 60);
                text("Trykk 'ESC' for å avbryte", width / 2, height / 2 + 70);


                text(typing, width / 2, height / 2 + 20);

                if (panelActivated) {
                    // Do nothing
                } else {
                    robot.mouseMove(width - 250, height / 2);
                    leftClick();
                    panelActivated = true;
                }
            }
        }
    }

    /**
     * Behaviour for key pressing and mouse clicking
     */
    public void keyPressed() {

        if (signatureButton) {

            // If the return key is pressed, save the String and clear it
            if (key == '\n') {
                saved = typing;

                // A String can be cleared by setting it equal to ""
                typing = "";


                image(screenshot, 0, 0);

                PFont signatureFont;
                signatureFont = loadFont("fonts/Purisa-Bold-30.vlw");
                fill(0, 0, 0);
                textFont(signatureFont);

                textAlign(RIGHT);
                text(saved, width - 18, height - 28);

                PFont signatureFont2;
                signatureFont2 = loadFont("fonts/Purisa-Bold-30.vlw");
                fill(255, 255, 255);
                textFont(signatureFont2);

                textAlign(RIGHT);
                text(saved, width - 20, height - 30);

                signatureButton = false;

            } else if
                    (key == ESC)  {
                key=0;
                image(screenshot, 0, 0);
                signatureButton = false;
            } else {
                // Otherwise, concatenate the String
                // Each character typed by the user is added to the end of the String variable.
                typing = typing + key;

                if (key == '\u0008') {
                    typing = "";
                }
            }


        } else if (filterButton) {

            if (key == '1') {
                screenshot.filter(THRESHOLD);
                image(screenshot, 0, 0);
                filterButton = false;

            }

            if (key == '2') {
                screenshot.filter(GRAY);
                image(screenshot, 0, 0);
                filterButton = false;
            }

            if (key == '3') {
                screenshot.filter(OPAQUE);
                image(screenshot, 0, 0);
                filterButton = false;
            }

            if (key == '4') {
                screenshot.filter(INVERT);
                image(screenshot, 0, 0);
                filterButton = false;
            }

            if (key == '5') {
                screenshot.filter(POSTERIZE, 5);
                image(screenshot, 0, 0);
                filterButton = false;
            }

            if (key == '6') {
                screenshot.filter(BLUR);
                image(screenshot, 0, 0);
                filterButton = false;
            }

            if (key == '7') {
                screenshot.filter(ERODE);
                image(screenshot, 0, 0);
                filterButton = false;
            }

            if (key == '8') {
                screenshot.filter(DILATE);
                image(screenshot, 0, 0);
                filterButton = false;
            }
            if
                    (key == ESC)  {
                key=0;
                image(screenshot, 0, 0);
                filterButton = false;
            }


        } else if (saveButton) {

            // If the return key is pressed, save the String and clear it

            if (key == '\n') {

                saved = typing;
                // A String can be cleared by setting it equal to ""
                typing = "";
                if (saved.contains("jpg") || saved.contains("JPG") || saved.contains("Jpg") || saved.contains("jpeg") || saved.contains("JPEG") || saved.contains("Jpeg")) {
                    int dotPos = saved.lastIndexOf(".");
                    if (dotPos > 0)
                        saved = saved.substring(0, dotPos);

                    String desktopPath = System.getProperty("user.home") + "/Desktop/";
                    screenshot.save(desktopPath + saved + ".jpg");

                    image(screenshot, 0, 0);

                    saveButton = false;

                }
                if (saved.contains("PNG") || saved.contains("Png") || saved.contains("png")) {
                    int dotPos = saved.lastIndexOf(".");
                    if (dotPos > 0)
                        saved = saved.substring(0, dotPos);

                    String desktopPath = System.getProperty("user.home") + "/Desktop/";
                    screenshot.save(desktopPath + saved + ".png");

                    image(screenshot, 0, 0);

                    saveButton = false;
                }
            } else if
                    (key == ESC)  {
                key=0;
                image(screenshot, 0, 0);
                saveButton = false;
            } else {

                // Otherwise, concatenate the String
                // Each character typed by the user is added to the end of the String variable.
                typing = typing + key;

                if (key == '\u0008') {
                    typing = "";
                }
            }
        } else if (randomize) {

            if   (key == ESC)  {
                key=0;
                killTormod = true;
                Tormod.end();
                Tormod.reset();
                randomize = false;
            }
        }

    }


    /**
     * implementation from interface ActionListener
     * method is called from the Application
     * the String being compared is the ActionCommand from the button
     */
    public void actionPerformed(ActionEvent evt) {

        if (!evt.getActionCommand().equals("strokencolour")) {
            vectorButton = false;
            randomLineButton = false;
            ballbutton = false;
            varBubblesButton = false;
            pulseButton = false;
            crossDotsButton = false;
            saveButton = false;
            bgButton = false;
            closeButton = false;
            starzButton = false;
            squarezButton = false;
            trianglezButton = false;
            heartButton = false;
            signatureButton = false;
            symButton = false;
            drunkButton = false;
        }

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
            screenshot = get();
            panelActivated = false;
            saveButton = true;

        } else if (evt.getActionCommand().equals("bg")) {
            if (state != 0) {
                state = 0;
            }
            bgButton = true;

        } else if (evt.getActionCommand().equals("close")) {
            closeButton = true;

        } else if (evt.getActionCommand().equals("starz")) {
            starzButton = true;
        } else if (evt.getActionCommand().equals("printing")) {
            printButton = true;

        } else if (evt.getActionCommand().equals("heartz")) {
            heartButton = true;

        } else if (evt.getActionCommand().equals("drunk")) {
            drunkButton = true;

        } else if (evt.getActionCommand().equals("squarez")) {
            squarezButton = true;

        } else if (evt.getActionCommand().equals("trianglez")) {
            trianglezButton = true;

        } else if (evt.getActionCommand().equals("syms")) {
            symButton = true;

        } else if (evt.getActionCommand().equals("strokencolour")) {
            strokeNColourButton = true;
            col = colors.showDialog(null, "Velg En Farge", Color.GREEN);
            appInit.setRandomclrState(false);
            reds = col.getRed();
            greens = col.getGreen();
            blues = col.getBlue();
            alphas = col.getAlpha();
            setColorForAllBrushes();
        } else if (evt.getActionCommand().equals("filter")) {
            screenshot = get();
            filterButton = true;
            panelActivated = false;
            pause = false;
        } else if (evt.getActionCommand().equals("signature")) {
            screenshot = get();
            panelActivated = false;

            // Variable to store text currently being typed
            String typing = "";

            // Variable to store saved text when return is hit
            String saved = "";

            signatureButton = true;


        } else {
            println("actionPerformed(): can't handle " + evt.getActionCommand());
        }
        pause = false;
    }

    public void itemStateChanged(ItemEvent e) {
        if (appInit.getRandomColorState() == true) {
            randomclr = true;
            for (Brush b : brushes) {
                b.setCC(false);
            }
            for (Mover m : ((Mover) moverInstance).getMovers()) {
                m.setCC(false);
            }
        } else randomclr = false;
    }

    /**
     * Clear the screen.
     */
    public void clear() {
        background(255);
        ((Mover) moverInstance).clearMovers();
        ballInstance.clearBallList();
    }

    /**
     * Function to help set focus on drawing area after selecting filter
     */
    private void leftClick() {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(200);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(200);
    }

    /**
     * Setting colours for brushes
     */
    private void setColorForAllBrushes() {
        for (Brush b : brushes) {
            b.setCC(true);
            b.setColor(reds, greens, blues, alphas);
        }
        for (Mover m : ((Mover) moverInstance).getMovers()) {
            m.setCC(true);
            m.setColor(reds, greens, blues, alphas);
        }
    }

    /**
     * Help function to be able to print to paper
     */
    public void saveToPrint() {
        save(System.getProperty("user.home") + "/Desktop/PRINTMEG.jpg");

    }

}
