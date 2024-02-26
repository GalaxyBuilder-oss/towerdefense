package com.scenes;

import static com.salim.GameStates.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.salim.Game;
import com.ui.NewMyButton;

public class Menu extends GameScene implements ScenesMethods {

    private NewMyButton bPlaying, bEditLevel, bSettings, bExit;
    int frames, updates;

    public Menu(Game game) {
        super(game);
        initButtons();
    }

    private void initButtons() {

        int w = 100;
        int h = w / 3;
        int x = 640 / 2 - w / 2;
        int y = 150;
        int yOffset = 50;

        bPlaying = new NewMyButton("Play", x, y, w, h);
        bPlaying.setBackgroundColor(Color.green);
        bPlaying.setHoverColor(Color.cyan);

        bEditLevel = new NewMyButton("Edit Level", x, y + yOffset, w, h);
        bEditLevel.setBackgroundColor(Color.green);
        bEditLevel.setHoverColor(Color.cyan);

        bSettings = new NewMyButton("Settings", x, y + yOffset * 2, w, h);
        bSettings.setBackgroundColor(Color.green);
        bSettings.setHoverColor(Color.cyan);

        bExit = new NewMyButton("Exit", x, y + yOffset * 3 + 2, w, h);
        bExit.setBackgroundColor(Color.red);
        bExit.setHoverColor(Color.lightGray);
    }

    private void drawText(Graphics g) {

        String text = "FPS : " + frames + " | UPS : " + updates;
        Font font = new Font(text, Font.TRUETYPE_FONT, 12);
        g.setFont(font);
        g.drawString(text, getGame().getWidth() - 115, 20);
    }

    private void drawButton(Graphics g) {

        bPlaying.draw(g);
        bEditLevel.draw(g);
        bSettings.draw(g);
        bExit.draw(g);
    }

    private void resetButton() {

        bPlaying.resetBooleans();
        bEditLevel.resetBooleans();
        bSettings.resetBooleans();
        bExit.resetBooleans();
    }

    public void showFramePerSeconds(int frames, int updates) {

        this.frames = frames;
        this.updates = updates;
    }

    @Override
    public void render(Graphics g) {
        drawButton(g);
        drawText(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        
        if (bPlaying.getBounds().contains(x, y)) {
            setGameState(PLAYING);
        } else if (bEditLevel.getBounds().contains(x, y)) {
            setGameState(EDIT_LEVEL);
        } else if (bSettings.getBounds().contains(x, y)) {
            setGameState(SETTINGS);
        } else if (bExit.getBounds().contains(x, y)) {
            System.exit(0);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {

        bPlaying.setMouseOver(false);
        bEditLevel.setMouseOver(false);
        bSettings.setMouseOver(false);
        bExit.setMouseOver(false);
        if (bPlaying.getBounds().contains(x, y))
            bPlaying.setMouseOver(true);
        else if (bEditLevel.getBounds().contains(x, y))
            bEditLevel.setMouseOver(true);
        else if (bSettings.getBounds().contains(x, y))
            bSettings.setMouseOver(true);
        else if (bExit.getBounds().contains(x, y))
            bExit.setMouseOver(true);
    }

    @Override
    public void mousePressed(int x, int y) {

        if (bPlaying.getBounds().contains(x, y))
            bPlaying.setMousePressed(true);
        else if (bEditLevel.getBounds().contains(x, y))
            bEditLevel.setMousePressed(true);
        else if (bSettings.getBounds().contains(x, y))
            bSettings.setMousePressed(true);
        else if (bExit.getBounds().contains(x, y))
            bExit.setMousePressed(true);
    }

    @Override
    public void mouseReleased(int x, int y) {

        resetButton();
    }

    @Override
    public void mouseDragged(int x, int y) {

    }
}
