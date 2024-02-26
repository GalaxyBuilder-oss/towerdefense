package com.scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.helperMethods.LevelBuild;
import com.helperMethods.LoadSave;
import com.managers.TileManager;
import com.objects.Tile;
import com.salim.Game;
import com.ui.Toolbar;

public class LevelEditor extends GameScene implements ScenesMethods {

    private int xPos, yPos, width, height, tileSize;
    private boolean drawSelect;

    private TileManager tileManager;
    private Toolbar toolbar;
    private Tile selectedTile;

    public LevelEditor(Game game) {

        super(game);
        xPos=0;
        yPos=640;
        width=640;
        height=50;
        tileSize=32;

        // The Level
        // TileManager
        tileManager = new TileManager();
        toolbar = new Toolbar(xPos, yPos, width, height, this);

        loadDefaultLevel();
    }

    private void loadDefaultLevel() {
        // lvl = LevelBuild.getLevelData();
        lvl = LoadSave.getLevelData("new level.txt");
    }

    public void saveLevel() {
        LoadSave.saveLevel("new level.txt", lvl);
        getGame().getPlaying().setLevel(lvl);
    }

    public void resetLevel(int[][] lvl) {
        this.lvl = lvl;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    @Override
    public void render(Graphics g) {

        updateTick();
        drawLevel(g);
        toolbar.draw(g);
        selectedTile(g);
    }

    private void selectedTile(Graphics g) {
        if (selectedTile != null && drawSelect) {
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, tileSize, tileSize, null);
            g.setColor(Color.black);
            g.drawRect(mouseX, mouseY, tileSize, tileSize);
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_R && selectedTile != null) {
            toolbar.rotateSprite();
        }
    }

    public void setSelectedTile(Tile tile) {
        selectedTile = tile;
        drawSelect = true;
    }

    private void changeTile(int x, int y) {
        if (selectedTile != null) {
            int tileX = x / tileSize;
            int tileY = y / tileSize;

            if (lastTileX == tileX && lastTileY == tileY && lastTileId == selectedTile.getId())
                return;

            lastTileId = selectedTile.getId();
            lastTileX = tileX;
            lastTileY = tileY;
            lvl[tileY][tileX] = selectedTile.getId();
        }
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (y >= 640) {
            toolbar.mouseClicked(x, y);
        } else {
            changeTile(mouseX, mouseY);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 640) {
            toolbar.mouseMoved(x, y);
            drawSelect = false;
        } else {
            drawSelect = true;
            mouseX = (x / tileSize) * tileSize;
            mouseY = (y / tileSize) * tileSize;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (y >= 640) {
            toolbar.mousePressed(x, y);
        } else {
            changeTile(mouseX, mouseY);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        toolbar.mouseReleased(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {
        if (y >= getGame().getWidth() || x >= 640 || y <= 0 || x <= 0) {

        } else {
            changeTile(x, y);
        }
    }

    public void update() {
    }
}
