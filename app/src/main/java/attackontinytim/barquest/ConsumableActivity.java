package attackontinytim.barquest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import attackontinytim.barquest.Database.ConsumableItem;
import attackontinytim.barquest.Database.InventoryRepo;



public class ConsumableActivity extends AppCompatActivity {
    public Hero hero;
    public ConsumableItem currPot;

    // This is called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumable_layout);

        Bundle bundle = getIntent().getExtras();

        hero = bundler.unbundleHero(bundle);

        // populate consumable items list
        final ListView itemsList = (ListView) findViewById(R.id.consumableList);
        final ArrayList<String> list2 = new ArrayList<String>();
        ListView.LayoutParams lprams = new ListView.LayoutParams(
                ListView.LayoutParams.WRAP_CONTENT, ListView.LayoutParams.WRAP_CONTENT);


        final List<ConsumableItem> iList = InventoryRepo.getAllConsumables();

        for (int i = 0; i < iList.size(); i++) {
            currPot = iList.get(i);
            list2.add(currPot.getName());
        }

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list2);

        itemsList.setAdapter(itemsAdapter);

        itemsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the GridView selected/clicked item text
                String selectedItem = parent.getItemAtPosition(position).toString();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("Consumable", selectedItem);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();

            }
        });

    };

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

    public void ConsumableActivity(View view) {
    }
}
