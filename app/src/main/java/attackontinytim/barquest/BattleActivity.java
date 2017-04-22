package attackontinytim.barquest;

import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import attackontinytim.barquest.Database.Monster;
import attackontinytim.barquest.Database.MonsterRepo;
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

    // Description variables
    private String attacker;
    private String defender;
    private int damage;

    // This is what is done when the BattleActivity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle_laout);
        Bundle bundle = getIntent().getExtras();

        hero = bundler.unbundleHero(bundle);
        // enemy = new Monster(/*some sort of Cursor*/);

        try{
            enemy = MonsterRepo.getMonsterFromHash(bundle.getInt("MonsterHash"));
        }
        catch (Exception e){
            Log.d("HERE", e.getMessage());
            enemy = new Monster(1,"Bob", 100, 50, "Close", 5.0, 1, "Common", 5, 5, 5);
        }

        battle = new Battle(hero, enemy);

        // Hook up UI variables to backend variables for Hero
        TextView Name = (TextView) findViewById(R.id.CharName);
        TextView LvlStat = (TextView) findViewById(R.id.lvlstat);
        TextView TotalHPStat = (TextView) findViewById(R.id.hpstat);
        TextView CurrHPStat = (TextView) findViewById(R.id.currCharHP);
        ProgressBar HeroHPBar = (ProgressBar) findViewById(R.id.CharHPBar);

        Name.setText(String.valueOf(battle.hero.getName()));
        LvlStat.setText(String.valueOf(battle.hero.getLevel()));
        TotalHPStat.setText(String.valueOf(hero.getHP()));
        CurrHPStat.setText(String.valueOf(battle.battleHero.getHP()));
        HeroHPBar.setVisibility(View.VISIBLE);
        HeroHPBar.setMax(battle.hero.getHP());
        HeroHPBar.setProgress(battle.hero.getHP());

        // Hook up UI variables to backend variables for Monster
        TextView MonName = (TextView) findViewById(R.id.MonName);
        TextView MonLvl = (TextView) findViewById(R.id.monlvl);
        TextView TotalMonHP = (TextView) findViewById(R.id.monhp);
        TextView CurrMonHP = (TextView) findViewById(R.id.currMonHP);
        ProgressBar MonHPBar = (ProgressBar) findViewById(R.id.MonHPBar);

        MonName.setText(String.valueOf(battle.enemy.getName()));
        MonLvl.setText(String.valueOf(battle.enemy.getLevel()));
        TotalMonHP.setText(String.valueOf(enemy.getHP()));
        CurrMonHP.setText(String.valueOf(battle.battleEnemy.getHP()));
        MonHPBar.setVisibility(View.VISIBLE);
        MonHPBar.setMax(battle.enemy.getHP());
        MonHPBar.setProgress(battle.enemy.getHP());

        onClickButtonListener();

    }

    public void onClickButtonListener() {
        attack = (Button) findViewById(R.id.attackButton);
        item = (Button) findViewById(R.id.itemButton);
        flee = (Button) findViewById(R.id.fleeButton);
        //TODO: implement flee and item functionality

        attack.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        final TextView CurrMonHP = (TextView) findViewById(R.id.currMonHP);
                        final TextView CurrHPStat = (TextView) findViewById(R.id.currCharHP);

                        if(battle.heroPriority()) {
                            damage = battle.battleEnemy.getHP();
                            battle.heroTurn();
                            attacker = battle.hero.getName();
                            defender = battle.enemy.getName();
                            damage = damage - battle.battleEnemy.getHP();
                            reloadBattleScreen();
                        }
                        else {
                            battle.enemyTurn();
                            attacker = battle.enemy.getName();
                            defender = battle.hero.getName();
                            damage = battle.enemy.getAttack();
                            reloadBattleScreen();
                        }

                        //insert pause here for dramatic effect
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run(){
                                if(battle.heroPriority()) {
                                    battle.enemyTurn();
                                    attacker = battle.enemy.getName();
                                    defender = battle.hero.getName();
                                    damage = battle.enemy.getAttack();
                                    reloadBattleScreen();
                                }
                                else {
                                    damage = battle.battleEnemy.getHP();
                                    battle.heroTurn();
                                    attacker = battle.hero.getName();
                                    defender = battle.enemy.getName();
                                    damage = damage - battle.battleEnemy.getHP();
                                    reloadBattleScreen();
                                }
                            }
                        }, 2000); //wait 1.5s
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
        /* Reloads the Battle Screen to update certain stat values rather than
           reloading the entire page from scratch
        */

        // Update battle description
        TableLayout battleDesc = (TableLayout) findViewById(R.id.battleDescription);
        battleDesc.setVisibility(View.VISIBLE);
        TextView attackerText = (TextView) findViewById(R.id.attackerText);
        TextView defenderText = (TextView) findViewById(R.id.defenderText);
        TextView damageText = (TextView) findViewById(R.id.damageText);

        attackerText.setText(attacker);
        defenderText.setText(defender);
        damageText.setText(String.valueOf(damage));

        // Update Player stats
        TextView CurrHPStat = (TextView) findViewById(R.id.currCharHP);
        ProgressBar HeroHPBar = (ProgressBar) findViewById(R.id.CharHPBar);

        CurrHPStat.setText(String.valueOf(battle.battleHero.getHP()));
        HeroHPBar.setProgress(battle.battleHero.getHP());

        // Update Monster stats
        TextView CurrMonHP = (TextView) findViewById(R.id.currMonHP);
        ProgressBar MonHPBar = (ProgressBar) findViewById(R.id.MonHPBar);

        CurrMonHP.setText(String.valueOf(battle.battleEnemy.getHP()));
        MonHPBar.setProgress(battle.battleEnemy.getHP());

        // Attack effects
        ImageView attackPic = (ImageView) findViewById(R.id.attackPic);
        attackPic.setVisibility(View.VISIBLE);
        if(attacker == battle.hero.getName())
            attackPic.setScaleX(1);
        else if(attacker == battle.enemy.getName())
            attackPic.setScaleX(-1);

    }
}
