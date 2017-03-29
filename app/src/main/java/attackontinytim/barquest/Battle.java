package attackontinytim.barquest;

import android.widget.TextView;

import attackontinytim.barquest.Database.Monster;
import attackontinytim.barquest.Database.Weapon;

public class Battle {
    /* ********* */
    /* VARIABLES */
    /* ********* */

    public Hero hero;
    public Monster enemy;
    private double wep_triangle;

    /** Battle Formula Modifiers - more to come? */
    private static final double CH_SPD  = 1;
    private static final double WEP_WT = 2;
    private static final double MON_SPD  = 3;
    private static final int CRIT_MULT = 2;

    /* ************ */
    /* CONSTRUCTORS */
    /* ************ */

    /** Default constructor */
    public Battle(){

        // name, attack, weight, crit, type
        Weapon testWeapon = new Weapon("close", 1,1, "testWeapon", 5.0, 1);

        // String name, int level, int hitPoints, int attack, int defense, int speed, int experience, int money
        this.hero = new Hero(0, "testPlayer", 20, 0, 1, 5, 5,5, 0, testWeapon);

        // String name, int level, int hitPoints, int attack, int defense, int speed, String type
        this.enemy = new Monster(1, "testEnemy", 20, 1, "close", 1.0, 1, "Common", 1,1,1);

        setWeaponTriangle();
    }

    // Constructor for testing
    public Battle(Hero hero){
        this.hero = hero;

        // String name, int level, int hitPoints, int attack, int defense, int speed, String type
        this.enemy = new Monster(1, "testEnemy", 20, 1, "close", 1.0, 1, "Common", 1,1,1);

        setWeaponTriangle();
    }

    /** Constructs a Battle object with a Hero and Enemy Character */
    public Battle(Hero hero, Monster enemy) {
        this.hero = hero;
        this.enemy = enemy;

        setWeaponTriangle();
    }

    /* ************* */
    /* Battle Methods */
    /* ************* */

    /** Calculates if the attack hits or not - returns true if hit, false for miss
     * Hit = 100D <= max(0, 100 + α*CSpd - β*WWt - δ*MSpd */
    protected Boolean calc_hit() {
        Boolean landed = false;

        int calc = (int)Math.round(100 + (CH_SPD * this.hero.getSpeed()) - (WEP_WT * this.hero.getActive().getWeight()) - (MON_SPD * this.enemy.getSpeed()));
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
    protected Boolean calc_crit(){
        Boolean critical = false;
        int diceRoll = (int)(Math.random() * (101));
        if(diceRoll <= this.hero.getSpeed()/2 + this.hero.getActive().getCriticalRate()){
            critical = true;
        }
        return critical;
    }

    /** Calculates damage based on the formula:
     * Damage = max(1, (Attack Type Modifier * (CAtk + WAtk) - MDef))
     * returns an int with the damage value calculated */
    protected int calc_dmg(){
        int damage = 0;

        int calc = (int)Math.round(this.wep_triangle * (this.hero.getAttack() + this.hero.getActive().getAttack()) - this.enemy.getDefense());
        damage = Math.max(1, calc);

        //multiply damage if critical hit lands
        if(calc_crit()){
            damage = damage * CRIT_MULT;
        }
        return damage;
    }

    /** Calculates flee rate based on the formula:
     * 100D <= max(0, (CSpd - WWt - MSpeed))
     * returns a true if successful, false if not */
    protected Boolean calc_flee(){
        Boolean fled = false;

        int diceRoll = (int)(Math.random() * (101));
        if(diceRoll <= Math.max(0, (this.hero.getSpeed() - this.hero.getActive().getWeight() - this.enemy.getSpeed()))){
            fled = true;
        }
        return fled;
    }

    // Miscellaneous Methods
    ////////////////////////

    private void setWeaponTriangle(){

        String weapType = this.hero.getActive().getAttackType();
        String mons = this.enemy.getAttackType();

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

    /**It's showtime
    */
    protected void performBattle(){
        //for now, hero always attacks first - will change in the future */
        if(this.enemy.getHP() > 0 && this.hero.getHP() > 0) {
            if (this.calc_hit() == Boolean.TRUE) {
                //crit calculations are automatically done in the calc_dmg() stage
                int damage = this.calc_dmg();

                //subtract damage from monster's HP
                this.enemy.setHP(this.enemy.getHP() - damage);

                //enemy automatically attacks if they still have health left
                if (this.enemy.getHP() > 0 && this.hero.getHP() > 0)
                    this.hero.setHP(this.hero.getHP() - this.enemy.getAttack());
            }
        }
    }
}

