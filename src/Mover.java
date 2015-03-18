import processing.core.*;

/**
 * Second inner class 'Mover'. Makes something
 * similar to the bouncing balls, really. Supposed to be several vectors
 * moving in a circle or another pattern of motion.
 */
class Mover extends PApplet {

    PVector location;
    PVector velocity;
    PVector acceleration;
    float topspeed;
    float r = 10;
    float vWidth, vHeight;


    Mover() {
        location = new PVector(0, 0);
        velocity = new PVector(0, 0);
        topspeed = 35;

    }

    void update() {
        // Our algorithm for calculating acceleration:
        //topspeed += .01;

        r += .19;
        float xx = cos(r) * 60;
        float yy = sin(r) * 60;
        PVector aim = new PVector(xx, yy);
        PVector dir = PVector.sub(aim, location);  // Find vector pointing towards aim.

        dir.normalize();     // Normalize
        dir.mult((float) 0.5);       // Scale
        acceleration = aim;

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

    public void setWidth(float w){
        vWidth = w;
        location.x = vWidth/2;
    }
    public void setHeight(float h){
        vHeight = h;
        location.y = vHeight/2;
    }
    public void setVecLocation(PVector loc){
        location = loc;
    }
}