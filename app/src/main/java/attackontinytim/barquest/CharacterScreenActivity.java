package attackontinytim.barquest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import attackontinytim.barquest.Character;

public class CharacterScreenActivity extends AppCompatActivity {
    Player avatar = new Player("Bob", 1, 10, 10, 10, 10, 0, 500);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_screen_layout);

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

    }
}
