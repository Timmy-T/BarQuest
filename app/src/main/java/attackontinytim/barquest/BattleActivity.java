package attackontinytim.barquest;

import android.util.Log;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import attackontinytim.barquest.Database.DBHandler;
import attackontinytim.barquest.Database.Monster;
import attackontinytim.barquest.Database.Weapon;

public class BattleActivity extends AppCompatActivity /*implements Parcelable*/{

    private Battle battle;
    private Hero hero;
    private Monster enemy;
    int HeroHP = 0;
    int MonHP = 0;

    // Return
    static public int MAIN_RETURN_CODE = 1;

    // Buttons
    private static Button attack;
    private static Button item;
    private static Button flee;

	// This is what is done when the BattleActivity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle_laout);
        Bundle bundle = getIntent().getExtras();

        hero = bundler.unbundleHero(bundle);
        // enemy = new Monster(/*some sort of Cursor*/);

        try{
            int monsterHash = bundle.getInt("MonsterHash");

            DBHandler db = new DBHandler(this.getApplicationContext());
            List<Monster> mon = db.getAllMonsters();
            int numOfMonsters = mon.size();

            enemy = mon.get(monsterHash%numOfMonsters);
        }
        catch (Exception e){
            Log.d("HERE", e.getMessage());
            enemy = new Monster(1,"Bob", 100, 50, "Close", 5.0, 1, "Common", 5, 5, 5);
        }

        HeroHP = hero.getHP();
        MonHP = enemy.getHP();

        battle = new Battle(hero, enemy);

        // Hook up UI variables to backend variables for Hero
        TextView Name = (TextView) findViewById(R.id.CharName);
        TextView LvlStat = (TextView) findViewById(R.id.lvlstat);
        TextView TotalHPStat = (TextView) findViewById(R.id.hpstat);
        TextView CurrHPStat = (TextView) findViewById(R.id.currCharHP);

        Name.setText(String.valueOf(battle.hero.getName()));
        LvlStat.setText(String.valueOf(battle.hero.getLevel()));
        TotalHPStat.setText(String.valueOf(HeroHP));
        CurrHPStat.setText(String.valueOf(battle.hero.getHP()));

        // Hook up UI variables to backend variables for Monster
        TextView MonName = (TextView) findViewById(R.id.MonName);
        TextView MonLvl = (TextView) findViewById(R.id.monlvl);
        TextView TotalMonHP = (TextView) findViewById(R.id.monhp);
        TextView CurrMonHP = (TextView) findViewById(R.id.currMonHP);

        MonName.setText(String.valueOf(battle.enemy.getName()));
        MonLvl.setText(String.valueOf(battle.enemy.getLevel()));
        TotalMonHP.setText(String.valueOf(MonHP)); // change this once you can construct a monster
        CurrMonHP.setText(String.valueOf(battle.enemy.getHP()));

        onClickButtonListener();

    }

    public void onClickButtonListener() {
        attack = (Button) findViewById(R.id.attackButton);
        item = (Button) findViewById(R.id.itemButton);
        flee = (Button) findViewById(R.id.fleeButton);

        attack.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        String TAG = BattleActivity.class.getSimpleName();

                        /**insert Battle() functions here to do calculations and update accordingly*/
                        Log.d(TAG, "Character HP before battle:" + String.valueOf(battle.hero.getHP()));
                        Log.d(TAG, "Monster HP before battle:" + String.valueOf(battle.enemy.getHP()));
                        battle.performBattle();
                        Log.d(TAG, "Character HP after battle:" + String.valueOf(battle.hero.getHP()));
                        Log.d(TAG, "Monster HP after battle:" + String.valueOf(battle.enemy.getHP()));
                        reloadBattleScreen();
                        Log.d(TAG, "Character HP after reload:" + String.valueOf(battle.hero.getHP()));
                        Log.d(TAG, "Monster HP after reload:" + String.valueOf(battle.enemy.getHP()));
                        /*Intent intent = new Intent("attackontinytim.barquest.BattleActivity");
                        Bundle bundle = bundler.generateBundle(hero);
                        intent.putExtras(bundle);
                        startActivityForResult(intent, MAIN_RETURN_CODE);*/
                    }
                }
        );

        item.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // temporary; need to find a way to click "back" and not go back to MainActivity
						/////////////////////////////
						////////////////////////////
						// CHECK COMMENTS BELOW
						/////////////////////////////
						/////////////////////////////
                        Intent intent = new Intent("attackontinytim.barquest.InventoryActivity");
                        Bundle bundle = bundler.generateBundle(hero);
                        intent.putExtras(bundle);
                        startActivityForResult(intent, MAIN_RETURN_CODE);
                    }
                }
        );
		
		// Robert says: Check out the end function below
        flee.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // need to reset battle()?
                        Intent intent = new Intent("attackontinytim.barquest.MainActivity");
                        Bundle bundle = bundler.generateBundle(hero);
                        setResult(RESULT_OK,getIntent().putExtras(bundle));
                        finish();
                    }
                }
        );
    }

		// Completion of activity; not the same as pressing back
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (resultCode != RESULT_CANCELED) {
                switch (requestCode) {
                    default:
                        end();
                }
            }
        }
		
	// This is what is called when back is pressed
    @Override
    public void onBackPressed() {
		// Change it to whatever you want; right now it just ends
        end();
    }

	// end the activity
    private void end(){
        Bundle bundle = bundler.generateBundle(hero);
        setResult(RESULT_OK,getIntent().putExtras(bundle));

        finish();
    }

    protected void reloadBattleScreen() {
        /* Reloads the Battle Screen to update stat values rather than
            reloading the entire page from scratch
         */

        // Hook up UI variables to backend variables for Hero
        TextView Name = (TextView) findViewById(R.id.CharName);
        TextView LvlStat = (TextView) findViewById(R.id.lvlstat);
        TextView TotalHPStat = (TextView) findViewById(R.id.hpstat);
        TextView CurrHPStat = (TextView) findViewById(R.id.currCharHP);

        Name.setText(String.valueOf(battle.hero.getName()));
        LvlStat.setText(String.valueOf(battle.hero.getLevel()));
        TotalHPStat.setText(String.valueOf(HeroHP));
        CurrHPStat.setText(String.valueOf(battle.hero.getHP()));

        // Hook up UI variables to backend variables for Monster
        TextView MonName = (TextView) findViewById(R.id.MonName);
        TextView MonLvl = (TextView) findViewById(R.id.monlvl);
        TextView TotalMonHP = (TextView) findViewById(R.id.monhp);
        TextView CurrMonHP = (TextView) findViewById(R.id.currMonHP);

        MonName.setText(String.valueOf(battle.enemy.getName()));
        MonLvl.setText(String.valueOf(battle.enemy.getLevel()));
        TotalMonHP.setText(String.valueOf(MonHP)); // change this once you can construct a monster
        CurrMonHP.setText(String.valueOf(battle.enemy.getHP()));

    }

}
