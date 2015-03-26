import processing.core.PApplet;

/**
 * Created by Gøran on 26.03.2015.
 */
public class LineShape {
    
    PApplet p;
    
    LineShape(PApplet input){
        p = input;
    }
    
    public void drawLines(){
        if (p.mousePressed && (p.mouseButton == p.LEFT)) {
            p.strokeWeight(p.random(3, 8));
            p.stroke(p.random(0, 255), p.random(0, 255), p.random(0, 255), p.random(0, 255));
            p.line(p.mouseX - p.random(-100, 100), p.mouseY, p.mouseX + p.random(-100, 100), p.mouseY);
        } else if (p.mousePressed && (p.mouseButton == p.RIGHT)) {
            p.strokeWeight(p.random(3, 8));
            p.stroke(p.random(0, 255), p.random(0, 255), p.random(0, 255), p.random(0, 255));
            p.line(p.mouseX, p.mouseY - p.random(-100, 100), p.mouseX, p.mouseY + p.random(-100, 100));
        }
    }
}
