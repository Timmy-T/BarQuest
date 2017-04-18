package attackontinytim.barquest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

import attackontinytim.barquest.Database.Weapon;

public class QuestActivity extends AppCompatActivity {

    private Hero hero;

    public void turnInQuest() {
        int get = hero.getCurrentQuest().getCurrentCompleted();
        int goal = hero.getCurrentQuest().getCompletionGoal();

        if ((get != 0) && ((hero.getCurrentQuest().getCanTurnInEarly()) || (get == goal)))
        {
            // Default Quest is empty
            Quest newQuest = new Quest();
            // Money Calculation
            double moneyGet = 0;
            if (get == goal)
                moneyGet = moneyGet + 1000;
            moneyGet = moneyGet + 500*(get/goal);
            // Add money to the heroes money bag
            moneyGet = moneyGet + hero.getMoney();
            hero.setMoney(moneyGet);
            // Clear quest
            hero.setCurrentQuest(newQuest);
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
