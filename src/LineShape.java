import processing.core.PApplet;
import processing.core.PConstants;

/**
 * Drawing tool - LineShape
 * @author Gruppe 6
 * @version 1.0, April 2015
 */

public class LineShape extends Brush{
    
    LineShape(PApplet input){
        super(input);
    }

    /***
     * Call this method to create a new Line with random characteristics
     */
    public void drawLines(){
        if (p.mousePressed && (p.mouseButton == PConstants.LEFT)) {
            p.strokeWeight(p.random(3, 8));
            if(!cc) { p.stroke(p.random(0, 255), p.random(0, 255), p.random(0, 255), p.random(0, 255)); }
            if(cc) { p.stroke(p.random(this.r), p.random(this.g), p.random(this.b), p.random(this.o)); }
            p.line(p.mouseX - p.random(-100, 100), p.mouseY, p.mouseX + p.random(-100, 100), p.mouseY);
        } else if (p.mousePressed && (p.mouseButton == PConstants.RIGHT)) {
            p.strokeWeight(p.random(3, 8));
            if(!cc) { p.stroke(p.random(0, 255), p.random(0, 255), p.random(0, 255), p.random(0, 255)); }
            if(cc) { p.stroke(p.random(this.r), p.random(this.g), p.random(this.b), p.random(this.o)); }
            p.line(p.mouseX, p.mouseY - p.random(-100, 100), p.mouseX, p.mouseY + p.random(-100, 100));
        }
    }
}
