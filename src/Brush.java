import processing.core.PApplet;

/***
 * The parent/super class to all brushes (art-methods).
 */
public class Brush {

    public PApplet p; //PApplet instance from class MyApplet.
    public int r, g, b, o; //The four ints containing the various color values.
    public boolean cc; //CC (Custom Color) stores whether or not a custom color should be used.

    /***
     * Constructor for class Brush.
     * @param input PApplet - instance from MyApplet.
     */
    public Brush(PApplet input){
        p = input;
    }

    /***
     * Sets the color for all brushes (art-methods), by taking
     * in four integers as parameters.
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

    /***
     * CC stands for custom color, and is a boolean that keeps track
     * of whether or not the user/robot wants a 'custom color' or not.
     * When set to true, the draw/display methods in sub-classes do not
     * choose a random color, but rather nuances of the user defined color.
     *
     * @param b boolean - CC(Custom Color)
     */
    public void setCC(Boolean b){
        cc = b;
    }

    /***
     * Print data about CC and Color status.
     */
    public void printCC(){
        System.out.println("CC   (" + getClass() + "): " + cc);
        System.out.println("Color(" + getClass() + "): " + r + " " + g + " " + b + " " + o);
    }

}
