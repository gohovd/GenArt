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
            if(!this.cc) { p.fill(p.random(255), p.random(255), p.random(255), 35); }
            if(this.cc) { p.fill(p.random(this.r), p.random(this.g), p.random(this.b), p.random(this.o)); }
            p.stroke(speed);
            p.ellipse(x, y, speed, speed);
        }
    }
}
