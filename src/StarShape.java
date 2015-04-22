import processing.core.PApplet;
import processing.core.PConstants;

/**
 * Drawing tool - Star
 * @author Gruppe 6
 * @version 1.0, April 2015
 */

public class StarShape extends Brush {

    StarShape(PApplet input){
        super(input);
        
    }

    /***
     * Draws Star Shape as you drag your mouse, with random colors with 50 percent alpha
     */

    public void drawStars() {
        if (p.mousePressed == true) {
            p.strokeWeight((float) 0.1);
            if(!cc) { p.fill(p.random(255), p.random(255), p.random(255), 127); }
            if(cc) { p.fill(p.random(this.r), p.random(this.g), p.random(this.b), p.random(this.o)); }
            //p.scale(p.random(1));
            p.beginShape();
            p.vertex(p.mouseX, p.mouseY - 50);
            p.vertex(p.mouseX + 14, p.mouseY - 20);
            p.vertex(p.mouseX + 47, p.mouseY - 15);
            p.vertex(p.mouseX + 23, p.mouseY + 7);
            p.vertex(p.mouseX + 29, p.mouseY + 40);
            p.vertex(p.mouseX, p.mouseY + 25);
            p.vertex(p.mouseX - 29, p.mouseY + 40);
            p.vertex(p.mouseX - 23, p.mouseY + 7);
            p.vertex(p.mouseX - 47, p.mouseY - 15);
            p.vertex(p.mouseX - 14, p.mouseY - 20);
            p.endShape(PConstants.CLOSE);
        }
    }

}
