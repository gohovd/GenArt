/**
 * Created by jens on 24.03.15.
 */

import sun.plugin.dom.css.RGBColor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColorChooser extends JFrame {
    private JLabel sampleText = new JLabel("Label");
    private JButton chooseButton = new JButton("Choose Color");

    private float strokeSize = 5;
    private Color strokeColor = Color.darkGray;

    ArrayList complementToStrokeColors = new ArrayList<Color>();


    public ColorChooser() {
        this.setSize(300, 300);
        //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.white);
        sampleText.setBackground(null);
        panel1.add(sampleText);

        chooseButton.addActionListener(new ButtonListener());
        panel1.add(chooseButton);

        this.add(panel1);
        // this.setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Color c = JColorChooser.showDialog(null, "Choose Stroke Color", sampleText.getForeground());

            if (c != null)
                sampleText.setForeground(c);
            System.out.println(c.toString());
            strokeColor = c;
            System.out.println(strokeColor);

            // TODO generate complementcolors
            // TODO generate other sets of colors
        }
    }

    public float getStrokeSize() {
        return strokeSize;
    }

    public void setStrokeSize(float strokeSize) {
        this.strokeSize = strokeSize;
    }

    public int getStrokeColor() {
        return strokeColor.getRGB();
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public int getRandomStrokeColor(int min, int max) {
        // TODO lage til så den returnerer mellom min og max
        return 1;
    }
}
