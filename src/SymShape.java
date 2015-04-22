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
    boolean first = true;

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
        p.stroke(p.random(255), p.random(255), p.random(255), 180);
        if(first) {
            X = w/2; Y = h/2;
            nX = w/2; nY = h/2;
            pX = w/2; pY = h/2;
            first = false; }
        if(!first) {
            X += (nX - X) / delay;
            Y += (nY - Y) / delay;
        }
        nX = p.mouseX;
        nY = p.mouseY;
        p.line(X, Y, pX, pY);
        p.line(Y, X, pY, pX);
        pX = X;
        pY = Y;
    }


}
