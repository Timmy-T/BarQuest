package attackontinytim.barquest;

import android.os.Parcelable;
import android.os.Parcel;

import attackontinytim.barquest.Database.Weapon;

public class Hero implements Parcelable {
    
    /* ********* */
    /* VARIABLES */
    /* ********* */
    
    /** Common variables for all Hero objects */
    private int id;
    private String name;
    private int HP;
    private int XP;
    private int level;
    private int speed;
    private int defense;
    private int attack;
    private double money;
    private Weapon active;
    private Quest currentQuest;

     /* ************ */
    /* CONSTRUCTORS */
    /* ************ */
    
    /** Obligatory default constructor */
    public Hero(){}
    
    /** Construct a Hero object with the provided name (fresh hero character) */
    public Hero(String name) {
        this.id = 0;
        this.name = name;
        this.HP = 20;
        this.XP = 0 ;
        this.money = 0;
        this.level = 1;
        this.speed = 5;
        this.defense = 5;
        this.attack = 5;
        // Default cuz reasons
        this.active =  new Weapon("Close", 1,1,"Dagger of Wood", 0.1, 0.5);
        this.currentQuest = new Quest();
    }
    
    /** Construct a Hero object with the provided stats (for testing) */
    public Hero(int id, String name, int HP, int XP, int level, int speed, int defense, int attack, double money, Weapon active, Quest currentQuest) {
        this.id = id;
        this.name = name;
        this.HP = HP;
        this.XP = XP;
        this.level = level;
        this.speed = speed;
        this.defense = defense;
        this.attack = attack;
        this.money = money;

        // Default cuz reasons
        this.active = active;
        this.currentQuest = currentQuest;
    }
    
    
    
    
    /* *********** */
    /* GET-METHODS */
    /* *********** */

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getHP() {
        return HP;
    }
    public int getXP() {
        return XP;
    }
    public double getMoney() {
        return money;
    }
    public Weapon getActive() {
        return active;
    }
    public int getAttack() {
        return attack;
    }
    public int getLevel() {
        return level;
    }
    public int getSpeed() {
        return speed;
    }
    public int getDefense() {
        return defense;
    }
    public Quest getCurrentQuest() { return currentQuest; }

    public void setId(int id) {
        this.id = id;
    }
    public void setHP(int HP) {
        this.HP = HP;
    }
    public void setXP(int XP) {
        this.XP = XP;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setMoney(double money) {
        this.money = money;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public void setActive(Weapon active) {
        this.active = active;
    }
    public void setCurrentQuest(Quest currentQuest) {
        this.currentQuest = currentQuest;
    }

    public static Creator<Hero> getCREATOR() {
        return CREATOR;
    }
    
    
    
    /* ************* */
    /* MISC. METHODS */
    /* ************* */
    
    /** Increment Hero Experience count by the provided amount */
    public void inc_experience(int expGain) {
        
        this.setXP( this.getXP() + expGain);

        // While loop in case multiple levels are gained
        while (this.getXP() >= 100) {
            this.setLevel( this.getLevel() + 1);
            this.setXP( this.getXP() - 100 );
        }
    }

    // Framework Methods
    /////////////////////

    // Parcel Constructor
    public Hero(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.HP = in.readInt();
        this.XP = in.readInt();
        this.level = in.readInt();
        this.speed = in.readInt();
        this.defense = in.readInt();
        this.attack = in.readInt();
        this.money = in.readDouble();
        this.active = in.readParcelable(Weapon.class.getClassLoader());
        this.currentQuest =  in.readParcelable(Quest.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }
	
	// This allows us to use parcelling for easier transmission of Hero objects
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TO-DO: Parcelelize weapon attributes
        dest.writeInt(this.getId());
        dest.writeString(this.getName());
        dest.writeInt(this.getHP());
        dest.writeInt(this.getXP());
        dest.writeInt(this.getLevel());
        dest.writeInt(this.getSpeed());
        dest.writeInt(this.getDefense());
        dest.writeInt(this.getAttack());
        dest.writeDouble(this.getMoney());
        dest.writeParcelable(this.getActive(), flags);
        dest.writeParcelable(this.getCurrentQuest(), flags);
    }

    public static final Parcelable.Creator<Hero> CREATOR = new Parcelable.Creator<Hero>() {

        public Hero createFromParcel(Parcel in) {
            return new Hero(in);
        }

        public Hero[] newArray(int size) {
            return new Hero[size];
        }
    };
}