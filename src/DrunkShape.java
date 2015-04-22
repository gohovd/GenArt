import processing.core.PApplet;

public class DrunkShape extends Brush {
    int x, y;

    int h = Application.panel.getHeight();
    int w = Application.panel.getWidth();

    int x1 = w/2;
    int y1 = h/2;

    public DrunkShape(PApplet input) {
        super(input);

    }

    public void display() {
        float rr = p.random(0, 255);
        float gg = p.random(0, 255);
        float bb = p.random(0, 255);
        y = y1 + (h / 70) - Math.round(p.random(h / 33));
        x = x1 + (w / 70) - Math.round(p.random(w / 33));
        p.strokeWeight(2);
        //fill(r,g,b);
        if(!cc) { p.stroke(rr, gg, bb); }
        if(cc) { p.stroke(r, g, b); }
        p.line(x1, y1, x, y);
        x1 = x;
        y1 = y;
    }

    public void setX1(int x){
        x1 = x;
    }

    public void setY1(int y){
        y1 = y;
    }
}
