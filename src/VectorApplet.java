import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import processing.core.*;

/**
 * Created by Gøran on 11.03.2015.
 */
public class VectorApplet extends PApplet implements ActionListener{
    // Creating an array of objects.
    ArrayList <Mover> movers;
    PImage bgImg = null;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public void setup() {
        size(screenSize.width-300, screenSize.height);
        movers = new ArrayList<Mover>();
        createNewMover();
        smooth();
    }

    public void draw() {
        if (bgImg == null) {
            background(255);
        } else {
            image(bgImg,0,0, width, height);
        }
        noStroke();
        // Wondering why this is here..
        /*fill(255, 0);
        rect(0, 0, width, height);*/

        // Calling functions of all of the objects in the array.
        for (int i=0; i<movers.size(); i++) {
            Mover mover = movers.get(i);
            mover.update();
            mover.checkEdges();
            mover.display();
        }
    }

    /**
     * implementation from interface ActionListener
     * method is called from the Application
     * the String being compared is the ActionCommand from the button
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("create vector")) {
            createNewMover();
        } else {
            println("actionPerformed(): can't handle " +evt.getActionCommand());
        }
    }

    public void createNewMover(){
        Mover nMov = new Mover();
        movers.add(nMov);
    }

    class Mover {

        PVector location;
        PVector velocity;
        PVector acceleration;
        float topspeed;
        float r = 0;

        Mover() {
            location = new PVector(width/2, height/2);
            velocity = new PVector(0, 0);
            topspeed = 10;
        }

        void update() {


            // Our algorithm for calculating acceleration:
            topspeed += .01;
            r += .19;
            float xx = cos(r)*10;
            float yy = sin(r)*10;
            PVector aim = new PVector(xx, yy);

            PVector dir = PVector.sub(aim, location);  // Find vector pointing towards center
            dir.normalize();     // Normalize
            dir.mult(1);       // Scale
            acceleration = aim;

            // Motion 101!  Velocity changes by acceleration.  Location changes by velocity.
            velocity.add(acceleration);
            velocity.limit(topspeed);
            location.add(velocity);

        }

        void display() {
            noStroke();

            fill(random(255), random(255), random(255), random(255));
            ellipse(location.x, location.y, 10, 10);
        }

        void checkEdges() {

            if (location.x > width) {
                location.x = 0;
            } else if (location.x < 0) {
                location.x = width;
            }

            if (location.y > height) {
                location.y = 0;
            } else if (location.y < 0) {
                location.y = height;
            }
        }
    }

}
