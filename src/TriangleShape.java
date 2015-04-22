import processing.core.PApplet;

/**
 * Created by Gøran on 26.03.2015.
 */
public class TriangleShape extends Brush {

    float x1, y1, x2, y2, x3, y3;

    TriangleShape(PApplet input) {
        super(input);
    }

    /***
     * Draws triangles with unique/random angles, colors & 50 percent alpha (transparent)
     */

    public void drawTriangles() {
        if (p.mousePressed) {
            //p.frameRate(20);
            p.strokeWeight((float) 0.1);
            if(!this.cc) { p.fill(p.random(255), p.random(255), p.random(255), 127); }
            if(this.cc) { p.fill(p.random(this.r), p.random(this.g), p.random(this.b), p.random(this.o)); }
            x1 = p.random(p.mouseX - p.random(80, 100), p.mouseX + p.random(80, 100));
            y1 = p.random(p.mouseY - p.random(80, 100), p.mouseY + p.random(80, 100));
            x2 = p.random(p.mouseX - p.random(60, 80), p.mouseX + p.random(80, 100));
            y2 = p.random(p.mouseY - p.random(60, 80), p.mouseY + p.random(80, 100));
            x3 = p.random(p.mouseX - p.random(60, 80), p.mouseX + p.random(80, 100));
            y3 = p.random(p.mouseY - p.random(60, 80), p.mouseY + p.random(80, 100));
            p.triangle(x1, y1, x2, y2, x3, y3);
            
        }
    }
}
