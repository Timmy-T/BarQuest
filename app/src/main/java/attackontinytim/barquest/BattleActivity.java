package attackontinytim.barquest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import attackontinytim.barquest.Database.Monster;
import attackontinytim.barquest.Database.Weapon;

public class BattleActivity extends AppCompatActivity /*implements Parcelable*/{

    private Battle battle;
    private Hero hero;
    private Monster enemy;

    // Return
    static public int MAIN_RETURN_CODE = 1;

    // Buttons
    private static Button attack;
    private static Button item;
    private static Button flee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle_laout);
        Bundle bundle = getIntent().getExtras();

        hero = bundler.unbundleHero(bundle);

        battle = new Battle(hero);

        // Hook up UI variables to backend variables for Hero
        TextView Name = (TextView) findViewById(R.id.CharName);
        TextView LvlStat = (TextView) findViewById(R.id.lvlstat);
        TextView TotalHPStat = (TextView) findViewById(R.id.hpstat);
        TextView CurrHPStat = (TextView) findViewById(R.id.currCharHP);

        Name.setText(String.valueOf(battle.hero.getName()));
        LvlStat.setText(String.valueOf(battle.hero.getLevel()));
        TotalHPStat.setText(String.valueOf(hero.getHP()));
        CurrHPStat.setText(String.valueOf(battle.hero.getHP()));

        // Hook up UI variables to backend variables for Monster
        TextView MonName = (TextView) findViewById(R.id.MonName);
        TextView MonLvl = (TextView) findViewById(R.id.monlvl);
        TextView TotalMonHP = (TextView) findViewById(R.id.monhp);
        TextView CurrMonHP = (TextView) findViewById(R.id.currMonHP);

        MonName.setText(String.valueOf(battle.enemy.getName()));
        MonLvl.setText(String.valueOf(battle.enemy.getLevel()));
        TotalMonHP.setText(String.valueOf(battle.enemy.getHP()));
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
                        // insert Battle() functions here to do calculations and update accordingly
                        Intent intent = new Intent("attackontinytim.barquest.BattleActivity");
                        Bundle bundle = bundler.generateBundle(hero);
                        intent.putExtras(bundle);
                        startActivityForResult(intent, MAIN_RETURN_CODE);
                    }
                }
        );

        item.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // temporary; need to find a way to click "back" and not go back to MainActivity
                        Intent intent = new Intent("attackontinytim.barquest.InventoryActivity");
                        Bundle bundle = bundler.generateBundle(hero);
                        intent.putExtras(bundle);
                        startActivityForResult(intent, MAIN_RETURN_CODE);
                    }
                }
        );

        flee.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // need to reset battle()?
                        Intent intent = new Intent("attackontinytim.barquest.MainActivity");
                        Bundle bundle = bundler.generateBundle(hero);
                        intent.putExtras(bundle);
                        startActivityForResult(intent, MAIN_RETURN_CODE);
                    }
                }
        );
    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (resultCode != RESULT_CANCELED) {
                switch (requestCode) {
                    default:
                        end();
                }
            }
        }

    @Override
    public void onBackPressed() {
        end();
    }

    private void end(){
        Bundle bundle = bundler.generateBundle(hero);
        setResult(RESULT_OK,getIntent().putExtras(bundle));

        finish();
    }
}
