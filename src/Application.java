import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

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
    private static final JTextField vColorField = new JTextField(5);
    private static final JCheckBox circular = new JCheckBox("Circular");
    private static final JCheckBox randomclr = new JCheckBox("Random Color");
    private static final JCheckBox linear = new JCheckBox("Linear");

    public Application(){}

    public static void main(String[] args) {
        //create a frame for the application
        final JFrame frame = new JFrame("Fantastic Art Generator");
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setBackground(Color.white);
//frame.setUndecorated(true); // Aktiver for å fjerne tittel etc, "skikkelig" fullskjerm
//make sure to shut down the application, when the frame is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//create a panel for the applet and the button panel
        JPanel panel = new JPanel();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        panel.setSize(screenSize.width, screenSize.height);
        panel.setBackground(Color.white);



//create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        buttonPanel.setBackground(Color.white);

        // svart border til venstre for knapper
        buttonPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
//create an instance of your processing applet
        final MyApplet applet = new MyApplet();
//start the applet
        applet.init();
// Textboxes
//create a textbox that takes three ints as inputs to form a color
        vColorField.setToolTipText("COLOR: 255 255 255 255");
        vColorField.addActionListener(applet);
//Buttons
//create a button labled "create new ball"
        JButton buttonCreate = new JButton("create new ball");
        JButton vectorButton = new JButton("create new vector");
        JButton clearButton = new JButton("clear");
        JButton randomLinesButton = new JButton("randomLines");
        JButton varBubblesButton = new JButton("varBubbles");
        JButton pulseButton = new JButton("pulse");
        JButton crossDotsButton = new JButton("Cross Dots");
//assing a tooltip
        buttonCreate.setToolTipText("creates a new ball ");
        vectorButton.setToolTipText("creates a new vector");
        clearButton.setToolTipText("clears the screen");
        randomLinesButton.setToolTipText("creates random lines");
        varBubblesButton.setToolTipText("Draws random size bubbles depending on mouse speed");
        pulseButton.setToolTipText("Pulsing");
        crossDotsButton.setToolTipText("Draws dots in cross formation");
//give a name for the command
//if this is not assigned the actionCommand equals the button label
        buttonCreate.setActionCommand("create ball");
        vectorButton.setActionCommand("create vector");
        clearButton.setActionCommand("clear");
        randomLinesButton.setActionCommand("randomLines");
        varBubblesButton.setActionCommand("varBubbles");
        pulseButton.setActionCommand("pulse");
        crossDotsButton.setActionCommand("crossDots");
//create a button lable "load file"
        JButton buttonLoad = new JButton("load file");
        buttonLoad.setToolTipText("loads a new background image");
//button actions
//the create button is simply linked to the applet
//the action is executed inside applet.actionPerformed()
        buttonCreate.addActionListener(applet);
        vectorButton.addActionListener(applet);
        clearButton.addActionListener(applet);
        randomLinesButton.addActionListener(applet);
        varBubblesButton.addActionListener(applet);
        pulseButton.addActionListener(applet);
        crossDotsButton.addActionListener(applet);

        circular.addItemListener(applet);
        circular.setSelected(false);
        linear.addItemListener(applet);
        linear.setSelected(false);
        randomclr.addItemListener(applet);
        randomclr.setSelected(true);

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
        buttonPanel.add(randomLinesButton);
        buttonPanel.add(vectorButton);
        buttonPanel.add(varBubblesButton);
        buttonPanel.add(pulseButton);
        buttonPanel.add(crossDotsButton);

        buttonPanel.add(randomclr); buttonPanel.add(linear); buttonPanel.add(circular);
        buttonPanel.add(vColorField);
        buttonPanel.add(clearButton);

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

    public String getVColor(){
        return vColorField.getText();
    }


    // Returns the states of each checkbox.
    public boolean getCircularState(){
        return circular.isSelected();
    }
    public boolean getLinearState(){
        return linear.isSelected();
    }
    public boolean getRandomColorState(){
        return randomclr.isSelected();
    }

    // Set the state of the checkboxes.
    public void setCircularState(boolean b){
        circular.setSelected(b);
    }
    public void setLinearState(boolean b){
        linear.setSelected(b);
    }
    public void setRandomclrState(boolean b){
        randomclr.setSelected(b);
    }


}