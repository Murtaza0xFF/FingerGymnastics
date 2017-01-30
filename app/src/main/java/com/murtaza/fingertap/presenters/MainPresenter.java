package com.murtaza.fingertap.presenters;


/**
 * Created by murtaza on 30/1/17.
 */

public interface MainPresenter {

    void createPlayers();

    void addViews(int gridSize);

    void modifyPlayer(int player, int x, int y);

    void incrementPlayerCount(int player);

    int playerRow(int object);

    int playerColumn(int object);

    void decrementPlayerCount(int player);

    int getCount(int player);
}
