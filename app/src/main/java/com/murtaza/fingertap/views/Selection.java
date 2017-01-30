package com.murtaza.fingertap.views;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.murtaza.fingertap.R;

/**
 * Created by murtaza on 30/1/17.
 */

public class Selection extends View {


    private int xvar;
    private int yvar;
    public static final int FIRSTPLAYER = 1;
    public static final int SECONDPLAYER = 2;
    private int position;
    private TouchEvents touchEvent;
    private int person;
    Paint paint = new Paint();
    private boolean colorBool;

    public Selection(Context context) {
        this(context, null);
    }

    public Selection(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Selection(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }


    public Selection(int x, int y, Context context) {
        super(context);
        xvar = x;
        yvar = y;
    }

    public int getPosition() {
        return position;
    }

    public int getXvar() {
        return xvar;
    }

    public int getYvar() {
        return yvar;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setPerson(int person) {

        this.person = person;
    }


    public void touchListener(TouchEvents touchEvent) {
        this.touchEvent = touchEvent;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (colorBool) {
            paint.setColor(ContextCompat.getColor(getContext(), R.color.md_white_1000));
            paint.setTextSize(25);
            paint.setAntiAlias(true);
            paint.setTextAlign(Paint.Align.CENTER);

            if (person == FIRSTPLAYER) {
                canvas.drawColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
                canvas.drawText("1", this.getMeasuredWidth() / 2, this.getMeasuredHeight() / 2, paint);
            } else {
                canvas.drawColor(ContextCompat.getColor(getContext(), R.color.md_light_blue_A700));
                canvas.drawText("2", this.getMeasuredWidth() / 2, this.getMeasuredHeight() / 2, paint);
            }
        } else {
            canvas.drawColor(Color.parseColor("#9acc9a"));
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (MainActivity.isTouchable) {
            super.onTouchEvent(event);

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    invalidate();
                    if (touchEvent != null) {
                        touchEvent.addItem(this, person);
                    }
                    return true;

                case MotionEvent.ACTION_UP:
                    setColor(false);
                    invalidate();
                    touchEvent.removeItem(this, person);
                    return true;

            }
        }
        return false;
    }

    public void setColor(boolean colorBool) {
        this.colorBool = colorBool;
    }

}
