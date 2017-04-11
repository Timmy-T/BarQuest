package attackontinytim.barquest;

import android.os.Parcelable;
import android.os.Parcel;

public class Quest implements Parcelable {
    
    /* ********* */
    /* VARIABLES */
    /* ********* */
    
    /** Common variables for all Quest objects */
    private int id;

     /* ************ */
    /* CONSTRUCTORS */
    /* ************ */
    
    /** Obligatory default constructor */
    public Quest(){}
    
    /** Construct a Quest object with the provided id*/
    public Quest(int newID) {
        this.id = newID;
    }
   
    
    
    
    
    /* *********** */
    /* GET-METHODS */
    /* *********** */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    /* ************* */
    /* MISC. METHODS */
    /* ************* */
    

    // Framework Methods
    /////////////////////

    // Parcel Constructor
    /*public Hero(Parcel in) {
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
    }

    public static final Parcelable.Creator<Hero> CREATOR = new Parcelable.Creator<Hero>() {

        public Hero createFromParcel(Parcel in) {
            return new Hero(in);
        }

        public Hero[] newArray(int size) {
            return new Hero[size];
        }
    };*/
}