import jdk.internal.util.xml.impl.Input;
import processing.core.PApplet;

import javax.swing.*;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Random;

/**
 * Created by Gøran on 23.03.2015.
 */
public class aRobot {

    public static Application instance = new Application();
    //Users screen-cWidth and -cHeight.
    private int cWidth = instance.panel.getWidth();
    private int cHeight = instance.panel.getHeight();
    //Small pause/delay between mouse-press/-release etc.
    private static final long delay = 500;
    private static final long d = 10;
    //Instantiate a robot.
    public Robot r;
    private boolean keepPainting = true;
    int err = 10;
    //Collect the PApplet from main class (MyApplet).
    PApplet p;
    //Collect all buttons from class Application.
    HashMap buttons = instance.getButtons();
    ArrayList<String> keys;
    Random rand = new Random();
    //Counting the number of times the rMotion method has been visited.
    long motionsMade = 0;
    //Variables holding the positions of the cursor.
    int cX = cWidth / 2;
    int cY = cHeight / 2;
    //Boolean making sure instructions only show up once.
    boolean tutorial;

    aRobot() {
        try {
            r = new Robot();
            r.setAutoDelay(5);
            r.waitForIdle();
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }

    public void displayInstructions() throws InterruptedException, AWTException {
        p.fill(210, 54, 65, 255);
        p.textSize(40);
        p.text("THE ROBOT WILL SOON START TO DRAW", cWidth/2, cHeight/2);
        p.textSize(32);
        p.fill(110, 101, 104, 255);
        p.text("Press 'Q' and/or shout at your computer to end session.", cWidth/2, cHeight/2+50);
        Thread.sleep(3200);
        p.background(255);
    }

    private void clickRandomGUIButton() throws InterruptedException, AWTException {
        keys = new ArrayList();
        for (Object jb : buttons.keySet()) {
            String nameOfButton = jb.toString();
            if(nameOfButton.contains("clearButton") || nameOfButton.contains("Randomize")) { // do nothing..
            } else keys.add(nameOfButton);
        }
        int roulette = rand.nextInt(keys.size());
        clickGUIButton(keys.get(roulette));
    }

    /**
     * "Random" motion. Via this method the robot should utilize
     * a random variety of the tools added, and create art.
     *
     * @throws InterruptedException
     */
    public void rMotion() throws AWTException, InterruptedException {
        if (motionsMade % 600 == 0) {
            Thread.sleep(1000);
            r.delay(1000);
            clickRandomGUIButton();
            r.delay(500);
            r.mousePress(InputEvent.BUTTON1_MASK);
            r.delay(500);
            r.mouseRelease(InputEvent.BUTTON1_MASK);
        }
        r.mouseMove(cX, cY);
        r.mousePress(InputEvent.BUTTON1_MASK);
        cX += 30 - rand.nextInt(60);
        if (cX > cWidth) {
            cX -= cWidth;
        }
        if (cX < 0) {
            cX += cWidth;
        }
        cY += 30 - rand.nextInt(60);
        if (cY > cHeight) {
            cY -= cHeight;
        }
        if (cY < 0) {
            cY += cHeight;
        }
        r.mouseMove(cX, cY);
        tutorial = true;
        motionsMade++;
    }

    public void clickGUIButton(String key) throws AWTException, InterruptedException {
        buttons = instance.getButtons();
        if (!buttons.containsKey(key)) {
            System.out.println("No such button.");
        }
        if (buttons.isEmpty()) {
            System.out.println("There are no buttons to click.");
        } else {
            JButton t;
            t = (JButton) buttons.get(key);
            //Always add 'err' to make sure it hits the button.
            int bX = Application.panel.getWidth() + err + t.getX();
            int bY = err + t.getY();
            r.mouseMove(bX, bY);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r.mousePress(InputEvent.BUTTON1_MASK);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r.mouseRelease(InputEvent.BUTTON1_MASK);
        }
    }

    /**
     * Takes the PApplet in use from main class, and renames it "p"
     * for use in this class. Without it, this class would never be able
     * to draw anything. In other words, this class wouldn't be able to
     * use any processing traits without suffering a NPE.
     *
     * @param input - The PApplet from main class.
     */
    public void setPapp(PApplet input) {
        p = input;
    }

    /**
     * Prints data about the buttons from Application
     */
    public void printButtonData() {
        for (Object jb : buttons.keySet()) {
            JButton t;
            String nameOfButton = jb.toString();
            t = (JButton) buttons.get(nameOfButton);
            int buttonX = t.getX();
            int buttonY = t.getY();
            System.out.println(nameOfButton + " X: " + buttonX + " Y: " + buttonY);
        }
    }

    /**
     * End the random session.
     */
    public void end() {
        r.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    public void reset() {
        tutorial = false;
        motionsMade = 0;
    }


}
