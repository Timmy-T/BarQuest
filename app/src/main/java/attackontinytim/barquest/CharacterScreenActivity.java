package attackontinytim.barquest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import attackontinytim.barquest.Character;

<<<<<<< HEAD
    public Player player;

=======
public class CharacterScreenActivity extends AppCompatActivity {
    Player avatar = new Player("Bob", 1, 10, 10, 10, 10, 0, 500);
>>>>>>> refs/remotes/origin/UI
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_screen_layout);

<<<<<<< HEAD
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
=======
        TextView Name = (TextView) findViewById(R.id.CharName);
        TextView Exp = (TextView) findViewById(R.id.expstat);
        TextView Money = (TextView) findViewById(R.id.moneystat);
        TextView LvlStat = (TextView) findViewById(R.id.lvlstat);
        TextView HPStat = (TextView) findViewById(R.id.hpstat);
        TextView AtkStat = (TextView) findViewById(R.id.atkstat);
        TextView DefStat = (TextView) findViewById(R.id.defstat);
        TextView SpdStat = (TextView) findViewById(R.id.spdstat);

        Name.setText(String.valueOf(avatar.getName()));
        Exp.setText(String.valueOf(avatar.getExperience()));
        Money.setText(String.valueOf(avatar.getMoney()));
        LvlStat.setText(String.valueOf(avatar.getLevel()));
        HPStat.setText(String.valueOf(avatar.getHitPoints()));
        AtkStat.setText(String.valueOf(avatar.getAttack()));
        DefStat.setText(String.valueOf(avatar.getDefense()));
        SpdStat.setText(String.valueOf(avatar.getSpeed()));
    }

    public void CharacterScreenActivity(View view) {

>>>>>>> refs/remotes/origin/UI
    }
}
