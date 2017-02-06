package attackontinytim.barquest;

public abstract class Character {
    
    /* ********* */
    /* VARIABLES */
    /* ********* */
    
    /** Common variables for all Character objects */
    protected String name;
    protected int level, hitPoints, attack, defense, speed;
    
    
    
    
    /* ************ */
    /* CONSTRUCTORS */
    /* ************ */
    
    /** Obligatory default constructor */
    public Character(){}
    
    
    
    
    /* *********** */
    /* GET-METHODS */
    /* *********** */
    
    /** Return Character Name */
    public String getName() {return name;}
    
    /** Return Character Level */
    public int getLevel() {return level;}
    
    /** Return Character HP */
    public int getHitPoints() {return hitPoints;}
    
    /** Return Character Attack */
    public int getAttack() {return attack;}
    
    /** Return Character Defense */
    public int getDefense() {return defense;}
    
    /** Return Character Speed */
    public int getSpeed() {return speed;}
    
    
    
    
    /* *********** */
    /* SET-METHODS */
    /* *********** */
    
    /** Appoint Character Name */
    public void  set_name(String name) {this.name = name;}
    
    /** Appoint Character Level */
    public void set_level(int level) {this.level = level;}
    
    /** Appoint Character HP */
    public void set_hit_points(int hitPoints) {this.hitPoints = hitPoints;}
    
    /** Appoint Character Attack */
    public void set_attack(int attack) {this.attack = attack;}
    
    /** Appoint Character Defense */
    public void set_defense(int defense) {this.defense = defense;}
    
    /** Appoint Character Speed */
    public void set_speed(int speed) {this.speed = speed;}
}