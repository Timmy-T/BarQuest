package attackontinytim.barquest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;

import attackontinytim.barquest.Database.Weapon;

public class BattleActivity extends AppCompatActivity /*implements Parcelable*/{

    private Battle battle;
    private Hero hero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle_laout);
        Bundle bundle = getIntent().getExtras();

        hero = bundler.unbundleHero(bundle);

        battle = new Battle(hero);
        TextView textView = (TextView) this.findViewById(R.id.textView2);
        textView.setText("Player Name: " + battle.hero.getName());
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
}
