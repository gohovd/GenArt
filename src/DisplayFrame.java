

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Toolkit;

public class DisplayFrame extends JFrame implements ActionListener {

    private Animate animate;

    private JPanel panel;
    private JButton startButton;
    private JButton stopButton;
    private JButton button;

    public DisplayFrame() {


        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel = new JPanel();
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        button = new JButton("Add Particles");
        animate = new Animate();

        panel.add(animate);
        this.add(panel);
        this.add(startButton);
        this.add(stopButton);
        this.add(button);

        fixLayout();
        actionListeners();

        animate.init();

        this.setVisible(true);
    }

    private void fixLayout() {
        setLayout(null);

        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth())-200;
        int ySize = ((int) tk.getScreenSize().getHeight());

        int xSize2 = xSize+20;

        panel.setBounds(20, 20, xSize, ySize);
        startButton.setBounds(xSize2, 20, 100, 30);
        stopButton.setBounds(xSize2, 20, 100, 30);
        button.setBounds(xSize2, 70, 150, 30);

    }

    private void actionListeners() {
        startButton.addActionListener(animate);
        startButton.setActionCommand("run");

        stopButton.addActionListener(animate);
        stopButton.setActionCommand("stop");

        button.addActionListener(animate);
        button.setActionCommand("custom");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
