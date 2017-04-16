package attackontinytim.barquest;

import android.widget.TextView;
import android.os.Handler;
import java.lang.Runnable;
import android.util.Log;
import attackontinytim.barquest.Database.Monster;
import attackontinytim.barquest.Database.Weapon;

public class Battle {
    /* ********* */
    /* VARIABLES */
    /* ********* */
    String TAG = BattleActivity.class.getSimpleName();

    public Hero hero;
    public Monster enemy;

    protected Hero battleHero;
    protected Monster battleEnemy;

    private double wep_triangle;

    /** Battle Formula Modifiers - more to come? */
    private static final double CH_DEF = 2;
    private static final double MON_DEF  = 2;
    private static final int CRIT_MULT = 2;

    /* ************ */
    /* CONSTRUCTORS */
    /* ************ */

    /** Default constructor */
    public Battle(){

        // name, attack, weight, crit, type
        Weapon testWeapon = new Weapon("close", 1,1, "testWeapon", 5.0, 1);

        // String name, int level, int hitPoints, int attack, int defense, int speed, int experience, int money
        this.hero = new Hero(0, "testPlayer", 20, 0, 1, 5, 5,5, 0, testWeapon, 10);

        // String name, int level, int hitPoints, int attack, int defense, int speed, String type
        this.enemy = new Monster(1, "testEnemy", 20, 1, "close", 1.0, 1, "Common", 1,1,1);

        // Create temp objects for stat manipulation
        this.battleHero = this.hero.cloneHero();
        this.battleEnemy = this.enemy.cloneMonster();

        setWeaponTriangle();
    }

    // Constructor for testing
    public Battle(Hero hero){
        this.hero = hero;

        // String name, int level, int hitPoints, int attack, int defense, int speed, String type
        this.enemy = new Monster(1, "testEnemy", 20, 1, "close", 1.0, 1, "Common", 1,1,1);

        // Create temp objects for stat manipulation
        this.battleHero = this.hero.cloneHero();
        this.battleEnemy = this.enemy.cloneMonster();

        setWeaponTriangle();
    }

    /** Constructs a Battle object with a Hero and Enemy Character */
    public Battle(Hero hero, Monster enemy) {
        this.hero = hero;
        this.enemy = enemy;

        // Create temp objects for stat manipulation
        this.battleHero = this.hero.cloneHero();
        this.battleEnemy = this.enemy.cloneMonster();

        setWeaponTriangle();
    }

    /* ************* */
    /* Battle Methods */
    /* ************* */

    /** Calculates if the hero's attack hits or not - returns true if hit, false for miss
     * Hit = 100D <= max(0, WepTri * (100 + CAtkSpd - MEvade) */
    protected boolean calc_hit_hero() {
        boolean landed = false;

        int evade = this.battleEnemy.getAtkSpd();
        if ((int)Math.round(this.battleEnemy.getDefense()/MON_DEF) > this.battleEnemy.getSpeed())
            evade += (int)Math.round(this.battleEnemy.getDefense()/MON_DEF);

        int calc = (int)this.wep_triangle * (100 + this.battleHero.getAtkSpd() - evade);
        int maxCalc = Math.max(0, calc);
        int diceRoll = (int)(Math.random() * (101));

        if(diceRoll <= maxCalc){
            landed = true;
        }

        return landed;
    }

    /** Calculates if the enemy's attack hits or not - returns true if hit, false for miss
     * Hit = 100D <= max(0, (2 - WepTri) * (100 + MAtkSpd - CMEvade) */
    protected boolean calc_hit_enemy() {
        boolean landed = false;

        int evade = this.battleHero.getAtkSpd();
        if ((int)Math.round(this.battleHero.getDefense()/CH_DEF) > this.battleHero.getSpeed())
            evade += (int)Math.round(this.battleHero.getDefense()/CH_DEF);

        int calc = (int)(2 - this.wep_triangle) * (100 + this.battleEnemy.getAtkSpd() - evade);
        int maxCalc = Math.max(0, calc);
        int diceRoll = (int)(Math.random() * (101));

        if(diceRoll <= maxCalc){
            landed = true;
        }

        return landed;
    }

    /** Calculates Critical hit rate
     * Crit Rate = 100D <= CSpd/2 + WCrit
     * returns true if crit, false if not */
    protected boolean calc_crit(){
        boolean critical = false;
        int diceRoll = (int)(Math.random() * (101));
        if(diceRoll <= this.battleHero.getSpeed()/2 + this.battleHero.getActive().getCriticalRate()){
            critical = true;
        }
        return critical;
    }

    /** Calculates damage based on the formula:
     * Damage = max(1, (WepTri * (CAtk + WAtk) - MDef))
     * returns an int with the damage value calculated */
    protected int calc_dmg(){
        int calc = (int)Math.round(this.wep_triangle * (this.battleHero.getAttack() + this.battleHero.getActive().getAttack()) - this.battleEnemy.getDefense());
        int damage = Math.max(1, calc);

        //multiply damage if critical hit lands
        if(calc_crit()){
            damage = damage * CRIT_MULT;
        }
        return damage;
    }

    /** Calculates flee rate based on the formula:
     * 100D <= max(50, (100 + CAtkSpd - MAtkSpd))
     * returns true if successful, false if not */
    protected boolean calc_flee(){
        boolean fled = false;

        int diceRoll = (int)(Math.random() * (101));
        if(diceRoll <= Math.max(50, (100 + this.battleHero.getAtkSpd() - this.battleEnemy.getAtkSpd()))){
            fled = true;
        }
        return fled;
    }

    // Miscellaneous Methods
    ////////////////////////

    private void setWeaponTriangle(){
        String weapType = this.battleHero.getActive().getAttackType();
        String mons = this.battleEnemy.getAttackType();

        if ((weapType.equals("long") && mons.equals("mid")) ||
                (weapType.equals("mid") && mons.equals("close")) ||
                (weapType.equals("close") && mons.equals("long"))){
            this.wep_triangle = 1.1;
        }
        else if ((weapType.equals("long") && mons.equals("close")) ||
                (weapType.equals("mid") && mons.equals("long")) ||
                (weapType.equals("close") && mons.equals("mid"))){
            this.wep_triangle = 0.9;
        }
        else{
            this.wep_triangle = 1;
        }
    }

    /** Returns true if Hero has attack priority; false otherwise */
    protected boolean heroPriority(){
        if (this.battleHero.getAtkSpd() > this.battleEnemy.getAtkSpd())
            return true;
        return false;
    }

    /**And it's finally showtime
     /**performs hero's turn in a battle
     * returns True if attack succeeds, False otherwise*/
    protected boolean heroTurn() {
        boolean success = false;
        if(this.battleEnemy.getHP() > 0 && this.battleHero.getHP() > 0) {
            if (this.calc_hit_hero()) {
                success = true;
                Log.d(TAG, "battleEnemy HP before: " + String.valueOf(this.battleEnemy.getHP()));
                //crit calculations are automatically done in the calc_dmg() stage
                int damage = this.calc_dmg();

                //subtract damage from monster's HP
                this.battleEnemy.setHP(this.battleEnemy.getHP() - damage);
                Log.d(TAG, "battleEnemy HP after: " + String.valueOf(this.battleEnemy.getHP()));
            }
        }
        return success;
    }

    /**performs enemy's turn in a battle
     * returns True if attack succeeds, False otherwise */
    protected boolean enemyTurn() {
        boolean success = false;

        if (this.battleEnemy.getHP() > 0 && this.battleHero.getHP() > 0) {
            if(this.calc_hit_enemy()){
                Log.d(TAG, "battleHero HP before: " + String.valueOf(this.battleHero.getHP()));
                success = true;
                this.battleHero.setHP(this.battleHero.getHP() - this.battleEnemy.getAttack());
                Log.d(TAG, "battleHero HP after: " + String.valueOf(this.battleHero.getHP()));
            }
        }
        return success;
    }
}
//TODO: function to check if battle has ended and give exp accordingly

