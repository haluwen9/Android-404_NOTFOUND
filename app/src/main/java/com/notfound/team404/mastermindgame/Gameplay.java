package com.notfound.team404.mastermindgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class Gameplay extends AppCompatActivity {
    protected static final int[] Color = {
            0xCCFFFFFF, 0xCCFF0000, 0xCC00FF00, 0xCC0000FF, 0xCCFFFF00, 0xCCFF00FF, 0xCC00FFFF, 0xCC000000
    };
    protected int currentTurn;
    protected int maxTurn;
    protected int maxLength;
    protected int maxColor;
    protected int answer;
    protected String currentAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);
        currentTurn = 1;
        maxTurn = 13;
        maxColor = 8;
        maxLength = 8;
        currentAddress = "A1";
    }
}
