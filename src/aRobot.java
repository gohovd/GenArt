import processing.core.PApplet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * The class aRobot holds all the various movement patterns we wish our
 * drawing robot to use. It can click buttons in the GUI and choose filters.
 * Most of its behavior is meant to be random.
 */
public class aRobot {
    //Robots very own instance of class Application
    public static Application instance = new Application();
    //Users screen-width and -height.
    private int cWidth = Application.panel.getWidth();
    private int cHeight = Application.panel.getHeight();
    //Small pause/delay between mouse-press/-release etc.
    private static final long delay = 200;
    //Instantiate a robot.
    public Robot r;
    //Error margin. Makes sure the robot hits the button, and not the edge/origin point.
    int err = 10;
    //Collect the PApplet from main class (MyApplet).
    PApplet p;
    //Collect all buttons from class Application.
    HashMap buttons = instance.getButtons();
    //ArrayList holding all the keys from HashMap buttons.
    ArrayList<String> keys;
    //A random generator.
    Random rand = new Random();
    //Counting the number of times the rMotion method has been visited.
    long motionsMade = 0;
    //Variables holding the positions of the cursor.
    int cX = cWidth / 2;
    int cY = cHeight / 2;
    //Boolean making sure instructions only show up once.
    boolean tutorial; // Deactivated in class MyApplet (commented out).
    //This int is to control that the robot doesn't pick a filter before we've drawn something.
    int avoidFirst = 1;
    //Int holding the index of the filter to be chosen (1-8).
    int randFilter;
    //Boolean controlling whether or not we are in the process of choosing a filter.
    boolean filterSelection = false;
    //String holding the name (key, in HashMap buttons..) of the GUI button we previously clicked.
    String previousButton = "whatever"; //It'll change soon anyways, so we call it whatever.
    //String holding the name (key, in HashMap buttons.) of the GUI button we just clicked.
    String currentButton;
    //Boolean that tells us if we've found a color that's not black or white yet.
    boolean colorNotFound;
    //Integers holding various values for red, green, blue and alpha (opacity).
    int red, green, blue, alpha;
    //Variables connected to the sinMotion.
    float a = 0;
    float radius = 5;

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
                    nameOfButton.contains("closeButton") ||
                    nameOfButton.contains("printButton") ||
                    nameOfButton.contains("drunkLinesButton") ||
                    nameOfButton.contains("bgButton")) { // do nothing..
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

    /***
     * The robot analyzes random pixels on the canvas (Application panel),
     * and if the pixel is colored with anything other than black or white
     * the color is extracted and sent to MyApplet, which in turn sends the color
     * to all instantiated objects of class Brush - thus changing the color.
     *
     * @throws InterruptedException
     * @throws AWTException
     */
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

    /***
     * Returns the color selected by the robot as a string.
     *
     * @return String - R (0-255), G (0-255), B (0-255), O (0-255).
     * @throws AWTException
     * @throws InterruptedException
     */
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
        r.delay(500);
        clickGUIButton("filterButton");
        r.delay(500);
        r.mousePress(InputEvent.BUTTON1_MASK);
        r.delay(500);
        r.mouseRelease(InputEvent.BUTTON1_MASK);
        r.delay(500);
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
    public void Motion(int select) throws AWTException, InterruptedException {
        if (motionsMade % 1200 == 0) {
            if(avoidFirst == 0) { selectFilter(); avoidFirst += 1; return; }
            r.delay(25);
            clickRandomGUIButton();
            r.delay(25);
            r.mousePress(InputEvent.BUTTON1_MASK);
            r.delay(25);
            r.mouseRelease(InputEvent.BUTTON1_MASK);
            avoidFirst -= 1;
            motionsMade = 0;
        }
        r.mouseMove(cX, cY);
        r.mousePress(InputEvent.BUTTON1_MASK);
        if(select == 1) {
            cX += 35 - rand.nextInt(70);
            cY += 35 - rand.nextInt(70);
        }
        else if(select == 2){
            int updown = rand.nextInt(2);
            if(updown == 1) { radius += 0.6; }
            if(updown == 0) { radius -= 0.6; }
            cX += p.cos(radius) * 60 / 2;
            cY += p.sin(radius) * 60 / 2;
            radius += 0.5;
        }
        else if(select == 3) {
            float x = p.map(p.sin(a)*p.sin(a * (float) 0.8), -1, 1, 0, Application.panel.getWidth());
            float y = p.map(p.sin(a + (float) 1.5), -1, 1, 0, Application.panel.getHeight());
            cX = Math.round(x*2);
            cY = Math.round(y*2);
            a = a + (float)0.035;
        }
        else {
            float x = p.map(p.sin(a)*p.sin(a*(float)0.8), -1, 1, 0, Application.panel.getWidth());
            float y = p.map(p.sin(a+(float)1.5), -1, 1, 0, Application.panel.getHeight());
            cX = Math.round(x);
            cY = Math.round(y);
            a = a + rand.nextFloat();
        }
        if(!currentButton.contains("symButton")) {
            if (cX > cWidth) {
                cX -= cWidth;
            }
            if (cX < 0) {
                cX += cWidth;
            }
            if (cY > cHeight) {
                cY -= cHeight;
            }
            if (cY < 0) {
                cY += cHeight;
            }
        }
            r.mouseMove(cX, cY);
            motionsMade++;
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
     * Forces the robot to release the mouse.
     */
    public void end() {
        r.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    /***
     * Sets the tutorial boolean back to false, to that the next time
     * the randomize button is clicked - the tutorial appears again.
     * Motionsmade is set back to zero as though no steps were made in the past.
     */
    public void reset() {
        tutorial = false;
        motionsMade = 0;
        filterSelection = false;
        avoidFirst = 1;
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
