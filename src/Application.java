import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;

/**
 * A simple demo application launching a Processing Applet
 * <p/>
 * Demonstrates the combination of JFrame, JButton, JFileChooser
 * and PApplet.
 *
 * @author georg munkel
 */
public class Application {
    private int width;
    private int height;

    public static void main(String[] args) {
//create a frame for the application
        final JFrame frame = new JFrame("PApplet in Java Application");
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
// frame.setUndecorated(true); // Aktiver for å fjerne tittel etc, "skikkelig" fullskjerm
//make sure to shut down the application, when the frame is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//create a panel for the applet and the button panel
        JPanel panel = new JPanel();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        panel.setSize(screenSize.width, screenSize.height);
//create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
//create an instance of your processing applet
        final MyApplet applet = new MyApplet();
//start the applet
        applet.init();
//Buttons
//create a button labled "create new ball"
        JButton buttonCreate = new JButton("create new ball");
//create a button labeled "create new vector"
        JButton vectorButton = new JButton("create new vector");
//assing a tooltip
        buttonCreate.setToolTipText("creates a new ball ");
//assign tooltip to button
        vectorButton.setToolTipText("creates a new vector");
//give a name for the command
//if this is not assigned the actionCommand equals the button label
        buttonCreate.setActionCommand("create ball");
        vectorButton.setActionCommand("create vector");
//create a button lable "load file"
        JButton buttonLoad = new JButton("load file");
        buttonLoad.setToolTipText("loads a new background image");
//button actions
//the create button is simply linked to the applet
//the action is executed inside applet.actionPerformed()
        buttonCreate.addActionListener(applet);
        vectorButton.addActionListener(applet);
//this action is implemented NOT in the PApplet on purpose
//fileDialogues like to crash a PApplet
//
//if the JFileChooser returns a valid file
//loadBgImage() in MyApplet is executed
        buttonLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser chooser = new JFileChooser();
//example of an image fileFilter
//no need to use, just switch it off
                chooser.setFileFilter(new MyImageFileFilter());
                int returnVal = chooser.showOpenDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this file: " +
                            chooser.getSelectedFile().getName());
//sending the selectedFile to loadBgImage() in the PApplet
                    applet.loadBgImage(chooser.getSelectedFile());
                }
            }
        });
//store the two buttons in the button panel
        buttonPanel.add(buttonCreate);
        buttonPanel.add(buttonLoad);
        buttonPanel.add(vectorButton);
//store the applet in panel
        panel.add(applet);
//store the buttonPanel in panel
        panel.add(buttonPanel);
//store the panel in the frame
        frame.add(panel);
//assign a size for the frame
//reading the size from the applet
        frame.setSize(applet.getSize().width, applet.getSize().height + 200);
//display the frame
        frame.setVisible(true);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}