import processing.core.*;

import java.awt.Color;
import java.util.ArrayList;

class Ball {
    private static Application a;
    private int sWidth, sHeight;
    float x;
    float y;
    float size;
    float speedX;
    float speedY;
    ArrayList<Ball> ballList = new ArrayList();
    PApplet p;

    int r, g, b, o;
    boolean cc = true; //Custom color

    /***
     * Empty (no parameter) constructor for class Ball.
     */
    Ball(){
    }

    /***
     * Constructor for class Ball, taking the ball's direction
     * as parameters to force it in that specific direction.
     *
     * @param xDir - (float) The X-direction of the ball.
     * @param yDir - (float) The Y-direction of the ball.
     */
    Ball(float xDir, float yDir) {
        this.speedX = xDir;
        this.speedY = yDir;
        this.size = 10;
    }

    public void move() {
        if (x + size / 2f > sWidth || x - size / 2f < 0) speedX = -speedX;
        if (y + size / 2f > sHeight || y - size / 2f < 0) speedY = -speedY;
        x += speedX;
        y += speedY;
    }

    public void display() {
        p.stroke(p.random(255), p.random(255), p.random(255));
        if(cc == true) { p.fill(p.random(255), p.random(255), p.random(255), 127); }
        if(cc == false) { p.fill(p.random(r), p.random(g), p.random(b), p.random(o)); }
        p.ellipse(x, y, size, size);
    }

    /**
     * Creates multiple balls, with various directions.
     */
    public void initializeBalls() {
        // X and Y speed to make the ball go in desired direction.
        float xDir, yDir;
        Ball up, down, left, right, left45, negleft45, right45, negright45;
        // UP
        xDir = 0;
        yDir = -10;
        up = new Ball(xDir, yDir);

        // DOWN
        xDir = 0;
        yDir = 5;
        down = new Ball(xDir, yDir);

        // LEFT
        xDir = -5;
        yDir = 0;
        left = new Ball(xDir, yDir);

        // RIGHT
        xDir = 5;
        yDir = 0;
        right = new Ball(xDir, yDir);

        // 45 degrees left
        xDir = -5;
        yDir = -5;
        left45 = new Ball(xDir, yDir);

        // negative 45 degrees left
        xDir = -5;
        yDir = 5;
        negleft45 = new Ball(xDir, yDir);

        // 45 degrees right
        xDir = 5;
        yDir = -5;
        right45 = new Ball(xDir, yDir);

        // negative 45 degrees right
        xDir = 5;
        yDir = 5;
        negright45 = new Ball(xDir, yDir);

        ballList.add(up);
        ballList.add(down);
        ballList.add(left);
        ballList.add(right);
        ballList.add(left45);
        ballList.add(negleft45);
        ballList.add(right45);
        ballList.add(negright45);
    }


    private void setIX(){
        x = p.random(size, sWidth - size);
    }
    private void setIY(){
        y = p.random(size, sWidth - size);
    }


    public ArrayList<Ball> getBallList() {
        return ballList;
    }

    public void clearBallList(){
        ballList.clear();
    }

    /**
     * Since every ball has to know about the PApplet,
     * and since they all need a starting point (x and y).
     * This method gives all balls that information.
     * @param input - The PApplet exported from main class (MyApplet).
     */
    public void setPapp(PApplet input, Application app) {
        for(Ball b : ballList){
            b.p = input;
            b.a = app;
            b.sWidth = app.panel.getWidth();
            b.sHeight = app.panel.getHeight();
            b.setIX(); b.setIY();
        }

    }

    public void setColor(String input){
        String[] split = input.split(" ");
        r = Integer.parseInt(split[0]);
        g = Integer.parseInt(split[1]);
        b = Integer.parseInt(split[2]);
        o = Integer.parseInt(split[3]);
    }

    public void setCC(Boolean b){
        cc = b;
    }

}