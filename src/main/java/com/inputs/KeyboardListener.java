package com.inputs;

import static com.salim.GameStates.EDIT_LEVEL;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
// import static com.salim.GameStates.*;

import com.salim.Game;
import com.salim.GameStates;


public class KeyboardListener implements KeyListener{

    private Game game;

    public KeyboardListener(Game game){
        this.game= game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(GameStates.gameState==EDIT_LEVEL){
            game.getLevelEditor().keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
