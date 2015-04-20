import processing.core.PApplet;

/**
 * Created by Gøran on 26.03.2015.
 */
public class LineShape {
    
    PApplet p;
    int r, g, b, o;
    boolean cc = true; //Custom color
    
    LineShape(PApplet input){
        p = input;
    }
    
    public void drawLines(){
        if (p.mousePressed && (p.mouseButton == p.LEFT)) {
            p.strokeWeight(p.random(3, 8));
            p.stroke(p.random(0, 255), p.random(0, 255), p.random(0, 255), p.random(0, 255));
            p.line(p.mouseX - p.random(-100, 100), p.mouseY, p.mouseX + p.random(-100, 100), p.mouseY);
        } else if (p.mousePressed && (p.mouseButton == p.RIGHT)) {
            p.strokeWeight(p.random(3, 8));
            if(cc == true) { p.stroke(p.random(0, 255), p.random(0, 255), p.random(0, 255), p.random(0, 255)); }
            if(cc == false) { p.stroke(p.random(r), p.random(g), p.random(b), p.random(o)); }
            p.line(p.mouseX, p.mouseY - p.random(-100, 100), p.mouseX, p.mouseY + p.random(-100, 100));
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
