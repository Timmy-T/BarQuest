package attackontinytim.barquest.Database;

import android.database.Cursor;

/**
 * Created by Tim Buesking on 2/21/2017.
 */

public class ConsumableItem {
    public ConsumableItem(String effect, int HPeffect, int speedEffect, int defenseEffect, int attackEffect, String target, String name, double value) {
        this.name = name;
        this.effect = effect;
        this.value = value;
        this.HPeffect = HPeffect;
        this.speedEffect = speedEffect;
        this.defenseEffect = defenseEffect;
        this.attackEffect = attackEffect;
        this.target = target;
    }

    public ConsumableItem(Cursor cursor)
    {
        this.name=cursor.getString(0);
        this.effect = cursor.getString(1);
        this.value=cursor.getInt(2);
        this.HPeffect = cursor.getInt(3);
        this.speedEffect = cursor.getInt(4);
        this.defenseEffect = cursor.getInt(5);
        this.attackEffect = cursor.getInt(6);
        this.target = cursor.getString(7);
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
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

    public int getHPeffect() {
        return HPeffect;
    }

    public void setHPeffect(int HPeffect) {
        this.HPeffect = HPeffect;
    }

    public int getSpeedEffect() {
        return speedEffect;
    }

    public void setSpeedEffect(int speedEffect) {
        this.speedEffect = speedEffect;
    }

    public int getDefenseEffect() {
        return defenseEffect;
    }

    public void setDefenseEffect(int defenseEffect) {
        this.defenseEffect = defenseEffect;
    }

    public int getAttackEffect() {
        return attackEffect;
    }

    public void setAttackEffect(int attackEffect) {
        this.attackEffect = attackEffect;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    private String name;
    private String effect;
    private double value;
    private int HPeffect;
    private int speedEffect;
    private int defenseEffect;
    private int attackEffect;
    private String target;
}
