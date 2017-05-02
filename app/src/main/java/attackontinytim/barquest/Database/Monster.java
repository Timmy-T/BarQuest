package attackontinytim.barquest.Database;

import android.database.Cursor;

public class Monster {

    private static final double MON_SPD  = 2;
    private static final double MON_ATK  = 2;

    private int id;
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
    private String image;

    public Monster(int id, String name, int HP, int XP, String attackType, double money, int level,
                   String rarity, int speed, int defense, int attack, String image)
    {
        this.id = id;
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
        this.image = image;
    }

    public Monster(Cursor cursor)
    {
        this.id = cursor.getInt(0);
        this.name= cursor.getString(1);
        this.HP=cursor.getInt(2);
        this.XP=cursor.getInt(3);
        this.attackType = cursor.getString(4);
        this.money = cursor.getDouble(5);
        this.level = cursor.getInt(6);
        this.rarity = cursor.getString(7);
        this.speed = cursor.getInt(8);
        this.defense = cursor.getInt(9);
        this.attack = cursor.getInt(10);
        this.image = cursor.getString(11);
    }

    /** Copy Constructor makes a deep copy of the Monster */
    public Monster cloneMonster() {
        return new Monster(
                this.getId(),
                this.getName(),
                this.getHP(),
                this.getXP(),
                this.getAttackType(),
                this.getMoney(),
                this.getLevel(),
                this.getRarity(),
                this.getSpeed(),
                this.getDefense(),
                this.getAttack(),
                this.getImage());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public int getAtkSpd(){
        int atk_spd = (int)Math.round(MON_SPD * this.getSpeed());

        if ((int)Math.round(this.getAttack()/MON_ATK) > this.getSpeed())
            atk_spd += (int)Math.round(this.getAttack()/MON_ATK);

        return atk_spd;
    }
}
