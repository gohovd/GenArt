import processing.core.PApplet;

/***
 * The parent/super class to all brushes (art-methods).
 */
public class Brush {

    public PApplet p;
    public int r, g, b, o;
    public boolean cc;

    public Brush(PApplet input){
        p = input;
    }

    /***
     *
     * @param red int - Degree of red (0-255)
     * @param green int - Degree of green (0-255)
     * @param blue int - Degree of blue (0-255)
     * @param opacity int - Degree of opacity (0-255)
     */
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
