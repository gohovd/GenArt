import processing.core.PApplet;

/**
 * Created by Gøran on 26.03.2015.
 */
public class PulseShape {
    
    PApplet p;
    int pulseAngle = 0;

    int r, g, b, o;
    boolean cc = true; //Custom color
    
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
                if(cc == true) { p.fill(p.random(255), p.random(255), p.random(255), p.random(255)); }
                if(cc == false) { p.fill(p.random(r), p.random(g), p.random(b), p.random(o)); }
                p.ellipse(p.mouseX + xoff, p.mouseY + yoff, val, val);
            }
            p.fill(255);
            p.ellipse(p.mouseX, p.mouseY, 2, 2);
        }
    }

    public void setColor(String input){
        String[] split = input.split(" ");
        r = Integer.parseInt(split[0]);
        g = Integer.parseInt(split[1]);
        b = Integer.parseInt(split[2]);
        o = Integer.parseInt(split[3]);
    }

    public void setCC(Boolean b){
        cc = b;
    }
}
