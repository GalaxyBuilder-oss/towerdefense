package com.ui;

import static com.salim.GameStates.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.managers.TileManager;
import com.scenes.Playing;

public class ActionBar extends Bar {

    private NewMyButton bMenu;
    private Playing playing;

    public ActionBar(int x, int y, int width, int height, Playing playing) {
        
        super(x, y, width, height);
        this.playing = playing;

        // initiate CostumButton
        initButtons();
    }

    @Override
    public void draw(Graphics g) {

        g.setColor(Color.MAGENTA);
        g.fillRect(x, y, width, height);

        drawButton(g);
    }

    @Override
    protected void initButtons() {
        
        bMenu = new NewMyButton("Menu", 2, 642, 70, 30);
    }

    @Override
    protected TileManager getTileManager(){
        return playing.getGame().getTileManager();
    }

    @Override
    protected void drawButton(Graphics g) {

        bMenu.draw(g);
    }

    @Override
    protected void drawSelectedTile(Graphics g) {

    }


    @Override
    protected BufferedImage getButtImage(int id) {
        return getTileManager().getSprite(id);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            setGameState(MENU);
        } else {
            
        }
    }

    @Override
    public void mouseMoved(int x, int y) {

        bMenu.setMouseOver(false);
        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMouseOver(true);
        } else {
            
        }
    }

    @Override
    public void mousePressed(int x, int y) {

        if (bMenu.getBounds().contains(x, y))
            bMenu.setMousePressed(true);
        else {
            
        }
    }

    @Override
    public void mouseReleased(int x, int y) {

        bMenu.resetBooleans();
    }
}
