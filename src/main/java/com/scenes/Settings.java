package com.scenes;

import static com.salim.GameStates.*;

import java.awt.Color;
import java.awt.Graphics;

import com.salim.Game;
import com.ui.NewMyButton;

public class Settings extends GameScene implements ScenesMethods{

    private NewMyButton bMenu;

    public Settings(Game game) {
        super(game);
        initButtons();
    }

    private void initButtons() {
        bMenu=new NewMyButton("Menu", 6, 6, 70, 30);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(0, 0, 640, 640);
        drawButton(g);
    }

    private void drawButton(Graphics g) {
        bMenu.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        
        if(bMenu.getBounds().contains(x, y)){
            setGameState(MENU);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        
        bMenu.setMouseOver(false);
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMouseOver(true);
    }

    @Override
    public void mousePressed(int x, int y) {
        
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMousePressed(true);
    }

    @Override
    public void mouseReleased(int x, int y) {
        
        bMenu.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {
        
    }
    
}
