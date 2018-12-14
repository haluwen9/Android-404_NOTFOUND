package com.notfound.team404.mastermindgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Gameplay extends AppCompatActivity {
    protected int currentStep;
    protected int maxStep;
    protected String currentAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);
        currentStep = 1;
        maxStep = 13;
    }
}
