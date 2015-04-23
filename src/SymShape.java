import processing.core.PApplet;

/**
 * Drawing tool - SymShape
 * @author Gruppe 6
 * @version 1.0, April 2015
 */
public class SymShape extends Brush{
    
    int h = Application.panel.getHeight(); //Height of the canvas.
    int w = Application.panel.getWidth(); //Width of the canvas.

    /***
     * Constructor for class SymShape.
     * @param input PApplet - instance for class MyApplet.
     */
    public SymShape(PApplet input) {
        super(input);
    }

    /***
     * Display method for class SymShape.
     * Draws in symmetry.
     */
    public void display(){
        p.smooth();
        //p.noFill();
        if(!cc) { p.stroke(p.random(255), p.random(255), p.random(255), 127); }
        if(cc) { p.stroke(p.random(r), p.random(g), p.random(b), 127); }
        int n = 0;
        for(int i = 0; i < 20; i++) {
            p.line(p.mouseX + n, p.mouseY + n, p.pmouseX + n, p.pmouseY + n);
            n++;
        }
        n = 0;
        for(int i = 0; i < 20; i++) {
            p.line(w - p.mouseX+n, p.mouseY+n, w - p.pmouseX+n, p.pmouseY+n);
            n++;
        }

    }


}
