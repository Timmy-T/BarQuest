package attackontinytim.barquest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

import attackontinytim.barquest.Database.ConsumableItem;
import attackontinytim.barquest.Database.ConsumableRepo;
import attackontinytim.barquest.Database.InventoryRepo;
import attackontinytim.barquest.Database.QuestRepo;
import attackontinytim.barquest.Database.Weapon;
import attackontinytim.barquest.Database.WeaponRepo;

public class QuestActivity extends AppCompatActivity {

    private Hero hero;

    public void turnInQuest() {
        Quest quest = hero.getCurrentQuest();

        if (quest.getGoal() >= quest.getProgress() && quest.isCompleted() == false) {
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
            hero.setCurrentQuest(null);
        }
    }

	// This is called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quest_layout);

        Bundle bundle = getIntent().getExtras();
        hero = bundler.unbundleHero(bundle);
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
