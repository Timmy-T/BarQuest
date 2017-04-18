package attackontinytim.barquest;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.R.layout;

import attackontinytim.barquest.Database.Weapon;

public class CharacterScreenActivity extends AppCompatActivity {

    public Hero hero;

    // This is called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_screen_layout);

        Bundle bundle = getIntent().getExtras();
        hero = bundler.unbundleHero(bundle);

        // Hook up UI variables to backend variables
        TextView Name = (TextView) findViewById(R.id.CharName);
        TextView Exp = (TextView) findViewById(R.id.expstat);
        TextView Money = (TextView) findViewById(R.id.moneystat);
        TextView LvlStat = (TextView) findViewById(R.id.lvlstat);
        TextView HPStat = (TextView) findViewById(R.id.hpstat);
        TextView AtkStat = (TextView) findViewById(R.id.atkstat);
        TextView DefStat = (TextView) findViewById(R.id.defstat);
        TextView SpdStat = (TextView) findViewById(R.id.spdstat);

        Name.setText(String.valueOf(hero.getName()));
        Exp.setText(String.valueOf(hero.getXP()));
        Money.setText(String.valueOf(hero.getMoney()));
        LvlStat.setText(String.valueOf(hero.getLevel()));
        HPStat.setText(String.valueOf(hero.getHP()));
        AtkStat.setText(String.valueOf(hero.getAttack()));
        DefStat.setText(String.valueOf(hero.getDefense()));
        SpdStat.setText(String.valueOf(hero.getSpeed()));

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

    // What is done when hardware back button is pressed
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