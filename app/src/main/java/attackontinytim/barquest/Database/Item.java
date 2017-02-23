package attackontinytim.barquest.Database;

import android.database.Cursor;

/**
 * Created by Tim Buesking on 2/21/2017.
 */

public class Item {
    private String attackType;
    private int attack;
    private int criticalRate;
    private String name;
    private double value;
    private double weight;

    public Item(String attackType, int attack, int criticalRate, String name, double value, double weight){
        this.attackType = attackType;
        this.attack = attack;
        this.criticalRate = criticalRate;
        this.name = name;
        this.value = value;
        this.weight = weight;
    }

    // TO DO: Change cursor to not be hardcode index instead use cursor.getString(cursor.getColumnIndex("COLUMN NAME"))
    // This is not currently done because the Item class and the DB interaction class are two separate classes
    public Item (Cursor cursor){
        this.name = cursor.getString(0);
        this.attackType = cursor.getString(1);
        this.attack = cursor.getInt(2);
        this.criticalRate = cursor.getInt(3);
        this.value = cursor.getDouble(4);
        this.weight = cursor.getDouble(5);
    }

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
