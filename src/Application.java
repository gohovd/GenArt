import java.awt.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.BorderFactory;

/**
 * Main class for ArtGen. Initiate to run GenArt
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
            starButton, heartButton, squarezButton,  buttonCreate, varBubblesButton, trianglezButton,
            Randomize,strokeNColourButton, filterButton, saveButton, closeButton, signatureButton,
        printButton, drunkLinesButton, symButton, bgButton;

    // Hashmap to support "Tormod"
    public static HashMap<String, JButton> buttonMap = new HashMap();

    public Application() {
    }

    public static void main(String[] args) {
        //Size of imageIcons (for buttons)
        int size = 60;
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
        buttonCreate.setPreferredSize(new Dimension(size, size));

        // Round vector
        ImageIcon imageForvectorButton = new ImageIcon("images/roundvector.png");
        vectorButton = new JButton("", imageForvectorButton);
        vectorButton.setBackground(Color.white);
        vectorButton.setPreferredSize(new Dimension(size, size));

        // "Drunk"-lines
        ImageIcon imageFordrunkLines = new ImageIcon("images/soon.png");
        drunkLinesButton = new JButton("", imageFordrunkLines);
        drunkLinesButton.setBackground(Color.white);
        drunkLinesButton.setPreferredSize(new Dimension(size, size));
        
        // Reset drawing area
        clearButton = new JButton("Reset");
        
        // Randomized, automatic drawing, "Tormod"
        Randomize = new JButton("Random");
        Randomize.setBackground(Color.GREEN);
        Randomize.setForeground(Color.WHITE);
        Randomize.setBorderPainted(true);

        // Save
        saveButton = new JButton("Lagre");

        // Bakgrunnsfarge
        bgButton = new JButton("    Bakgrunns Farge    ");

        // Close program
        closeButton = new JButton("   Lukk Programmet   ");
        closeButton.setBackground(Color.RED);
        closeButton.setForeground(Color.WHITE);
        closeButton.setBorderPainted(true);

        // Filters
        filterButton = new JButton("   Filter    ");

        // Random lines
        ImageIcon imageForrandomLinesButton = new ImageIcon("images/rndlines.png");
        randomLinesButton = new JButton("", imageForrandomLinesButton);
        randomLinesButton.setBackground(Color.white);
        randomLinesButton.setPreferredSize(new Dimension(size, size));

        // Symmetric
        ImageIcon imageForSymButton = new ImageIcon("images/sym.png");
        symButton = new JButton("", imageForSymButton);
        symButton.setBackground(Color.WHITE);
        symButton.setPreferredSize(new Dimension(size, size));

        // Bubbles
        ImageIcon imageForvarBubblesButton = new ImageIcon("images/varbubbles.png");
        varBubblesButton = new JButton("", imageForvarBubblesButton);
        varBubblesButton.setBackground(Color.white);
        varBubblesButton.setPreferredSize(new Dimension(size, size));

        // Pulse
        ImageIcon imageForpulseButton = new ImageIcon("images/puls.png");
        pulseButton = new JButton("", imageForpulseButton);
        pulseButton.setBackground(Color.white);
        pulseButton.setPreferredSize(new Dimension(size, size));

        // CrossDots
        ImageIcon imageForcrossDotsButton = new ImageIcon("images/crossdots.png");
        crossDotsButton = new JButton("", imageForcrossDotsButton);
        crossDotsButton.setBackground(Color.white);
        crossDotsButton.setPreferredSize(new Dimension(size, size));

        // Printerbutton
        ImageIcon imageForPrintButton = new ImageIcon("images/printer1.png");
        printButton = new JButton("", imageForPrintButton);
        printButton.setBackground(Color.white);
        printButton.setPreferredSize(new Dimension(size, size));

        // Starbutton
        ImageIcon imageForStarButton = new ImageIcon("images/star.png");
        starButton = new JButton("", imageForStarButton);
        starButton.setBackground(Color.white);
        starButton.setPreferredSize(new Dimension(size, size));

        // Mirror
        // TODO refactor heartButton (expired) to mirrorButton
        ImageIcon imageForHeartButton = new ImageIcon("images/mirrorvector.png");
        heartButton = new JButton("", imageForHeartButton);
        heartButton.setBackground(Color.white);
        heartButton.setPreferredSize(new Dimension(size, size));

        // Squarez
        ImageIcon imageForsquarezButton = new ImageIcon("images/sq.png");
        squarezButton = new JButton("", imageForsquarezButton);
        squarezButton.setBackground(Color.white);
        squarezButton.setPreferredSize(new Dimension(size, size));

        // Triangles
        ImageIcon imageFortrianglezButton = new ImageIcon("images/trianglez.png");
        trianglezButton = new JButton("", imageFortrianglezButton);
        trianglezButton.setBackground(Color.white);
        trianglezButton.setPreferredSize(new Dimension(size, size));

        // Color chooser
        ImageIcon imageForStrokeNColourButton = new ImageIcon("images/colorPick.png");
        strokeNColourButton = new JButton("", imageForStrokeNColourButton);
        strokeNColourButton.setBackground(Color.white);
        strokeNColourButton.setPreferredSize(new Dimension(size, size));

        // Signature
        ImageIcon imageForSignatureButton = new ImageIcon("images/signature.png");
        signatureButton = new JButton("", imageForSignatureButton);
        signatureButton.setBackground(Color.white);
        signatureButton.setPreferredSize(new Dimension(size, size));

        //Help text for buttons.
        buttonCreate.setToolTipText("Lager baller som går i forskjellige retninger.");
        vectorButton.setToolTipText("Lager en mengde vektorer som beveger seg i sirkel.");
        clearButton.setToolTipText("Fjerner alt på lerretet.");
        filterButton.setToolTipText("Tilfør et filter.");
        randomLinesButton.setToolTipText("Tegner linjer i forskjellig størrelse.");
        varBubblesButton.setToolTipText("Tegner bobler som varierer etter musepeker-hastigheten.");
        pulseButton.setToolTipText("Tegner sirkler som pulserer i forskjellige størrelse.");
        crossDotsButton.setToolTipText("Tegner 'dotter' som går i kryss formasjon.");
        saveButton.setToolTipText("Lagrer bildet ditt på skrivebordet.");
        bgButton.setToolTipText("Endre bakgrunn farge");
        closeButton.setToolTipText("Lukk programmet.");
        printButton.setToolTipText("Printer ut bildet.");
        starButton.setToolTipText("Tegner stjerner.");
        heartButton.setToolTipText("Tegner hjerter.");
        squarezButton.setToolTipText("Tegner firkanter i forskjellig størrelse.");
        trianglezButton.setToolTipText("Tegner trekanter i forskjellig vinkel og størrelse.");
        strokeNColourButton.setToolTipText("Velger farge på penslene dine.");
        signatureButton.setToolTipText("Legg igjen din signatur på bildet.");
        drunkLinesButton.setToolTipText("Tegner linjer som er dritings.");
        symButton.setToolTipText("Tegn symmetrisk.");

        // Set names for buttons in order to initiate ActionCommand
        buttonCreate.setActionCommand("create ball");
        vectorButton.setActionCommand("create vector");
        clearButton.setActionCommand("clear");
        filterButton.setActionCommand("filter");
        randomLinesButton.setActionCommand("randomLines");
        varBubblesButton.setActionCommand("varBubbles");
        pulseButton.setActionCommand("pulse");
        crossDotsButton.setActionCommand("crossDots");
        bgButton.setActionCommand("bg");
        saveButton.setActionCommand("save");
        closeButton.setActionCommand("close");
        starButton.setActionCommand("starz");
        heartButton.setActionCommand("heartz");
        squarezButton.setActionCommand("squarez");
        trianglezButton.setActionCommand("trianglez");
        Randomize.setActionCommand("randomize");
        strokeNColourButton.setActionCommand("strokencolour");
        signatureButton.setActionCommand("signature");
        printButton.setActionCommand("printing");
        drunkLinesButton.setActionCommand("drunk");
        symButton.setActionCommand("syms");

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
        buttonMap.put("bgButton", bgButton);
        buttonMap.put("closeButton", closeButton);
        buttonMap.put("Randomize", Randomize);
        buttonMap.put("clearButton", clearButton);
        buttonMap.put("filterButton", filterButton);
        buttonMap.put("signatureButton", signatureButton);
        buttonMap.put("symButton", symButton);
        buttonMap.put("printButton", printButton);
        buttonMap.put("starButton", starButton);
        buttonMap.put("drunkLinesButton", drunkLinesButton);

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
        //make 3 new separators
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setPreferredSize(new Dimension(200, 20));
        JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);
        separator2.setPreferredSize(new Dimension(200,20));
        JSeparator separator3 = new JSeparator(SwingConstants.HORIZONTAL);
        separator3.setPreferredSize(new Dimension(200,20));
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
        buttonPanel.add(starButton);
        buttonPanel.add(symButton);
        buttonPanel.add(drunkLinesButton);
        buttonPanel.add(separator);
        buttonPanel.add(printButton);
        buttonPanel.add(strokeNColourButton);
        buttonPanel.add(signatureButton);
        buttonPanel.add(separator2);

        //Also add buttons/radio-buttons/check-boxes.
        buttonPanel.add(randomclr);
        buttonPanel.add(border);
        buttonPanel.add(separator3);
        buttonPanel.add(Randomize);
        buttonPanel.add(clearButton);
        buttonPanel.add(filterButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(bgButton);
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