import processing.core.PApplet;

/**
 * Created by Gøran on 26.03.2015.
 */
public class CrossShape {
    
    PApplet p;
    int i = 0;
    boolean q = false;
    int r, g, b, o;
    boolean cc = true; //Custom color
    
    CrossShape(PApplet input){
        p = input;
    }
    
    public void drawCross(){
        if (p.mousePressed && (p.mouseButton == p.LEFT)) {
            if (i >= 0 && q == false) {
                i += 1;
            }
            p.ellipse(p.mouseX + i, p.mouseY, 10, 10);
            p.ellipse(p.mouseX - i, p.mouseY, 10, 10);
            p.ellipse(p.mouseX, p.mouseY + i, 10, 10);
            p.ellipse(p.mouseX, p.mouseY - i, 10, 10);
            for (int i = 0; i < 100; i++) {
                float r = p.random(0, 255);
                float g = p.random(0, 255);
                float b = p.random(0, 255);
                p.noStroke();
                p.fill(r, g, b);
            }
            if (i == 100) {
                q = true;

            }
            if (q == true) {
                i -= 1;
            }

            if (i == 0) {
                q = false;
            }
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
