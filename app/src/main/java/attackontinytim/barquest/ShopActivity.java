package attackontinytim.barquest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import attackontinytim.barquest.Database.ConsumableItem;
import attackontinytim.barquest.Database.ConsumableRepo;
import attackontinytim.barquest.Database.InventoryRepo;
import attackontinytim.barquest.Database.Weapon;
import attackontinytim.barquest.Database.WeaponRepo;

public class ShopActivity extends AppCompatActivity {

    public Hero hero;

    Random rand = new Random();

    //3 types of hp pots
    private  int randHP = rand.nextInt(3)+1;
    //3 types of stat pots
    private int randStat1 = rand.nextInt(3)+1;
    //3 types of stat pots
    private int randStat2 = rand.nextInt(6)+3;
    //3 types of debuff pots
    private int randStat3 = rand.nextInt(3)+1;
    //6 types of weapons
    private int randWeapon = rand.nextInt(6)+1;




    //Buttons~
    private static Button HP_Pot_button;
    private static Button Stat_pot_1_button;
    private static Button Stat_pot_2_button;
    private static Button Stat_pot_3_button;
    private static Button weapon_button;

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
        TextView hp_pot_quantity = (TextView) findViewById(R.id.hp_pot_quanitty);
        TextView stat_pot_1_quantity = (TextView) findViewById(R.id.stat_pot_1_quantity);
        TextView stat_pot_2_quantity = (TextView) findViewById(R.id.stat_pot_2_quantity);
        TextView stat_pot_3_quantity = (TextView) findViewById(R.id.stat_pot_3_quantity);
        TextView weapon_quantity = (TextView) findViewById(R.id.weapon_quanity);

        //get random potions

        hpList = ConsumableRepo.getConsumableByType("Heal");
        attackList = ConsumableRepo.getConsumableByType("Attack Up");
        defenseList = ConsumableRepo.getConsumableByType("Defense Up");
        speedList = ConsumableRepo.getConsumableByType("speedList");
        //private ConsumableItem[] statPotList = attackList + defenseList + speedList;
        debuffSpeedList = ConsumableRepo.getConsumableByType("Monster Speed Debuff");
        debuffDefenseList = ConsumableRepo.getConsumableByType("Monster Defense Debuff");
        debuffAttackList = ConsumableRepo.getConsumableByType("Monster Attack Debuff");
        //debuffPotList = debuff

        weaponList = WeaponRepo.getAllItems();



        hpPot = hpList[randHP];
        statPot1 = statPotList[randStat1];
        statPot2 = statPotList[randStat2];
        statPot3 = statPotList[randStat3];




        //set labels to name of item + cost
        //hp_pot_label.setText("HI");
        //stat_pot_1_label.setText("Bye");
        //stat_pot_2_label.setText("asdf");
        //stat_pot_3_label.setText("WHY");
        //weapon_label.setText("no");

        //set quantities to their values
        //    public static int getItemQuantity(ConsumableItem con) {

        hp_pot_quantity.setText(String.valueOf("666"));
        stat_pot_1_quantity.setText(String.valueOf("asdf"));
        stat_pot_2_quantity.setText(String.valueOf("coo"));
        stat_pot_3_quantity.setText(String.valueOf("asdff"));
        weapon_quantity.setText(String.valueOf("asss"));


        //joe = ConsumableRepo.getConsumableByName("coffee");
        //InventoryRepo.getItemQuantity(joe);
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
