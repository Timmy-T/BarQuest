package attackontinytim.barquest.Database;

import android.database.Cursor;

/**
 * Created by Scott on 4/07/2017.
 */

public class Hero {
    public Hero(int id, String name, int HP, int XP, int level, int speed, int defense, int attack, double money, Weapon active) {
        this.id = id;
        this.name = name;
        this.HP = HP;
        this.XP = XP;
        this.level = level;
        this.speed = speed;
        this.defense = defense;
        this.attack = attack;
        this.money = money;
        this.active = active;
    }

    public Hero(Cursor cursor)
    {
        this.id = cursor.getInt(0);
        this.name = cursor.getString(1);
        this.HP = cursor.getInt(2);
        this.XP = cursor.getInt(3);
        this.level = cursor.getInt(4);
        this.speed = cursor.getInt(5);
        this.defense = cursor.getInt(6);
        this.attack = cursor.getInt(7);
        this.money = cursor.getDouble(8);
        this.active = new Weapon("Close", 1,1,"Dagger of Wood", 0.1, 0.5);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Weapon getActive() {
        return active;
    }

    public void setActive(Weapon active) {
        this.active = active;
    }

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

}
