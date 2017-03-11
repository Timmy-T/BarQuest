package attackontinytim.barquest.Database;

import android.database.Cursor;

/**
 * Created by Tim Buesking on 2/21/2017.
 */

public class ConsumableItem {
    public ConsumableItem(String effect, int effectStrength, String name, double value) {
        this.effect = effect;
        this.effectStrength = effectStrength;
        this.name = name;
        this.value = value;
    }

    public ConsumableItem(Cursor cursor)
    {
        this.effect = cursor.getString(0);
        this.effectStrength= cursor.getInt(1);
        this.name=cursor.getString(2);
        this.value=cursor.getInt(3);
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public int getEffectStrength() {
        return effectStrength;
    }

    public void setEffectStrength(int effectStrength) {
        this.effectStrength = effectStrength;
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

    private String effect;
    private int effectStrength;
    private String name;
    private double value;


}
