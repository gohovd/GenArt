import processing.core.PApplet;


/**
 * Created by Gøran on 26.03.2015.
 */
public class BubbleShape {
    
    PApplet p;

    int r, g, b, o;
    boolean cc = true; //Custom color
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
            if(cc == true) { p.fill(p.random(255), p.random(255), p.random(255), 127); }
            if(cc == false) { p.fill(p.random(r), p.random(g), p.random(b), p.random(o)); }
            p.stroke(speed);
            p.ellipse(x, y, speed, speed);
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
