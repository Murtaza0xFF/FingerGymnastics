package com.murtaza.fingertap.views;

/**
 * Created by murtaza on 30/1/17.
 */

public interface TouchEvents {

    /**
     * Triggered when a new touch has been made. It maps the position to the player, and persists the data.
     * @param selection selection object corresponding to the touch
     * @param player Pass in the player type (can be either 1, or 2, as defined in the Selection class)
     */


    public void addItem(Selection selection, int player);

    /**
     * Triggered when a new touch has been undone. It maps the position to the player, and removes the data.
     * @param selection selection object corresponding to the touch.
     * @param player Pass in the player type (can be either 1, or 2, as defined in the Selection class)
     */

    public void removeItem(Selection selection, int player);
}
