package attackontinytim.barquest.Database;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by Tim Buesking on 2/21/2017.
 */

public class Weapon implements Parcelable{
    private String attackType;
    private int attack;
    private int criticalRate;
    private String name;
    private double value;
    private double weight;

    public Weapon(String attackType, int attack, int criticalRate, String name, double value, double weight){
        this.attackType = attackType;
        this.attack = attack;
        this.criticalRate = criticalRate;
        this.name = name;
        this.value = value;
        this.weight = weight;
    }

    // TO DO: Change cursor to not be hardcode index instead use cursor.getString(cursor.getColumnIndex("COLUMN NAME"))
    // This is not currently done because the Weapon class and the DB interaction class are two separate classes
    public Weapon(Cursor cursor){
        this.name = cursor.getString(0);
        this.attackType = cursor.getString(1);
        this.attack = cursor.getInt(2);
        this.criticalRate = cursor.getInt(3);
        this.value = cursor.getDouble(4);
        this.weight = cursor.getDouble(5);
    }

    // Parcel Constructor
    public Weapon(Parcel in) {
        this.name = in.readString();
        this.attackType = in.readString();
        this.attack = in.readInt();
        this.criticalRate = in.readInt();
        this.value = in.readDouble();
        this.weight = in.readDouble();
    }


    //// Parcelable Functions
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TO-DO: Parcelelize weapon attributes

        dest.writeString(this.name);
        dest.writeString(this.attackType);
        dest.writeInt(this.attack);
        dest.writeInt(this.criticalRate);
        dest.writeDouble(this.value);
        dest.writeDouble(this.weight);
    }

    public static final Parcelable.Creator<Weapon> CREATOR = new Parcelable.Creator<Weapon>() {

        public Weapon createFromParcel(Parcel in) {
            return new Weapon(in);
        }

        public Weapon[] newArray(int size) {
            return new Weapon[size];
        }
    };


    public String getAttackType() {
        return attackType;
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getCriticalRate() {
        return criticalRate;
    }

    public void setCriticalRate(int criticalRate) {
        this.criticalRate = criticalRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
