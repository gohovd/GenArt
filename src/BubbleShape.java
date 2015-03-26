import processing.core.PApplet;

/**
 * Created by Gøran on 26.03.2015.
 */
public class BubbleShape {
    
    PApplet p;
    
    BubbleShape(PApplet input){
        p = input;
    }
    
    public void drawBubbles(){
        if (p.mousePressed == true) {
            int x = p.mouseX;
            int y = p.mouseY;
            int px = p.pmouseX;
            int py = p.pmouseY;

            float speed = p.abs(x - px) + p.abs(y - py);
            p.fill(p.random(255), p.random(255), p.random(255), 127);
            p.stroke(speed);
            p.ellipse(x, y, speed, speed);
        }
    }
}
