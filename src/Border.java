import processing.core.PApplet;
import javax.swing.*;

/**
 * Created by Ivan on 20.04.2015.
 */
public class Border {
    PApplet p;
    Application ap;
    JFrame frame = new JFrame("Input");
    int state = 0;
    int stokeSize = 30;

    Border(PApplet input, Application a){
        p = input;
        ap = a;
    }


    public void drawBorder(){
        if (state == 0) {

            // prompt the user to enter their name
            String[] color = {"Hvit", "Svart", "Rød", "Blå", "Grøn", "Lilla", "Oransj", "Gul"};


            String pickedColor = (String) JOptionPane.showInputDialog(frame,
                    "What color border do you want?",
                    "Color Picker",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    color,
                    color[0]);


            // favoritePizza will be null if the user clicks Cancel
            System.out.printf("Picked color %s.\n", pickedColor);

            p.strokeWeight(stokeSize);
            if (pickedColor == "Hvit") {
                p.stroke(255);
            } else if (pickedColor == "Svart") {
                p.stroke(0);
            } else if (pickedColor == "Rød") {
                p.stroke(255, 0, 0);
            } else if (pickedColor == "Blå") {
                p.stroke(0, 0, 255);
            } else if (pickedColor == "Grøn") {
                p.stroke(0, 255, 0);
            } else if (pickedColor == "Lilla") {
                p.stroke(161, 0, 236);
            } else if (pickedColor == "Oransj") {
                p.stroke(236, 128, 0);
            } else if (pickedColor == "Gul") {
                p.stroke(255, 255, 0);
            }


            //System.exit(0);




            p.noFill();
            //rectMode(CENTER);
            p.rect(13, 10, ap.panel.getWidth() - stokeSize, ap.panel.getHeight() - stokeSize);


        }
        state = 1;



    }
}