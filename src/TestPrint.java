import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 * Printing tool - TestPrint
 * @author Gruppe 6
 * @version 1.0, April 2015
 */

public class TestPrint {
    /***
     * Print function which lets the user print our art
     */
    public void printolini() {
        try {
            Desktop desktop = null;
            if (Desktop.isDesktopSupported()) {
                desktop = Desktop.getDesktop();
            }

            desktop.print(new File(System.getProperty("user.home") + "/Desktop/PRINTMEG.jpg"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
}

          
  