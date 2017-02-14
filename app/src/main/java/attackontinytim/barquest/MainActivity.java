package attackontinytim.barquest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.content.DialogInterface;

public class MainActivity extends AppCompatActivity {

    public Player player = new Player("HERO");


    // Buttons
    private static Button battle;
    private static Button scan;
    private static Button character;
    private static Button inventory;
    private static Button shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        onClickButtonListener();
    }

    public void onClickButtonListener(){
        battle = (Button)findViewById(R.id.battleButton);
        scan = (Button)findViewById(R.id.scannerButton);
        character = (Button)findViewById(R.id.characterButton);
        inventory = (Button)findViewById(R.id.inventoryButton);
        shop = (Button)findViewById(R.id.shopButton);
        battle.setOnClickListener(
            new View.OnClickListener() {
                public void onClick(View v) {
                     Intent intent = new Intent("attackontinytim.barquest.BattleActivity");
                    intent.putExtra("attackontinytim.barquest.Player", player);
                    startActivity(intent);
                }
            }
        );
        scan.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("attackontinytim.barquest.ScannerActivity");
                        startActivity(intent);
                    }
                }
        );
        character.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("attackontinytim.barquest.CharacterScreenActivity");
                        startActivity(intent);
                    }
                }
        );
        inventory.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("attackontinytim.barquest.InventoryActivity");
                        startActivity(intent);
                    }
                }
        );
        shop.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("attackontinytim.barquest.ShopActivity");
                        startActivity(intent);
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
}
