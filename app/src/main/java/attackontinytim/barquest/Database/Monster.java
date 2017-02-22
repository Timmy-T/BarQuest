package attackontinytim.barquest.Database;

import android.database.Cursor;

public class Monster {

    private String name;
    private int HP;
    private int XP;
    private String attackType;
    private double money;
    private int level;
    private String rarity;
    private int speed;
    private int defense;
    private int attack;

    public Monster(String name, int HP, int XP, String attackType, double money, int level, String rarity, int speed, int defense, int attack)
    {
        this.name=name;
        this.HP=HP;
        this.XP=XP;
        this.attackType = attackType;
        this.money = money;
        this.level = level;
        this.rarity = rarity;
        this.speed = speed;
        this.defense = defense;
        this.attack = attack;
    }

    public Monster(Cursor cursor)
    {
        this.name= cursor.getString(0);
        this.HP=cursor.getInt(1);
        this.XP=cursor.getInt(2);
        this.attackType = cursor.getString(3);
        this.money = cursor.getDouble(4);
        this.level = cursor.getInt(5);
        this.rarity = cursor.getString(6);
        this.speed = cursor.getInt(7);
        this.defense = cursor.getInt(8);
        this.attack = cursor.getInt(9);
    }


    public String getAttackType() {
        return attackType;
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
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
}
