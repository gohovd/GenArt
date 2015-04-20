import processing.core.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Makes a vector that moves in a predefined pattern.
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
    int red, g, b, o;
    boolean cc = true; //Custom color

    /***
     * Constructor for class Mover.
     */
    Mover() {
        location = new PVector(0, 0);
        velocity = new PVector(0, 0);
        topspeed = 22;

    }

    /***
     * Collects the PApplet instance from class MyApplet,
     * and iterates through all existing instances of class Mover
     * and gives the PApplet to each object.
     * Also collects the Application instance from class MyApplet,
     * and gives that as well to every mover in existance.
     *
     * @param input - PApplet instance
     * @param a - Application instance
     */
    public void setPapp(PApplet input, Application a) {
        for(Mover m : movers){
            m.p = input;
            m.a = a;
        }

    }

    /***
     * The display method for class Mover,
     * this is where the vectors (in the shape of ellipse)
     * are drawn.
     */
    void display() {
        p.noStroke();
        if(cc == true) { p.fill(p.random(255), p.random(255), p.random(255), 127); }
        if(cc == false) { p.fill(p.random(red), p.random(g), p.random(b), p.random(o)); }
        p.ellipse(location.x, location.y, random(2, 10), random(2, 10));
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
        Mover nMov = new Mover();
        nMov.topspeed = ts;
        nMov.incTorq = inc;
        nMov.width = a.panel.getWidth();
        nMov.vHeight = a.panel.getHeight();
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
            r += incTorq;
            float xx = cos(r) * 60;
            float yy = sin(r) * 60;
            aim = new PVector(xx, yy);
            dir = PVector.sub(aim, location);  // Find vector pointing towards aim.
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

    public void setColor(String input){
        String[] split = input.split(" ");
        red = Integer.parseInt(split[0]);
        g = Integer.parseInt(split[1]);
        b = Integer.parseInt(split[2]);
        o = Integer.parseInt(split[3]);
    }

    public void setCC(Boolean b){
        cc = b;
    }
}