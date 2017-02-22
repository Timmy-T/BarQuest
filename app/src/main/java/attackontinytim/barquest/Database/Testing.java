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
        db.addMonster(new Monster("Bob", 100, 50, "Close", 5.0, 1, "Common", 5, 5, 5));
        db.addMonster(new Monster("Tim", 50, 10, "Long", 5.0, 1, "Rare", 15, 15, 15));
        db.addMonster(new Monster("MatLab", 200, 100, "Mid", 5.0, 1, "Uncommon", 50, 5, 25));
        db.addMonster(new Monster("Jimbo", 10000, 5000, "Mid", 5.0, 1, "Common", 45, 55, 35));

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

