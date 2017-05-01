package attackontinytim.barquest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import attackontinytim.barquest.Database.ConsumableItem;
import attackontinytim.barquest.Database.ConsumableRepo;
import attackontinytim.barquest.Database.HeroRepo;
import attackontinytim.barquest.Database.InventoryRepo;
import attackontinytim.barquest.Database.Weapon;
import attackontinytim.barquest.Database.WeaponRepo;

/**
 * Created by NeilBlood on 4/30/2017.
 */

public class fragment_mainActivity extends AppCompatActivity {

    public Hero hero;

    Random rand = new Random();

    //3 types of hp pots
    private  int randHP = rand.nextInt(3)+1;
    //3 types of stat pots
    private int randStat1 = rand.nextInt(3)+1;
    //3 types of stat pots
    private int randStat2 = rand.nextInt(3)+1;
    //3 types of debuff pots
    private int randStat3 = rand.nextInt(3)+1;
    //6 types of weapons
    private int randWeapon = rand.nextInt(6)+1;




    //Buttons~
    private Button HP_Pot_button;
    private Button Stat_pot_1_button;
    private Button Stat_pot_2_button;
    private Button stat_pot_3_button;
    private Button weapon_button;

    //Consumables
    //public static ConsumableItem getConsumableByName(String name)
    //InventoryRepo.addItemToInventory(ConsumableRepo.getConsumableByName("Virus Scan"));

    //statpot1 = ConsumableRepo.getConsumableByName("Debug");
    //statpot1quantity = ConsumableRepo.getItemQuantity(statpot1) ;

    private ConsumableItem hpPot;
    private ConsumableItem statPot1;
    private ConsumableItem statPot2;
    private ConsumableItem statPot3;
    private Weapon weapon;

    private List<ConsumableItem> hpList;

    private List<ConsumableItem> attackList;
    private List<ConsumableItem> defenseList;
    private List<ConsumableItem> speedList;
    private List<ConsumableItem> statPotList;


    private List<ConsumableItem> debuffAttackList;
    private List<ConsumableItem> debuffDefenseList;
    private List<ConsumableItem> debuffSpeedList;
    private List<ConsumableItem> debuffPotList;

    private List<Weapon> weaponList;




    // This is called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_layout);

        Bundle bundle = getIntent().getExtras();
        hero = bundler.unbundleHero(bundle);



        //Hook up UI to backend stuff
        TextView hp_pot_label = (TextView) findViewById(R.id.hp_pot_label);
        TextView stat_pot_1_label = (TextView) findViewById(R.id.stat_pot_1_label);
        TextView stat_pot_2_label = (TextView) findViewById(R.id.stat_pot_2_label);
        TextView stat_pot_3_label = (TextView) findViewById(R.id.stat_pot_3_label);
        TextView weapon_label = (TextView) findViewById(R.id.weapon_label);
        TextView hp_pot_quantity = (TextView) findViewById(R.id.hp_pot_quantity);
        TextView stat_pot_1_quantity = (TextView) findViewById(R.id.stat_pot_1_quantity);
        TextView stat_pot_2_quantity = (TextView) findViewById(R.id.stat_pot_2_quantity);
        TextView stat_pot_3_quantity = (TextView) findViewById(R.id.stat_pot_3_quantity);
        TextView weapon_quantity = (TextView) findViewById(R.id.weapon_quantity);

        //get random potions

        hpList = ConsumableRepo.getConsumableListByType("Heal");
        attackList = ConsumableRepo.getConsumableListByType("Attack Up");
        defenseList = ConsumableRepo.getConsumableListByType("Defense Up");
        speedList = ConsumableRepo.getConsumableListByType("speedList");
        debuffSpeedList = ConsumableRepo.getConsumableListByType("Monster Speed Debuff");
        debuffDefenseList = ConsumableRepo.getConsumableListByType("Monster Defense Debuff");
        debuffAttackList = ConsumableRepo.getConsumableListByType("Monster Attack Debuff");
        weaponList = WeaponRepo.getAllItems();

        //make a master list of all stat pots
        statPotList = attackList;
        statPotList.addAll(defenseList);
        statPotList.addAll(speedList);
        //shuffle this new master list.
        Collections.shuffle(statPotList);

        //make a master list of all debuff pots
        debuffPotList = debuffAttackList;
        debuffPotList.addAll(debuffDefenseList);
        debuffPotList.addAll(debuffSpeedList);
        //shuffle that
        Collections.shuffle(debuffPotList);

        //get a random
        hpPot = hpList.get(randHP);
        statPot1 = statPotList.get(randStat1);
        statPot2 = statPotList.get(randStat2);
        statPot3 = debuffPotList.get(randStat3);
        weapon = weaponList.get(randWeapon);


        //set labels to name of item + cost
        hp_pot_label.setText(hpPot.getName() + " " + hpPot.getValue());
        stat_pot_1_label.setText(statPot1.getName()+" "+statPot1.getValue());
        stat_pot_2_label.setText(statPot2.getName()+" "+statPot2.getValue());
        stat_pot_3_label.setText(statPot3.getName()+" "+statPot3.getValue());
        weapon_label.setText(weapon.getName()+" "+weapon.getValue());

        //set quantities to their values

        hp_pot_quantity.setText(String.valueOf(InventoryRepo.getItemQuantity(hpPot)));
        stat_pot_1_quantity.setText(String.valueOf(InventoryRepo.getItemQuantity(statPot1)));
        stat_pot_2_quantity.setText(String.valueOf(InventoryRepo.getItemQuantity(statPot2)));
        stat_pot_3_quantity.setText(String.valueOf(InventoryRepo.getItemQuantity(statPot3)));
        weapon_quantity.setText(String.valueOf(InventoryRepo.getItemQuantity(weapon)));

        onClickButtonListener();

    }

    // This is called when the activity is ended via result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                default:
                    // it ends the acitivity
                    end();
            }
        }
    }

    //THIS is beautiful
    public void onClickButtonListener(){

        HP_Pot_button = (Button)findViewById(R.id.HP_Pot_button);
        Stat_pot_1_button = (Button)findViewById(R.id.Stat_pot_1_button);
        Stat_pot_2_button = (Button)findViewById(R.id.Stat_pot_2_button);
        stat_pot_3_button = (Button)findViewById(R.id.stat_pot_3_button);
        weapon_button = (Button)findViewById(R.id.weapon_button);


        HP_Pot_button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        if(hero.getMoney() >= hpPot.getValue()){
                            hero.setMoney(hero.getMoney() - hpPot.getValue());
                            InventoryRepo.addItemToInventory(hpPot);
                            HeroRepo.updateHero(hero);
                        }
                    }
                }
        );

        Stat_pot_1_button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        if(hero.getMoney() >= statPot1.getValue()){
                            hero.setMoney(hero.getMoney() - statPot1.getValue());
                            InventoryRepo.addItemToInventory(statPot1);
                            HeroRepo.updateHero(hero);
                        }
                    }
                }

        );

        Stat_pot_2_button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        if(hero.getMoney() >= statPot2.getValue()){
                            hero.setMoney(hero.getMoney() - statPot2.getValue());
                            InventoryRepo.addItemToInventory(statPot2);
                            HeroRepo.updateHero(hero);
                        }
                    }
                }

        );

        stat_pot_3_button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        if(hero.getMoney() >= statPot1.getValue()){
                            hero.setMoney(hero.getMoney() - statPot1.getValue());
                            InventoryRepo.addItemToInventory(statPot1);
                            HeroRepo.updateHero(hero);
                        }
                    }
                }
        );

        weapon_button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        if(hero.getMoney() >= weapon.getValue()){
                            hero.setMoney(hero.getMoney() - weapon.getValue());
                            InventoryRepo.addItemToInventory(weapon);
                            HeroRepo.updateHero(hero);
                        }
                    }
                }
        );

    }


    // This is what is called when back is pressed
    @Override
    public void onBackPressed() {
        // it ends the acitivity
        end();
    }

    // This is the act of ending the activity
    private void end(){
        Bundle bundle = bundler.generateBundle(hero);

        setResult(RESULT_OK,getIntent().putExtras(bundle));
        finish();
    }

    public void ShopActivity(View view) {
    }
}
