import processing.core.PApplet;

import java.awt.*;

class VarBubbles extends PApplet {



// The simple method variableEllipse() was created specifically
// for this program. It calculates the speed of the mouse
// and draws a small ellipse if the mouse is moving slowly
// and draws a large ellipse if the mouse is moving quickly

    void variableEllipse(int x, int y, int px, int py) {
        variableEllipse(mouseX, mouseY, pmouseX, pmouseY);
        float speed = abs(x-px) + abs(y-py);
        stroke(speed);
        ellipse(x, y, speed, speed);
    }


}