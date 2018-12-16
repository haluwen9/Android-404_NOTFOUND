package com.notfound.team404.mastermindgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class Credits extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        TextView tv_credits =  findViewById(R.id.tv_credits);
        String text = "";
        try {
            InputStream inp = getAssets().open("credits.txt");
            int size = inp.available();
            byte[] buffer = new byte[size];
            inp.read(buffer);
            inp.close();
            text = new String(buffer);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        tv_credits.setText(text);

    }

}
