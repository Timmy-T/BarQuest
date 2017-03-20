package attackontinytim.barquest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import attackontinytim.barquest.Database.Weapon;

public class BattleActivity extends AppCompatActivity /*implements Parcelable*/{

    private Battle battle;
    private Hero hero;
    private static Button attack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle_laout);
        Bundle bundle = getIntent().getExtras();

        hero = bundler.unbundleHero(bundle);

        battle = new Battle(hero);
        TextView textView = (TextView) this.findViewById(R.id.textView2);
        textView.setText("Player Name: " + battle.hero.getName());

        attack = (Button) findViewById(R.id.PLACEHOLDER); //REPLACE "PLACEHOLDER" ONCE A REAL ATTACK BUTTON IS CODED
        onClickButtonListener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                default:
                    end();
            }
        }
    }

    @Override
    public void onBackPressed() {
        end();
    }

    private void end(){
        Bundle bundle = bundler.generateBundle(hero);
        setResult(RESULT_OK,getIntent().putExtras(bundle));

        finish();
    }

    public void onClickButtonListener() {
        attack.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("attackontinytim.barquest.BattleActivity");
                        Bundle bundle = bundler.generateBundle(hero);

                        intent.putExtras(bundle);
                        startActivityForResult(intent,  1);
                    }
                }
        );
    }
}
