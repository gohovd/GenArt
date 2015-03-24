import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Gøran on 23.03.2015.
 */
public class aRobot {

    public static Application instance = new Application();
    private int width;
    private int height;
    private static final long delay = 1000;
    private Robot r;
    private boolean keepPainting = true;

    aRobot() {
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void clickVectorButton() throws AWTException {
        int x = instance.getVectorButton().getX();
        int y = instance.getVectorButton().getY();
        r.mouseMove(x + 5, y + 5);
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

    public void startPaint() {
        r.mouseMove(width / 2, height / 2);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        r.mousePress(InputEvent.BUTTON1_MASK);
    }

    public void printButtonData(){
        System.out.println("VB POINT: " + instance.getVectorButton().getLocation());
        System.out.println("VB X: " + instance.getVectorButton().getX());
        System.out.println("VB Y: " + instance.getVectorButton().getY());
        System.out.println("CB POINT: " + instance.getClearButton().getLocation());
    }

    public void setWidth(int w) {
        width = w;
    }

    public void setHeight(int h) {
        height = h;
    }

    public void setBool(boolean b){
        keepPainting = b;
    }
}
