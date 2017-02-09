package com.example.scott.app;

public class Monster {

    private String name;
    private int HP;
    private int XP;
    public Monster()
    {
    }
    public Monster(String name, int HP, int XP){
        this.name=name;
        this.HP=HP;
        this.XP=XP;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }
    public int getXP() {
        return XP;
    }

    public int getHP() {
        return HP;
    }


}
