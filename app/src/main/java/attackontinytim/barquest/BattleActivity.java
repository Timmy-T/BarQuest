package attackontinytim.barquest;

import android.app.Activity;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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
import java.util.Collections;

import attackontinytim.barquest.Database.HeroRepo;
import attackontinytim.barquest.Database.InventoryRepo;
import attackontinytim.barquest.Database.Monster;
import attackontinytim.barquest.Database.MonsterRepo;
import attackontinytim.barquest.Database.Weapon;
import attackontinytim.barquest.Database.WeaponRepo;
import attackontinytim.barquest.Database.ConsumableItem;
import attackontinytim.barquest.Database.ConsumableRepo;

public class BattleActivity extends AppCompatActivity /*implements Parcelable*/{

    private Battle battle;
    private Hero hero;
    private Monster enemy;
    private ConsumableActivity consumableActivity;

    // Return
    static public int MAIN_RETURN_CODE = 1;
    final static public int LEVEL_UP_RETURN = 6969;

    // Buttons
    private static Button attack;
    private static Button item;
    private static Button flee;

    // Description variables
    private String attacker;
    private String usedattacks;
    private String defender;
    private int damage;
    private String exc;

    // Music
    private MediaPlayer mp;
    
    private boolean aftermath;

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
            enemy = new Monster(1,"Bob", 100, 50, "Close", 5.0, 1, "Common", 5, 5, 5, "bug");
        }

        battle = new Battle(hero, enemy);
        aftermath = false;

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
        HeroHPBar.setMax(battle.hero.getHP());
        HeroHPBar.setProgress(battle.hero.getHP());

        // Hook up UI variables to backend variables for Monster
        TextView MonName = (TextView) findViewById(R.id.MonName);
        TextView MonLvl = (TextView) findViewById(R.id.monlvl);
        TextView TotalMonHP = (TextView) findViewById(R.id.monhp);
        TextView CurrMonHP = (TextView) findViewById(R.id.currMonHP);
        ProgressBar MonHPBar = (ProgressBar) findViewById(R.id.MonHPBar);
        ImageView monPic = (ImageView) findViewById(R.id.monsterPic);

        MonName.setText(String.valueOf(battle.enemy.getName()));
        MonLvl.setText(String.valueOf(battle.enemy.getLevel()));
        TotalMonHP.setText(String.valueOf(enemy.getHP()));
        CurrMonHP.setText(String.valueOf(battle.battleEnemy.getHP()));
        MonHPBar.setVisibility(View.VISIBLE);
        MonHPBar.setMax(battle.enemy.getHP());
        MonHPBar.setProgress(battle.enemy.getHP());

        int resourceId = this.getResources().getIdentifier(battle.enemy.getImage(), "drawable", getPackageName());
        monPic.setImageResource(resourceId);
        if(monPic.getDrawable() == null) {
            resourceId = this.getResources().getIdentifier("temp_monster", "drawable", getPackageName());
            monPic.setImageResource(resourceId);
        }

        // Music
        mp = MediaPlayer.create(this, R.raw.battle_music);
        mp.start();

        onClickButtonListener();

        Tutorial.battleTutorial(this);
    }

    public void onClickButtonListener() {
        attack = (Button) findViewById(R.id.attackButton);
        item = (Button) findViewById(R.id.itemButton);
        flee = (Button) findViewById(R.id.fleeButton);
        
        final AlertDialog endDialog = new AlertDialog.Builder(BattleActivity.this).create();
        endDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",		
                new DialogInterface.OnClickListener() {		
                    public void onClick(DialogInterface dialog, int which) {		
                        end();		
                    }		
                });

        attack.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        attack.setClickable(false);
                        final TextView CurrMonHP = (TextView) findViewById(R.id.currMonHP);
                        final TextView CurrHPStat = (TextView) findViewById(R.id.currCharHP);

                        battle.checkPriority();

                        if(battle.heroPriority()) {
                            damage = battle.battleEnemy.getHP();
                            battle.heroTurn();
                            attacker = battle.hero.getName();
                            usedattacks = " attacks ";
                            defender = battle.enemy.getName();
                            damage = damage - battle.battleEnemy.getHP();
                            exc = " damage!";
                            reloadBattleScreen();

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run(){
                                    if (battle.isWon()) {
                                        if (!aftermath){
                                            aftermath = true;
                                            if(battle.setReward()){
                                                Intent intent = new Intent("attackontinytim.barquest.LevelUpActivity");
                                                Bundle bundle = bundler.generateBundle(hero);
                                                intent.putExtras(bundle);
                                                startActivityForResult(intent, LEVEL_UP_RETURN);
                                            }
                                        }
                                        String weapReward = battle.winWeaponName;
                                        String itemReward = battle.winItemName;
                                        String moneyReward = String.valueOf(battle.enemy.getMoney());
                                        String expReward = String.valueOf(battle.enemy.getXP());
                                        endDialog.setMessage("You won!\nReward: " + weapReward + itemReward + "\nMoney: +" + moneyReward + "\nEXP: +" + expReward);
                                        endDialog.show();
                                    }
                                }
                            },1500);
                        }
                        else {
                            battle.enemyTurn();
                            attacker = battle.enemy.getName();
                            usedattacks = " attacks ";
                            defender = battle.hero.getName();
                            damage = battle.enemy.getAttack();
                            exc = " damage!";
                            reloadBattleScreen();

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run(){
                                    if (battle.isLost()) {
                                        if(!aftermath){
                                            aftermath = true;
                                            battle.setPenalty();
                                        }
                                        endDialog.setMessage("You lost!\nMoney: -" + String.valueOf(hero.getMoney()/10));
                                        endDialog.show();
                                    }
                                }
                            },1500);
                        }

                        //insert pause here for dramatic effect
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run(){
                                if (battle.heroPriority()) {
                                    battle.enemyTurn();
                                    attacker = battle.enemy.getName();
                                    usedattacks = " attacks ";
                                    defender = battle.hero.getName();
                                    damage = battle.enemy.getAttack();
                                    exc = " damage!";
                                    reloadBattleScreen();

                                    handler.postDelayed(new Runnable() {
                                        public void run(){
                                            if (battle.isLost()) {
                                                if (!aftermath){
                                                    aftermath = true;
                                                    battle.setPenalty();
                                                }
                                                endDialog.setMessage("You lost!\nMoney: -" + String.valueOf(hero.getMoney()/10));
                                                endDialog.show();
                                            }
                                        }
                                    },1500);
                                }
                                else {
                                    damage = battle.battleEnemy.getHP();
                                    battle.heroTurn();
                                    attacker = battle.hero.getName();
                                    usedattacks = " attacks ";
                                    defender = battle.enemy.getName();
                                    damage = damage - battle.battleEnemy.getHP();
                                    exc = " damage!";
                                    reloadBattleScreen();

                                    handler.postDelayed(new Runnable() {
                                        public void run(){
                                            if (battle.isWon()) {
                                                if (!aftermath){
                                                    aftermath = true;
                                                    if(battle.setReward()){
                                                        Intent intent = new Intent("attackontinytim.barquest.LevelUpActivity");
                                                        Bundle bundle = bundler.generateBundle(hero);
                                                        intent.putExtras(bundle);
                                                        startActivityForResult(intent, LEVEL_UP_RETURN);
                                                    }
                                                }
                                                String weapReward = battle.winWeaponName;
                                                String itemReward = battle.winItemName;
                                                String moneyReward = String.valueOf(battle.enemy.getMoney());
                                                String expReward = String.valueOf(battle.enemy.getXP());
                                                endDialog.setMessage("You won!\nReward: " + weapReward + itemReward + "\nMoney: +" + moneyReward + "\nEXP: +" + expReward);
                                                endDialog.show();
                                            }
                                        }
                                    },1500);
                                }
                            }
                        }, 1000); //wait 1s

                        handler.postDelayed(new Runnable() {
                            public void run(){
                                attack.setClickable(true);
                            }
                        },1000);

                    }
                }
        );

        item.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // TODO: make a dialog box and a way to "go back" without resetting battle
                        // temporary; need to find a way to click "back" and not go back to MainActivity
                        /////////////////////////////
                        ////////////////////////////
                        // CHECK COMMENTS BELOW
                        /////////////////////////////
                        /////////////////////////////
                        Intent intent = new Intent("attackontinytim.barquest.ConsumableActivity");
                        Bundle bundle = bundler.generateBundle(hero);
                        intent.putExtras(bundle);
                        startActivityForResult(intent, 9000);

                        reloadBattleScreen();
                    }
                }
        );

        // Robert says: Check out the end function below
        flee.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                if (battle.calc_flee()) {
                                    Intent intent = new Intent("attackontinytim.barquest.MainActivity");
                                    Bundle bundle = bundler.generateBundle(hero);
                                    setResult(RESULT_OK, getIntent().putExtras(bundle));

                                    AlertDialog fleeDialog = new AlertDialog.Builder(BattleActivity.this).create();
                                    fleeDialog.setMessage("You successfully fled!");
                                    fleeDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    end();
                                                }
                                            });
                                    fleeDialog.show();
                                }
                                else {
                                    AlertDialog fleeDialog = new AlertDialog.Builder(BattleActivity.this).create();
                                    fleeDialog.setMessage("You couldn't escape!");
                                    fleeDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            });
                                    fleeDialog.show();

                                    battle.enemyTurn();
                                    attacker = battle.enemy.getName();
                                    defender = battle.hero.getName();
                                    damage = battle.enemy.getAttack();
                                    reloadBattleScreen();

                                    if (battle.isLost()) {
                                        battle.setPenalty();
                                        end();
                                    }
                                }
                            }
                        }, 1000);
                    }
                }
        );
    }

    // Completion of activity; not the same as pressing back
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case (9000) : {
                    if (resultCode == Activity.RESULT_OK) {
                        final TextView CurrHPStat = (TextView) findViewById(R.id.currCharHP);
                        String pot_drank_name = data.getStringExtra("Consumable");
                        battle.consumeItem(ConsumableRepo.getConsumableByName(pot_drank_name));

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run(){
                                reloadBattleScreen();
                                CurrHPStat.setText(String.valueOf(battle.battleHero.getHP()));
                            }
                        }, 1000); //wait 1s
                        handler.postDelayed(new Runnable() {
                            public void run(){
                                battle.enemyTurn();
                                attacker = battle.hero.getName();
                                usedattacks = " used ";
                                defender = battle.usedItem.getName();
                                damage = -1;
                                exc = "!";
                                reloadBattleScreen();
                            }
                        }, 1500); //wait 1s
                    }
                    break;
                }
                case (LEVEL_UP_RETURN) :{
                    hero = HeroRepo.getHeroByName(hero.getName());
                }
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
        mp.stop();
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
        TextView usedattacksText = (TextView) findViewById(R.id.usedattacks);
        TextView defenderText = (TextView) findViewById(R.id.defenderText);
        TextView damageText = (TextView) findViewById(R.id.damageText);
        TextView excText = (TextView) findViewById(R.id.exc);

        attackerText.setText(attacker);
        usedattacksText.setText(usedattacks);
        defenderText.setText(defender);
        if(damage == -1)
            damageText.setText(battle.usedItem.getEffect());
        else
            damageText.setText(String.valueOf(damage));
        excText.setText(exc);

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
