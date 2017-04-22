package attackontinytim.barquest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import attackontinytim.barquest.Database.ConsumableItem;
import attackontinytim.barquest.Database.ConsumableRepo;
import attackontinytim.barquest.Database.HeroRepo;
import attackontinytim.barquest.Database.InventoryRepo;
import attackontinytim.barquest.Database.QuestRepo;
import attackontinytim.barquest.Database.Weapon;
import attackontinytim.barquest.Database.WeaponRepo;

import static attackontinytim.barquest.R.styleable.View;

public class QuestActivity extends AppCompatActivity {

    private Hero hero;

    public void turnInQuest() {
        Quest quest = hero.getCurrentQuest();

        if (quest.getGoal() <= quest.getProgress() && quest.isCompleted() == false) {
            quest.setCompleted(true);

            if (quest.getItemName() != "") {
                // Try to see if the reward is a weapon
                Weapon wep = WeaponRepo.getItemByName(quest.getItemName());
                if (wep != null) {
                    InventoryRepo.addItemToInventory(wep);
                } else {
                    ConsumableItem con = ConsumableRepo.getConsumableByName(quest.getItemName());
                    if (con != null) {
                        InventoryRepo.addItemToInventory(con);
                    }
                }
            }

            hero.inc_experience(quest.getXP());
            hero.setMoney(hero.getMoney() + quest.getMoney());

            QuestRepo.updateQuest(quest);
            HeroRepo.updateHero(hero);
        }
    }

	// This is called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quest_layout);

        Bundle bundle = getIntent().getExtras();
        hero = bundler.unbundleHero(bundle);

        Quest quest = hero.getCurrentQuest();

        ((TextView) findViewById(R.id.Title)).setText(quest.getName());
        ((TextView) findViewById(R.id.Description)).setText(quest.getDescription());
        ((TextView) findViewById(R.id.Complete)).setText(String.valueOf(quest.isCompleted()));
        ((TextView) findViewById(R.id.QuestTotal)).setText(String.valueOf(quest.getGoal()));
        ((TextView) findViewById(R.id.Progress)).setText(String.valueOf(quest.getProgress()));

        findViewById(R.id.submitQuest).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                turnInQuest();
                // Do something in response to button click
            }
        });


        // TODO: Make Quests clickable to expand and show more values
        final ListView questView = (ListView) findViewById(R.id.questList);
        final ArrayList<String> list = new ArrayList<String>();

        List<Quest> qList = QuestRepo.getAllQuest();
        for (int i = 0; i < qList.size(); i++) {
            list.add(qList.get(i).getName());
        }

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

        questView.setAdapter(itemsAdapter);
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
}
