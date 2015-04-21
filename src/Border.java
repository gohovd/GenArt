import processing.core.PApplet;

import java.awt.*;


/**
 * Created by Ivan on 20.04.2015.
 */
public class Border {
    PApplet p;
    Application ap;
    ColorPicker colorPicker = new ColorPicker();

    int state = 0;
    int stokeSize = 30;


    Border(PApplet input, Application a) {
        p = input;
        ap = a;
    }


    public void drawBorder() {
        if (state == 0) {

            colorPicker.color();
            if (colorPicker.getC() != null) {

                p.strokeWeight(stokeSize);
                p.stroke(colorPicker.getR(), colorPicker.getG(), colorPicker.getB());

                p.noFill();
                //rectMode(CENTER);
                p.rect(13, 10, ap.panel.getWidth() - stokeSize, ap.panel.getHeight() - stokeSize);
            }

        }
        state = 1;
    }

}