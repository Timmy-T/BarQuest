package attackontinytim.barquest;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.R.layout;

import attackontinytim.barquest.Database.DBHandler;
import attackontinytim.barquest.Database.HeroRepo;
import attackontinytim.barquest.Database.Weapon;

public class CharacterScreenActivity extends AppCompatActivity {

    public Hero hero;

    static public int MAIN_RETURN_CODE = 1;

    private static Button statButton;
    private static Button resetButton;

    private static DBHandler dbHandler;

    // This is called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_screen_layout);

        Bundle bundle = getIntent().getExtras();
        hero = bundler.unbundleHero(bundle);

        // Hook up UI variables to backend variables
        TextView Name = (TextView) findViewById(R.id.CharName);
        ProgressBar ExpBar = (ProgressBar) findViewById(R.id.ExpBar);
        TextView Exp = (TextView) findViewById(R.id.expstat);
        TextView Money = (TextView) findViewById(R.id.moneystat);
        TextView LvlStat = (TextView) findViewById(R.id.lvlstat);
        TextView APStat = (TextView) findViewById(R.id.apstat);
        TextView HPStat = (TextView) findViewById(R.id.hpstat);
        TextView AtkStat = (TextView) findViewById(R.id.atkstat);
        TextView DefStat = (TextView) findViewById(R.id.defstat);
        TextView SpdStat = (TextView) findViewById(R.id.spdstat);

        Name.setText(String.valueOf(hero.getName()));
        ExpBar.setMax(100);
        ExpBar.setProgress(hero.getXP());
        Exp.setText(String.valueOf(hero.getXP()));
        Money.setText(String.valueOf(hero.getMoney()));
        LvlStat.setText(String.valueOf(hero.getLevel()));
        APStat.setText(String.valueOf(hero.getAP()));
        HPStat.setText(String.valueOf(hero.getHP()));
        AtkStat.setText(String.valueOf(hero.getAttack()));
        DefStat.setText(String.valueOf(hero.getDefense()));
        SpdStat.setText(String.valueOf(hero.getSpeed()));

        onClickButtonListener();
    }

    public void onClickButtonListener() {
        statButton = (Button) findViewById(R.id.statButton);
        resetButton = (Button) findViewById(R.id.resetButton);

        statButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("attackontinytim.barquest.LevelUpActivity");
                        Bundle bundle = bundler.generateBundle(hero);
                        intent.putExtras(bundle);
                        startActivityForResult(intent, MAIN_RETURN_CODE);
                    }
                }
        );

        resetButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        AlertDialog alertDialog = new AlertDialog.Builder(CharacterScreenActivity.this).create();
                        alertDialog.setMessage("ARE YOU SURE YOU WANT TO RESET YOUR CHARACTER?");
                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "YES",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dbHandler.resetData();
                                        hero = HeroRepo.getHeroByName("HERO");

                                        end();
                                    }
                                });
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "CANCEL",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }
                }
        );
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