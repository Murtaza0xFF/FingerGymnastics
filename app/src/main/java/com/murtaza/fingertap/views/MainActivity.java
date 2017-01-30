package com.murtaza.fingertap.views;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.GridLayout;

import com.murtaza.fingertap.FingerTap;
import com.murtaza.fingertap.R;
import com.murtaza.fingertap.di.modules.MainActivityModule;
import com.murtaza.fingertap.presenters.MainPresenter;
import com.murtaza.fingertap.utils.MultiTouchType;

import java.util.ArrayList;
import java.util.Random;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView, TouchEvents,
    ViewTreeObserver.OnGlobalLayoutListener {

    @Inject
    PackageManager packageManager;

    @Inject
    MainPresenter mainPresenter;

    @Inject
    Random random;

    @Inject
    Paint paint;

    GridLayout gridLayout;

    int gridSize;
    View rootView;
    ArrayList<Selection> touchedSelections;
    ArrayList<Selection> selections;
    ArrayList<Integer> positions;
    private boolean touchStatus;
    private int randomColumn;
    private int randomRow;
    private boolean gameEnded;
    Context context = this;
    private int nextPlayer;
    public static boolean isTouchable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        rootView = findViewById(R.id.root_view);
        gridLayout = (GridLayout) findViewById(R.id.main_grid);
        ((FingerTap) getApplication()).getAppComponent().plus(new MainActivityModule(this)).inject(this);
        if (!isDeviceSupported(packageManager)) {
            notSupportedDialog();
        } else {
            startGame();
        }
    }

    private void startGame() {
        invalidateGridLayout();
        initPersistedData();
        initPreRequisiteVariables();
        getNumberOfTiles();
    }

    private void initPreRequisiteVariables() {
        isTouchable = true;
        nextPlayer = Selection.FIRSTPLAYER;
        gameEnded = false;
        touchStatus = true;
    }

    private void invalidateGridLayout() {
        gridLayout.invalidate();
    }

    private void initPersistedData() {
        touchedSelections = new ArrayList<>();
        selections = new ArrayList<>();
        positions = new ArrayList<>();
    }

    private void getNumberOfTiles() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle(R.string.number_of_tiles);
        dialogBuilder.setMessage(R.string.example_message);
        final EditText editText = new EditText(this);
        dialogBuilder.setView(editText);
        dialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (validateInput(editText)) {
                    setGridSize();
                    mainPresenter.createPlayers();
                    mainPresenter.addViews(gridSize);
                    addLayoutListener();
                    selectCell(nextPlayer);
                } else {
                    reEnterSize();
                }
            }

        });
        AlertDialog dialog = dialogBuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private boolean validateInput(EditText editText) {
        if (TextUtils.isDigitsOnly(editText.getText()) && editText.getText().length() != 0) {
            gridSize = Integer.parseInt(editText.getText().toString());
            return (gridSize % 2 == 0 && gridSize > 1);
        } else {
            return false;
        }
    }

    private void reEnterSize() {
        Snackbar.make(rootView, R.string.invalid_number, Snackbar.LENGTH_LONG).show();
        getNumberOfTiles();
    }

    private void setGridSize() {
        gridLayout.setColumnCount(gridSize);
        gridLayout.setRowCount(gridSize);
    }

    private void addLayoutListener() {
        gridLayout.
            getViewTreeObserver().
            addOnGlobalLayoutListener((ViewTreeObserver.OnGlobalLayoutListener) context);
    }


    private void notSupportedDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle(R.string.apologies);
        dialogBuilder.setMessage(R.string.not_supported);
        dialogBuilder.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog dialog = dialogBuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }


    @Override
    public void addView(Selection selection) {
        gridLayout.addView(selection);
        selections.add(selection);
        positions.add(selection.getPosition());
    }

    @Override
    public Paint getPaint() {
        return paint;
    }

    public boolean isDeviceSupported(PackageManager packageManager) {
        return MultiTouchType.getMultiTouchType(packageManager);
    }

    @Override
    public void addItem(Selection selection, int object) {
        touchedSelections.add(selection);
        int x = selection.getXvar();
        int y = selection.getYvar();
        mainPresenter.modifyPlayer(object, x, y);
        if (touchStatus) {
            if (selection.getXvar() == randomRow && selection.getYvar() == randomColumn) {
                mainPresenter.incrementPlayerCount(object);
                if (touchedSelections.size() == gridSize * 2) {
                    showFinalDialog("", true);
                    return;
                }
                // The maximum number of touches Android phones can support is 10.
                if (mainPresenter.getCount(object) > 5) {
                    showFinalDialog(object == Selection.FIRSTPLAYER ? getString(R.string.second_player) :
                        getString(R.string.first_player), false);
                }
                selectCell(nextPlayer);
            } else {
                showFinalDialog(object == Selection.FIRSTPLAYER ? getString(R.string.second_player) :
                    getString(R.string.first_player), false);
            }
        }
    }


    @Override
    public void removeItem(Selection selection, int object) {
        if (!gameEnded && touchStatus) {
            int row = mainPresenter.playerRow(object);
            int column = mainPresenter.playerColumn(object);
            if (selection.getXvar() == row && selection.getYvar() == column) {
                touchStatus = false;
                showFinalDialog(object == Selection.FIRSTPLAYER ? getString(R.string.second_player) :
                    getString(R.string.first_player), false);
            }

        }
    }

    private void removeLayoutListener() {
        gridLayout.getViewTreeObserver().
            removeOnGlobalLayoutListener(this);
    }

    private void showFinalDialog(String player, boolean draw) {
        removeLayoutListener();
        gameEnded = true;
        MainActivity.isTouchable = false;
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle(R.string.game_over);
        if (draw) {
            dialogBuilder.setMessage(getString(R.string.draw) + " " + getString(R.string.play_again_prompt));
        } else {
            dialogBuilder.setMessage(
                getString(R.string.congratulations) + "! " + player + " " + getString(R.string.wins) +
                    ". " + getString(R.string.play_again_prompt));
        }
        dialogBuilder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                gridLayout.removeAllViews();
                startGame();
            }
        }).setNegativeButton(getString(R.string.quit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        final AlertDialog dialog = dialogBuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    public void selectCell(int object) {
        Selection view = getView();
        view.setColor(true);
        view.setPerson(object);
        view.invalidate();
        nextPlayer = object == Selection.FIRSTPLAYER ? Selection.SECONDPLAYER : Selection.FIRSTPLAYER;
    }

    private Selection getView() {
        randomRow = random.nextInt(gridSize);
        randomColumn = random.nextInt(gridSize);
        int pos = randomRow * gridSize + randomColumn;
        for (Selection i : touchedSelections) {
            if (i.getPosition() == pos) {
                return getView();
            }
        }
        return selections.get(pos);
    }

    @Override
    public void onGlobalLayout() {
        final int marginSize = 2;

        int width = gridLayout.getWidth() / gridSize;
        int height = gridLayout.getWidth() / gridSize;

        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                int pos = x * gridSize + y;
                GridLayout.LayoutParams params = (GridLayout.LayoutParams)
                    selections.get(pos).getLayoutParams();
                params.width = width - 2 * marginSize;
                params.height = height - 2 * marginSize;
                params.setMargins(marginSize, marginSize, marginSize, marginSize);
                selections.get(pos).setLayoutParams(params);
            }
        }
    }

}

