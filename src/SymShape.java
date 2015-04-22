import processing.core.PApplet;

/***
 * Class SymShape draws in symmetry.
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
     * Draws ellipses in symmetry.
     */
    public void display(){
        p.noStroke();
        if(!cc) { p.fill(p.random(255), p.random(255), p.random(255), 127); }
        if(cc) { p.fill(p.random(r), p.random(g), p.random(b), p.random(o)); }
        p.ellipse(p.mouseX, p.mouseY, 15, 15);
        p.ellipse(p.mouseY, p.mouseX, 15, 15);
        p.ellipse(w - p.mouseX, h - p.mouseY, 15, 15);
        p.ellipse(w - p.mouseY, h - p.mouseX, 15, 15);
    }


}
