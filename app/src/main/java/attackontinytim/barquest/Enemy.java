package attackontinytim.barquest;

public class Enemy extends Character {
    
    /* ********* */
    /* VARIABLES */
    /* ********* */
    
    /** Common variables for all Enemy objects */
    private String type; // long, mid, close
    
    /* INHERITED */
    /* private int level, hitPoints, attack, defense, speed; */
    
    
    
    
    /* ************ */
    /* CONSTRUCTORS */
    /* ************ */
    
    /** Obligatory default constructor */
    private Enemy(){}
    
    /** Construct an Enemy object with the provided stats (for testing) */
    public Enemy(String name, int level, int hitPoints, int attack, int defense, int speed, String type) {
        this.name = name;
        this.level = level;
        this.hitPoints = hitPoints;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.type = type;
    }
    
    
    
    
    /* *********** */
    /* GET-METHODS */
    /* *********** */
    
    /** Return Enemy Attack Type */
    public String getType() {return type;}
    
    
    
    
    /* *********** */
    /* SET-METHODS */
    /* *********** */
    
    /** Appoint Enemy Attack Type (Long-Range "long", Mid-Range "mid", Close-Range "close") */
    public void set_type (String type) {this.type = type;}
}