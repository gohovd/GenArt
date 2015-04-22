
import java.awt.Color;
import javax.swing.JColorChooser;

/**
 * Drawing tool - ColorPicker
 * @author Gruppe 6
 * @version 1.0, April 2015
 */

public class ColorPicker {
    /***
     * Creates global variables (r,g,b an c)
     */
    int r,g,b;
    Color c;

    public void color() {
        JColorChooser cc = new JColorChooser();
        c = cc.showDialog(null, "Velg farge for rammen", Color.white);

        if(c != null) {
            String colorPick = c.toString();

            System.out.println(c);
            /***
             * Converts toString to only get the RGB values
             */
            colorPick = colorPick.replace("java.awt.Color[r=", "");
            colorPick = colorPick.replace("g=", "");
            colorPick = colorPick.replace("b=", "");
            colorPick = colorPick.replace("]", "");


            /***
             * Spliting the commas and adding the values to the int array
             */
            String[] strArray = colorPick.split(",");
            int[] intArray = new int[strArray.length];
            for (int i = 0; i < strArray.length; i++) {
                intArray[i] = Integer.parseInt(strArray[i]);
            }

            /***
             * Adding RGB values from the int array to the r,g,b global variables
             */
            r = intArray[0];
            g = intArray[1];
            b = intArray[2];

            /***
             * This was just a test if I get the right values
             * Prints the RGB values from the global variables
             */
            //System.out.println("RGB = " + r + " " + g + " " + b);
        }
    }

    /***
     * Getters
     * @return Color, & RGB Values
     */

    public Color getC() { return c; }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }
}
