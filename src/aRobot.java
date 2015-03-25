import jdk.internal.util.xml.impl.Input;
import processing.core.PApplet;

import javax.swing.*;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Gøran on 23.03.2015.
 */
public class aRobot extends MyApplet{

    public static Application instance = new Application();
    //Users screen-width and -height.
    private int width;
    private int height;
    //Small pause/delay between mouse-press/-release etc.
    private static final long delay = 500;
    private static final long d = 10;
    //Instantiate a robot.
    public Robot r;
    private boolean keepPainting = true;
    int err = 30;

    aRobot() {
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void clickGUIButton(int x, int y) throws AWTException, InterruptedException {
        //Always add 'err' to make sure it hits the button.
        int bX = Application.panel.getWidth() + x + err;
        int bY = y + err;
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

    public void startPaint() throws InterruptedException {
        r.mouseMove(width / 2, height / 2);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        r.mousePress(InputEvent.BUTTON1_MASK);
        try {
            Thread.sleep(d);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }

    public void rMotion() throws InterruptedException {
        int x = width/2; int y = height/2;
        r.mouseMove(x, y);
        r.mousePress(InputEvent.BUTTON1_MASK);
        Thread.sleep(1000);
        int c; int k = 200;
        for(c = 0; c < k; c++) { x--; r.mouseMove(x, y); c++; fill(0); ellipse(x, y, 10, 10); Thread.sleep(10); }
        k = 200;
        for(c = 0; c < k; c++) { y++; r.mouseMove(x, y); c++; fill(0); ellipse(x, y, 10, 10); Thread.sleep(10); }
        k = 200;
        r.mouseRelease(InputEvent.BUTTON1_MASK);
        resetPosition();
        x = width/2; y = height/2;
        Thread.sleep(1000);
        r.mousePress(InputEvent.BUTTON1_MASK);
        for(c = 0; c < k; c++) { x++; r.mouseMove(x, y); c++; fill(0); ellipse(x, y, 10, 10); Thread.sleep(10); }
        k = 200;
        for(c = 0; c < k; c++) { y--; r.mouseMove(x, y); c++; fill(0); ellipse(x, y, 10, 10); Thread.sleep(10); }
        k = 200;
        r.mouseRelease(InputEvent.BUTTON1_MASK);
        resetPosition();
        x = width/2; y = height/2;
        Thread.sleep(1000);
        r.mousePress(InputEvent.BUTTON1_MASK);
        for(c = 0; c < k; c++) { y--; r.mouseMove(x, y); c++; fill(0); ellipse(x, y, 10, 10); Thread.sleep(10); }
        k = 200;
        for(c = 0; c < k; c++) { x--; r.mouseMove(x, y); c++; fill(0); ellipse(x, y, 10, 10); Thread.sleep(10); }
        k = 200;
        r.mouseRelease(InputEvent.BUTTON1_MASK);
        resetPosition();
        x = width/2; y = height/2;
        Thread.sleep(1000);
        r.mousePress(InputEvent.BUTTON1_MASK);
        for(c = 0; c < k; c++) { y++; r.mouseMove(x, y); c++; fill(0); ellipse(x, y, 10, 10); Thread.sleep(10); }
        k = 200;
        for(c = 0; c < k; c++) { x++; r.mouseMove(x, y); c++; fill(0); ellipse(x, y, 10, 10); Thread.sleep(10); }
        r.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    public void printButtonData(){
        System.out.println("VB POINT: " + Application.getVectorButton().getLocation());
        System.out.println("VB X: " + Application.getVectorButton().getX());
        System.out.println("VB Y: " + Application.getVectorButton().getY());
        System.out.println("CB POINT: " + Application.getClearButton().getLocation());
        System.out.println("BALLZ POINT: " + Application.getButtonCreate().getLocation());
        System.out.println("SIDEBAR HEIGHT: " + Application.buttonPanel.getHeight());
        System.out.println("SIDEBAR WIDTH: " + Application.buttonPanel.getWidth());
    }

    public void setWidth(int w) {
        width = w;
    }

    public void setHeight(int h) {
        height = h;
    }

    /**
     * End the random session.
     */
    public void end(){
        r.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    public void resetPosition() throws InterruptedException {
        r.mouseMove(width/2, height/2);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
