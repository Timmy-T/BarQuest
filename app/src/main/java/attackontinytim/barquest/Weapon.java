package attackontinytim.barquest;

public class Weapon {
    
    /* ********* */
    /* VARIABLES */
    /* ********* */
    
    /** Common variables for all Weapon objects */
    private int attack, weight, crit;
    private String name, type; // (Types are: Long-Range "long", Mid-Range "mid", Close-Range "close")
    
    
    
    
    /* ************ */
    /* CONSTRUCTORS */
    /* ************ */
    
    /** Obligatory default constructor */
    private Weapon(){}
    
    /** Construct a Weapon object with the provided stats */
    public Weapon(String name, int attack, int weight, int crit, String type) {
        this.name = name;
        this.attack = attack;
        this.weight = weight;
        this.crit = crit;
        this.type = type;
    }
    
    
    
    
    /* ************* */
    /* GET-FUNCTIONS */
    /* ************* */
    
    /** Return Weapon Name */
    public String getName() {
        return name;
    }
    
    /** Return Weapon Attack Strength */
    public int getAttack() {
        return attack;
    }
    
    /** Return Weapon Weight */
    public int getWeight() {
        return weight;
    }
    
    /** Return Weapon Critical Hit Rate */
    public int getCrit() {
        return crit;
    }
    
    /** Return Weapon Attack Type */
    public String getType() {
        return type;
    }
}