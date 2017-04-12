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
    
    private int hero_atk_spd;
    private int enemy_atk_spd;

    /** Battle Formula Modifiers */
    private static final double CH_SPD  = 2;
    private static final double CH_ATK  = 2;
    private static final double CH_DEF  = 2;
    private static final double WEP_WT = 2;
    private static final double MON_SPD  = 2;
    private static final double MON_ATK  = 2;
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
        this.hero = new Hero(0, "testPlayer", 20, 0, 1, 5, 5,5, 0, testWeapon);

        // String name, int level, int hitPoints, int attack, int defense, int speed, String type
        this.enemy = new Monster(1, "testEnemy", 20, 1, "close", 1.0, 1, "Common", 1,1,1);

        setWeaponTriangle();
        setAttackSpeeds();
    }

    // Constructor for testing
    public Battle(Hero hero){
        this.hero = hero;

        // String name, int level, int hitPoints, int attack, int defense, int speed, String type
        this.enemy = new Monster(1, "testEnemy", 20, 1, "close", 1.0, 1, "Common", 1,1,1);

        setWeaponTriangle();
        setAttackSpeeds();
    }

    /** Constructs a Battle object with a Hero and Enemy Character */
    public Battle(Hero hero, Monster enemy) {
        this.hero = hero;
        this.enemy = enemy;

        setWeaponTriangle();
        setAttackSpeeds();
    }

    /* ************* */
    /* Battle Methods */
    /* ************* */

    /** Calculates if the hero's attack hits or not - returns true if hit, false for miss
     * Hit = 100D <= max(0, WepTri * (100 + CAtkSpd - MEvade) */
    protected boolean calc_hit_hero() {
        boolean landed = false;
        
        int evade = this.enemy_atk_spd;
        if ((int)Math.round(this.enemy.getDefense()/MON_DEF) > this.enemy.getSpeed())
            evade += (int)Math.round(this.enemy.getDefense()/MON_DEF);

        int calc = this.wep_triangle * (100 + this.hero_atk_spd - evade);
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
        
        int evade = this.hero_atk_spd;
        if ((int)Math.round(this.hero.getDefense()/CH_DEF) > this.hero.getSpeed())
            evade += (int)Math.round(this.hero.getDefense()/CH_DEF);

        int calc = (2 - this.wep_triangle) * (100 + this.enemy_atk_spd - evade);
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
        if(diceRoll <= this.hero.getSpeed()/2 + this.hero.getActive().getCriticalRate()){
            critical = true;
        }
        return critical;
    }

    /** Calculates damage based on the formula:
     * Damage = max(1, (WepTri * (CAtk + WAtk) - MDef))
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
     * 100D <= max(50, (100 + CAtkSpd - MAtkSpd))
     * returns true if successful, false if not */
    protected boolean calc_flee(){
        boolean fled = false;

        int diceRoll = (int)(Math.random() * (101));
        if(diceRoll <= Math.max(50, (100 + hero_atk_spd - enemy_atk_spd))){
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
    
    private void setAttackSpeeds(){
        // Calculates Hero Attack Speed
        this.hero_atk_spd = (int)Math.round(CH_SPD * this.hero.getSpeed());
        if ((int)Math.round(WEP_WT * this.hero.getActive().getWeight()) > this.hero.getAttack())
            this.hero_atk_spd -= (int)Math.round(WEP_WT * this.hero.getActive().getWeight());
        if ((int)Math.round(this.hero.getAttack()/CH_ATK) > this.hero.getSpeed())
            this.hero_atk_spd += (int)Math.round(this.hero.getAttack()/CH_ATK);
        
        // Calculates Enemy Attack Speed
        this.enemy_atk_spd = (int)Math.round(MON_SPD * this.enemy.getSpeed());
        if ((int)Math.round(this.enemy.getAttack()/MON_ATK) > this.enemy.getSpeed())
            this.enemy_atk_spd += (int)Math.round(this.enemy.getAttack()/MON_ATK);
    }

    /**It's showtime
    */
    protected void performBattle(){
        int damage;
        if(this.enemy.getHP() > 0 && this.hero.getHP() > 0) {
            if (this.hero_atk_spd > this.enemy_atk_spd) {
                if (this.calc_hit_hero() == true) {
                    //crit calculations are automatically done in the calc_dmg() stage
                    damage = this.calc_dmg();

                    //subtract damage from monster's HP
                    this.enemy.setHP(this.enemy.getHP() - damage);

                    //enemy automatically attacks if they still have health left
                    if (this.enemy.getHP() > 0 && this.hero.getHP() > 0) {
                        if (this.calc_hit_enemy() == true) {
                            this.hero.setHP(this.hero.getHP() - this.enemy.getAttack());
                        }
                    }
                }
            }
            else {
                if (this.calc_hit_enemy() == true) {
                    this.hero.setHP(this.hero.getHP() - this.enemy.getAttack());
                    if (this.hero.getHP() > 0 && this.enemy.getHP() > 0){
                        if (this.calc_hit_hero() == true) {
                            damage = this.calc_dmg();
                            this.enemy.setHP(this.enemy.getHP() - damage);
                        }
                    }
                }
            }
        }
    }
}
