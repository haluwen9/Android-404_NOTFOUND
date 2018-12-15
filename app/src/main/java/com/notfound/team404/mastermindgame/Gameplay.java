package com.notfound.team404.mastermindgame;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;


public class Gameplay extends AppCompatActivity {
    protected static final int[] Colors = {
            0x55FFFFFF, 0x55FF0000, 0x5500FF00, 0x550000FF, 0x55FFFF00, 0x55FF00FF, 0x5500FFFF, 0x55000000
    };
    protected static final int[][] colorStates = new int[][] {
            new int[] { android.R.attr.state_enabled}, // enabled
            new int[] {-android.R.attr.state_enabled}, // disabled
            new int[] {-android.R.attr.state_checked}, // unchecked
            new int[] { android.R.attr.state_pressed}  // pressed
    };
    protected int maxTurn;
    protected int maxLength;
    protected int maxColor;

    protected int currentTurn;
    protected String currentAddress;
    protected int selectedColor;

    protected int[] answer;
    protected int[] answer_white;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);
        currentTurn = 1;
        maxTurn = 13;
        maxColor = 8;
        maxLength = 8;
        currentAddress = "A1";
        selectedColor = Colors[0];
        for (int i = 0; i < 8; ++i) {
            int btnColorId = this.getResources().getIdentifier("color"+i, "id", this.getPackageName());
            int[] colors = new int[] {Colors[i], Color.GRAY, Color.GRAY, Colors[i]+(0xAA<<24)};
            AppCompatButton tmp = findViewById(btnColorId);
            tmp.setBackgroundTintList(new ColorStateList(colorStates, colors));
        }
        generateRandomAnswer();
    }

    public void generateRandomAnswer() {
        answer = new int[maxLength];
        answer_white = new int[maxColor];
        for (int i = 0; i < maxLength; ++i) {
            answer[i] = (int)(Math.round(Math.random()*1000)%maxColor);
            answer_white[answer[i]] += 1;
        }
    }

    public void pickColor(View view) {

    }

    public void selectCell(View view) {

    }
}
