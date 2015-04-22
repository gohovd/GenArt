import processing.core.PApplet;

import java.awt.*;


/**
 * Drawing tool - Border
 * @author Gruppe 6
 * @version 1.0, April 2015
 */
public class Border {
    PApplet p;
    Application ap;
    ColorPicker colorPicker = new ColorPicker();


    /***
     * Creating variables
     */
    int state = 0;
    int stokeSize = 30;


    Border(PApplet input, Application a) {
        p = input;
        ap = a;
    }

    /***
     * Runs colorpicker, if a color is picked it applies the picked RGB values to the border
     * Creates a transparent rectangle with opaque stroke
     */
    public void drawBorder() {
        if (state == 0) {

            colorPicker.color();
            if (colorPicker.getC() != null) {

                p.strokeWeight(stokeSize);
                p.stroke(colorPicker.getR(), colorPicker.getG(), colorPicker.getB());

                p.noFill();
                //rectMode(CENTER);
                p.rect(14, 10, ap.panel.getWidth() - stokeSize, ap.panel.getHeight() - stokeSize);
            }

        }
        /***
         * Change the state to 1 to let the system know a color has been picked, else it will keep asking to pick a colour.
         * Had to change the Stroke size to 1, else it would impact other classes stroke size.
         */
        state = 1;
        stokeSize = 1;
    }

}