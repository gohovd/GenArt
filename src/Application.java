import java.awt.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.BorderFactory;

/**
 * Main class for ArtGen. Initiate to create objects and run GenArt
 * @author  Gruppe 6
 * @version 1.0, April 2015
 */

public class Application {

    //private static final JCheckBox circular = new JCheckBox("Sirkulær");
    private static final JCheckBox randomclr = new JCheckBox("Tilfeldig farge");

    //private static final JCheckBox linear = new JCheckBox("Lineær");
    private static final JCheckBox border = new JCheckBox("Ramme");

    // Width for right panel, menupanel
    private static final int menuWidth = 200; // Husk å endre i MyApplet hvis du endrer her

    // panel holds Processing applet, buttonPanel holds menu items
    public static JPanel panel, buttonPanel;

    // For availability, declare all buttons
    private static JButton vectorButton, clearButton, randomLinesButton, pulseButton, crossDotsButton,
            starButton, heartButton, squarezButton, buttonCreate, varBubblesButton, trianglezButton,
            Randomize, strokeNColourButton, filterButton, saveButton, closeButton, signatureButton;

    // Hashmap to support "Tormod"
    public static HashMap<String, JButton> buttonMap = new HashMap();

    public Application() {
    }

    public static void main(String[] args) {
        // Create a JFrame for the application
        final JFrame frame = new JFrame("Fantastic Art Generator");
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setBackground(Color.white);
        frame.setLayout(null);

        // For "true fullscreen". Can be set to false
        frame.setUndecorated(true);

        // To ensure proper shutdown upon exit
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel for the Processing applet, drawing area
        panel = new JPanel();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int panelXChange = screenSize.width - menuWidth;
        panel.setBounds(0, 0, panelXChange, screenSize.height);
        panel.setBackground(Color.white);

        // Create a JPanel for menu items
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.white);
        buttonPanel.setBounds(panelXChange, 0, menuWidth, screenSize.height);
        buttonPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.LIGHT_GRAY));


        // Create an instance of Processing applet
        final MyApplet applet = new MyApplet();

        // Start the applet
        applet.init();

        // Create buttons
        // Ball grid
        ImageIcon imageForbuttonCreate = new ImageIcon("images/ballgrid.png");
        buttonCreate = new JButton("", imageForbuttonCreate);
        buttonCreate.setBackground(Color.white);
        buttonCreate.setPreferredSize(new Dimension(90, 90));

        // Round vector
        ImageIcon imageForvectorButton = new ImageIcon("images/roundvector.png");
        vectorButton = new JButton("", imageForvectorButton);
        vectorButton.setBackground(Color.white);
        vectorButton.setPreferredSize(new Dimension(90, 90));

        // Reset drawing area
        clearButton = new JButton("Reset");

        // Randomized, automatic drawing, "Tormod"
        Randomize = new JButton("Randomisert");
        Randomize.setBackground(Color.GREEN);
        Randomize.setForeground(Color.WHITE);
        Randomize.setBorderPainted(true);

        // Save
        saveButton = new JButton("Lagre");

        // Close program
        closeButton = new JButton("Lukk Programmet");

        // Filters
        filterButton = new JButton("Filter");

        // Random lines
        ImageIcon imageForrandomLinesButton = new ImageIcon("images/rndlines.png");
        randomLinesButton = new JButton("", imageForrandomLinesButton);
        randomLinesButton.setBackground(Color.white);
        randomLinesButton.setPreferredSize(new Dimension(90, 90));

        // Bubbles
        ImageIcon imageForvarBubblesButton = new ImageIcon("images/varbubbles.png");
        varBubblesButton = new JButton("", imageForvarBubblesButton);
        varBubblesButton.setBackground(Color.white);
        varBubblesButton.setPreferredSize(new Dimension(90, 90));

        // Pulse
        ImageIcon imageForpulseButton = new ImageIcon("images/pulse.png");
        pulseButton = new JButton("", imageForpulseButton);
        pulseButton.setBackground(Color.white);
        pulseButton.setPreferredSize(new Dimension(90, 90));

        // CrossDots
        ImageIcon imageForcrossDotsButton = new ImageIcon("images/crossdots.png");
        crossDotsButton = new JButton("", imageForcrossDotsButton);
        crossDotsButton.setBackground(Color.white);
        crossDotsButton.setPreferredSize(new Dimension(90, 90));

        // Starbutton
        ImageIcon imageForStarButton = new ImageIcon("images/printer1.png");
        starButton = new JButton("", imageForStarButton);
        starButton.setBackground(Color.white);
        starButton.setPreferredSize(new Dimension(90, 90));

        // Mirror
        // TODO refactor heartButton (expired) to mirrorButton
        ImageIcon imageForHeartButton = new ImageIcon("images/mirrorvector.png");
        heartButton = new JButton("", imageForHeartButton);
        heartButton.setBackground(Color.white);
        heartButton.setPreferredSize(new Dimension(90, 90));

        // Squarez
        ImageIcon imageForsquarezButton = new ImageIcon("images/squarez.png");
        squarezButton = new JButton("", imageForsquarezButton);
        squarezButton.setBackground(Color.white);
        squarezButton.setPreferredSize(new Dimension(90, 90));

        // Triangles
        ImageIcon imageFortrianglezButton = new ImageIcon("images/trianglez.png");
        trianglezButton = new JButton("", imageFortrianglezButton);
        trianglezButton.setBackground(Color.white);
        trianglezButton.setPreferredSize(new Dimension(90, 90));

        // Color chooser
        ImageIcon imageForStrokeNColourButton = new ImageIcon("images/strokencolour80x80.png");
        strokeNColourButton = new JButton("", imageForStrokeNColourButton);
        strokeNColourButton.setBackground(Color.white);
        strokeNColourButton.setPreferredSize(new Dimension(90, 90));

        // Signature
        ImageIcon imageForSignatureButton = new ImageIcon("images/signature.png");
        signatureButton = new JButton("", imageForSignatureButton);
        signatureButton.setBackground(Color.white);
        signatureButton.setPreferredSize(new Dimension(90, 90));


        // Assing tooltip to buttons
        buttonCreate.setToolTipText("creates a new ball ");
        vectorButton.setToolTipText("creates a new vector");
        clearButton.setToolTipText("clears the screen");
        filterButton.setToolTipText("add a filter");
        randomLinesButton.setToolTipText("creates random lines");
        varBubblesButton.setToolTipText("Draws random size bubbles depending on mouse speed");
        pulseButton.setToolTipText("Pulsing");
        crossDotsButton.setToolTipText("Draws dots in cross formation");
        saveButton.setToolTipText("Draws dots in cross formation");
        closeButton.setToolTipText("Lukk programmet");
        starButton.setToolTipText("Print out your art");
        heartButton.setToolTipText("Draw mirror vectors");
        squarezButton.setToolTipText("Draws squares as you drag your mouse");
        trianglezButton.setToolTipText("Draws triangles as you drag your mouse");
        strokeNColourButton.setToolTipText("Choose stroke size and colours");
        signatureButton.setToolTipText("Add your signature");


        // Set names for buttons in order to initiate ActionCommand
        buttonCreate.setActionCommand("create ball");
        vectorButton.setActionCommand("create vector");
        clearButton.setActionCommand("clear");
        filterButton.setActionCommand("filter");
        randomLinesButton.setActionCommand("randomLines");
        varBubblesButton.setActionCommand("varBubbles");
        pulseButton.setActionCommand("pulse");
        crossDotsButton.setActionCommand("crossDots");
        saveButton.setActionCommand("save");
        closeButton.setActionCommand("close");
        starButton.setActionCommand("starz");
        heartButton.setActionCommand("heartz");
        squarezButton.setActionCommand("squarez");
        trianglezButton.setActionCommand("trianglez");
        Randomize.setActionCommand("randomize");
        strokeNColourButton.setActionCommand("strokencolour");
        signatureButton.setActionCommand("signature");

        // Add buttons to map. Name of button is key, while actual button is the value.
        buttonMap.put("buttonCreate", buttonCreate);
        buttonMap.put("vectorButton", vectorButton);
        buttonMap.put("randomLinesButton", randomLinesButton);
        buttonMap.put("varBubblesButton", varBubblesButton);
        buttonMap.put("pulseButton", pulseButton);
        buttonMap.put("crossDotsButton", crossDotsButton);
        buttonMap.put("starButton", starButton);
        buttonMap.put("heartButton", heartButton);
        buttonMap.put("squarezButton", squarezButton);
        buttonMap.put("tranglezButton", trianglezButton);
        buttonMap.put("strokeNColourButton", strokeNColourButton);
        buttonMap.put("saveButton", saveButton);
        buttonMap.put("closeButton", closeButton);
        buttonMap.put("Randomize", Randomize);
        buttonMap.put("clearButton", clearButton);
        buttonMap.put("filterButton", filterButton);
        buttonMap.put("signatureButton", signatureButton);

        //Iterate through entrySet, and set class Applet as action listener for every button.
        for (Object button : buttonMap.keySet()) {
            String nameOfButton = button.toString();
            JButton t = buttonMap.get(nameOfButton);
            t.addActionListener(applet);
        }

        border.addItemListener(applet);
        border.setSelected(false);

        randomclr.addItemListener(applet);
        randomclr.setSelected(false);

        //Add buttons to the button-panel.
        buttonPanel.add(buttonCreate);
        buttonPanel.add(randomLinesButton);
        buttonPanel.add(vectorButton);
        buttonPanel.add(varBubblesButton);
        buttonPanel.add(pulseButton);
        buttonPanel.add(crossDotsButton);
        buttonPanel.add(heartButton);
        buttonPanel.add(squarezButton);
        buttonPanel.add(trianglezButton);
        buttonPanel.add(strokeNColourButton);
        buttonPanel.add(starButton);
        buttonPanel.add(signatureButton);

        //Also add buttons/radio-buttons/check-boxes.
        buttonPanel.add(randomclr); /*buttonPanel.add(linear); buttonPanel.add(circular);*/
        buttonPanel.add(border);
        buttonPanel.add(Randomize);
        buttonPanel.add(clearButton);
        buttonPanel.add(filterButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(closeButton);

        // Store the applet in panel
        panel.add(applet);

        // Store the buttonPanel in panel
        frame.add(buttonPanel);

        // Store the panel in the frame
        frame.add(panel);

        // Assign a size for the frame
        frame.setSize(applet.getSize().width, applet.getSize().height);

        // Display the frame
        frame.setVisible(true);
    }

    /***
     *
     * @return Return boolean (border selected)
     */
    public boolean getBorderState() {
        return border.isSelected();
    }

    /***
     *
     * @return Return boolean (random colour selected)
     */
    public boolean getRandomColorState() {
        return randomclr.isSelected();
    }

    /***
     *
     * @param b boolean (set random colour state)
     */
    public void setRandomclrState(boolean b) {
        randomclr.setSelected(b);
    }

    /***
     *
     * @return HashMap (buttons)
     */
    public HashMap getButtons() {
        return buttonMap;
    }
}