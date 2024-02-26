package com.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.managers.TileManager;

public abstract class Bar {
    
    protected int x, y, width, height;

    public Bar(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    public abstract void draw(Graphics g);
    protected abstract TileManager getTileManager();
    protected abstract void initButtons();
    protected abstract void drawButton(Graphics g);
    protected abstract void drawSelectedTile(Graphics g);
    protected abstract BufferedImage getButtImage(int id);

    public abstract void mouseClicked(int x, int y);
    public abstract void mouseMoved(int x, int y);
    public abstract void mousePressed(int x, int y);
    public abstract void mouseReleased(int x, int y);
}
