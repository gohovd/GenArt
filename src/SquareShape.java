import processing.core.PApplet;

/**
 * Created by Gøran on 26.03.2015.
 */
public class SquareShape {
    
    PApplet p;
    
    SquareShape(PApplet input){
        p = input;
    }
    
    public void drawSquares(){
        if (p.mousePressed == true && p.mouseButton == p.LEFT) {
            p.strokeWeight((float) 0.1);
            p.fill(p.random(255), p.random(255), p.random(255), 127);
            p.rect(p.mouseX - 25, p.mouseY - 25, 100, 100);
        }

        if (p.mousePressed == true && p.mouseButton == p.RIGHT) {
            p.strokeWeight((float) 0.1);
            p.fill(p.random(255), p.random(255), p.random(255), 127);
            p.rect(p.mouseX, p.mouseY, 100, 100);
            p.fill(255);
            p.rect(p.mouseX + 10, p.mouseY + 10, 80, 80);
        }
    }
}
