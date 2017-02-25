package attackontinytim.barquest;

public class Battle {
    /* ********* */
    /* VARIABLES */
    /* ********* */

    public Player player;
    private Enemy enemy;
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
        Weapon testWeapon = new Weapon("testWeapon", 1, 1, 1, "close");

        // String name, int level, int hitPoints, int attack, int defense, int speed, int experience, int money
        this.player = new Player("testPlayer", 1, 20, 1, 1, 1, 0, 0);
        this.player.set_active(testWeapon);
        // String name, int level, int hitPoints, int attack, int defense, int speed, String type
        this.enemy = new Enemy("testEnemy", 1, 20, 1, 1, 1, "close");

        setWeaponTriangle();
    }

    // Constructor for testing
    public Battle(Player player){
        this.player = player;

        // String name, int level, int hitPoints, int attack, int defense, int speed, String type
        this.enemy = new Enemy("testEnemy", 1, 20, 1, 1, 1, "close");

        setWeaponTriangle();
    }

    /** Constructs a Battle object with a Player and Enemy Character */
    public Battle(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;

        setWeaponTriangle();
    }

    /* ************* */
    /* Battle Methods */
    /* ************* */

    /** Calculates if the attack hits or not - returns true if hit, false for miss
     * Hit = 100D <= max(0, α*CSpd - β*WWt - δ*MSpd */
    private Boolean calc_hit() {
        Boolean landed = false;

        int calc = (int)Math.round((CH_SPD * this.player.speed) - (WEP_WT * this.player.getActive().getWeight()) - (MON_SPD * this.enemy.speed));
        int maxCalc = Math.max(0, calc);
        int diceRoll = (int)(Math.random() * (101));

        if(diceRoll <= maxCalc){
            landed = true;
        }

        return landed;
    }

    /** Calculates Critical hit rate
     * Crit Rate = 100D <= CSpd + WCrit
     * returns true if crit, false if not */
    private Boolean calc_crit(){
        Boolean critical = false;
        int diceRoll = (int)(Math.random() * (101));
        if(diceRoll <= this.player.speed + this.player.getActive().getCrit()){
            critical = true;
        }
        return critical;
    }

    /** Calculates damage based on the formula:
     * Damage = max(1, (Attack Type Modifier * (CAtk + WAtk) - MDef))
     * returns an int with the damage value calculated */
    private int calc_dmg(){
        int damage = 0;

        int calc = (int)Math.round(this.wep_triangle * (this.player.attack + this.player.getActive().getAttack()) - this.enemy.defense);
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
    private Boolean calc_flee(){
        Boolean fled = false;

        int diceRoll = (int)(Math.random() * (101));
        if(diceRoll <= Math.max(0, (this.player.speed - this.player.getActive().getWeight() - this.enemy.speed))){
            fled = true;
        }
        return fled;
    }

    // Miscellaneous Methods
    ////////////////////////

    private void setWeaponTriangle(){
        //this is JAVA
        String weapType = this.player.getActive().getType();
        String mons = this.enemy.getType();

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
}

