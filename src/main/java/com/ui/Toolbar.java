package com.ui;

import static com.salim.GameStates.*;

import java.awt.*;
import java.util.*;
import java.util.Map.Entry;
import java.awt.image.BufferedImage;

import com.objects.Tile;
import com.helperMethods.LevelBuild;
import com.managers.TileManager;
import com.scenes.LevelEditor;

public class Toolbar extends Bar {

    private NewMyButton bMenu, bSave, bReset;
    private Tile selectedTile;

    private Map<NewMyButton, ArrayList<Tile>> map = new HashMap<NewMyButton, ArrayList<Tile>>();
    private NewMyButton bGrass, bDirthpath, bDirtpathXY, bWater, bWaterEdges, bWaterCorners, bWaterCornersDot;

    private NewMyButton currentButton;
    private int currentIndex;

    private LevelEditor levelEditor;

    public Toolbar(int x, int y, int width, int height, LevelEditor levelEditor) {
        super(x, y, width, height);
        this.levelEditor = levelEditor;

        initButtons();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillRect(x, y, width, height);

        drawButton(g);
    }

    @Override
    protected TileManager getTileManager() {
        return levelEditor.getTileManager();
    }

    @Override
    protected void initButtons() {
        bMenu = new NewMyButton("Menu", 2, 642, 70, 30);
        bSave = new NewMyButton("Save", 496, 642, 70, 30);
        bReset = new NewMyButton("Reset", 568, 642, 70, 30);

        int h = 32;
        int w = 32;
        int xStart = 140;
        int yStart = 642;
        int xOffset = (int) (w * 1.1);
        int i = 0;

        bGrass = new NewMyButton("Grass", xStart, yStart, w, h, i++);
        bWater = new NewMyButton("water", xStart + xOffset, yStart, w, h, i++);

        initMapButton(bDirthpath, levelEditor.getTileManager().dirtpathXY, xStart, yStart, xOffset, w, h, i++);
        initMapButton(bDirtpathXY, levelEditor.getTileManager().dirtpathDirections, xStart, yStart, xOffset, w, h, i++);
        initMapButton(bWaterCorners, levelEditor.getTileManager().water_corners, xStart, yStart, xOffset, w, h, i++);
        initMapButton(bWaterEdges, levelEditor.getTileManager().water_edges, xStart, yStart, xOffset, w, h, i++);
        initMapButton(bWaterCornersDot, levelEditor.getTileManager().water_edges_dot, xStart, yStart, xOffset, w, h,
                i++);
    }

    private void initMapButton(NewMyButton b, ArrayList<Tile> list, int x, int y, int xOffset,
            int width, int height, int index) {
        b = new NewMyButton("", x + xOffset * index, y, width, height, index);
        map.put(b, list);
    }

    @Override
    protected void drawButton(Graphics g) {
        bMenu.draw(g);
        bSave.draw(g);
        bReset.draw(g);

        // drawFileButton(g);
        drawNormalButton(g, bGrass);
        drawNormalButton(g, bWater);
        drawSelectedTile(g);
        drawMapButton(g);
    }

    private void drawNormalButton(Graphics g, NewMyButton b) {

        // Sprites
        g.drawImage(getButtImage(b.getId()), b.x, b.y, b.width, b.height, null);

        // Button Feedback
        drawButtonFeedback(g, b);
    }

    private void drawMapButton(Graphics g) {
        for (Entry<NewMyButton, ArrayList<Tile>> entry : map.entrySet()) {

            NewMyButton b = entry.getKey();
            BufferedImage img = entry.getValue().get(0).getSprite();

            // Sprite
            g.drawImage(img, b.x, b.y, b.width, b.height, null);

            // Button Feedback
            drawButtonFeedback(g, b);
        }
    }

    private void drawButtonFeedback(Graphics g, NewMyButton b) {
        // Mouseover
        if (b.isMouseOver()) {
            g.setColor(Color.white);
        } else {
            g.setColor(Color.black);
        }

        // border
        g.drawRect(b.x, b.y, b.width, b.height);

        // Mousepress
        if (b.isMousePress()) {
            g.drawRect(b.x + 1, b.y + 1, b.width - 2, b.height - 2);
            g.drawRect(b.x + 2, b.y + 2, b.width - 4, b.height - 4);
        }
    }

    @Override
    protected void drawSelectedTile(Graphics g) {
        if (selectedTile != null) {
            g.drawImage(selectedTile.getSprite(), 86, 644, 42, 42, null);
        }
    }

    @Override
    protected BufferedImage getButtImage(int id) {
        return getTileManager().getSprite(id);
    }

    public void saveLevel() {
        levelEditor.saveLevel();
    }

    public void resetLevelEditor() {
        levelEditor.resetLevel(LevelBuild.getLevelData());
    }

    public void rotateSprite() {

        if (selectedTile != getTileById(bGrass.getId()) && selectedTile != getTileById(bWater.getId())) {
            currentIndex++;
            if (currentIndex >= map.get(currentButton).size()) {
                currentIndex = 0;
            }
            setSelected(map.get(currentButton).get(currentIndex));
        } else {
            setSelected(getTileById(currentButton.getId()));
            currentIndex = 0;
        }
    }

    private Tile getTileById(int tileId) {
        return getTileManager().getTile(tileId);
    }

    private void setSelected(Tile tile) {
        selectedTile = tile;
        levelEditor.setSelectedTile(selectedTile);
    }

    private void setRotate(NewMyButton currentButton) {
        this.currentButton = currentButton;
        this.currentIndex = 0;
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            setSelected(null);
            resetLevelEditor();
            setGameState(MENU);
        } else if (bSave.getBounds().contains(x, y)) {
            setSelected(null);
            saveLevel();
        } else if (bReset.getBounds().contains(x, y)) {
            setSelected(null);
            resetLevelEditor();
        } else if (bGrass.getBounds().contains(x, y)) {
            setSelected(getTileById(bGrass.getId()));
            setRotate(bGrass);
        } else if (bWater.getBounds().contains(x, y)) {
            setSelected(getTileById(bWater.getId()));
            setRotate(bWater);
        } else {
            for (NewMyButton b : map.keySet()) {
                if (b.getBounds().contains(x, y)) {
                    setSelected(map.get(b).get(0));
                    setRotate(b);
                    return;
                }
            }
        }
    }

    @Override
    public void mouseMoved(int x, int y) {

        for (NewMyButton b : map.keySet())
            b.setMouseOver(false);
        bMenu.setMouseOver(false);
        bSave.setMouseOver(false);
        bReset.setMouseOver(false);
        bGrass.setMouseOver(false);
        bWater.setMouseOver(false);

        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMouseOver(true);
        } else if (bSave.getBounds().contains(x, y)) {
            bSave.setMouseOver(true);
        } else if (bReset.getBounds().contains(x, y)) {
            bReset.setMouseOver(true);
        } else if (bGrass.getBounds().contains(x, y)) {
            bGrass.setMouseOver(true);
        } else if (bWater.getBounds().contains(x, y)) {
            bWater.setMouseOver(true);
        } else {
            for (NewMyButton b : map.keySet()) {
                if (b.getBounds().contains(x, y)) {
                    b.setMouseOver(true);
                    return;
                }
            }
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMousePressed(true);
        else if (bSave.getBounds().contains(x, y))
            bSave.setMousePressed(true);
        else if (bReset.getBounds().contains(x, y))
            bReset.setMousePressed(true);
        else if (bGrass.getBounds().contains(x, y))
            bGrass.setMousePressed(true);
        else if (bWater.getBounds().contains(x, y))
            bWater.setMousePressed(true);
        else {
            for (NewMyButton b : map.keySet()) {
                if (b.getBounds().contains(x, y)) {
                    b.setMousePressed(true);
                    return;
                }
            }
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        for (NewMyButton b : map.keySet()) {
            b.resetBooleans();
        }
        bMenu.resetBooleans();
        bSave.resetBooleans();
        bReset.resetBooleans();
        bGrass.resetBooleans();
        bWater.resetBooleans();
    }

}
