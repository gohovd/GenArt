import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Gøran on 23.03.2015.
 */
public class aRobot {

    aRobot(){
    }

    public void click(int x, int y) throws AWTException {
        try {
            Robot r = new Robot();
            r.mouseMove(x+5, y+5);
            r.mousePress(InputEvent.BUTTON1_MASK);
            r.mouseRelease(InputEvent.BUTTON1_MASK);

        } catch (AWTException e) {
            e.printStackTrace();
        }


    }
}
