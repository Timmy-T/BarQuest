package attackontinytim.barquest.Database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import attackontinytim.barquest.MainActivity;
import attackontinytim.barquest.R;

public class Testing extends AppCompatActivity {
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void testMe(MainActivity activity){

        DBHandler db = new DBHandler(activity);

        // Inserting rows
        Log.d("Insert: ", "Inserting ..");
        db.addMonster(new Monster(1,"Bob", 100, 50, "Close", 5.0, 1, "Common", 5, 5, 5));
        db.addMonster(new Monster(2,"Tim", 50, 10, "Long", 5.0, 1, "Rare", 15, 15, 15));
        db.addMonster(new Monster(3,"MatLab", 200, 100, "Mid", 5.0, 1, "Uncommon", 50, 5, 25));
        db.addMonster(new Monster(4,"Jimbo", 10000, 5000, "Mid", 5.0, 1, "Common", 45, 55, 35));

        // Reading all monsters
        Log.d("Reading: ", "Reading all shops..");
        List<Monster> monsters = db.getAllMonsters();

        for (Monster monster : monsters) {
            String log = "Name: " + monster.getName() + " ,HP: " + monster.getHP() + " ,XP: " + monster.getXP();
            // Writing monsters to log
            Log.d("Monster: : ", log);
        }

        // Getting a monster from the list
        Monster myMon = monsters.get(0);
        // Changing its name
        myMon.setName("TY");
        // Changing its ID
        myMon.setId(5);
        // Adding to monster table
        db.addMonster(myMon);
        Monster addedMon = db.getMonster("TY");

        Log.d("Checking data: ", "Fetching Updated Monster List");
        List<Monster> newMonsters = db.getAllMonsters();

        for (Monster monster : newMonsters) {
            String log = "Name: " + monster.getName() + " ,HP: " + monster.getHP() + " ,XP: " + monster.getXP();
            // Writing monsters to log
            Log.d("Monster: : ", log);
        }

        Log.d("Deleting Data: ", "Kill it");
        db.deleteMonster(addedMon);

        Log.d("Checking data: ", "Fetching Monster List");
        List<Monster> oldMonsters = db.getAllMonsters();

        for (Monster monster : oldMonsters) {
            String log = "Name: " + monster.getName() + " ,HP: " + monster.getHP() + " ,XP: " + monster.getXP();
            // Writing monsters to log
            Log.d("Monster: : ", log);
        }
    }
}

