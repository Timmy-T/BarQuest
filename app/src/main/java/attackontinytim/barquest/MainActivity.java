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

    // global Player
    public Player player = new Player("HERO");

    // Return
    static public int MAIN_RETURN_CODE = 1;


    // Buttons
    private static Button battle;
    private static Button scan;
    private static Button character;
    private static Button inventory;
    private static Button shop;
    private static Button levelUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        onClickButtonListener();
    }

    // THIS is disgusting
    public void onClickButtonListener(){
        battle = (Button)findViewById(R.id.battleButton);
        scan = (Button)findViewById(R.id.scannerButton);
        character = (Button)findViewById(R.id.characterButton);
        inventory = (Button)findViewById(R.id.inventoryButton);
        shop = (Button)findViewById(R.id.shopButton);
<<<<<<< HEAD
        levelUp = (Button)findViewById(R.id.levelUpButton);
=======

>>>>>>> refs/remotes/origin/UI
        battle.setOnClickListener(
            new View.OnClickListener() {
                public void onClick(View v) {
                     Intent intent = new Intent("attackontinytim.barquest.BattleActivity");
                    Bundle bundle = new Bundle();

                    bundle.putString("name", player.getName());
                    bundle.putInt("level", player.getLevel());
                    bundle.putInt("hitPoints", player.getHitPoints());
                    bundle.putInt("attack", player.getAttack());
                    bundle.putInt("defense", player.getDefense());
                    bundle.putInt("speed", player.getSpeed());
                    bundle.putInt("experience", player.getExperience());
                    bundle.putInt("money", player.getMoney());

                    bundle.putString("weaponName", player.getActive().getName());
                    bundle.putString("weaponType", player.getActive().getType());
                    bundle.putInt("weaponAttack", player.getActive().getAttack());
                    bundle.putInt("weaponWeight", player.getActive().getWeight());
                    bundle.putInt("weaponCrit", player.getActive().getCrit());

                    intent.putExtras(bundle);
                    startActivityForResult(intent,  MAIN_RETURN_CODE);
                }
            }
        );
        scan.setOnClickListener(
<<<<<<< HEAD
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("attackontinytim.barquest.ScannerActivity");
                        Bundle bundle = new Bundle();

                        bundle.putString("name", player.getName());
                        bundle.putInt("level", player.getLevel());
                        bundle.putInt("hitPoints", player.getHitPoints());
                        bundle.putInt("attack", player.getAttack());
                        bundle.putInt("defense", player.getDefense());
                        bundle.putInt("speed", player.getSpeed());
                        bundle.putInt("experience", player.getExperience());
                        bundle.putInt("money", player.getMoney());

                        bundle.putString("weaponName", player.getActive().getName());
                        bundle.putString("weaponType", player.getActive().getType());
                        bundle.putInt("weaponAttack", player.getActive().getAttack());
                        bundle.putInt("weaponWeight", player.getActive().getWeight());
                        bundle.putInt("weaponCrit", player.getActive().getCrit());

                        intent.putExtras(bundle);
                        startActivityForResult(intent,  MAIN_RETURN_CODE);
                    }
                }
        );
        levelUp.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("attackontinytim.barquest.LevelUpActivity");
                        Bundle bundle = new Bundle();

                        bundle.putString("name", player.getName());
                        bundle.putInt("level", player.getLevel());
                        bundle.putInt("hitPoints", player.getHitPoints());
                        bundle.putInt("attack", player.getAttack());
                        bundle.putInt("defense", player.getDefense());
                        bundle.putInt("speed", player.getSpeed());
                        bundle.putInt("experience", player.getExperience());
                        bundle.putInt("money", player.getMoney());

                        bundle.putString("weaponName", player.getActive().getName());
                        bundle.putString("weaponType", player.getActive().getType());
                        bundle.putInt("weaponAttack", player.getActive().getAttack());
                        bundle.putInt("weaponWeight", player.getActive().getWeight());
                        bundle.putInt("weaponCrit", player.getActive().getCrit());

                        intent.putExtras(bundle);
                        startActivityForResult(intent,  MAIN_RETURN_CODE);
                    }
=======
            new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent("attackontinytim.barquest.ScannerActivity");
                    startActivity(intent);
>>>>>>> refs/remotes/origin/UI
                }
            }
        );
        character.setOnClickListener(
<<<<<<< HEAD
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("attackontinytim.barquest.CharacterScreenActivity");
                        Bundle bundle = new Bundle();

                        bundle.putString("name", player.getName());
                        bundle.putInt("level", player.getLevel());
                        bundle.putInt("hitPoints", player.getHitPoints());
                        bundle.putInt("attack", player.getAttack());
                        bundle.putInt("defense", player.getDefense());
                        bundle.putInt("speed", player.getSpeed());
                        bundle.putInt("experience", player.getExperience());
                        bundle.putInt("money", player.getMoney());

                        bundle.putString("weaponName", player.getActive().getName());
                        bundle.putString("weaponType", player.getActive().getType());
                        bundle.putInt("weaponAttack", player.getActive().getAttack());
                        bundle.putInt("weaponWeight", player.getActive().getWeight());
                        bundle.putInt("weaponCrit", player.getActive().getCrit());

                        intent.putExtras(bundle);
                        startActivityForResult(intent,  MAIN_RETURN_CODE);
                    }
=======
            new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent("attackontinytim.barquest.CharacterScreenActivity");
                    startActivity(intent);
>>>>>>> refs/remotes/origin/UI
                }
            }
        );
        inventory.setOnClickListener(
<<<<<<< HEAD
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("attackontinytim.barquest.InventoryActivity");
                        Bundle bundle = new Bundle();

                        bundle.putString("name", player.getName());
                        bundle.putInt("level", player.getLevel());
                        bundle.putInt("hitPoints", player.getHitPoints());
                        bundle.putInt("attack", player.getAttack());
                        bundle.putInt("defense", player.getDefense());
                        bundle.putInt("speed", player.getSpeed());
                        bundle.putInt("experience", player.getExperience());
                        bundle.putInt("money", player.getMoney());

                        bundle.putString("weaponName", player.getActive().getName());
                        bundle.putString("weaponType", player.getActive().getType());
                        bundle.putInt("weaponAttack", player.getActive().getAttack());
                        bundle.putInt("weaponWeight", player.getActive().getWeight());
                        bundle.putInt("weaponCrit", player.getActive().getCrit());

                        intent.putExtras(bundle);
                        startActivityForResult(intent,  MAIN_RETURN_CODE);
                    }
=======
            new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent("attackontinytim.barquest.InventoryActivity");
                    startActivity(intent);
>>>>>>> refs/remotes/origin/UI
                }
            }
        );
        shop.setOnClickListener(
<<<<<<< HEAD
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("attackontinytim.barquest.ShopActivity");
                        Bundle bundle = new Bundle();

                        bundle.putString("name", player.getName());
                        bundle.putInt("level", player.getLevel());
                        bundle.putInt("hitPoints", player.getHitPoints());
                        bundle.putInt("attack", player.getAttack());
                        bundle.putInt("defense", player.getDefense());
                        bundle.putInt("speed", player.getSpeed());
                        bundle.putInt("experience", player.getExperience());
                        bundle.putInt("money", player.getMoney());

                        bundle.putString("weaponName", player.getActive().getName());
                        bundle.putString("weaponType", player.getActive().getType());
                        bundle.putInt("weaponAttack", player.getActive().getAttack());
                        bundle.putInt("weaponWeight", player.getActive().getWeight());
                        bundle.putInt("weaponCrit", player.getActive().getCrit());

                        intent.putExtras(bundle);
                        startActivityForResult(intent,  MAIN_RETURN_CODE);
                    }
=======
            new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent("attackontinytim.barquest.ShopActivity");
                    startActivity(intent);
>>>>>>> refs/remotes/origin/UI
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                default:
                    Bundle bundle = data.getExtras();

                    Player player = new Player();
                    player.set_name(bundle.getString("name"));
                    player.set_level(bundle.getInt("level"));
                    player.set_hit_points(bundle.getInt("hitPoints"));
                    player.set_attack(bundle.getInt("attack"));
                    player.set_defense(bundle.getInt("defense"));
                    player.set_speed(bundle.getInt("speed"));
                    player.set_experience(bundle.getInt("experience"));
                    player.set_money(bundle.getInt("money"));

                    String weaponName = bundle.getString("weaponName");
                    String weaponType = bundle.getString("weaponType");
                    int weaponAttack = bundle.getInt("weaponAttack");
                    int weaponWeight = bundle.getInt("weaponWeight");
                    int weaponCrit = bundle.getInt("weaponCrit");
                    Weapon active = new Weapon(weaponName, weaponAttack, weaponWeight, weaponCrit, weaponType);

                    player.set_active(active);
            }
        }
    }
}
