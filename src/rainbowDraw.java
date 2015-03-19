import processing.core.PApplet;

/**
 * Created by Ivan on 19.03.2015.
 */
class rainbowDraw extends PApplet {
    float h=0;
    float s=100;
    float g=100;


    rainbowDraw(){

    }

    void rainbowColor(int x, int y, int px, int py) {
        System.out.println("Ran RainbowDraw)");
        System.out.println("mousex: " + x + " mouse y: " + y + " pmousex: " + px + " pmousey: " + py );
        test(x, y, px, py);
    }

    void test (int a, int b, int c, int d){
        colorMode(255, 100);

        //ellipse(mouseX,mouseY,10,10);
        stroke(h,s,g);

        line(a, b, c, d);
        h+=.5; //make rainbow colors change
        if(h>=100){h=0;}

    }




}
