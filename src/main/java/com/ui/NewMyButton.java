package com.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class NewMyButton {
    public int x, y, width, height;
    private int id;
    private String text;

    private Rectangle bounds;
    private boolean mouseOver, mousePressed;
    private Color bgColor, hoverColor;

    public NewMyButton(String text, int x, int y, int width, int height) {

        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id=-1;
        initBounds();
    }

    public NewMyButton(String text, int x, int y, int width, int height,int id) {

        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id=id;
        initBounds();
    }

    public void draw(Graphics g) {

        // body
        drawBody(g);

        // border
        drawBorder(g);

        // text
        drawText(g);
    }

    private void drawBody(Graphics g) {
        g.setColor(Color.white);
        if (bgColor != null)
            g.setColor(bgColor);
        if (mouseOver) {
            g.setColor(Color.GRAY);
            if (hoverColor != null)
                g.setColor(hoverColor);
        }
        g.fillRect(x, y, width, height);
    }

    private void drawBorder(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(x, y, width, height);
        if (mousePressed) {
            g.drawRect(x + 1, y + 1, width - 2, height - 2);
            g.drawRect(x + 2, y + 2, width - 4, height - 4);
        }
    }

    private void drawText(Graphics g) {

        int w = g.getFontMetrics().stringWidth(text);
        int h = g.getFontMetrics().getHeight();
        g.drawString(text, x - w / 2 + width / 2, (y + h / 2 + height / 2) - 2);
    }

    public void resetBooleans() {
        this.mouseOver = false;
        this.mousePressed = false;
    }

    public void initBounds() {
        this.bounds = new Rectangle(x + 1, y + 1, width + 3, height + 3);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getId(){
        return id;
    }

    public void setBackgroundColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMouseOver(){
        return mouseOver;
    }

    public boolean isMousePress(){
        return mousePressed;
    }
}
