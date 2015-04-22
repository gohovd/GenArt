import processing.core.PApplet;

/**
 * Drawing tool - Triangle
 * @author Gruppe 6
 * @version 1.0, April 2015
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
            if(!cc) { p.fill(p.random(255), p.random(255), p.random(255), 127); }
            if(cc) { p.fill(p.random(r), p.random(g), p.random(b), p.random(o)); }
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
