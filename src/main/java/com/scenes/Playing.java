package com.scenes;

import java.awt.Graphics;

import com.helperMethods.LoadSave;
import com.managers.EnemyManager;
import com.salim.Game;
import com.ui.ActionBar;

public class Playing extends GameScene implements ScenesMethods {

    private ActionBar actionBar;

    public Playing(Game game) {
        super(game);

        // The Level
        initLevel();

        enemyManager=new EnemyManager(this);
        actionBar = new ActionBar(0, 640, 640, 50, this);
    }

    public void initLevel() {
        lvl = LoadSave.getLevelData("new level.txt");
    }

    public void setLevel(int[][] lvl) {
        this.lvl = lvl;
    }

    public void update(){
        updateTick();
        enemyManager.update();
    }

    @Override
    public void render(Graphics g) {

        update();
        drawLevel(g);
        actionBar.draw(g);
        enemyManager.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (y >= 640) {
            actionBar.mouseClicked(x, y);
        } else {
            // enemyManager.addEnemy(x, y);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 640) {
            actionBar.mouseMoved(x, y);
        } else {
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (y >= 640) {
            actionBar.mousePressed(x, y);
        } else {

        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        actionBar.mouseReleased(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {
        if (y >= 640 || x >= 640 || y <= 0 || x <= 0) {

        } else {
        }
    }

    public int getTileType(int x, int y) {

        int id=lvl[y/32][x/32];
        return getGame().getTileManager().getTile(id).getTileType();
    }
}
