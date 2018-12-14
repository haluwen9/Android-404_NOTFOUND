package com.notfound.team404.mastermindgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class Guide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        TextView tv_guide =  findViewById(R.id.tv_guide);
        String text = "";
        try {
            InputStream inp = getAssets().open("guide.txt");
            int size = inp.available();
            byte[] buffer = new byte[size];
            inp.read(buffer);
            inp.close();
            text = new String(buffer);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        tv_guide.setText(text);

    }
}
