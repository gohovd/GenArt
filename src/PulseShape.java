import processing.core.PApplet;

/**
 * Created by Gøran on 26.03.2015.
 */
public class PulseShape {
    
    PApplet p;
    int pulseAngle = 0;
    
    PulseShape(PApplet input){
        p = input;
    }
    
    public void drawPulse(){
        if (p.mousePressed == true) {

            pulseAngle += 5;

            float val = (float) (p.cos(p.radians(pulseAngle)) * 12.0);
            for (int a = 0; a < 360; a += 75) {
                float xoff = p.cos(p.radians(a)) * val;
                float yoff = p.sin(p.radians(a)) * val;
                p.fill(p.random(0, 255), p.random(0, 255), p.random(0, 255), p.random(0, 255));
                p.ellipse(p.mouseX + xoff, p.mouseY + yoff, val, val);
            }
            p.fill(255);
            p.ellipse(p.mouseX, p.mouseY, 2, 2);
        }
    }
}
