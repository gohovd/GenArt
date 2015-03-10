import javax.swing.*;

import processing.core.PApplet;

public class GUI extends PApplet(){

        void setup() {
        size(640, 480);
        PFrame f = new PFrame(width, height);
        frame.setTitle("first window");
        f.setTitle("second window");
        fill(0);
        }

        void draw() {
        background(255);
        ellipse(mouseX, mouseY, 10, 10);
        s.setGhostCursor(mouseX, mouseY);
        }
    class PFrame extends JFrame {
    public PFrame(int width, int height) {
        setBounds(100, 100, width, height);
        SecondApplet s = new SecondApplet();
        add(s);
        s.init();
        show();
    }
}
    class SecondApplet extends PApplet {
    int ghostX, ghostY;
    public void setup() {
        background(0);
        noStroke();
    }

    public void draw() {
        background(50);
        fill(255);
        ellipse(mouseX, mouseY, 10, 10);
        fill(0);
        ellipse(ghostX, ghostY, 10, 10);
    }
    public void setGhostCursor(int ghostX, int ghostY) {
        this.ghostX = ghostX;
        this.ghostY = ghostY;
    }
}