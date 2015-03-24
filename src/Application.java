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

    private static final int menuWidth = 200; // Husk å endre i MyApplet hvis du endrer her

    public static JButton vectorButton, clearButton, randomLinesButton, pulseButton, crossDotsButton, buttonCreate, varBubblesButton;

    public Application(){}

    public static void main(String[] args) {
        //create a frame for the application
        final JFrame frame = new JFrame("Fantastic Art Generator");
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setBackground(Color.white);
        frame.setLayout(null);




//frame.setUndecorated(true); // Aktiver for å fjerne tittel etc, "skikkelig" fullskjerm
//make sure to shut down the application, when the frame is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//create a panel for the applet and the button panel
        JPanel panel = new JPanel();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int panelXChange = screenSize.width - menuWidth;
        panel.setBounds(0,0,panelXChange,screenSize.height);

        panel.setBackground(Color.white);


//create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.white);

        buttonPanel.setBounds(panelXChange,0,menuWidth,screenSize.height);
        System.out.println(screenSize.width);
        System.out.println(screenSize.height);
        System.out.println(panelXChange);

// svart border til venstre for knapper
        buttonPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.LIGHT_GRAY));


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

        ImageIcon imageForbuttonCreate = new ImageIcon("images/ballgrid.png");
        buttonCreate = new JButton("", imageForbuttonCreate);
        buttonCreate.setBackground(Color.white);
        buttonCreate.setPreferredSize(new Dimension(90, 90));


        ImageIcon imageForvectorButton = new ImageIcon("images/roundvector.png");
        vectorButton = new JButton("", imageForvectorButton);
        vectorButton.setBackground(Color.white);
        vectorButton.setPreferredSize(new Dimension(90, 90));


        clearButton = new JButton("clear");

        ImageIcon imageForrandomLinesButton = new ImageIcon("images/rndlines.png");
        randomLinesButton = new JButton("", imageForrandomLinesButton);
        randomLinesButton.setBackground(Color.white);
        randomLinesButton.setPreferredSize(new Dimension(90, 90));

        ImageIcon imageForvarBubblesButton = new ImageIcon("images/varbubbles.png");
        varBubblesButton = new JButton("",imageForvarBubblesButton);
        varBubblesButton.setBackground(Color.white);
        varBubblesButton.setPreferredSize(new Dimension(90, 90));


        ImageIcon imageForpulseButton = new ImageIcon("images/pulse.png");
        pulseButton = new JButton("",imageForpulseButton);
        pulseButton.setBackground(Color.white);
        pulseButton.setPreferredSize(new Dimension(90, 90));

        ImageIcon imageForcrossDotsButton = new ImageIcon("images/crossdots.png");
        crossDotsButton = new JButton("", imageForcrossDotsButton);
        crossDotsButton.setBackground(Color.white);
        crossDotsButton.setPreferredSize(new Dimension(90, 90));

//assing a tooltip
        buttonCreate.setToolTipText("creates a new ball ");
        vectorButton.setToolTipText("creates a new vector");
        clearButton.setToolTipText("clears the screen");
        randomLinesButton.setToolTipText("creates random lines");
        varBubblesButton.setToolTipText("Draws random size bubbles depending on mouse speed");
        pulseButton.setToolTipText("Pulsing");
        crossDotsButton.setToolTipText("Draws dots in cross formation");
        // Adding button graphics


        //  pulseButton.setIcon(new ImageIcon("/pulse.png"));


//give a name for the command
//if this is not assigned the actionCommand equals the button label
        buttonCreate.setActionCommand("create ball");
        vectorButton.setActionCommand("create vector");
        clearButton.setActionCommand("clear");
        randomLinesButton.setActionCommand("randomLines");
        varBubblesButton.setActionCommand("varBubbles");
        pulseButton.setActionCommand("pulse");
        crossDotsButton.setActionCommand("crossDots");



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


//store the two buttons in the button panel
        buttonPanel.add(buttonCreate);

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
        frame.add(buttonPanel);
//store the panel in the frame
        frame.add(panel);
//assign a size for the frame
//reading the size from the applet
        frame.setSize(applet.getSize().width, applet.getSize().height);
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

    public static JButton getVectorButton() {
        return vectorButton;
    }

    public static JButton getClearButton() {
        return clearButton;
    }

    public static JButton getRandomLinesButton() {
        return randomLinesButton;
    }

    public static JButton getPulseButton() {
        return pulseButton;
    }

    public static JButton getCrossDotsButton() {
        return crossDotsButton;
    }

    public static JButton getButtonCreate() {
        return buttonCreate;
    }

    public static JButton getVarBubblesButton() {
        return varBubblesButton;
    }
}