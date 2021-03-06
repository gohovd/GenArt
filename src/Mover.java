import processing.core.*;
import java.util.ArrayList;

/**
 * Makes a vector that moves in a predefined pattern.
 * @author Gruppe 6
 * @version 1.0, April 2015
 */

class Mover extends Brush {

    PVector location;
    PVector velocity;
    PVector acceleration;
    float topspeed;
    float rad = 10;
    float vWidth, vHeight;
    ArrayList<Mover> movers = new ArrayList();
    float incTorq = (float) 0.19;

    /***
     * Constructor for class Mover.
     * @param input PApplet - PApplet instance from MyApplet.
     */
    Mover(PApplet input) {
        super(input);
        location = new PVector(0, 0);
        velocity = new PVector(0, 0);
        topspeed = 22;

    }

    /***
     * The display method for class Mover,
     * this is where the vectors (in the shape of ellipse)
     * are drawn.
     */
    void display() {
        p.noStroke();
        if(!this.cc) { p.fill(p.random(255), p.random(255), p.random(255), 127); }
        if(this.cc) { p.fill(p.random(this.r), p.random(this.g), p.random(this.b), p.random(this.o)); }
        p.ellipse(location.x, location.y, p.random(2, 10), p.random(2, 10));
    }

    /***
     * Creates a mover. Its topspeed and radius incrementation speed
     * is set by the parameters. Also collects the width and height of the
     * canvas (panel) to make the checkEdges method work properly.
     *
     * @param ts - float Topspeed
     * @param inc - float The rate at which the radius of the circle motion grows.
     */
    public void createNewMover(float ts, float inc) {
        Mover nMov = new Mover(p);
        nMov.topspeed = ts;
        nMov.incTorq = inc;
        nMov.vWidth = Application.panel.getWidth();
        nMov.vHeight = Application.panel.getHeight();
        movers.add(nMov);
    }

    /***
     * Method for updating the location of the vector.
     * The vectors either move purely linearly, or circularly,
     * which is decided by the int parameter.
     *
     * @param choice - int
     */
    void update(int choice) {
        //The choice parameter decides the pattern of motion
        //in which the vectors will move.
        //Choice 1: Circular
        //Choice 2: Linear
        PVector aim, dir;
        if (choice == 1) {
            rad += incTorq;
            float xx = p.cos(rad) * 60;
            float yy = p.sin(rad) * 60;
            aim = new PVector(xx, yy);
            dir = PVector.sub(aim, location);  // Find vector pointing towards aim.
            dir.normalize();     // Normalize
            dir.mult((float) 0.5);       // Scale
            acceleration = aim;

        } else if (choice == 2) {
            aim = new PVector(p.mouseX, p.mouseY);
            dir = PVector.sub(aim, location);
            dir.normalize();     // Normalize
            dir.mult((float) 0.5);       // Scale
            acceleration = aim;
        }

        velocity.add(acceleration);
        velocity.limit(topspeed);
        location.add(velocity);


    }

    /***
     * Checks the edges. If the vector goes over the predefined width
     * or height, it wraps around.
     */
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

    /***
     * Returns the location of the vector.
     * @return PVector - location
     */
    public PVector getVecLocation() {
        return location;
    }

    /***
     * Returns the array containing all instances
     * of class Mover
     * @return ArrayList(Mover)
     */
    public ArrayList<Mover> getMovers() {
        return movers;
    }

    /***
     * Clears the arraylist containing
     * all instances of class Mover
     */
    public void clearMovers(){
        movers.clear();
    }

    /***
     * Forcibly changes the locational data
     * of the PVector location for an instance
     * of class Mover
     * @param loc - PVector
     */
    public void setVecLocation(PVector loc) {
        this.location.x = loc.x;
        this.location.y = loc.y;
    }

}