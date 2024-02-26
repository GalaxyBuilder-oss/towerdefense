package com.salim;

import javax.swing.JFrame;

import com.helperMethods.LoadSave;
import com.managers.TileManager;
import com.scenes.LevelEditor;
import com.scenes.Menu;
import com.scenes.Playing;
import com.scenes.Settings;

public class Game extends JFrame implements Runnable {

    private GameScreen gameScreen;

    private Thread gameThread;

    private final double FPS_SET = 120.0;
    private final double UPS_SET = 60.0;

    // Classes
    private Render render;
    private Menu menu;
    private Playing playing;
    private Settings settings;
    private LevelEditor levelEditor;
    private TileManager tileManager;

    public Game() {

        initClasses();
        createDefaultLevel();
        updateGame();

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(gameScreen);
        pack();
        setVisible(true);
    }

    private void initClasses() {

        render = new Render(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        playing = new Playing(this);
        settings = new Settings(this);
        levelEditor = new LevelEditor(this);
        tileManager = new TileManager();
    }

    public void createDefaultLevel() {
        int[] arr = new int[400];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }

        LoadSave.createLevel("new level.txt", arr);
    }

    private void updateGame(){
        switch (GameStates.gameState) {

            case MENU:
                    
                break;
            case PLAYING:
                    playing.update();
                break;
            case EDIT_LEVEL:
                    levelEditor.update();
                break;
            case SETTINGS:
                    
                break;
        }
    }

    private void start() {
        gameThread = new Thread(this) {
        };
        gameThread.start();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.gameScreen.initInputs();
        game.start();
    }

    @Override
    public void run() {

        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();
        double timePerframe = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long lastTimeCheck = System.currentTimeMillis();

        int frames = 0;
        int updates = 0;

        long now;

        while (true) {

            now = System.nanoTime();

            // Render
            if (now - lastFrame >= timePerframe) {
                repaint();
                lastFrame = now;
                frames++;
            }

            // Updates
            if (now - lastUpdate >= timePerUpdate) {
                // updateGame();
                lastUpdate = now;
                updates++;
            }

            // Checking FPS and UPS
            if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
                // System.out.println("FPS : " + frames + " | UPS : " + updates);
                menu.showFramePerSeconds(frames,updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }

    public Render getRender() {
        return render;
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public Settings getSettings() {
        return settings;
    }

    public LevelEditor getLevelEditor() {
        return levelEditor;
    }

    public TileManager getTileManager() {
        return tileManager;
    }
}