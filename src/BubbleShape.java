import processing.core.PApplet;


/**
 * Drawing tool - Bubble
 * @author Gruppe 6
 * @version 1.0, April 2015
 */

public class BubbleShape extends Brush {

    BubbleShape(PApplet input){
        super(input);
    }

    /***
     * Call this method to create new Bubble with random characteristics
     */
    public void drawBubbles(){
        if (p.mousePressed == true) {
            int x = p.mouseX;
            int y = p.mouseY;
            int px = p.pmouseX;
            int py = p.pmouseY;

            float speed = PApplet.abs(x - px) + PApplet.abs(y - py);
            if(!cc) { p.fill(p.random(255), p.random(255), p.random(255), 35); }
            if(cc) { p.fill(p.random(r), p.random(g), p.random(b), p.random(o)); }
            if(speed > 500) { speed = 300; }
            p.stroke(speed);
            p.ellipse(x, y, speed, speed);
        }
    }
}
