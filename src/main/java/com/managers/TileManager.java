package com.managers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.helperMethods.ImgFix;
import com.helperMethods.LoadSave;
import com.objects.Tile;
import static com.helperMethods.Constants.Tiles.*;

public class TileManager {

    public Tile GRASSS, WATERS, DIRTPATHS, DIRTPATH_2, BL_WATER_CORNER, TL_WATER_CORNER, BR_WATER_CORNER,
            TR_WATER_CORNER,
            Y_DIRTPATH, BR_DIRTPATH, BL_DIRTPATH, TR_DIRTPATH, TL_DIRTPATH, BOTTOM_WATER_EDGE, LEFT_WATER_EDGE,
            TOP_WATER_EDGE, RIGHT_WATER_EDGE, TL_WATER_CORNER_DOT, TR_WATER_CORNER_DOT, BL_WATER_CORNER_DOT,
            BR_WATER_CORNER_DOT;
    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();

    public ArrayList<Tile> dirtpathXY = new ArrayList<>();
    public ArrayList<Tile> dirtpathDirections = new ArrayList<>();
    public ArrayList<Tile> water_corners = new ArrayList<>();
    public ArrayList<Tile> water_edges = new ArrayList<>();
    public ArrayList<Tile> water_edges_dot = new ArrayList<>();

    public TileManager() {

        loadAtlas();
        createTiles();
    }

    private void createTiles() {

        int id = 0;

        tiles.add(GRASSS = new Tile(getSprite(9, 0), id++, GRASS));
        tiles.add(WATERS = new Tile(getAnimatedSprite(0, 0), id++, WATER));

        dirtpathXY.add(DIRTPATHS = new Tile(getSprite(8, 0), id++, ROAD));
        dirtpathXY.add(DIRTPATH_2 = new Tile(getRotImage(8, 0, 90), id++, ROAD));

        dirtpathDirections.add(BR_DIRTPATH = new Tile(getSprite(7, 0), id++, WATER));
        dirtpathDirections.add(BL_DIRTPATH = new Tile(getRotImage(7, 0, 90), id++, WATER));
        dirtpathDirections.add(TL_DIRTPATH = new Tile(getRotImage(7, 0, 180), id++, WATER));
        dirtpathDirections.add(TR_DIRTPATH = new Tile(getRotImage(7, 0, -90), id++, WATER));

        water_corners
                .add(BL_WATER_CORNER = new Tile(getRotAnimateSprite(0, 0, 5, 0, 0), id++, WATER));
        water_corners.add(TL_WATER_CORNER = new Tile(getRotAnimateSprite(0, 0, 5, 0, 90), id++, WATER));
        water_corners
                .add(TR_WATER_CORNER = new Tile(getRotAnimateSprite(0, 0, 5, 0, 180), id++, WATER));
        water_corners
                .add(BR_WATER_CORNER = new Tile(getRotAnimateSprite(0, 0, 5, 0, -90), id++, WATER));

        water_edges.add(BOTTOM_WATER_EDGE = new Tile(getRotAnimateSprite(0, 0, 6, 0, 180), id++, WATER));
        water_edges.add(LEFT_WATER_EDGE = new Tile(getRotAnimateSprite(0, 0, 6, 0, -90), id++, WATER));
        water_edges.add(TOP_WATER_EDGE = new Tile(getRotAnimateSprite(0, 0, 6, 0, 0), id++, WATER));
        water_edges.add(RIGHT_WATER_EDGE = new Tile(getRotAnimateSprite(0, 0, 6, 0, 90), id++, WATER));

        water_edges_dot
                .add(BL_WATER_CORNER_DOT = new Tile(getRotAnimateSprite(0, 0, 4, 0, 0), id++,
                        WATER));
        water_edges_dot.add(
                TL_WATER_CORNER_DOT = new Tile(getRotAnimateSprite(0, 0, 4, 0, 90), id++, WATER));
        water_edges_dot.add(
                TR_WATER_CORNER_DOT = new Tile(getRotAnimateSprite(0, 0, 4, 0, 180), id++, WATER));
        water_edges_dot.add(BR_WATER_CORNER_DOT = new Tile(getRotAnimateSprite(0, 0, 4, 0, -90), id++,
                WATER));

        tiles.addAll(dirtpathXY);
        tiles.addAll(dirtpathDirections);
        tiles.addAll(water_corners);
        tiles.addAll(water_edges);
        tiles.addAll(water_edges_dot);
    }

    private BufferedImage[] getRotAnimateSprite(int firstX, int firstY, int secondX, int secondY, int rotAngle) {
        return ImgFix.getBuildRotImages(getAnimatedSprite(firstX, firstY), getSprite(secondX, secondY), rotAngle);
    }

    // private BufferedImage getRotImages(int firstX, int firstY, int secondX, int
    // secondY, int angle, int index) {

    // return ImgFix.getBuildRotImage(new BufferedImage[] { getSprite(firstX,
    // firstY), getSprite(secondX, secondY) },
    // angle, index);
    // }

    private BufferedImage getRotImage(int xCord, int yCord, int angle) {
        return ImgFix.getRotImage(getSprite(xCord, yCord), angle);
    }

    // private BufferedImage getImages(int firstX, int firstY, int secondX, int
    // secondY) {

    // return ImgFix.buildImage(new BufferedImage[] { getSprite(firstX, firstY),
    // getSprite(secondX, secondY) });
    // }

    private void loadAtlas() {
        atlas = LoadSave.getSpriteAtlas();
    }

    public BufferedImage getSprite(int id) {
        return tiles.get(id).getSprite();
    }

    public BufferedImage getSprite(int xCord, int yCord) {
        return atlas.getSubimage(xCord * 32, yCord * 24, 32, 32);
    }

    public BufferedImage getAniSprite(int id, int animationIndex) {
        return tiles.get(id).getSprite(animationIndex);
    }

    public BufferedImage[] getAnimatedSprite(int xCord, int yCord) {

        BufferedImage[] arr = new BufferedImage[4];
        for (int i = 0; i < 4; i++) {
            arr[i] = getSprite(xCord + i, yCord);
        }

        return arr;
        // return atlas.getSubimage(xCord * 32, yCord * 24, 32, 32);
    }

    public Tile getTile(int id) {

        return tiles.get(id);
    }

    public boolean isSpriteAnimated(int spriteId) {

        return tiles.get(spriteId).isAnimation();
    }
}
