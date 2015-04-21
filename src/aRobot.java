import jdk.internal.util.xml.impl.Input;
import processing.core.PApplet;

import javax.swing.*;
import java.awt.*;
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
    //Robots very own instance of class Application
    public static Application instance = new Application();
    //Users screen-width and -height.
    private int cWidth = Application.panel.getWidth();
    private int cHeight = Application.panel.getHeight();
    //Small pause/delay between mouse-press/-release etc.
    private static final long delay = 200;
    private static final long d = 10;
    //Instantiate a robot.
    public Robot r;
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
    boolean tutorial; // Deactivated in class MyApplet (commented out).
    int avoidFirst = 1;
    int randFilter;
    boolean filterSelection = false;
    String previousButton = "whatever"; //It'll change soon anyways, so.. whatever.
    String currentButton;
    //About the colors..
    boolean colorNotFound;
    int red, green, blue, alpha;

    float radius = 25;

    /**
     * Constructor for class Robot.
     */
    aRobot() {
        try {
            r = new Robot();
            //r.setAutoDelay(5);
            r.waitForIdle();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /***
     * Selects a random button from HashMap buttons.
     * Takes the keyset from HashMap buttons, and adds
     * the name (String: keys) of the desired buttons
     * to an array (datatype: string).
     * It then goes on to generate a random int (highest value
     * is the size of the array), and passes that value to
     * clickGUIButton.
     *
     * @throws InterruptedException
     * @throws AWTException
     */
    private void clickRandomGUIButton() throws InterruptedException, AWTException {
        keys = new ArrayList();
        for (Object jb : buttons.keySet()) {
            String nameOfButton = jb.toString();
            // here we add the buttons that we want the robot to ignore..
            if (nameOfButton.contains("clearButton") ||
                    nameOfButton.contains("Randomize") ||
                    nameOfButton.contains("saveButton") ||
                    nameOfButton.contains("strokeNColourButton") ||
                    nameOfButton.contains("filterButton") ||
                    nameOfButton.contains("clrButton") ||
                    nameOfButton.contains("signatureButton") ||
                    nameOfButton.contains("buttonCreate") ||
                    nameOfButton.contains("closeButton")) { // do nothing..
            } else keys.add(nameOfButton);
        }
        //give it whatever value, it'll change def. anyways.
        int roulette = 0;
        boolean theSame = true;
        while(theSame) {
            roulette = rand.nextInt(keys.size());
            currentButton = keys.get(roulette);
            if (!previousButton.contains(currentButton)) {
                theSame = false;
            }
        }
        clickGUIButton(keys.get(roulette));
    }

    private void getColor() throws InterruptedException, AWTException {
        Color c = null;
        colorNotFound = true;
        while(colorNotFound) {
            c = r.getPixelColor(rand.nextInt(Application.panel.getWidth()), rand.nextInt(Application.panel.getHeight()));
            String cString =  "" + c.getRed() + c.getGreen() + c.getBlue() + c.getAlpha();
            if(!cString.equals("255255255255") ||
                    !cString.equals("0000") ||
                    !cString.equals("000255") ||
                    !cString.equals("2552552550")) {
                colorNotFound = false;
            }
        }
        red = c.getRed(); green = c.getGreen(); blue = c.getBlue(); alpha = c.getAlpha();
    }

    public String getColorString() throws AWTException, InterruptedException {
        getColor();
        return red + " " + green + " " + blue + " " + alpha;
    }


    /***
     * Generates a random int with the highest value being the number of
     * available filters (+1 as highest is exclusive).
     * Tells the clickGUIButton method to click the "filterButton",
     * and then goes on to set the boolean filterSelection true -
     * which prevents any other operations to interfere with the robot.
     *
     * @throws InterruptedException
     * @throws AWTException
     */
    private void selectFilter() throws InterruptedException, AWTException {
        randFilter = rand.nextInt(8) + 1; //Number of filters (8).
        r.delay(250);
        clickGUIButton("filterButton");
        r.delay(250);
        r.mousePress(InputEvent.BUTTON1_MASK);
        r.delay(250);
        r.mouseRelease(InputEvent.BUTTON1_MASK);
        r.delay(250);
        filterSelection = true;
    }

    /***
     * Selects any out of the eight available filters.
     * The number "i" (number between 1-8) represents
     * the numbers on your keyboard, as the robot must press (keypress)
     * one of these buttons to invoke a filter.
     *
     * @param i
     */
    public void selectRandomFilter(int i){
        if(i == 1){ r.keyPress(KeyEvent.VK_1); r.delay(500); r.keyRelease(KeyEvent.VK_1);}
        if(i == 2){ r.keyPress(KeyEvent.VK_2); r.delay(500); r.keyRelease(KeyEvent.VK_1);}
        if(i == 3){ r.keyPress(KeyEvent.VK_3); r.delay(500); r.keyRelease(KeyEvent.VK_1);}
        if(i == 4){ r.keyPress(KeyEvent.VK_4); r.delay(500); r.keyRelease(KeyEvent.VK_1);}
        if(i == 5){ r.keyPress(KeyEvent.VK_5); r.delay(500); r.keyRelease(KeyEvent.VK_1);}
        if(i == 6){ r.keyPress(KeyEvent.VK_6); r.delay(500); r.keyRelease(KeyEvent.VK_1);}
        if(i == 7){ r.keyPress(KeyEvent.VK_7); r.delay(500); r.keyRelease(KeyEvent.VK_1);}
        if(i == 8){ r.keyPress(KeyEvent.VK_8); r.delay(500); r.keyRelease(KeyEvent.VK_1);}
    }

    /**
     * "Random" motion. Via this method the robot should utilize
     * a random variety of the tools added, and create art.
     *
     * @throws InterruptedException
     */
    public void rMotion() throws AWTException, InterruptedException {
        if (motionsMade % 800 == 0) {
            if(avoidFirst == 0) { selectFilter(); avoidFirst += 1; return; }
            Thread.sleep(1500);
            r.delay(1000);
            clickRandomGUIButton();
            r.delay(500);
            r.mousePress(InputEvent.BUTTON1_MASK);
            r.delay(500);
            r.mouseRelease(InputEvent.BUTTON1_MASK);
            avoidFirst -= 1;
            motionsMade = 0;
        }
            r.mouseMove(cX, cY);
            r.mousePress(InputEvent.BUTTON1_MASK);
            cX += 35 - rand.nextInt(70);
            if (cX > cWidth) {
                cX -= cWidth;
            }
            if (cX < 0) {
                cX += cWidth;
            }
            cY += 35 - rand.nextInt(70);
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

    public void oMotion() throws AWTException, InterruptedException {
        if (motionsMade % 200 == 0) {
            if(avoidFirst == 0) { selectFilter(); avoidFirst += 1; return; }
            r.delay(250);
            clickRandomGUIButton();
            r.delay(250);
            r.mousePress(InputEvent.BUTTON1_MASK);
            r.delay(250);
            r.mouseRelease(InputEvent.BUTTON1_MASK);
            avoidFirst -= 1;
        }
        //float radius = p.random((float)1);
        int updown = rand.nextInt(1);
        if(updown == 1) { radius += 0.4; }
        if(updown == 0) { radius -= 0.4; }
        r.mouseMove(cX, cY);
        r.mousePress(InputEvent.BUTTON1_MASK);
        cX += PApplet.cos(radius) * 60;
        if (cX > cWidth) {
            cX -= cWidth;
        }
        if (cX < 0) {
            cX += cWidth;
        }
        cY += PApplet.sin(radius) * 60;
        if (cY > cHeight) {
            cY -= cHeight;
        }
        if (cY < 0) {
            cY += cHeight;
        }
        r.mouseMove(cX, cY);
        tutorial = true;
        motionsMade++;
        radius += 0.1;
    }




    /***
     * Iterates through the keyset of HashMap buttons,
     * looks for the parameter (String key), and sees if
     * it can find a button with a name equal to that of the
     * parameter. If it does, it collects data about the button's
     * location on the screen - and makes the robot move the mousecursor
     * to that specific location. And finally, makes the robot press the button.
     *
     * @param key
     * @throws AWTException
     * @throws InterruptedException
     */
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
            if(!key.contains("filterButton")) { previousButton = key; }
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

    /***
     * This method clears the screen, and writes
     * instructions on how to stop the robot after starting.
     *
     */
    /*public void displayInstructions() throws InterruptedException, AWTException {
        p.background(255);
        p.fill(210, 54, 65, 255);
        p.textSize(32);
        p.text("Robot will soon draw...", cWidth / 2 - 300, cHeight / 2);
        p.textSize(28);
        p.fill(110, 101, 104, 255);
        p.text("Press 'Q' to end session.", cWidth / 2 - 300, cHeight / 2 + 50);
        Thread.sleep(3200);
        p.background(255);
    }*/

    /***
     * Returns the number of the filter that will be chosen.
     * @return int - Number representing the filter to be chosen (1-8)
     */
    public int getRandFilter(){
        return randFilter;
    }

    /***
     * Returns whether or not the robot is in the process
     * of selecting a filter.
     *
     * @return boolean - State of filter selection.
     */
    public boolean getFilterSelection(){
        return filterSelection;
    }

    /***
     * Tells the robot to enter filter selection,
     * or exit it.
     * @param b - Boolean.
     */
    public void setFilterSelection(boolean b){
        filterSelection = b;
    }

}
