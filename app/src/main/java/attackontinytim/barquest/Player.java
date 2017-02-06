package attackontinytim.barquest;

public class Player extends Character {
    
    /* ********* */
    /* VARIABLES */
    /* ********* */
    
    /** Common variables for all Player objects */
    private int experience, money;
    private Weapon active;
    
    /* We'll probably need this variable for leveling up stats, but not sure how we want to implement */
    //private int statPoints;
    
    /* INHERITED */
    /* protected int level, hitPoints, attack, defense, speed; */
    
    
    
    
    /* ************ */
    /* CONSTRUCTORS */
    /* ************ */
    
    /** Obligatory default constructor */
    private Player(){}
    
    /** Construct a Player object with the provided name (fresh player character) */
    public Player(String name) {
        this.name = name;
        this.level = 1;
        this.hitPoints = 20;
        this.attack = 5;
        this.defense = 5;
        this.speed = 5;
        this.experience = 0;
        this.money = 0;
    }
    
    /** Construct a Player object with the provided stats (for testing) */
    public Player(String name, int level, int hitPoints, int attack, int defense, int speed, int experience, int money) {
        this.name = name;
        this.level = level;
        this.hitPoints = hitPoints;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.experience = experience;
        this.money = money;
    }
    
    
    
    
    /* *********** */
    /* GET-METHODS */
    /* *********** */
    
    /** Return Player Experience count */
    public int getExperience() {return experience;}
    
    /** Return Player Money count */
    public int getMoney() {return money;}
    
    /** Return Player's Active Weapon */
    public Weapon getActive() {return active;}
    
    
    
    
    /* *********** */
    /* SET-METHODS */
    /* *********** */
    
    /** Appoint Player Experience count (0-100) */
    public void set_experience(int experience) {this.experience = experience;}
    
    /** Appoint Player Money count */
    public void set_money(int money) {this.money = money;}
    
    /** Appoint Player's Active Weapon */
    public void set_active(Weapon active) {this.active = active;}
    
    
    
    
    /* ************* */
    /* MISC. METHODS */
    /* ************* */
    
    /** Increment Player Experience count by the provided amount */
    public void inc_experience(int expGain) {
        
        this.experience = this.experience + expGain;
        
        if (this.experience >= 100) {
            this.level++;
            this.experience = this.experience - 100;
        }
    }
}