package attackontinytim.barquest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import junit.framework.Test;

import attackontinytim.barquest.Database.DBHandler;
import attackontinytim.barquest.Database.HeroRepo;
import attackontinytim.barquest.Database.InsertDataValues;
import attackontinytim.barquest.Database.Testing;
import attackontinytim.barquest.Database.DatabaseManager;
public class MainActivity extends AppCompatActivity {

    // global Hero
    public Hero hero;

    // Return
    static public int MAIN_RETURN_CODE = 1;


    // Buttons
    private static Button battle;
    private static Button scan;
    private static Button character;
    private static Button inventory;
    private static Button shop;
    private static Button levelUp;
    private static Button quest;
    private static Button consumables;
    private static Button reset;

    // DB Handler object for all database calls
    private static DBHandler dbHandler;

	// This is called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        onClickButtonListener();

        // Checks for permissions to save and read from storage
        checkPermissions();

        // Loads database handler
        dbHandler = new DBHandler(this.getApplicationContext());
        DatabaseManager.initializeInstance(dbHandler);
        // Inserts value to database if database is empty
        InsertDataValues.createDatabaseValues();

        if (HeroRepo.getAllHeros().size() == 0){
            InsertDataValues.initializeHeroValues();
        }
        else {
            hero = HeroRepo.getHeroByName("HERO");
         }
    }

    private void checkPermissions() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
            new String[]{Manifest.permission.CAMERA}, 0);
        }
    }

    // THIS is disgusting
    public void onClickButtonListener(){
        battle = (Button)findViewById(R.id.battleButton);
        scan = (Button)findViewById(R.id.scannerButton);
        character = (Button)findViewById(R.id.characterButton);
        inventory = (Button)findViewById(R.id.inventoryButton);
        shop = (Button)findViewById(R.id.shopButton);
        levelUp = (Button)findViewById(R.id.levelUpButton);
        quest = (Button)findViewById(R.id.questButton);
        consumables = (Button)findViewById(R.id.consumableButton);
        reset = (Button)findViewById(R.id.CharReset);

        battle.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("attackontinytim.barquest.BattleActivity");
                        Bundle bundle = bundler.generateBundle(hero);

                        intent.putExtras(bundle);
                        startActivityForResult(intent,  MAIN_RETURN_CODE);
                    }
                }
        );
        scan.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("attackontinytim.barquest.ScannerActivity");
                        Bundle bundle = bundler.generateBundle(hero);
                        intent.putExtras(bundle);
                        startActivityForResult(intent,  MAIN_RETURN_CODE);
                    }
                }
        );
        levelUp.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("attackontinytim.barquest.LevelUpActivity");
                        Bundle bundle = bundler.generateBundle(hero);
                        intent.putExtras(bundle);
                        startActivityForResult(intent,  MAIN_RETURN_CODE);
                    }
                }
        );
        character.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("attackontinytim.barquest.CharacterScreenActivity");
                        Bundle bundle = bundler.generateBundle(hero);
                        intent.putExtras(bundle);
                        startActivityForResult(intent,  MAIN_RETURN_CODE);
                    }
                }
        );
        inventory.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("attackontinytim.barquest.InventoryActivity");
                        Bundle bundle = bundler.generateBundle(hero);
                        intent.putExtras(bundle);
                        startActivityForResult(intent,  MAIN_RETURN_CODE);
                    }
                }
        );
        shop.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("attackontinytim.barquest.ShopActivity");
                        Bundle bundle = bundler.generateBundle(hero);

                        intent.putExtras(bundle);
                        startActivityForResult(intent,  MAIN_RETURN_CODE);
                    }
                }
        );
        quest.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("attackontinytim.barquest.QuestActivity");
                        Bundle bundle = bundler.generateBundle(hero);
                        intent.putExtras(bundle);
                        startActivityForResult(intent,  MAIN_RETURN_CODE);
                    }
                }
        );
        consumables.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("attackontinytim.barquest.ConsumableActivity");
                        Bundle bundle = bundler.generateBundle(hero);
                        intent.putExtras(bundle);
                        startActivityForResult(intent,  MAIN_RETURN_CODE);
                    }
                }
        );

        reset.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        dbHandler.resetData();
                        hero = HeroRepo.getHeroByName("HERO");
                    }
                }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

	// This reconstructs the hero after a called activity ends
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                default:
                    Bundle bundle = data.getExtras();
                    hero = bundler.unbundleHero(bundle);
            }
        }
    }
}
