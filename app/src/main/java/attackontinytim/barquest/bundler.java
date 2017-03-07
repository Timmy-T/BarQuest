package attackontinytim.barquest;

import android.os.Bundle;

import attackontinytim.barquest.Database.Weapon;
import attackontinytim.barquest.Hero;
import attackontinytim.barquest.Hero;

import android.os.Bundle;

/**
 * Created by Tim Buesking on 3/6/2017.
 */

public class bundler {
    public static Bundle generateBundle(Hero hero) {
        Bundle bundle = new Bundle();

        bundle.putInt("id", hero.getId());
        bundle.putString("name", hero.getName());
        bundle.putInt("hitPoints", hero.getHP());
        bundle.putInt("experience", hero.getXP());
        bundle.putInt("level", hero.getLevel());
        bundle.putInt("speed", hero.getSpeed());
        bundle.putInt("attack", hero.getAttack());
        bundle.putInt("defense", hero.getDefense());
        bundle.putDouble("money", hero.getMoney());

        bundle.putString("weaponName", hero.getActive().getName());
        bundle.putString("weaponType", hero.getActive().getAttackType());
        bundle.putInt("weaponAttack", hero.getActive().getAttack());
        bundle.putDouble("weaponWeight", hero.getActive().getWeight());
        bundle.putInt("weaponCrit", hero.getActive().getCriticalRate());
        bundle.putDouble("weaponValue", hero.getActive().getValue());

        return bundle;
    }

    public static Hero unbundleHero(Bundle bundle){
        Hero hero = new Hero();
        hero.setId(bundle.getInt("id"));
        hero.setName(bundle.getString("name"));
        hero.setLevel(bundle.getInt("level"));
        hero.setHP(bundle.getInt("hitPoints"));
        hero.setAttack(bundle.getInt("attack"));
        hero.setDefense(bundle.getInt("defense"));
        hero.setSpeed(bundle.getInt("speed"));
        hero.setXP(bundle.getInt("experience"));
        hero.setMoney(bundle.getInt("money"));

        String weaponName = bundle.getString("weaponName");
        String weaponType = bundle.getString("weaponType");
        int weaponAttack = bundle.getInt("weaponAttack");
        double weaponWeight = bundle.getDouble("weaponWeight");
        int weaponCrit = bundle.getInt("weaponCrit");
        double weaponValue = bundle.getDouble("weaponValue");

        Weapon active = new Weapon(weaponType, weaponAttack, weaponCrit, weaponName, weaponValue, weaponWeight);


        hero.setActive(active);
        return hero;
    }
}
