package com.managers;

import com.enemies.Enemy;
import com.helperMethods.LoadSave;
import com.scenes.Playing;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static com.helperMethods.Constants.Direction.*;
import static com.helperMethods.Constants.Tiles.*;

public class EnemyManager {

    private Playing playing;

    private BufferedImage[] enemyImages;

    private ArrayList<Enemy> enemies = new ArrayList<>();

    private float speed = 0.5f;
    private boolean isMove=true;
    // private Enemy testEnemy;

    public EnemyManager(Playing playing) {

        this.playing = playing;

        // Enemy Total in sprite
        enemyImages = new BufferedImage[4];

        // testEnemy = new Enemy(32 * 4, 32 * 8, 0, 0);
        addEnemy(7 * 32, 9 * 32);
        loadEnemyImages();
    }

    public void addEnemy(int x, int y) {
        enemies.add(new Enemy((float) x, (float) y, 0, 0));
    }

    private void loadEnemyImages() {

        BufferedImage atlas = LoadSave.getSpriteAtlas();

        enemyImages[0] = atlas.getSubimage(0 * 32, 32, 32, 32);
        enemyImages[1] = atlas.getSubimage(1 * 32, 32, 32, 32);
        enemyImages[2] = atlas.getSubimage(2 * 32, 32, 32, 32);
        enemyImages[3] = atlas.getSubimage(3 * 32, 32, 32, 32);
    }

    public void update() {

        for (Enemy e : enemies) {

            // if next tile road, given enemy pos and direction
            if (ifIsNextTilePath(e)) {
                // move enemy
            }
            // enemy.move(0.5f, 0);
        }
    }

    private boolean ifIsNextTilePath(Enemy e) {

        // enemy pos

        // enemy dir

        // tile at new possible position
        int newX = (int) (e.getX() + getSpeedWidth(e.getLastDirection()));
        int newY = (int) (e.getY() + getSpeedHeight(e.getLastDirection()));

        if (getTileType(newX, newY) == GRASS) {

            // if moving same direction
            // System.out.println("Move");
            e.move(speed, e.getLastDirection());
        } else if (isAtEnd(e)) {
        } else {
            // find new direction
            setNewDirectionMove(e);
        }
        return false;
    }

    private void setNewDirectionMove(Enemy e) {

        int dir = e.getLastDirection();

        // move into current type
        int xCord = (int) e.getX() / 32;
        int yCord = (int) e.getY() / 32;

        fixEnemyOffset(e, dir, xCord, yCord);

        if(isAtEnd(e))
            return;

        if (dir == RIGHT || dir == LEFT) {

            // System.out.println("Right");
            int newY = (int) (e.getY() + getSpeedHeight(UP));
            if (getTileType((int) e.getX(), newY) == ROAD)
                e.move(speed, UP);
            else
                e.move(speed, LEFT);
        } else {

            // System.out.println("Down");
            int newX = (int) (e.getX() + getSpeedWidth(RIGHT));
            if (getTileType(newX, (int) e.getY()) == ROAD)
                e.move(speed, RIGHT);
            else
                e.move(speed, LEFT);
        }
        // isMove=false;
    }

    private void fixEnemyOffset(Enemy e, int dir, int xCord, int yCord) {
        switch (dir) {
            // case LEFT:
            //     if (xCord > 0) {
            //         xCord--;
            //     }
            //     break;
            // case UP:
            //     if (yCord > 0) {
            //         yCord--;
            //     }
            //     break;
            case RIGHT:
                if (xCord < 19) {
                    xCord++;
                }
                break;
            case DOWN:
                if (yCord < 19) {
                    yCord++;
                }
                break;
        }
        e.setPos(xCord * 32, yCord * 32);
    }

    private boolean isAtEnd(Enemy e) {

        return false;
    }

    private int getTileType(int x, int y) {
        return playing.getTileType(x, y);
    }

    private float getSpeedWidth(int lastDirection) {

        if (lastDirection == LEFT)
            return -speed;
        else if (lastDirection == RIGHT)
            return speed+32;
        return 0;
    }

    private float getSpeedHeight(int lastDirection) {

        if (lastDirection == UP)
            return -speed;
        else if (lastDirection == DOWN)
            return speed+32;
        return 0;
    }

    public void draw(Graphics g) {

        for (Enemy enemy : enemies) {

            drawEnemy(g, enemy);
        }
    }

    private void drawEnemy(Graphics g, Enemy e) {

        g.drawImage(enemyImages[0], (int) e.getX(), (int) e.getY(), null);
    }

    public Playing getPlaying() {
        return playing;
    }
}
