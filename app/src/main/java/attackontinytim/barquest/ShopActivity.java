package attackontinytim.barquest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ShopActivity extends AppCompatActivity {

    public Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_layout);

        Bundle bundle = getIntent().getExtras();

        player = new Player();
        player.set_name(bundle.getString("name"));
        player.set_level(bundle.getInt("level"));
        player.set_hit_points(bundle.getInt("hitPoints"));
        player.set_attack(bundle.getInt("attack"));
        player.set_defense(bundle.getInt("defense"));
        player.set_speed(bundle.getInt("speed"));
        player.set_experience(bundle.getInt("experience"));
        player.set_money(bundle.getInt("money"));

        String weaponName = bundle.getString("weaponName");
        String weaponType = bundle.getString("weaponType");
        int weaponAttack = bundle.getInt("weaponAttack");
        int weaponWeight = bundle.getInt("weaponWeight");
        int weaponCrit = bundle.getInt("weaponCrit");
        Weapon active = new Weapon(weaponName, weaponAttack, weaponWeight, weaponCrit, weaponType);

        player.set_active(active);
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
        Bundle bundle = new Bundle();

        bundle.putString("name", player.getName());
        bundle.putInt("level", player.getLevel());
        bundle.putInt("hitPoints", player.getHitPoints());
        bundle.putInt("attack", player.getAttack());
        bundle.putInt("defense", player.getDefense());
        bundle.putInt("speed", player.getSpeed());
        bundle.putInt("experience", player.getExperience());
        bundle.putInt("money", player.getMoney());

        bundle.putString("weaponName", player.getActive().getName());
        bundle.putString("weaponType", player.getActive().getType());
        bundle.putInt("weaponAttack", player.getActive().getAttack());
        bundle.putInt("weaponWeight", player.getActive().getWeight());
        bundle.putInt("weaponCrit", player.getActive().getCrit());

        setResult(RESULT_OK,getIntent().putExtras(bundle));
        finish();
    }

    public void ShopActivity(View view) {
    }
}
