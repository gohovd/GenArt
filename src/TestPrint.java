import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class TestPrint {
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

          
  