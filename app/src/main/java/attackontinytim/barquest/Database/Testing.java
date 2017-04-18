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

    public void testMonster(MainActivity activity){

        Log.d("Checking data: ", "Fetching Monster List");
        List<Monster> oldMonsters = MonsterRepo.getAllMonsters();

        for (Monster monster : oldMonsters) {
            String log = "Name: " + monster.getName() + " ,HP: " + monster.getHP() + " ,XP: " + monster.getXP();
            // Writing monsters to log
            Log.d("Monster: : ", log);
        }

        // Inserting rows
        Log.d("Insert: ", "Inserting ..");
        //MonsterRepo.addMonster(new Monster(1,"Bob", 100, 50, "Close", 5.0, 1, "Common", 5, 5, 5));
        //MonsterRepo.addMonster(new Monster(2,"Tim", 50, 10, "Long", 5.0, 1, "Rare", 15, 15, 15));
        //MonsterRepo.addMonster(new Monster(3,"MatLab", 200, 100, "Mid", 5.0, 1, "Uncommon", 50, 5, 25));
        //MonsterRepo.addMonster(new Monster(4,"Jimbo", 10000, 5000, "Mid", 5.0, 1, "Common", 45, 55, 35));

        // Reading all monsters
        Log.d("Reading: ", "Reading all shops..");
        List<Monster> monsters = MonsterRepo.getAllMonsters();

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
        myMon.setId(9999);
        // Adding to monster table
        MonsterRepo.addMonster(myMon);
        Monster addedMon = MonsterRepo.getMonsterByName("TY");

        Log.d("Checking data: ", "Fetching Updated Monster List");
        monsters = MonsterRepo.getAllMonsters();

        for (Monster monster : monsters) {
            String log = "Name: " + monster.getName() + " ,HP: " + monster.getHP() + " ,XP: " + monster.getXP();
            // Writing monsters to log
            Log.d("Monster: : ", log);
        }


        Log.d("Deleting Data: ", "Kill it");
        MonsterRepo.deleteMonster(addedMon);

        Log.d("Checking data: ", "Fetching Monster List");
        monsters = MonsterRepo.getAllMonsters();

        for (Monster monster : monsters) {
            String log = "Name: " + monster.getName() + " ,HP: " + monster.getHP() + " ,XP: " + monster.getXP();
            // Writing monsters to log
            Log.d("Monster: : ", log);
        }

    }

    public void testItems(MainActivity activity){

        // Inserting rows
        Log.d("Insert: ", "Inserting ..");
        //WeaponRepo.addItem(new Weapon("Close", 1,1,"Dagger of Wood", 0.1, 0.5));
        //WeaponRepo.addItem(new Weapon("Mid", 190,95,"Sword of a Thousand Truths", 9999, 0.1));
        //WeaponRepo.addItem(new Weapon("Long", 55,3,"David Bowie", 10, 2.5));

        // Reading all weapons
        Log.d("Reading: ", "Reading all weapons..");
        List<Weapon> weapons = WeaponRepo.getAllItems();

        for (Weapon weapon : weapons) {
            String log = "Name: " + weapon.getName() + " ,Attack Type: " + weapon.getAttackType() + ", Attack" + weapon.getAttack();
            // Writing weapons to log
            Log.d("Weapon: : ", log);
        }

        // Getting a monster from the list
        Weapon myWeapon = weapons.get(0);
        // Changing its name
        myWeapon.setName("Dev's Sword");
        // Adding to item table
        WeaponRepo.addItem(myWeapon);
        Weapon addedWeapon = WeaponRepo.getItemByName("Dev's Sword");

        Log.d("Checking data: ", "Fetching Updated Weapon List");
        weapons = WeaponRepo.getAllItems();

        for (Weapon weapon : weapons) {
            String log = "Name: " + weapon.getName() + " ,Attack Type: " + weapon.getAttackType() + ", Attack" + weapon.getAttack();
            // Writing weapons to log
            Log.d("Weapon: : ", log);
        }

        Log.d("Deleting Data: ", "Kill it");
        WeaponRepo.deleteItem(myWeapon);

        Log.d("Checking data: ", "Fetching Updated Weapon List");
        weapons = WeaponRepo.getAllItems();

        for (Weapon weapon : weapons) {
            String log = "Name: " + weapon.getName() + " ,Attack Type: " + weapon.getAttackType() + ", Attack" + weapon.getAttack();
            // Writing weapons to log
            Log.d("Weapon: : ", log);
        }
    }
}

