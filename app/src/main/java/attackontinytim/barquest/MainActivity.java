package attackontinytim.barquest;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.Calendar;
import java.util.List;

import junit.framework.Test;

import attackontinytim.barquest.Database.DBHandler;
import attackontinytim.barquest.Database.HeroRepo;
import attackontinytim.barquest.Database.TimerRepo;
import attackontinytim.barquest.Database.InsertDataValues;
import attackontinytim.barquest.Database.Testing;
import attackontinytim.barquest.Database.DatabaseManager;
public class MainActivity extends AppCompatActivity {

    // global Hero
    public Hero hero;

    // Return
    final static public int MAIN_RETURN_CODE = 1;


    // Buttons
    private static Button scan;
    private static Button character;
    private static Button inventory;
    private static Button shop;
    private static Button levelUp;
    private static Button quest;

    // DB Handler object for all database calls
    private static DBHandler dbHandler;

    // Music
    private MediaPlayer mp;

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

        if (HeroRepo.getAllHeros().size() == 0) {
            Tutorial.setAllTrue();
            Tutorial.homeTutorial(this);
            InsertDataValues.initializeHeroValues();
        } else {
            hero = HeroRepo.getHeroByName("HERO");
        }
        mp = MediaPlayer.create(this, R.raw.main_music);
        mp.start();
    }

    private void checkPermissions() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, 0);
        }
    }

    // THIS is disgusting
    public void onClickButtonListener() {
        scan = (Button) findViewById(R.id.scannerButton);
        character = (Button) findViewById(R.id.characterButton);
        inventory = (Button) findViewById(R.id.inventoryButton);
        shop = (Button) findViewById(R.id.shopButton);
        levelUp = (Button) findViewById(R.id.levelUpButton);
        quest = (Button) findViewById(R.id.questButton);

        scan.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        mp.pause();
                        Intent intent = new Intent("attackontinytim.barquest.ScannerActivity");
                        Bundle bundle = bundler.generateBundle(hero);
                        intent.putExtras(bundle);
                        startActivityForResult(intent, MAIN_RETURN_CODE);
                    }
                }
        );
        levelUp.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("attackontinytim.barquest.LevelUpActivity");
                        Bundle bundle = bundler.generateBundle(hero);
                        intent.putExtras(bundle);
                        startActivityForResult(intent, MAIN_RETURN_CODE);
                    }
                }
        );
        character.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("attackontinytim.barquest.CharacterScreenActivity");
                        Bundle bundle = bundler.generateBundle(hero);
                        intent.putExtras(bundle);
                        startActivityForResult(intent, MAIN_RETURN_CODE);
                    }
                }
        );
        inventory.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("attackontinytim.barquest.InventoryActivity");
                        Bundle bundle = bundler.generateBundle(hero);
                        intent.putExtras(bundle);
                        startActivityForResult(intent, MAIN_RETURN_CODE);
                    }
                }
        );
        shop.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("attackontinytim.barquest.ShopActivity");
                        Bundle bundle = bundler.generateBundle(hero);

                        intent.putExtras(bundle);
                        startActivityForResult(intent, MAIN_RETURN_CODE);
                    }
                }
        );
        quest.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("attackontinytim.barquest.QuestActivity");
                        Bundle bundle = bundler.generateBundle(hero);
                        intent.putExtras(bundle);
                        startActivityForResult(intent, MAIN_RETURN_CODE);
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
        switch (requestCode) {
            default:
                try {
                    Bundle bundle = data.getExtras();
                    hero = bundler.unbundleHero(bundle);
                } catch (Exception ex) {
                    hero = HeroRepo.getHeroByName("HERO");
                }

        }
        Tutorial.homeTutorial(this);
        mp.start();
    }
}

