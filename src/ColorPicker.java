
import java.awt.Color;
import javax.swing.JColorChooser;


/**
 * Created by Ivan on 20.04.2015.
 */


public class ColorPicker {
    int r,g,b;
    Color c;

    public void color() {
        JColorChooser cc = new JColorChooser();
        c = cc.showDialog(null, "Velg farge for rammen", Color.white);

        if(c != null) {
            String colorPick = c.toString();

            System.out.println(c);
            //fikser toString sånn at eg får bare int verdier
            colorPick = colorPick.replace("java.awt.Color[r=", "");
            colorPick = colorPick.replace("g=", "");
            colorPick = colorPick.replace("b=", "");
            colorPick = colorPick.replace("]", "");
        /* String to split. */

            //legger til verdier inn i int array list
            String[] strArray = colorPick.split(",");
            int[] intArray = new int[strArray.length];
            for (int i = 0; i < strArray.length; i++) {
                intArray[i] = Integer.parseInt(strArray[i]);
            }

            //Setter inn verdier for rgb
            r = intArray[0];
            g = intArray[1];
            b = intArray[2];

            //Tester om det stemmer
            System.out.println("RGB = " + r + " " + g + " " + b);
        }
    }


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
