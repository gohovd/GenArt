import processing.core.PApplet;

/**
 * Created by Gøran on 26.03.2015.
 */
public class SquareShape {
    
    PApplet p;
    int r, g, b, o;
    boolean cc = true; //Custom color

    /***
     * Constructor for class SquareShape.
     *
     * @param input - PApplet (instance from MyApplet)
     */
    SquareShape(PApplet input){
        p = input;
    }

    /***
     * Draws squares. Either solid, or hollow
     * (hollow as in, we draw another square slightly
     * smaller, with the color of the background on top
     * of the solid square).
     */
    public void drawSquares(){
        if (p.mousePressed == true && p.mouseButton == p.LEFT) {
            p.strokeWeight((float) 0.1);
            if(cc == true) { p.fill(p.random(255), p.random(255), p.random(255), 127); }
            if(cc == false) { p.fill(p.random(r), p.random(g), p.random(b), p.random(o)); }
            p.scale(p.random(3));
            p.rect(p.mouseX - 25, p.mouseY - 25, p.random(10, 30), p.random(10, 30));
        }

        if (p.mousePressed == true && p.mouseButton == p.RIGHT) {
            p.strokeWeight((float) 0.1);
            p.fill(p.random(255), p.random(255), p.random(255), 127);
            p.rect(p.mouseX, p.mouseY, p.random(10, 30), p.random(10, 30));
            p.fill(255);
            p.rect(p.mouseX + 10, p.mouseY + 10, p.random(5, 10), p.random(5, 10));
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
