/**
 * Created by jens on 24.03.15.
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColorChooser extends JFrame {
    private JLabel sampleText = new JLabel("Label");
    private JButton chooseButton = new JButton("Choose Color");

    public ColorChooser() {
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.white);
        sampleText.setBackground(null);
        panel1.add(sampleText);

        chooseButton.addActionListener(new ButtonListener());
        panel1.add(chooseButton);

        this.add(panel1);
        this.setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Color c = JColorChooser.showDialog(null, "Choose a Color", sampleText.getForeground());
            if (c != null)
                sampleText.setForeground(c);
        }
    }
}