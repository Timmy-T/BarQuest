package attackontinytim.barquest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import attackontinytim.barquest.Database.Weapon;

public class InventoryActivity extends AppCompatActivity {

    public Hero hero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_layout);

        Bundle bundle = getIntent().getExtras();

        hero = bundler.unbundleHero(bundle);
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

    public void InventoryActivity(View view) {
    }
}
