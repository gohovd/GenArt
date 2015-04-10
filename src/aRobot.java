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
    //Users screen-width and -height.
    private int cWidth = instance.panel.getWidth();
    private int cHeight = instance.panel.getHeight();
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
    //Store all KeyEvents in a map.
    HashMap<Integer, Integer> filterMap = new HashMap();
    ArrayList<String> keys;
    Random rand = new Random();
    //Counting the number of times the rMotion method has been visited.
    long motionsMade = 0;
    //Variables holding the positions of the cursor.
    int cX = cWidth / 2;
    int cY = cHeight / 2;
    //Boolean making sure instructions only show up once.
    boolean tutorial;
    int avoidFirst = 1;
    int randFilter;
    boolean filterSelection = false;

    aRobot() {
        try {
            r = new Robot();
            //r.setAutoDelay(5);
            r.waitForIdle();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        mapKeyEvents();
    }

    private void mapKeyEvents() {
        filterMap.put(1, KeyEvent.VK_1);
        filterMap.put(2, KeyEvent.VK_2);
        filterMap.put(3, KeyEvent.VK_3);
        filterMap.put(4, KeyEvent.VK_4);
        filterMap.put(5, KeyEvent.VK_5);
        filterMap.put(6, KeyEvent.VK_6);
        filterMap.put(7, KeyEvent.VK_7);
        filterMap.put(8, KeyEvent.VK_8);
    }

    private void clickRandomGUIButton() throws InterruptedException, AWTException {
        keys = new ArrayList();
        for (Object jb : buttons.keySet()) {
            String nameOfButton = jb.toString();
            // here we add the buttons that we want the robot to ignore..
            if (nameOfButton.contains("clearButton") ||
                    nameOfButton.contains("Randomize") ||
                    nameOfButton.contains("saveButton") ||
                    nameOfButton.contains("strokeNColourButton") ||
                    nameOfButton.contains("filterButton")) { // do nothing..
            } else keys.add(nameOfButton);
        }
        int roulette = rand.nextInt(keys.size());
        clickGUIButton(keys.get(roulette));
    }

    private void selectFilter() throws InterruptedException, AWTException {
        randFilter = rand.nextInt(8) + 1; //Number of filters (8).
        Thread.sleep(1000);
        r.delay(1000);
        clickGUIButton("filterButton");
        r.delay(500);
        r.mousePress(InputEvent.BUTTON1_MASK);
        r.delay(500);
        r.mouseRelease(InputEvent.BUTTON1_MASK);
        r.delay(1000);
        filterSelection = true;
    }

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
        if (motionsMade % 600 == 0) {
            if(avoidFirst == 0) { selectFilter(); avoidFirst += 1;}
            Thread.sleep(1500);
            r.delay(1000);
            clickRandomGUIButton();
            r.delay(500);
            r.mousePress(InputEvent.BUTTON1_MASK);
            r.delay(500);
            r.mouseRelease(InputEvent.BUTTON1_MASK);
            avoidFirst -= 1;
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

    public void displayInstructions() throws InterruptedException, AWTException {
        p.background(255);
        p.fill(210, 54, 65, 255);
        p.textSize(32);
        p.text("Robot will soon draw...", cWidth / 2 - 300, cHeight / 2);
        p.textSize(28);
        p.fill(110, 101, 104, 255);
        p.text("Press 'Q' to end session.", cWidth / 2 - 300, cHeight / 2 + 50);
        Thread.sleep(3200);
        p.background(255);
    }

    public int getRandFilter(){
        return randFilter;
    }

    public boolean getFilterSelection(){
        return filterSelection;
    }

    public void setFilterSelection(boolean b){
        filterSelection = b;
    }
}
