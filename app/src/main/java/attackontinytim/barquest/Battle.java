package attackontinytim.barquest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import java.lang.Runnable;
import java.util.Collections;
import java.util.List;
import android.util.Log;

import attackontinytim.barquest.Database.ConsumableItem;
import attackontinytim.barquest.Database.InventoryRepo;
import attackontinytim.barquest.Database.Monster;
import attackontinytim.barquest.Database.ConsumableRepo;
import attackontinytim.barquest.Database.Weapon;
import attackontinytim.barquest.Database.WeaponRepo;

public class Battle {
    /* ********* */
    /* VARIABLES */
    /* ********* */
    String TAG = BattleActivity.class.getSimpleName();

    public Hero hero;
    public Monster enemy;
    protected Hero battleHero;
    protected Monster battleEnemy;

    private double wep_triangle;
    private boolean priority;

    /** Battle Formula Modifiers*/
    private static final double CH_DEF = 2;
    private static final double MON_DEF  = 2;
    private static final int CRIT_MULT = 2;

    /** Misc Variables */
    protected String winWeaponName = "";
    protected String winItemName = "";
    protected ConsumableItem usedItem;

    /* ************ */
    /* CONSTRUCTORS */
    /* ************ */

    /** Constructs a Battle object with a Hero and Enemy Character */
    public Battle(Hero hero, Monster enemy) {
        this.hero = hero;
        this.enemy = enemy;

        // Create temp objects for stat manipulation
        this.battleHero = this.hero.cloneHero();
        this.battleEnemy = this.enemy.cloneMonster();

        setWeaponTriangle();
    }

    /* ************* */
    /* Battle Methods */
    /* ************* */

    /** Calculates if the hero's attack hits or not - returns true if hit, false for miss
     * Hit = 100D <= max(0, WepTri * (100 + CAtkSpd - MEvade) */
    protected boolean calc_hit_hero() {
        boolean landed = false;

        int evade = this.battleEnemy.getAtkSpd();
        if ((int)Math.round(this.battleEnemy.getDefense()/MON_DEF) > this.battleEnemy.getSpeed())
            evade += (int)Math.round(this.battleEnemy.getDefense()/MON_DEF);

        int calc = (int)this.wep_triangle * (100 + this.battleHero.getAtkSpd() - evade);
        int maxCalc = Math.max(0, calc);
        int diceRoll = (int)(Math.random() * (101));

        if(diceRoll <= maxCalc){
            landed = true;
        }

        return landed;
    }

    /** Calculates if the enemy's attack hits or not - returns true if hit, false for miss
     * Hit = 100D <= max(0, (2 - WepTri) * (100 + MAtkSpd - CMEvade) */
    protected boolean calc_hit_enemy() {
        boolean landed = false;

        int evade = this.battleHero.getAtkSpd();
        if ((int)Math.round(this.battleHero.getDefense()/CH_DEF) > this.battleHero.getSpeed())
            evade += (int)Math.round(this.battleHero.getDefense()/CH_DEF);

        int calc = (int)(2 - this.wep_triangle) * (100 + this.battleEnemy.getAtkSpd() - evade);
        int maxCalc = Math.max(0, calc);
        int diceRoll = (int)(Math.random() * (101));

        if(diceRoll <= maxCalc){
            landed = true;
        }

        return landed;
    }

    /** Calculates Critical hit rate
     * Crit Rate = 100D <= CSpd/2 + WCrit
     * returns true if crit, false if not */
    protected boolean calc_crit(){
        boolean critical = false;
        int diceRoll = (int)(Math.random() * (101));
        if(diceRoll <= this.battleHero.getSpeed()/2 + this.battleHero.getActive().getCriticalRate()){
            critical = true;
        }
        return critical;
    }

    /** Calculates damage based on the formula:
     * Damage = max(1, (WepTri * (CAtk + WAtk) - MDef))
     * returns an int with the damage value calculated */
    protected int calc_dmg(){
        int calc = (int)Math.round(this.wep_triangle * (this.battleHero.getAttack() + this.battleHero.getActive().getAttack()) - this.battleEnemy.getDefense());
        int damage = Math.max(1, calc);

        //multiply damage if critical hit lands
        if(calc_crit()){
            damage = damage * CRIT_MULT;
        }
        return damage;
    }

    /** Calculates flee rate based on the formula:
     * 100D <= max(50, (100 + CAtkSpd - MAtkSpd))
     * returns true if successful, false if not */
    protected boolean calc_flee(){
        boolean fled = false;

        int diceRoll = (int)(Math.random() * (101));
        if(diceRoll <= Math.max(50, (100 + this.battleHero.getAtkSpd() - this.battleEnemy.getAtkSpd()))){
            fled = true;
        }
        return fled;
    }

    // Miscellaneous Methods
    ////////////////////////

    private void setWeaponTriangle(){
        String weapType = this.battleHero.getActive().getAttackType();
        String mons = this.battleEnemy.getAttackType();

        if ((weapType.equals("long") && mons.equals("mid")) ||
                (weapType.equals("mid") && mons.equals("close")) ||
                (weapType.equals("close") && mons.equals("long"))){
            this.wep_triangle = 1.1;
        }
        else if ((weapType.equals("long") && mons.equals("close")) ||
                (weapType.equals("mid") && mons.equals("long")) ||
                (weapType.equals("close") && mons.equals("mid"))){
            this.wep_triangle = 0.9;
        }
        else{
            this.wep_triangle = 1;
        }
    }

    protected void checkPriority(){
        if (this.battleHero.getAtkSpd() >= this.battleEnemy.getAtkSpd())
            this.priority = true;
        else
            this.priority = false;
    }

    /** Returns true if Hero has attack priority; false otherwise */
    protected boolean heroPriority(){
        return this.priority;
    }

    /**
     * Uses an item from the hero's inventory
     */
    protected void consumeItem(ConsumableItem item){
        //assume item exists in inventory, because if it didn't we wouldn't be able to click on it
        //from the item screen

        //apply effects to the hero or the enemy
        if (item.getTarget().equalsIgnoreCase("Hero")){
            this.battleHero.setHP(this.battleHero.getHP() + item.getHPeffect());
            if (battleHero.getHP() > hero.getHP()){
                battleHero.setHP(hero.getHP()); //prevent HP overflow error
            }
            this.battleHero.setSpeed(this.battleHero.getSpeed() + item.getSpeedEffect());
            this.battleHero.setDefense(this.battleHero.getDefense() + item.getDefenseEffect());
            this.battleHero.setAttack(this.battleHero.getAttack() + item.getAttackEffect());
        }

        else{ //item affects monster
            this.battleEnemy.setHP(this.battleEnemy.getHP() + item.getHPeffect());
            if (battleEnemy.getHP() > enemy.getHP()){
                battleEnemy.setHP(enemy.getHP());
            }
            this.battleEnemy.setSpeed(this.battleEnemy.getSpeed() + item.getSpeedEffect());
            this.battleEnemy.setDefense(this.battleEnemy.getDefense() + item.getDefenseEffect());
            this.battleEnemy.setAttack(this.battleEnemy.getAttack() + item.getAttackEffect());
        }

        //now that the item has been consumed, remove it from the inventory
        usedItem = item;
        InventoryRepo.subtractItemFromInvetory(item);

        Log.d(TAG, "battleHero HP after: " + String.valueOf(this.battleHero.getHP()));
        return;
    }

    /**And it's finally showtime
     /**performs hero's turn in a battle
     * returns True if attack succeeds, False otherwise*/
    protected boolean heroTurn() {
        boolean success = false;
        if(this.battleEnemy.getHP() > 0 && this.battleHero.getHP() > 0) {
            if (this.calc_hit_hero()) {
                success = true;
                Log.d(TAG, "battleEnemy HP before: " + String.valueOf(this.battleEnemy.getHP()));
                //crit calculations are automatically done in the calc_dmg() stage
                int damage = this.calc_dmg();

                //subtract damage from monster's HP
                if(this.battleEnemy.getHP() - damage < 0){
                    this.battleEnemy.setHP(0);
                }
                else
                    this.battleEnemy.setHP(this.battleEnemy.getHP() - damage);
                Log.d(TAG, "battleEnemy HP after: " + String.valueOf(this.battleEnemy.getHP()));
            }
        }
        return success;
    }

    /**performs enemy's turn in a battle
     * returns True if attack succeeds, False otherwise */
    protected boolean enemyTurn() {
        boolean success = false;

        if (this.battleEnemy.getHP() > 0 && this.battleHero.getHP() > 0) {
            if(this.calc_hit_enemy()){
                Log.d(TAG, "battleHero HP before: " + String.valueOf(this.battleHero.getHP()));
                success = true;
                if(this.battleHero.getHP() - this.battleEnemy.getAttack() < 0){
                    this.battleHero.setHP(0);
                }
                else
                    this.battleHero.setHP(this.battleHero.getHP() - this.battleEnemy.getAttack());
                Log.d(TAG, "battleHero HP after: " + String.valueOf(this.battleHero.getHP()));
            }
        }
        return success;
    }

    protected boolean isLost(){
        if (this.battleHero.getHP() <= 0){
            return true;
        }
        return false;
    }

    protected boolean isWon(){
        if (this.battleEnemy.getHP() <= 0){
            return true;
        }
        return false;
    }

    //returns true if the hero levels up
    protected boolean setReward(){
        boolean leveled = false;

        /** Update Quest */
        hero.getCurrentQuest().updateQuestProgress(this.battleEnemy);

        /** Gain Money */
        hero.setMoney(hero.getMoney() + enemy.getMoney());

        /** Loot Drop */
        int rand = (int)(Math.random()*10);
        if(rand < 2) {
            /** Drop Random Weapon (20%) */
            List<Weapon> wList = WeaponRepo.getAllItems();
            Collections.shuffle(wList);
            Weapon winWeapon = wList.get(0);
            winWeaponName = winWeapon.getName();
            InventoryRepo.addItemToInventory(winWeapon);

        }
        else {
            /** Drop Random Consumable (80%) */
            List<ConsumableItem> cList = ConsumableRepo.getAllConsumables();
            Collections.shuffle(cList);
            ConsumableItem winItem = cList.get(0);
            winItemName = winItem.getName();
            InventoryRepo.addItemToInventory(winItem);
        }

        /** Gain XP + Level Up */
        int xp = hero.getXP();
        hero.inc_experience(enemy.getXP());
        if(xp + enemy.getXP() >= 100) {
            leveled = true;
        }

        return leveled;
    }

    protected void setPenalty(){
        /** Lose money (10%) */
        hero.setMoney(hero.getMoney() - (hero.getMoney()/10));
    }
}