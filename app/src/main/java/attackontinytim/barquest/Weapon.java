package attackontinytim.barquest;

import android.os.Parcelable;
import android.os.Parcel;

public class Weapon implements Parcelable{
    
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
    public Weapon(){}
    
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

    // Framework Methods
    ///////////////////////
    // Framework Methods
    /////////////////////

    // Parcel Constructor
    public Weapon(Parcel in) {
        name = in.readString();
        type = in.readString();
        attack = in.readInt();
        weight = in.readInt();
        crit = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TO-DO: Parcelelize weapon attributes

        dest.writeString(name);
        dest.writeString(type);
        dest.writeInt(attack);
        dest.writeInt(weight);
        dest.writeInt(crit);
    }

    public static final Parcelable.Creator<Weapon> CREATOR = new Parcelable.Creator<Weapon>() {

        public Weapon createFromParcel(Parcel in) {
            return new Weapon(in);
        }

        public Weapon[] newArray(int size) {
            return new Weapon[size];
        }
    };
}