import processing.core.PApplet;

/**
 * Created by Gøran on 21.04.2015.
 */
public class Brush {

    public PApplet p;
    public int r, g, b, o;
    public boolean cc;

    public Brush(PApplet input){
        p = input;
    }

    public void setColor(int red, int green, int blue, int opacity) {
        r = red;
        g = green;
        b = blue;
        o = opacity;
    }

    public void setCC(Boolean b){
        cc = b;
    }

    public void printCC(){
        System.out.println("CC   (" + getClass() + "): " + cc);
        System.out.println("Color(" + getClass() + "): " + r + " " + g + " " + b + " " + o);
    }

}
