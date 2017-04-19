package attackontinytim.barquest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import org.w3c.dom.Text;

import attackontinytim.barquest.Database.HeroRepo;
import attackontinytim.barquest.Database.Weapon;

public class LevelUpActivity extends AppCompatActivity {

    private Hero hero;

    // Return
    static public int MAIN_RETURN_CODE = 1;


    // Buttons
    private static Button addHP;
    private static Button decHP;
    private static Button addATK;
    private static Button decATK;
    private static Button addDEF;
    private static Button decDEF;
    private static Button addSPD;
    private static Button decSPD;
    private static Button distStats;
    private static Button backButton;


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

        APStat.setText(String.valueOf(hero.getAP()));
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
        decHP = (Button)findViewById(R.id.decHP);
        addATK = (Button)findViewById(R.id.addATK);
        decATK = (Button)findViewById(R.id.decATK);
        addDEF = (Button)findViewById(R.id.addDEF);
        decDEF = (Button)findViewById(R.id.decDEF);
        addSPD = (Button)findViewById(R.id.addSPD);
        decSPD = (Button)findViewById(R.id.decSPD);
        distStats = (Button)findViewById(R.id.distStats);
        backButton = (Button)findViewById(R.id.backButton);

        final Hero tempHero = hero.cloneHero(); // used when distributing stats, but haven't confirmed

        addHP.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        if(tempHero.getAP() > 0) {
                            // increment HP stat
                            tempHero.setHP(tempHero.getHP() + 1);
                            TextView HPStat = (TextView) findViewById(R.id.newHPstat);
                            HPStat.setText(String.valueOf(tempHero.getHP()));

                            // decrement AP stat
                            tempHero.setAP(tempHero.getAP() - 1);
                            TextView APStat = (TextView) findViewById((R.id.currAPstat));
                            APStat.setText(String.valueOf(tempHero.getAP()));
                        }
                    }
                }
        );
        decHP.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        if(tempHero.getHP() > hero.getHP()) {
                            // increment HP stat
                            tempHero.setHP(tempHero.getHP() - 1);
                            TextView HPStat = (TextView) findViewById(R.id.newHPstat);
                            HPStat.setText(String.valueOf(tempHero.getHP()));

                            // increment AP stat
                            tempHero.setAP(tempHero.getAP() + 1);
                            TextView APStat = (TextView) findViewById((R.id.currAPstat));
                            APStat.setText(String.valueOf(tempHero.getAP()));
                        }
                    }
                }
        );
        addATK.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        if(tempHero.getAP() > 0) {
                            tempHero.setAttack(tempHero.getAttack() + 1);
                            TextView AtkStat = (TextView) findViewById(R.id.newATKstat);
                            AtkStat.setText(String.valueOf(tempHero.getAttack()));

                            tempHero.setAP(tempHero.getAP() - 1);
                            TextView APStat = (TextView) findViewById((R.id.currAPstat));
                            APStat.setText(String.valueOf(tempHero.getAP()));
                        }
                    }
                }
        );
        decATK.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        if(tempHero.getAttack() > hero.getAttack()) {
                            tempHero.setAttack(tempHero.getAttack() - 1);
                            TextView AtkStat = (TextView) findViewById(R.id.newATKstat);
                            AtkStat.setText(String.valueOf(tempHero.getAttack()));

                            tempHero.setAP(tempHero.getAP() + 1);
                            TextView APStat = (TextView) findViewById((R.id.currAPstat));
                            APStat.setText(String.valueOf(tempHero.getAP()));
                        }
                    }
                }
        );
        addDEF.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        if(tempHero.getAP() > 0) {
                            tempHero.setDefense(tempHero.getDefense() + 1);
                            TextView DefStat = (TextView) findViewById(R.id.newDEFstat);
                            DefStat.setText(String.valueOf(tempHero.getDefense()));

                            tempHero.setAP(tempHero.getAP() - 1);
                            TextView APStat = (TextView) findViewById((R.id.currAPstat));
                            APStat.setText(String.valueOf(tempHero.getAP()));
                        }
                    }
                }
        );
        decDEF.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        if(tempHero.getDefense() > hero.getDefense()) {
                            tempHero.setDefense(tempHero.getDefense() - 1);
                            TextView DefStat = (TextView) findViewById(R.id.newDEFstat);
                            DefStat.setText(String.valueOf(tempHero.getDefense()));

                            tempHero.setAP(tempHero.getAP() + 1);
                            TextView APStat = (TextView) findViewById((R.id.currAPstat));
                            APStat.setText(String.valueOf(tempHero.getAP()));
                        }
                    }
                }
        );
        addSPD.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        if(tempHero.getAP() > 0) {
                            tempHero.setSpeed(tempHero.getSpeed() + 1);
                            TextView SpdStat = (TextView) findViewById(R.id.newSPDstat);
                            SpdStat.setText(String.valueOf(tempHero.getSpeed()));

                            tempHero.setAP(tempHero.getAP() - 1);
                            TextView APStat = (TextView) findViewById((R.id.currAPstat));
                            APStat.setText(String.valueOf(tempHero.getAP()));
                        }
                    }
                }
        );
        decSPD.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        if(tempHero.getSpeed() > hero.getSpeed()) {
                            tempHero.setSpeed(tempHero.getSpeed() - 1);
                            TextView SpdStat = (TextView) findViewById(R.id.newSPDstat);
                            SpdStat.setText(String.valueOf(tempHero.getSpeed()));

                            tempHero.setAP(tempHero.getAP() + 1);
                            TextView APStat = (TextView) findViewById((R.id.currAPstat));
                            APStat.setText(String.valueOf(tempHero.getAP()));
                        }
                    }
                }
        );
        distStats.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        hero = tempHero.cloneHero();

                        Intent intent = new Intent("attackontinytim.barquest.LevelUpActivity");
                        Bundle bundle = bundler.generateBundle(hero);
                        intent.putExtras(bundle);
                        startActivityForResult(intent,  MAIN_RETURN_CODE);
                    }
                }
        );
        backButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        end();
                    }
                }
        );

    }

	// This is what is called when back is pressed
    @Override
    public void onBackPressed() {
		// it ends the activity
        end();
    }
	
	// This is the act of ending the activity
    private void end(){
        HeroRepo.updateHero(hero);
        
        Bundle bundle = bundler.generateBundle(hero);

        setResult(RESULT_OK,getIntent().putExtras(bundle));
        finish();
    }
}
