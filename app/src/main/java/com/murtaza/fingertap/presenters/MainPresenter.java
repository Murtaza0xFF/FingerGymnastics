package com.murtaza.fingertap.presenters;


/**
 * Created by murtaza on 30/1/17.
 */

public interface MainPresenter {

    /**
     * Use createPlayers to create the 2 players required to play this game.
     * The model class is defined as person.
     */

    void createPlayers();

    /**
     * Use addViews to create the selection object for gridsize*gridsize
     * </pre>
     * @param gridSize Pass in the gridsize entered by the user.
     */

    void addViews(int gridSize);

    /**
     * modifyPlayer records the coordinates for rows and columns for each click made by either player.
     * @param player Pass in the player type (can be either 1, or 2, as defined in the Selection class)
     * @param x X coordinate of the click
     * @param y Y coordinate of the click
     */

    void modifyPlayer(int player, int x, int y);

    /**
     * Increment the each player's count, depending who made the move.
     * @param player Pass in the player type (can be either 1, or 2, as defined in the Selection class)
     */

    void incrementPlayerCount(int player);

    /**
     * Returns the row for the player
     * @param player Pass in the player type (can be either 1, or 2, as defined in the Selection class)
     */

    int playerRow(int player);

    /**
     * Returns the column for the player
     * @param player Pass in the player type (can be either 1, or 2, as defined in the Selection class)
     */

    int playerColumn(int player);

    /**
     * Decrement the count of number of tiles held by the player, if the player has let go of (a) tile(s)
     * @param player Pass in the player type (can be either 1, or 2, as defined in the Selection class)
     */

    void decrementPlayerCount(int player);

    /**
     * Number of tile(s) being held by the player
     * @param player Pass in the player type (can be either 1, or 2, as defined in the Selection class)
     */


    int getCount(int player);
}
