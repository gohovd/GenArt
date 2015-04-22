import processing.core.PApplet;

/***
 * Class SymShape draws in symmetry.
 */
public class SymShape extends Brush{
    
    int h = Application.panel.getHeight(); //Height of the canvas.
    int w = Application.panel.getWidth(); //Width of the canvas.
    int X, Y;
    int nX, nY;
    int pX, pY;
    int delay = 4;
    boolean first = true; //Holds whether or not it's the first time it's trying to draw.

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
        p.noFill();
        if(!cc) { p.stroke(p.random(255), p.random(255), p.random(255), 127); }
        if(cc) { p.stroke(p.random(r), p.random(g), p.random(b), p.random(o)); }
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
