import processing.core.PApplet;
import processing.core.PConstants;

public class StarShape extends Brush {

    StarShape(PApplet input){
        super(input);
        
    }

    public void drawStars() {
        if (p.mousePressed == true) {
            p.strokeWeight((float) 0.1);
            if(!cc) { p.fill(p.random(255), p.random(255), p.random(255), 127); }
            if(cc) { p.fill(p.random(this.r), p.random(this.g), p.random(this.b), p.random(this.o)); }
            //p.scale(p.random(1));
            p.beginShape();
            p.vertex(p.mouseX, p.mouseY - 50);
            p.vertex(p.mouseX + 14, p.mouseY - 20);
            p.vertex(p.mouseX + 47, p.mouseY - 15);
            p.vertex(p.mouseX + 23, p.mouseY + 7);
            p.vertex(p.mouseX + 29, p.mouseY + 40);
            p.vertex(p.mouseX, p.mouseY + 25);
            p.vertex(p.mouseX - 29, p.mouseY + 40);
            p.vertex(p.mouseX - 23, p.mouseY + 7);
            p.vertex(p.mouseX - 47, p.mouseY - 15);
            p.vertex(p.mouseX - 14, p.mouseY - 20);
            p.endShape(PConstants.CLOSE);
        }
    }

    public void drawHearts(){
        if (p.mousePressed == true) {
            p.strokeWeight((float) 0.1);
            p.fill(p.random(255), p.random(255), p.random(255), 127);
            p.beginShape();
            p.vertex(p.mouseX + 150, p.mouseY + 150);
            p.bezierVertex(p.mouseX + 150, p.mouseY + 120, p.mouseX + 100, p.mouseY + 120, p.mouseX + 100, p.mouseY + 150);
            p.bezierVertex(p.mouseX + 100, p.mouseY + 180, p.mouseX + 150, p.mouseY + 185, p.mouseX + 150, p.mouseY + 210);
            p.bezierVertex(p.mouseX + 150, p.mouseY + 185, p.mouseX + 200, p.mouseY + 180, p.mouseX + 200, p.mouseY + 150);
            p.bezierVertex(p.mouseX + 200, p.mouseY + 120, p.mouseX + 150, p.mouseY + 120, p.mouseX + 150, p.mouseY + 150);
            p.endShape();
        }
    }
}
