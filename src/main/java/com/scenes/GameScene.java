package com.scenes;

import com.managers.EnemyManager;
import com.salim.Game;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class GameScene {

    private Game game;
    protected EnemyManager enemyManager;

    protected int[][] lvl;

    // Default Speed = 20, more bigger value more slower
    protected int animationIndex, tick, ANIMATION_SPEED=20;
    protected int mouseX, mouseY, lastTileX, lastTileY, lastTileId;

    protected void updateTick() {
        tick++;
        if (tick >= ANIMATION_SPEED) {
            tick = 0;
            animationIndex++;
            if (animationIndex >= 4)
                animationIndex = 0;
        }
    }

    protected void drawLevel(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {

            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                if (isAnimation(id)) {
                    g.drawImage(getSprite(id, animationIndex), x * 32, y * 32, null);
                } else
                    g.drawImage(getSprite(id), x * 32, y * 32, null);
            }
        }
    }

    protected BufferedImage getSprite(int spriteId, int animationIndex) {

        return getGame().getTileManager().getAniSprite(spriteId, animationIndex);
    }

    protected BufferedImage getSprite(int spriteID) {
        return getGame().getTileManager().getSprite(spriteID);
    }

    protected boolean isAnimation(int spriteId) {
        return getGame().getTileManager().isSpriteAnimated(spriteId);
    }
    
    public GameScene(Game game){
        this.game=game;
        
    }
    public Game getGame(){
        return game;
    }
}
