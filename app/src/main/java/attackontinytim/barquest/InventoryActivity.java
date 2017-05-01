package attackontinytim.barquest;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import attackontinytim.barquest.Database.ConsumableItem;
import attackontinytim.barquest.Database.ConsumableRepo;
import attackontinytim.barquest.Database.InventoryRepo;
import attackontinytim.barquest.Database.Weapon;
import attackontinytim.barquest.Database.WeaponRepo;

public class InventoryActivity extends AppCompatActivity {

    public Hero hero;

	// This is called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_layout);

        Bundle bundle = getIntent().getExtras();

        hero = bundler.unbundleHero(bundle);

        TextView currWeapon = (TextView) findViewById(R.id.currWeaponName);
        TextView currWeaponType = (TextView) findViewById(R.id.currWeaponType);
        TextView currWeaponAtk = (TextView) findViewById(R.id.currWeaponAtk);
        TextView currWeaponCrit = (TextView) findViewById(R.id.currWeaponCrit);

        currWeapon.setText(hero.getActive().getName());
        currWeaponType.setText(hero.getActive().getAttackType());
        currWeaponAtk.setText(String.valueOf(hero.getActive().getAttack()));
        currWeaponCrit.setText(String.valueOf(InventoryRepo.getItemQuantity(hero.getActive())));

        // populate weapons list
        final ListView weaponList = (ListView) findViewById(R.id.WeaponRepo);
        final ArrayList<String> list1 = new ArrayList<String>();

        List<Weapon> wList = InventoryRepo.getAllWeapons();
        for (int i = 0; i < wList.size(); i++) {
                Weapon currItem = wList.get(i);
                String weapName = currItem.getName();
                String weapType = currItem.getAttackType();
                String weapAtk = String.valueOf(currItem.getAttack());
                String weapCrit = String.valueOf(currItem.getCriticalRate());
                list1.add(weapName + "     " + weapType + "     Atk: " + weapAtk + "     Crit:" + weapCrit);
        }

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list1);

        weaponList.setAdapter(itemsAdapter);

        weaponList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the GridView selected/clicked item text
                String selectedWeapon = parent.getItemAtPosition(position).toString();

                Weapon equipWeapon = WeaponRepo.getItemByName(selectedWeapon);
                hero.setActive(equipWeapon);
                finish();
                startActivity(getIntent());
            }
        });

        // populate consumable items list
        final ListView itemsList = (ListView) findViewById(R.id.ItemsRepo);
        final ArrayList<String> list2 = new ArrayList<String>();

        List<ConsumableItem> iList = InventoryRepo.getAllConsumables();
        for (int i = 0; i < iList.size(); i++) {
            list2.add(iList.get(i).getName());
        }

        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list2);

        itemsList.setAdapter(itemsAdapter);

        itemsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the GridView selected/clicked item text
                String selectedItem = parent.getItemAtPosition(position).toString();

                AlertDialog alertDialog = new AlertDialog.Builder(InventoryActivity.this).create();
                ConsumableItem myItem = ConsumableRepo.getConsumableByName(selectedItem);
                String myMessage = selectedItem + "\n" + "Effect: " + myItem.getEffect();
                alertDialog.setMessage(myMessage);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "X",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });
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

    public void InventoryActivity(View view) {
    }
}
