import processing.core.PApplet;
import processing.core.PConstants;

/**
 * Created by Gøran on 26.03.2015.
 */
public class SquareShape extends Brush {

    /***
     * Constructor for class SquareShape.
     *
     * @param input - PApplet (instance from MyApplet)
     */
    SquareShape(PApplet input){
        super(input);
    }

    /***
     * Draws squares. Either solid, or hollow
     * (hollow as in, we draw another square slightly
     * smaller, with the color of the background on top
     * of the solid square).
     */
    public void drawSquares(){
        if (p.mousePressed == true && p.mouseButton == PConstants.LEFT) {
            p.strokeWeight((float) 0.1);
            if(!this.cc) { p.fill(p.random(255), p.random(255), p.random(255), 127); }
            if(this.cc) { p.fill(p.random(this.r), p.random(this.g), p.random(this.b), p.random(this.o)); }
            p.scale(p.random(3));
            p.rect(p.mouseX - 25, p.mouseY - 25, p.random(10, 30), p.random(10, 30));
        }

        if (p.mousePressed == true && p.mouseButton == PConstants.RIGHT) {
            p.strokeWeight((float) 0.1);
            p.fill(p.random(255), p.random(255), p.random(255), 127);
            p.rect(p.mouseX, p.mouseY, p.random(10, 30), p.random(10, 30));
            p.fill(255);
            p.rect(p.mouseX + 10, p.mouseY + 10, p.random(5, 10), p.random(5, 10));
        }
    }


}
