package attackontinytim.barquest;

import android.os.Bundle;

import attackontinytim.barquest.Database.HeroRepo;
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
        HeroRepo.updateHero(hero);

        bundle.putString("heroName", hero.getName());
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
        Hero hero = HeroRepo.getHeroByName(bundle.getString("heroName"));

        hero.getScanTimers();
        return hero;
    }
}
//TODO: Parcelable hero and Quest should be utilized. This will shorten the length of code and improve speed.