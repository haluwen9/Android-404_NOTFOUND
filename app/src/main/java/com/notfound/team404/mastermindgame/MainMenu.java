package com.notfound.team404.mastermindgame;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        else if (id.equals("btnCredits")) {
            Intent intent = new Intent(this, Credits.class);
            startActivity(intent);
        }
        else if (id.equals("btnGuide")) {
            Intent intent = new Intent(this, Guide.class);
            startActivity(intent);
        }
    }

}
