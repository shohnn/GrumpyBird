package com.kaze.gameobjects;

public class Cesped  extends Scrollable{
    public Cesped(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
    }
    
    public void onRestart(float x, float scrollSpeed) {
        position.x = x;
        velocity.x = scrollSpeed;
    }
    
}
