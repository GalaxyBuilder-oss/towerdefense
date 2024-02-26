package com.salim;

public enum GameStates {
    
    MENU,
    PLAYING,
    EDIT_LEVEL,
    SETTINGS;

    public static GameStates gameState=MENU;

    public static void setGameState(GameStates state){
        gameState=state;
    }

}
