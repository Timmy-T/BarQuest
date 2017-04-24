package attackontinytim.barquest;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import attackontinytim.barquest.Database.ConsumableItem;
import attackontinytim.barquest.Database.InventoryRepo;
import attackontinytim.barquest.Database.Weapon;
import attackontinytim.barquest.Database.WeaponRepo;
import attackontinytim.barquest.Database.InventoryRepo;

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
        TextView currWeaponStock = (TextView) findViewById(R.id.currWeaponStock);

        currWeapon.setText(hero.getActive().getName());
        currWeaponType.setText(hero.getActive().getAttackType());
        currWeaponAtk.setText(String.valueOf(hero.getActive().getAttack()));
        currWeaponStock.setText(String.valueOf(InventoryRepo.getItemQuantity(hero.getActive())));

        // populate weapons list
        final ListView weaponList = (ListView) findViewById(R.id.WeaponRepo);
        final ArrayList<String> list1 = new ArrayList<String>();

        List<Weapon> wList = InventoryRepo.getAllWeapons();
        for (int i = 0; i < wList.size(); i++) {
            list1.add(wList.get(i).getName());
        }

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list1);

        weaponList.setAdapter(itemsAdapter);

        // populate consumable items list
        final ListView itemsList = (ListView) findViewById(R.id.ItemsRepo);
        final ArrayList<String> list2 = new ArrayList<String>();

        List<ConsumableItem> iList = InventoryRepo.getAllConsumables();
        for (int i = 0; i < iList.size(); i++) {
            list2.add(iList.get(i).getName());
        }

        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list2);

        itemsList.setAdapter(itemsAdapter);
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
