import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.KeyEvent;

/**
 * Created by Gøran on 23.03.2015.
 */
public class aRobot {

    aRobot(){}

    public void click(int x, int y) throws AWTException {
        try {
            Robot tormod = new Robot();
            tormod.mouseMove(x, y);

        } catch (AWTException e) {
            e.printStackTrace();
        }


    }
}
