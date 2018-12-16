package com.notfound.team404.mastermindgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class GameSetting extends AppCompatActivity {
    protected AppCompatSeekBar slideMaxTurn;
    protected AppCompatSeekBar slideMaxLength;
    protected AppCompatSeekBar slideMaxColor;
    protected TextView valueMaxTurn;
    protected TextView valueMaxLength;
    protected TextView valueMaxColor;
    protected int maxTurn;
    protected int maxLength;
    protected int maxColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setting);
        initDefaultSettings();
        slideMaxTurn = findViewById(R.id.slideMaxTurn);
        slideMaxLength = findViewById(R.id.slideMaxLength);
        slideMaxColor = findViewById(R.id.slideMaxColor);

        valueMaxTurn = findViewById(R.id.valueMaxTurn);
        valueMaxLength = findViewById(R.id.valueMaxLength);
        valueMaxColor = findViewById(R.id.valueMaxColor);

        valueMaxTurn.setText(String.valueOf(maxTurn));
        valueMaxLength.setText(String.valueOf(maxLength));
        valueMaxColor.setText(String.valueOf(maxColor));

        slideMaxTurn.setMax(10);
        slideMaxTurn.setProgress(maxTurn-5);
        slideMaxTurn.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                maxTurn = progress + 5;
                valueMaxTurn.setText(String.valueOf(maxTurn));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        slideMaxLength.setMax(6);
        slideMaxLength.setProgress(maxLength-2);
        slideMaxLength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                maxLength = progress + 2;
                valueMaxLength.setText(String.valueOf(maxLength));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        slideMaxColor.setMax(4);
        slideMaxColor.setProgress(maxColor-4);
        slideMaxColor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                maxColor = progress+4;
                valueMaxColor.setText(String.valueOf(maxColor));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void initDefaultSettings() {
        maxTurn = 15;
        maxColor = 8;
        maxLength = 8;
    }

    public void startNewGame(View view) {
        Intent intent = new Intent(this, Gameplay.class);
        intent.putExtra("maxTurn", maxTurn);
        intent.putExtra("maxLength", maxLength);
        intent.putExtra("maxColor", maxColor);
        startActivity(intent);
        finish();
    }
}
