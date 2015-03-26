import processing.core.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Second inner class 'Mover'. Makes something
 * similar to the bouncing balls, really. Supposed to be several vectors
 * moving in a circle or another pattern of motion.
 */
class Mover extends PApplet {

    private static Application a;
    PVector location;
    PVector velocity;
    PVector acceleration;
    float topspeed;
    float r = 10;
    float vWidth, vHeight;
    float vR, vG, vB, vO;
    PApplet p;
    ArrayList<Mover> movers = new ArrayList();

    Mover() {
        location = new PVector(0, 0);
        velocity = new PVector(0, 0);
        topspeed = 35;

    }

    public void setPapp(PApplet input, Application a){
        p = input;
        this.a = a;
    }

    void display() {
        p.noStroke();
            vR = random(255);
            vG = random(255);
            vB = random(255);
            vO = random(255);

        p.fill(vR, vG, vB, vO);
        p.ellipse(location.x, location.y, random(15, 20), random(15, 20));
    }

    public void createNewMover() {
        Mover nMov = new Mover();
        nMov.width = a.panel.getWidth();
        nMov.vHeight = a.panel.getHeight();
        movers.add(nMov);
    }

    void update(int choice) {
        //The choice parameter decides the pattern of motion
        //in which the vectors will move.
        //Choice 1: Circular
        //Choice 2: Linear
        PVector aim, dir;
        if (choice == 1) {
            r += .19;
            float xx = cos(r) * 60;
            float yy = sin(r) * 60;
            aim = new PVector(xx, yy);
            dir = PVector.sub(aim, location);  // Find vector pointing towards aim.'
            dir.normalize();     // Normalize
            dir.mult((float) 0.5);       // Scale
            acceleration = aim;

        } else if (choice == 2) {
            aim = new PVector(mouseX, mouseY);
            dir = PVector.sub(aim, location);
            dir.normalize();     // Normalize
            dir.mult((float) 0.5);       // Scale
            acceleration = aim;
        }

        // Motion 101!  Velocity changes by acceleration.  Location changes by velocity.
        velocity.add(acceleration);
        velocity.limit(topspeed);
        location.add(velocity);


    }

    void checkEdges() {

        if (location.x > vWidth) {
            location.x = 0;
        } else if (location.x < 0) {
            location.x = vWidth;
        }

        if (location.y > vHeight) {
            location.y = 0;
        } else if (location.y < 0) {
            location.y = vHeight;
        }
    }


    public PVector getVecLocation() {
        return location;
    }

    public ArrayList<Mover> getMovers() {
        return movers;
    }

    public void clearMovers(){
        movers.clear();
    }

    public void setVecLocation(PVector loc) {
        location = loc;
    }
}