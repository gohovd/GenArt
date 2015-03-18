import processing.core.*;

import java.awt.Color;

/*
    * simple inner class Ball
    * balls have a position, speed, size and color
    * the basic constructor assign random values to all properties
    *
    * balls can move
    * balls can display themselves
    */
class Ball extends PApplet {
    float x;
    float y;
    float size;
    float speedX;
    float speedY;
    Color color;
    float sWidth, sHeight;

    Ball() {
        this.speedX = random(-2, 2) * 3;
        this.speedY = random(-2, 2) * 3;
        this.color = new Color(random(1), random(1), random(1));
        this.size = random(10, 30);
    }

    public void move() {
        if (x + size / 2f > sWidth || x - size / 2f < 0) speedX = -speedX;
        if (y + size / 2f > sHeight || y - size / 2f < 0) speedY = -speedY;
        x += speedX;
        y += speedY;
    }


    public float getLocX() {
        return x;
    }

    public Color getBallColor(){
        return color;
    }

    public float getLocY() {
        return y;
    }

    public float getBallSize() {
        return size;
    }

    public void setWidth(float w){
        sWidth = w;
        x = random(size, sWidth - size);
    }
    public void setHeight(float h){
        sHeight = h;
        y = random(size, sHeight - size);
    }

}