import processing.core.PApplet;
import processing.core.PConstants;

/**
 * Created by Gøran on 26.03.2015.
 */
public class CrossShape extends Brush{

    int i = 0;
    boolean q = false;
    
    CrossShape(PApplet input){
        super(input);
    }
    
    public void drawCross(){
        if (p.mousePressed && (p.mouseButton == PConstants.LEFT)) {
            if (i >= 0 && q == false) {
                i += 1;
            }
            p.ellipse(p.mouseX + i, p.mouseY, 10, 10);
            p.ellipse(p.mouseX - i, p.mouseY, 10, 10);
            p.ellipse(p.mouseX, p.mouseY + i, 10, 10);
            p.ellipse(p.mouseX, p.mouseY - i, 10, 10);
            if(!this.cc) {
                for (int i = 0; i < 100; i++) {
                    float red = p.random(0, 255);
                    float green = p.random(0, 255);
                    float blue = p.random(0, 255);
                    p.noStroke();
                    p.fill(red, green, blue);
                }
            }
            if(this.cc){
                for (int i = 0; i < 100; i++) {
                    float red = p.random(0, this.r);
                    float green = p.random(0, this.g);
                    float blue = p.random(0, this.b);
                    p.noStroke();
                    p.fill(red, green, blue);
                }
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
}
