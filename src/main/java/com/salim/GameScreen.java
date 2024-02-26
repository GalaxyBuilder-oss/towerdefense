package com.salim;

import javax.swing.JPanel;

import com.inputs.KeyboardListener;
import com.inputs.MyMouseListener;

import java.awt.Dimension;
import java.awt.Graphics;

public class GameScreen extends JPanel {

    private Dimension size;
    private MyMouseListener myMouseListener;
    private KeyboardListener keyboardListener;

    private Game game;

    public GameScreen(Game game) {
        
        this.game = game;
        setPanelSize();
    }

    public void initInputs() {

        myMouseListener = new MyMouseListener(game);
        keyboardListener = new KeyboardListener(game);

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyboardListener);

        requestFocus();
    }

    private void setPanelSize() {

        size = new Dimension(640, 690);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        game.getRender().render(g);
    }
}
