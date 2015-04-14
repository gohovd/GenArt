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
    PApplet p;
    ArrayList<Mover> movers = new ArrayList();
    float incTorq = (float) 0.19;

    Mover() {
        location = new PVector(0, 0);
        velocity = new PVector(0, 0);
        topspeed = 22;

    }

    public void setPapp(PApplet input, Application a) {
        for(Mover m : movers){
            m.p = input;
            m.a = a;
        }

    }

    void display() {
        p.noStroke();
        p.fill(random(255), random(255), random(255), random(255));
        p.ellipse(location.x, location.y, random(2, 10), random(2, 10));
    }

    public void createNewMover(float ts, float inc) {
        Mover nMov = new Mover();
        nMov.topspeed = ts;
        nMov.incTorq = inc;
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
            r += incTorq;
            //r += .19;
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
        this.location.x = loc.x;
        this.location.y = loc.y;
    }
}