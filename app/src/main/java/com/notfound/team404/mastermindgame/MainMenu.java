package com.notfound.team404.mastermindgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }

    public void onClick(View view) {
        String id=getResources().getResourceEntryName(view.getId());
        if (id.equals("btnPlay")) {
            Intent intent = new Intent(this, Gameplay.class);
            startActivity(intent);
        }
    }

    public void openCredits(View view) {
        TextView textView = (TextView)view;
        String id=getResources().getResourceEntryName(textView.getId());
        if (id.equals("btnCredit")) {
            Intent intent = new Intent(this, Credits.class);
            startActivity(intent);
        }
    }
}
