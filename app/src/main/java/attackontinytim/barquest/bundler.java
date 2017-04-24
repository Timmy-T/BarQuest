package attackontinytim.barquest;

import android.os.Bundle;

import attackontinytim.barquest.Database.QuestRepo;
import attackontinytim.barquest.Database.Weapon;
import attackontinytim.barquest.Hero;
import attackontinytim.barquest.Hero;

import android.os.Bundle;

/**
 * Created by Tim Buesking on 3/6/2017.
 * Destroyed by Robert Jones shortly thereafter
 */
public class bundler {

    /**
     * Function that generates a bundle containing the hero and weapon information.
     * This bundle is to be used for passing information between activities
     * @param hero A Hero object the be serialized into a bundle. This hero must also have a weapon
     */
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
        bundle.putInt("abilityPoints", hero.getAP());

        bundle.putString("weaponName", hero.getActive().getName());
        bundle.putString("weaponType", hero.getActive().getAttackType());
        bundle.putInt("weaponAttack", hero.getActive().getAttack());
        bundle.putDouble("weaponWeight", hero.getActive().getWeight());
        bundle.putInt("weaponCrit", hero.getActive().getCriticalRate());
        bundle.putDouble("weaponValue", hero.getActive().getValue());

        // This checks for a quest and jumps if a quest is inactive
        try {
         bundle.putInt("questID", hero.getCurrentQuest().getId());
        }
        catch (Exception ex) {}

        bundle.putLong("timer0", hero.getScanTimers()[0].getTime().getTimeInMillis());
        bundle.putLong("timer1", hero.getScanTimers()[1].getTime().getTimeInMillis());
        bundle.putLong("timer2", hero.getScanTimers()[2].getTime().getTimeInMillis());
        bundle.putLong("timer3", hero.getScanTimers()[3].getTime().getTimeInMillis());
        bundle.putLong("timer4", hero.getScanTimers()[4].getTime().getTimeInMillis());
        bundle.putLong("timer5", hero.getScanTimers()[5].getTime().getTimeInMillis());
        bundle.putLong("timer6", hero.getScanTimers()[6].getTime().getTimeInMillis());
        bundle.putLong("timer7", hero.getScanTimers()[7].getTime().getTimeInMillis());
        bundle.putLong("timer8", hero.getScanTimers()[8].getTime().getTimeInMillis());
        bundle.putLong("timer9", hero.getScanTimers()[9].getTime().getTimeInMillis());

        return bundle;
    }

    /**
     * Function that unbundles a hero object from a bundle.
     * To be used when receiving a bundle from another activity
     * @param bundle A Bundle object to be decompressed. This bundle must contain a hero
     *               and weapon class
     * @return Hero a hero object
     */
    public static Hero unbundleHero(Bundle bundle){
        boolean earlyValue;

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
        hero.setAP(bundle.getInt("abilityPoints"));

        String weaponName = bundle.getString("weaponName");
        String weaponType = bundle.getString("weaponType");
        int weaponAttack = bundle.getInt("weaponAttack");
        double weaponWeight = bundle.getDouble("weaponWeight");
        int weaponCrit = bundle.getInt("weaponCrit");
        double weaponValue = bundle.getDouble("weaponValue");

        Weapon active = new Weapon(weaponType, weaponAttack, weaponCrit, weaponName, weaponValue, weaponWeight);
        hero.setActive(active);

        // If no quest values were given
        try {
            int questID = bundle.getInt("questID");

            Quest heroQuest = QuestRepo.getQuestByID(questID);

            hero.setCurrentQuest(heroQuest);
        } catch (Exception ex){}

        Timer[] timers = new Timer[10];
        timers[0] = new Timer(bundle.getLong("timer0"));
        timers[1] = new Timer(bundle.getLong("timer1"));
        timers[2] = new Timer(bundle.getLong("timer2"));
        timers[3] = new Timer(bundle.getLong("timer3"));
        timers[4] = new Timer(bundle.getLong("timer4"));
        timers[5] = new Timer(bundle.getLong("timer5"));
        timers[6] = new Timer(bundle.getLong("timer6"));
        timers[7] = new Timer(bundle.getLong("timer7"));
        timers[8] = new Timer(bundle.getLong("timer8"));
        timers[9] = new Timer(bundle.getLong("timer9"));


        return hero;
    }
}
//TODO: Parcelable hero and Quest should be utilized. This will shorten the length of code and improve speed.