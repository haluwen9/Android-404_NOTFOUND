package com.notfound.team404.mastermindgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


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
    protected int[][] gameBoard;
    protected int[] resultBlack;
    protected int[] resultWhite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        if (getIntent().hasExtra("maxTurn"))
            maxTurn = getIntent().getIntExtra("maxTurn", 0);
        else
            maxTurn = 15;

        if (getIntent().hasExtra("maxColor"))
            maxColor = getIntent().getIntExtra("maxColor", 0);
        else
            maxColor = 8;

        if (getIntent().hasExtra("maxLength"))
            maxLength = getIntent().getIntExtra("maxLength", 0);
        else
            maxLength = 8;

        // Init Methods:
        initDefaultValues();
        generateRandomAnswer();
        initGameBoard();

        // Display Methods:
        fillColorPicker();
        fillAnswer();
        fillGameBoard();

        Log.d("--Debug Gameplay--", "onCreate: maxLength="+maxLength+" maxColor="+maxColor+" maxTurn="+maxTurn);
    }

    public void initDefaultValues() {
        currentTurn = 1;
        currentAddress = "A1";
        selectedColor = 0;
    }

    public void fillColorPicker() {
        for (int i = 0; i < maxColor; ++i) {
            int btnColorId = this.getResources().getIdentifier("color"+i, "id", this.getPackageName());
            AppCompatButton tmp = findViewById(btnColorId);
            tmp.setVisibility(View.VISIBLE);
            tmp.setBackgroundTintList(new ColorStateList(colorStates, new int[] {Colors[i], Colors[i], Colors[i], Colors[i]+(0xAA<<24)}));
        }
    }

    public void generateRandomAnswer() {
        answer = new int[maxLength];
        answer_white = new int[maxColor];
        for (int i = 0; i < maxLength; ++i) {
            // Generate Code Values:
            answer[i] = (int)(Math.round(Math.random()*1000)%maxColor);
            answer_white[answer[i]] += 1;
        }
    }

    public void fillAnswer() {
        for (int i = 0; i < maxLength; ++i) {
            // Generate Code Colors from Code Values:
            int btnId = this.getResources().getIdentifier(String.valueOf((char)('A'+i))+"C", "id", this.getPackageName());
            AppCompatButton tmp = findViewById(btnId);
            tmp.setBackgroundTintList(new ColorStateList(colorStates, new int[] {Colors[answer[i]], Colors[answer[i]], Colors[answer[i]], Colors[answer[i]]+(0xAA<<24)}));
        }
    }

    public void initGameBoard() {
        gameBoard = new int[maxTurn][maxLength];
        resultBlack = new int[maxTurn];
        resultWhite = new int[maxTurn];
        for (int i = 0; i < maxTurn; ++i) {
            for (int j = 0; j < maxLength; ++j) {
                gameBoard[i][j] = 0;
            }
        }
    }

    public void fillGameBoard() {
        for (int i = 1; i <= maxTurn; ++i) {
            for (char j = 'A'; j < 'A' + maxLength; ++j) {
                int btnId = this.getResources().getIdentifier(String.valueOf(j)+i, "id", this.getPackageName());
                AppCompatButton tmp = findViewById(btnId);
                tmp.setBackgroundTintList(new ColorStateList(colorStates, new int[] {Colors[selectedColor], Colors[selectedColor], Colors[selectedColor], Colors[selectedColor]+(0xAA<<24)}));
            }
        }
    }

    public void pickColor(View view) {
        String id=getResources().getResourceEntryName(view.getId());
        int color = Integer.parseInt(id.substring(id.length()-1));
        if (color < maxColor) {
            selectedColor = color;
//            Log.d("--Debug--", "pickColor: "+selectedColor);
//            int currentAddressId = this.getResources().getIdentifier(currentAddress, "id", this.getPackageName());
//            AppCompatButton tmp = findViewById(currentAddressId);
//            tmp.setBackgroundTintList(new ColorStateList(colorStates, new int[] {Colors[selectedColor], Colors[selectedColor], Colors[selectedColor], Colors[selectedColor]+(0xAA<<24)}));
        }
    }

    public void selectCell(View view) {
        String id=getResources().getResourceEntryName(view.getId());
        int turn = Integer.parseInt(id.substring(1));
        int pos = id.charAt(0)-'A';
        if (turn == currentTurn && pos < maxLength) {
            gameBoard[turn-1][pos] = selectedColor;
//            Log.d("--Debug--", String.valueOf(id.charAt(0)-'A'));
            currentAddress = id;
            view.setBackgroundTintList(new ColorStateList(colorStates, new int[] {Colors[selectedColor], Colors[selectedColor], Colors[selectedColor], Colors[selectedColor]+(0xAA<<24)}));
        }
    }

    public void submitTurn(View view) {
        judgeCurrentTurn();
        String result;
        if (resultBlack[currentTurn-1] == maxLength) {
            result = "Victory!";
            findViewById(R.id.Answer).setVisibility(View.VISIBLE);
            Toast.makeText(this, "Victory!", Toast.LENGTH_LONG).show();
        }
        else if (currentTurn < maxTurn) {
            currentTurn += 1;
            result = String.valueOf(currentTurn);
            findViewById(this.getResources().getIdentifier("turn"+currentTurn, "id", this.getPackageName())).setVisibility(View.VISIBLE);
        }
        else {
            result = "Defeat!";
            findViewById(R.id.Answer).setVisibility(View.VISIBLE);
            Toast.makeText(this, "Defeat!", Toast.LENGTH_LONG).show();
        }
        TextView tvLblGameState = findViewById(R.id.lblGameState);
        tvLblGameState.setText(result);
    }

    public void judgeCurrentTurn() {
        int[] whiteCheck = new int[maxColor];
        resultBlack[currentTurn-1] = 0;
        resultWhite[currentTurn-1] = 0;
        for (int i = 0; i < maxLength; ++i) {
            int tmp = gameBoard[currentTurn-1][i];
            if (tmp == answer[i]) {
                whiteCheck[tmp] += 1;
                resultBlack[currentTurn-1] += 1;
            }

        }
        for (int i = 0; i < maxLength; ++i) {
            int tmp = gameBoard[currentTurn-1][i];
            Log.d("--Debug--", "tmp: "+tmp+" -- i: "+i);
            if (tmp != answer[i] && whiteCheck[tmp] < answer_white[tmp]) {
                whiteCheck[tmp] += 1;
                resultWhite[currentTurn-1] += 1;
            }
        }
        TextView tv_black = findViewById(this.getResources().getIdentifier("I"+currentTurn, "id", this.getPackageName()));
        TextView tv_white = findViewById(this.getResources().getIdentifier("J"+currentTurn, "id", this.getPackageName()));
        tv_black.setText(String.valueOf(resultBlack[currentTurn-1]));
        tv_white.setText(String.valueOf(resultWhite[currentTurn-1]));
    }

    public void resetGame(View view) {
        Intent intent = new Intent(this, GameSetting.class);
        startActivity(intent);
        finish();
    }
}
