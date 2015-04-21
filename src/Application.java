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

    private int width;
    private int height;

    private int strokeSize = 1;

    private static final JCheckBox circular = new JCheckBox("Sirkulær");
    private static final JCheckBox randomclr = new JCheckBox("Tilfeldig farge");
    private static final JCheckBox linear = new JCheckBox("Lineær");
    private static final JCheckBox border = new JCheckBox("Ramme");

    private static final int menuWidth = 200; // Husk å endre i MyApplet hvis du endrer her

    // Gjør panel og buttonPanel public, for at roboten skal nå tak i info.
    public static JPanel panel, buttonPanel;

    private static JButton vectorButton, clearButton, randomLinesButton, pulseButton, crossDotsButton,
            starButton, heartButton, squarezButton,  buttonCreate, varBubblesButton, trianglezButton,
            Randomize,strokeNColourButton, filterButton, saveButton, signatureButton;

    public static HashMap<String, JButton> buttonMap = new HashMap();

    public Application(){}

    public static void main(String[] args) {
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
        buttonCreate.setPreferredSize(new Dimension(90, 90));


        ImageIcon imageForvectorButton = new ImageIcon("images/roundvector.png");
        vectorButton = new JButton("", imageForvectorButton);
        vectorButton.setBackground(Color.white);
        vectorButton.setPreferredSize(new Dimension(90, 90));


        clearButton = new JButton("Clear");
        Randomize = new JButton("Randomisert");
        saveButton = new JButton("Lagre");
        filterButton = new JButton("Filter");

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

        ImageIcon imageForStarButton = new ImageIcon("images/star.png");
        starButton = new JButton("", imageForStarButton);
        starButton.setBackground(Color.white);
        starButton.setPreferredSize(new Dimension(90, 90));

        ImageIcon imageForHeartButton = new ImageIcon("images/mirrorvector.png");
        heartButton = new JButton("", imageForHeartButton);
        heartButton.setBackground(Color.white);
        heartButton.setPreferredSize(new Dimension(90, 90));

        ImageIcon imageForsquarezButton = new ImageIcon("images/squarez.png");
        squarezButton = new JButton("", imageForsquarezButton);
        squarezButton.setBackground(Color.white);
        squarezButton.setPreferredSize(new Dimension(90, 90));

        ImageIcon imageFortrianglezButton = new ImageIcon("images/trianglez.png");
        trianglezButton = new JButton("", imageFortrianglezButton);
        trianglezButton.setBackground(Color.white);
        trianglezButton.setPreferredSize(new Dimension(90, 90));

        ImageIcon imageForStrokeNColourButton = new ImageIcon("images/strokencolour80x80.png");
        strokeNColourButton = new JButton("", imageForStrokeNColourButton);
        strokeNColourButton.setBackground(Color.white);
        strokeNColourButton.setPreferredSize(new Dimension(90, 90));

        ImageIcon imageForSignatureButton = new ImageIcon("images/signature.png");
        signatureButton = new JButton("", imageForSignatureButton);
        signatureButton.setBackground(Color.white);
        signatureButton.setPreferredSize(new Dimension(90, 90));

        /*
        ImageIcon imageForSaveFunctions = new ImageIcon("images/crossdots.png");
        saveButton = new JButton("", imageForSaveFunctions);
        saveButton.setBackground(Color.white);
        saveButton.setPreferredSize(new Dimension(90, 90));
        */

//assing a tooltip
        buttonCreate.setToolTipText("creates a new ball ");
        vectorButton.setToolTipText("creates a new vector");
        clearButton.setToolTipText("clears the screen");
        filterButton.setToolTipText("add a filter");
        randomLinesButton.setToolTipText("creates random lines");
        varBubblesButton.setToolTipText("Draws random size bubbles depending on mouse speed");
        pulseButton.setToolTipText("Pulsing");
        crossDotsButton.setToolTipText("Draws dots in cross formation");
        saveButton.setToolTipText("Draws dots in cross formation");
        starButton.setToolTipText("Draws stars as you drag your mouse");
        heartButton.setToolTipText("Draw hearts.");
        squarezButton.setToolTipText("Draws squares as you drag your mouse");
        trianglezButton.setToolTipText("Draws triangles as you drag your mouse");
        strokeNColourButton.setToolTipText("Choose stroke size and colours");
        signatureButton.setToolTipText("Add your signature");
        // Adding button graphics


        //  pulseButton.setIcon(new ImageIcon("/pulse.png"));


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
        starButton.setActionCommand("starz");
        heartButton.setActionCommand("heartz");
        squarezButton.setActionCommand("squarez");
        trianglezButton.setActionCommand("trianglez");
        Randomize.setActionCommand("randomize");
        strokeNColourButton.setActionCommand("strokencolour");
        signatureButton.setActionCommand("signature");

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
        buttonMap.put("Randomize", Randomize);
        buttonMap.put("clearButton", clearButton);
        buttonMap.put("filterButton", filterButton);
        buttonMap.put("signatureButton", signatureButton);


        //Iterate through entrySet, and set class Applet as action listener for every button.
        for(Object button : buttonMap.keySet()){
            String nameOfButton = button.toString();
            JButton t = buttonMap.get(nameOfButton);
            t.addActionListener(applet);
        }
        //Add item listener class.
        circular.addItemListener(applet);
        circular.setSelected(false);
        linear.addItemListener(applet);
        linear.setSelected(false);
        border.addItemListener(applet);
        border.setSelected(false);
        randomclr.addItemListener(applet);
        randomclr.setSelected(true);

        //Add you button to the button-panel.
        buttonPanel.add(buttonCreate);
        buttonPanel.add(randomLinesButton);
        buttonPanel.add(vectorButton);
        buttonPanel.add(varBubblesButton);
        buttonPanel.add(pulseButton);
        buttonPanel.add(crossDotsButton);
        buttonPanel.add(starButton);
        buttonPanel.add(heartButton);
        buttonPanel.add(squarezButton);
        buttonPanel.add(trianglezButton);
        buttonPanel.add(strokeNColourButton);
        buttonPanel.add(signatureButton);

        //Also add buttons/radio-buttons/check-boxes.
        buttonPanel.add(randomclr); buttonPanel.add(linear); buttonPanel.add(circular); buttonPanel.add(border);
        buttonPanel.add(Randomize); buttonPanel.add(clearButton); buttonPanel.add(filterButton);
        buttonPanel.add(saveButton);

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


    // Returns the states of each checkbox.
    public boolean getCircularState(){
        return circular.isSelected();
    }
    public boolean getLinearState(){
        return linear.isSelected();
    }
    public boolean getBorderState(){
        return border.isSelected();
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
    public void setBorderState(boolean b){
        border.setSelected(b);
    }
    public void setRandomclrState(boolean b){
        randomclr.setSelected(b);
    }

    public HashMap getButtons(){
        return buttonMap;
    }

    public int getStrokeSize() {
        return strokeSize;
    }

    public void setStrokeSize(int strokeSize) {
        this.strokeSize = strokeSize;
    }

}