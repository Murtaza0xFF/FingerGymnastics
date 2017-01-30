package com.murtaza.fingertap.views;

import android.graphics.Paint;

/**
 * Created by murtaza on 30/1/17.
 */

public interface MainView {

    /**
     * Add the selection to the frid, and record the posittions.
     * @param selection Pass in the selection object, that's been created.
     */

    void addView(Selection selection);

    Paint getPaint();

}
