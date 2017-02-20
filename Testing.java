package com.example.scott.app;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class Testing extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHandler db = new DBHandler(this);

// Inserting rows
        Log.d("Insert: ", "Inserting ..");
        db.addMonster(new Monster("Bob", 100, 50));
        db.addMonster(new Monster("Tim", 50, 10));
        db.addMonster(new Monster("MatLab", 200, 100));
        db.addMonster(new Monster("Jimbo", 10000, 5000));

// Reading all monsters
        Log.d("Reading: ", "Reading all shops..");
        List<Monster> monsters = db.getAllMonsters();

        for (Monster monster : monsters) {
            String log = "Name: " + monster.getName() + " ,HP: " + monster.getHP() + " ,XP: " + monster.getXP();
// Writing monsters to log
            Log.d("Monster: : ", log);
        }
    }
}

