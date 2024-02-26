package com.enemies;

import java.awt.Rectangle;
import static com.helperMethods.Constants.Direction.*;

public class Enemy {

    private float x, y;

    private Rectangle bounds;
    private int iD, health, enemyType;
    private int lastDirection;

    public Enemy(float x, float y, int iD, int enemyType) {

        this.x = x;
        this.y = y;
        this.iD = iD;
        this.enemyType = enemyType;
        bounds = new Rectangle((int) x, (int) y, 32, 32);
        lastDirection = RIGHT;
    }

    public void move(float speed, int direction) {

        lastDirection=direction;
        switch (direction) {
            case LEFT:
                this.x -= speed;
                break;
            case UP:
                this.y -= speed;
                break;
            case RIGHT:
                this.x += speed;
                break;
            case DOWN:
                this.y += speed;
                break;
        }
    }

    public void setPos(int xPos, int yPos){
        // Position Fix
        x=(float)xPos;
        y=(float)yPos;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getiD() {
        return iD;
    }

    public int getHealth() {
        return health;
    }

    public int getEnemyType() {
        return enemyType;
    }

    public int getLastDirection() {
        return lastDirection;
    }

}
