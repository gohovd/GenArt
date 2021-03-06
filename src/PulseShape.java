import processing.core.PApplet;

/**
 * Drawing tool - Pulsing Shape
 * @author Gruppe 6
 * @version 1.0, April 2015
 */
public class PulseShape extends Brush {

    int pulseAngle = 0;
    
    PulseShape(PApplet input){
        super(input);
    }

    /**
     * Creates a flower like pattern with random colors as you drag you mouse. The flower increases and decreases automatically
     */
    
    public void drawPulse(){
        if (p.mousePressed == true) {
            p.noStroke();
            pulseAngle += 5;

            float val = (float) (PApplet.cos(PApplet.radians(pulseAngle)) * 12.0);
            for (int a = 0; a < 360; a += 75) {
                float xoff = PApplet.cos(PApplet.radians(a)) * val;
                float yoff = PApplet.sin(PApplet.radians(a)) * val;
                if(!cc) { p.fill(p.random(255), p.random(255), p.random(255), 127); }
                if(cc) { p.fill(p.random(r), p.random(g), p.random(b), p.random(o)); }
                p.ellipse(p.mouseX + xoff, p.mouseY + yoff, val, val);
            }
            p.fill(255);
            p.ellipse(p.mouseX, p.mouseY, 2, 2);
        }
    }

}
