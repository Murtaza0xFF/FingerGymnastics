package com.murtaza.fingertap.presenters;

import com.murtaza.fingertap.models.Person;
import com.murtaza.fingertap.utils.MultiTouchType;
import com.murtaza.fingertap.views.MainActivity;
import com.murtaza.fingertap.views.MainView;
import com.murtaza.fingertap.views.Selection;
import com.murtaza.fingertap.views.TouchEvents;

import java.util.HashMap;

/**
 * Created by murtaza on 30/1/17.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;
    Person secondPlayer;
    Person firstPlayer;


    public MainPresenterImpl(MainView mainView){

        this.mainView = mainView;
    }


    @Override
    public void createPlayers() {
        firstPlayer = new Person();
        secondPlayer = new Person();
    }

    @Override
    public void addViews(int gridSize) {
        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                int pos = x * gridSize + y;
                Selection selections = new Selection(x, y, mainView);
                selections.touchListener((TouchEvents) mainView);
                selections.setPosition(pos);
                mainView.addView(selections);
            }
        }
    }

    @Override
    public void modifyPlayer(int player, int x, int y) {
        if (player == Selection.FIRSTPLAYER){
            firstPlayer.row = x;
            firstPlayer.column = y;
        }else{
            secondPlayer.row = x;
            secondPlayer.column = y;
        }
    }

    @Override
    public void incrementPlayerCount(int player) {
        if (player == Selection.FIRSTPLAYER) {
            firstPlayer.count += 1;
        }
        else {
            secondPlayer.count += 1;
        }
    }

    @Override
    public int playerRow(int object) {
        return object == Selection.FIRSTPLAYER ? firstPlayer.row : secondPlayer.row;
    }

    @Override
    public int playerColumn(int object) {
        return object == Selection.FIRSTPLAYER ? firstPlayer.column : secondPlayer.column;
    }

    @Override
    public void decrementPlayerCount(int player) {
        if (player == Selection.FIRSTPLAYER) {
            firstPlayer.count -= 1;
        }
        else {
            secondPlayer.count -= 1;
        }
    }

    @Override
    public int getCount(int player) {
        return player == Selection.FIRSTPLAYER ? firstPlayer.count : secondPlayer.count;
    }


}
