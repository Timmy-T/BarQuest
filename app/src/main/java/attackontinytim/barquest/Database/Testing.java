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

    /**
     * Adds dummy records to the database if none are present
     * @param activity The calling activity
     */
    public void addRecords(MainActivity activity){

        DBHandler monsterDB = new DBHandler(activity);
        if (monsterDB.getMonsterCount() == 0) {
            monsterDB.addMonster(new Monster(1,"Bob", 100, 50, "Close", 5.0, 1, "Common", 5, 5, 5));
            monsterDB.addMonster(new Monster(2,"Tim", 50, 10, "Long", 5.0, 1, "Rare", 15, 15, 15));
            monsterDB.addMonster(new Monster(3,"MatLab", 200, 100, "Mid", 5.0, 1, "Uncommon", 50, 5, 25));
            monsterDB.addMonster(new Monster(4,"Jimbo", 10000, 5000, "Mid", 5.0, 1, "Common", 45, 55, 35));
        }

        DBHandlerItems itemDB = new DBHandlerItems(activity);
        if (itemDB.getItemCount() == 0){
            itemDB.addItem(new Item("Close", 1,1,"Dagger of Wood", 0.1, 0.5));
            itemDB.addItem(new Item("Mid", 190,95,"Sword of a Thousand Truths", 9999, 0.1));
            itemDB.addItem(new Item("Long", 55,3,"David Bowie", 10, 2.5));
        }
    }


    public void testMonster(MainActivity activity){

        DBHandler db = new DBHandler(activity);

        Log.d("Checking data: ", "Fetching Monster List");
        List<Monster> oldMonsters = db.getAllMonsters();

        for (Monster monster : oldMonsters) {
            String log = "Name: " + monster.getName() + " ,HP: " + monster.getHP() + " ,XP: " + monster.getXP();
            // Writing monsters to log
            Log.d("Monster: : ", log);
        }

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
        Monster addedMon = db.getMonsterByName("TY");

        Log.d("Checking data: ", "Fetching Updated Monster List");
        monsters = db.getAllMonsters();

        for (Monster monster : monsters) {
            String log = "Name: " + monster.getName() + " ,HP: " + monster.getHP() + " ,XP: " + monster.getXP();
            // Writing monsters to log
            Log.d("Monster: : ", log);
        }


        Log.d("Deleting Data: ", "Kill it");
        db.deleteMonster(addedMon);

        Log.d("Checking data: ", "Fetching Monster List");
        monsters = db.getAllMonsters();

        for (Monster monster : monsters) {
            String log = "Name: " + monster.getName() + " ,HP: " + monster.getHP() + " ,XP: " + monster.getXP();
            // Writing monsters to log
            Log.d("Monster: : ", log);
        }

    }

    public void testItems(MainActivity activity){

        DBHandlerItems db = new DBHandlerItems(activity);

        // Inserting rows
        Log.d("Insert: ", "Inserting ..");
        db.addItem(new Item("Close", 1,1,"Dagger of Wood", 0.1, 0.5));
        db.addItem(new Item("Mid", 190,95,"Sword of a Thousand Truths", 9999, 0.1));
        db.addItem(new Item("Long", 55,3,"David Bowie", 10, 2.5));

        // Reading all items
        Log.d("Reading: ", "Reading all items..");
        List<Item> items= db.getAllItems();

        for (Item item  : items) {
            String log = "Name: " + item.getName() + " ,Attack Type: " + item.getAttackType() + ", Attack" + item.getAttack();
            // Writing items to log
            Log.d("Item: : ", log);
        }

        // Getting a monster from the list
        Item myItem = items.get(0);
        // Changing its name
        myItem.setName("Dev's Sword");
        // Adding to item table
        db.addItem(myItem);
        Item addedItem = db.getItemByName("Dev's Sword");

        Log.d("Checking data: ", "Fetching Updated Item List");
        items= db.getAllItems();

        for (Item item  : items) {
            String log = "Name: " + item.getName() + " ,Attack Type: " + item.getAttackType() + ", Attack" + item.getAttack();
            // Writing items to log
            Log.d("Item: : ", log);
        }

        Log.d("Deleting Data: ", "Kill it");
        db.deleteItem(myItem);

        Log.d("Checking data: ", "Fetching Updated Item List");
        items= db.getAllItems();

        for (Item item  : items) {
            String log = "Name: " + item.getName() + " ,Attack Type: " + item.getAttackType() + ", Attack" + item.getAttack();
            // Writing items to log
            Log.d("Item: : ", log);
        }
    }
}

