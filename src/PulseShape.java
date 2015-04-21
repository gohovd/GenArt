import processing.core.PApplet;

/**
 * Created by Gøran on 26.03.2015.
 */
public class PulseShape extends Brush {

    int pulseAngle = 0;
    
    PulseShape(PApplet input){
        super(input);
    }
    
    public void drawPulse(){
        if (p.mousePressed == true) {

            pulseAngle += 5;

            float val = (float) (PApplet.cos(PApplet.radians(pulseAngle)) * 12.0);
            for (int a = 0; a < 360; a += 75) {
                float xoff = PApplet.cos(PApplet.radians(a)) * val;
                float yoff = PApplet.sin(PApplet.radians(a)) * val;
                if(!this.cc) { p.fill(p.random(255), p.random(255), p.random(255), p.random(255)); }
                if(this.cc) { p.fill(p.random(this.r), p.random(this.g), p.random(this.b), p.random(this.o)); }
                p.ellipse(p.mouseX + xoff, p.mouseY + yoff, val, val);
            }
            p.fill(255);
            p.ellipse(p.mouseX, p.mouseY, 2, 2);
        }
    }

}
