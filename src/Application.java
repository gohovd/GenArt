import java.awt.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.BorderFactory;


/**
 * A simple demo application launching a Processing Applet
 * <p/>
 * Demonstrates the combination of JFrame, JButton, JFileChooser
 * and PApplet.
 *
 * @author georg munkel
 */

public class Application {
    private int strokeSize = 1;

    private static final JCheckBox randomclr = new JCheckBox("Tilfeldig farge");
    private static final JCheckBox border = new JCheckBox("Ramme");
    private static final int menuWidth = 200; // Husk å endre i MyApplet hvis du endrer her
    public static JPanel panel, buttonPanel;

    private static JButton vectorButton, clearButton, randomLinesButton, pulseButton, crossDotsButton,
            starButton, heartButton, squarezButton,  buttonCreate, varBubblesButton, trianglezButton,
            Randomize,strokeNColourButton, filterButton, saveButton, closeButton, signatureButton, printButton, drunkLinesButton;

    public static HashMap<String, JButton> buttonMap = new HashMap();

    public Application(){}

    public static void main(String[] args) {
        int size =60; //sets the size for buttons
        //create a frame for the application
        final JFrame frame = new JFrame("Fantastic Art Generator");
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setBackground(Color.white);
        frame.setLayout(null);




        frame.setUndecorated(true); // Aktiver for å fjerne tittel etc, "skikkelig" fullskjerm
//make sure to shut down the application, when the frame is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//create a panel for the applet and the button panel
        panel = new JPanel();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int panelXChange = screenSize.width - menuWidth;
        panel.setBounds(0,0,panelXChange,screenSize.height);

        panel.setBackground(Color.white);


//create a panel for the buttons
        buttonPanel = new JPanel();
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

//Buttons
//create a button labled "create new ball"

        ImageIcon imageForbuttonCreate = new ImageIcon("images/ballgrid.png");
        buttonCreate = new JButton("", imageForbuttonCreate);
        buttonCreate.setBackground(Color.white);
        buttonCreate.setPreferredSize(new Dimension(size, size));


        ImageIcon imageForvectorButton = new ImageIcon("images/roundvector.png");
        vectorButton = new JButton("", imageForvectorButton);
        vectorButton.setBackground(Color.white);
        vectorButton.setPreferredSize(new Dimension(size, size));

        ImageIcon imageFordrunkLines = new ImageIcon("images/soon.png");
        drunkLinesButton = new JButton("", imageFordrunkLines);
        drunkLinesButton.setBackground(Color.white);
        drunkLinesButton.setPreferredSize(new Dimension(size, size));




        clearButton = new JButton("Reset");
        Randomize = new JButton("Randomisert");
        Randomize.setBackground(Color.GREEN);
        Randomize.setForeground(Color.WHITE);
        Randomize.setBorderPainted(true);
        //Randomize.setBorder(null);
        saveButton = new JButton("Lagre");
        closeButton = new JButton("Lukk Programmet");
        filterButton = new JButton("Filter");



        ImageIcon imageForrandomLinesButton = new ImageIcon("images/rndlines.png");
        randomLinesButton = new JButton("", imageForrandomLinesButton);
        randomLinesButton.setBackground(Color.white);
        randomLinesButton.setPreferredSize(new Dimension(size, size));

        ImageIcon imageForvarBubblesButton = new ImageIcon("images/varbubbles.png");
        varBubblesButton = new JButton("",imageForvarBubblesButton);
        varBubblesButton.setBackground(Color.white);
        varBubblesButton.setPreferredSize(new Dimension(size, size));


        ImageIcon imageForpulseButton = new ImageIcon("images/pulse.png");
        pulseButton = new JButton("",imageForpulseButton);
        pulseButton.setBackground(Color.white);
        pulseButton.setPreferredSize(new Dimension(size, size));

        ImageIcon imageForcrossDotsButton = new ImageIcon("images/crossdots.png");
        crossDotsButton = new JButton("", imageForcrossDotsButton);
        crossDotsButton.setBackground(Color.white);
        crossDotsButton.setPreferredSize(new Dimension(size, size));

        ImageIcon imageForPrintButton = new ImageIcon("images/printer1.png");
        printButton = new JButton("", imageForPrintButton);
        printButton.setBackground(Color.white);
        printButton.setPreferredSize(new Dimension(size, size));

        ImageIcon imageForStarButton = new ImageIcon("images/star.png");
        starButton = new JButton("", imageForStarButton);
        starButton.setBackground(Color.white);
        starButton.setPreferredSize(new Dimension(size, size));

        ImageIcon imageForHeartButton = new ImageIcon("images/mirrorvector.png");
        heartButton = new JButton("", imageForHeartButton);
        heartButton.setBackground(Color.white);
        heartButton.setPreferredSize(new Dimension(size, size));

        ImageIcon imageForsquarezButton = new ImageIcon("images/sq.png");
        squarezButton = new JButton("", imageForsquarezButton);
        squarezButton.setBackground(Color.white);
        squarezButton.setPreferredSize(new Dimension(size, size));

        ImageIcon imageFortrianglezButton = new ImageIcon("images/trianglez.png");
        trianglezButton = new JButton("", imageFortrianglezButton);
        trianglezButton.setBackground(Color.white);
        trianglezButton.setPreferredSize(new Dimension(size, size));

        ImageIcon imageForStrokeNColourButton = new ImageIcon("images/cp.png");
        strokeNColourButton = new JButton("", imageForStrokeNColourButton);
        strokeNColourButton.setBackground(Color.white);
        strokeNColourButton.setPreferredSize(new Dimension(size, size));

        ImageIcon imageForSignatureButton = new ImageIcon("images/signature.png");
        signatureButton = new JButton("", imageForSignatureButton);
        signatureButton.setBackground(Color.white);
        signatureButton.setPreferredSize(new Dimension(size, size));

//assing a tooltip
        buttonCreate.setToolTipText("Lager baller som går i forskjellige retninger.");
        vectorButton.setToolTipText("Lager en mengde vektorer som beveger seg i sirkel.");
        clearButton.setToolTipText("Fjerner alt på lerretet.");
        filterButton.setToolTipText("Tilfør et filter.");
        randomLinesButton.setToolTipText("Tegner linjer i forskjellig størrelse.");
        varBubblesButton.setToolTipText("Tegner bobler som varierer etter musepeker-hastigheten.");
        pulseButton.setToolTipText("Tegner sirkler som pulserer i forskjellige størrelse.");
        crossDotsButton.setToolTipText("Tegner 'dotter' som går i kryss formasjon.");
        saveButton.setToolTipText("Lagrer bildet ditt på skrivebordet.");
        closeButton.setToolTipText("Lukk programmet.");
        printButton.setToolTipText("Printer ut bildet.");
        starButton.setToolTipText("Tegner stjerner.");
        heartButton.setToolTipText("Tegner hjerter.");
        squarezButton.setToolTipText("Tegner firkanter i forskjellig størrelse.");
        trianglezButton.setToolTipText("Tegner trekanter i forskjellig vinkel og størrelse.");
        strokeNColourButton.setToolTipText("Velger farge på penslene dine.");
        signatureButton.setToolTipText("Legg igjen din signatur på bildet.");
        drunkLinesButton.setToolTipText("Tegner linjer som er dritings.");
        // Adding button graphics


//give a name for the command
//if this is not assigned the actionCommand equals the button label
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
        printButton.setActionCommand("printing");
        drunkLinesButton.setActionCommand("drunk");

        //Add buttons to map. Name of button is key, while actual button is the value.
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
        buttonMap.put("printButton", printButton);
        buttonMap.put("drunkLinesButton", drunkLinesButton);


        //Iterate through entrySet, and set class Applet as action listener for every button.
        for(Object button : buttonMap.keySet()){
            String nameOfButton = button.toString();
            JButton t = buttonMap.get(nameOfButton);
            t.addActionListener(applet);
        }

        border.addItemListener(applet);
        border.setSelected(false);
        randomclr.addItemListener(applet);
        randomclr.setSelected(false);

        //Add you button to the button-panel.
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
        buttonPanel.add(printButton);
        buttonPanel.add(signatureButton);
        buttonPanel.add(drunkLinesButton);


        //Also add buttons/radio-buttons/check-boxes.
        buttonPanel.add(randomclr); buttonPanel.add(border);
        buttonPanel.add(Randomize); buttonPanel.add(clearButton); buttonPanel.add(filterButton);
        buttonPanel.add(saveButton);buttonPanel.add(closeButton);

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

    /***
     * Returns whether or not the border checkbox is selected.
     * @return boolean - State of the border checkbox.
     */
    public boolean getBorderState(){
        return border.isSelected();
    }

    /***
     * Returns whether or not the randomclr checkbox is selected.
     * @return boolean - State of the randomclr checkbox.
     */
    public boolean getRandomColorState(){
        return randomclr.isSelected();
    }

    /***
     * Set the state of the randomclr checkbox.
     * @param b - boolean
     */
    public void setRandomclrState(boolean b){
        randomclr.setSelected(b);
    }

    /***
     * HashMap containing all the buttons in the GUI,
     * and the keys (String values) connected to those buttons.
     * @return
     */
    public HashMap getButtons(){
        return buttonMap;
    }

}