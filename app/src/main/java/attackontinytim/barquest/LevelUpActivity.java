package attackontinytim.barquest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import attackontinytim.barquest.Database.Weapon;

public class LevelUpActivity extends AppCompatActivity {

    private Hero hero;

    // Return
    static public int MAIN_RETURN_CODE = 1;


    // Buttons
    private static Button addHP;
    private static Button addATK;
    private static Button addDEF;
    private static Button addSPD;
    private static Button distStats;


	// This is called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_up_layout);

        Bundle bundle = getIntent().getExtras();
        hero = bundler.unbundleHero(bundle);

        // Hook up UI variables to backend variables
        TextView APStat = (TextView) findViewById(R.id.currAPstat);
        TextView oldLvlStat = (TextView) findViewById(R.id.currLVLstat);
        TextView newLvlStat = (TextView) findViewById(R.id.newLVLstat);
        TextView HPStat = (TextView) findViewById(R.id.currHPstat);
        TextView AtkStat = (TextView) findViewById(R.id.currATKstat);
        TextView DefStat = (TextView) findViewById(R.id.currDEFstat);
        TextView SpdStat = (TextView) findViewById(R.id.currSPDstat);

        //APStat.setText(String.valueOf());
        oldLvlStat.setText(String.valueOf(hero.getLevel()-1));
        newLvlStat.setText(String.valueOf(hero.getLevel()));
        HPStat.setText(String.valueOf(hero.getHP()));
        AtkStat.setText(String.valueOf(hero.getAttack()));
        DefStat.setText(String.valueOf(hero.getDefense()));
        SpdStat.setText(String.valueOf(hero.getSpeed()));

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

    public void onClickButtonListener(){
        addHP = (Button)findViewById(R.id.addHP);
        addATK = (Button)findViewById(R.id.addATK);
        addDEF = (Button)findViewById(R.id.addDEF);
        addSPD = (Button)findViewById(R.id.addSPD);
        distStats = (Button)findViewById(R.id.distStats);

        final Hero tempHero = hero;

        // TODO: Update AP stat each time button is pressed; insert a check for AP > 0
        addHP.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        tempHero.setHP(tempHero.getHP()+1);
                        TextView HPStat = (TextView) findViewById(R.id.newHPstat);
                        HPStat.setText(String.valueOf(tempHero.getHP()));
                    }
                }
        );
        addATK.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        tempHero.setAttack(tempHero.getAttack()+1);
                        TextView AtkStat = (TextView) findViewById(R.id.newATKstat);
                        AtkStat.setText(String.valueOf(tempHero.getAttack()));
                    }
                }
        );
        addDEF.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        tempHero.setDefense(tempHero.getDefense()+1);
                        TextView DefStat = (TextView) findViewById(R.id.newDEFstat);
                        DefStat.setText(String.valueOf(tempHero.getDefense()));
                    }
                }
        );
        addSPD.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        tempHero.setSpeed(tempHero.getSpeed()+1);
                        TextView SpdStat = (TextView) findViewById(R.id.newSPDstat);
                        SpdStat.setText(String.valueOf(tempHero.getSpeed()));
                    }
                }
        );
        distStats.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // TODO: update the global hero here
                        Intent intent = new Intent("attackontinytim.barquest.LevelUpActivity");
                        Bundle bundle = bundler.generateBundle(hero);
                        intent.putExtras(bundle);
                        startActivityForResult(intent,  MAIN_RETURN_CODE);
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
}
