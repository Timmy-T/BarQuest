package attackontinytim.barquest.Database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import attackontinytim.barquest.Hero;
import attackontinytim.barquest.Quest;

/**
 * Created by Tim Buesking on 4/17/2017.
 */

public class InsertDataValues {

    /**
     * This function will insert values into the respective database tables
     * If new values are added make sure to upgrade the database version located in DBHandler.java
     */
    public static void createDatabaseValues(){
        if (MonsterRepo.getMonsterCount() == 0) {
            MonsterRepo.addMonster(new Monster(1,"Bug", 10, 10, "Close", 5.0, 1, "Common", 5, 5, 5));
            MonsterRepo.addMonster(new Monster(2,"Virus", 10, 10, "Close", 5.0, 1, "Common", 5, 5, 5));
            MonsterRepo.addMonster(new Monster(3,"MatLab", 10, 10, "Mid", 5.0, 1, "Common", 5, 5, 5));
            MonsterRepo.addMonster(new Monster(4,"Porygon", 10, 10, "Mid", 5.0, 1, "Common", 5, 5, 5));
            MonsterRepo.addMonster(new Monster(5,"PHP", 10, 10, "Long", 5.0, 1, "Common", 5, 5, 5));
            MonsterRepo.addMonster(new Monster(6,"Labview", 10, 5000, "Long", 5.0, 1, "Common", 5, 5, 5));
            MonsterRepo.addMonster(new Monster(7,"Wurm", 10, 10, "Long", 5.0, 1, "Common", 5, 5, 5));
            MonsterRepo.addMonster(new Monster(8,"Trojan Horse", 10, 10, "Long", 5.0, 1, "Common", 5, 5, 5));
            MonsterRepo.addMonster(new Monster(9,"Visual Basic", 10, 10, "Mid", 5.0, 1, "Common", 5, 5, 5));
            MonsterRepo.addMonster(new Monster(10,"Logic Bomb", 10, 100, "Mid", 5.0, 1, "Common", 5, 5, 5));
            MonsterRepo.addMonster(new Monster(11,"Botnet", 30, 50, "Close", 15.0, 1, "Uncommon", 10, 5, 5));
            MonsterRepo.addMonster(new Monster(12,"Windows Defender", 30, 50, "Mid", 15.0, 1, "Uncommon", 5, 10, 5));
            MonsterRepo.addMonster(new Monster(13,"Toolbars", 30, 50, "Long", 15.0, 1, "Uncommon", 5, 5, 10));
            MonsterRepo.addMonster(new Monster(14,"Windows Vista", 30, 50, "Long", 15.0, 1, "Uncommon", 10, 5, 10));
            MonsterRepo.addMonster(new Monster(15,"SegFault", 50, 50, "Mid", 15.0, 1, "Rare", 15, 5, 10));
            MonsterRepo.addMonster(new Monster(16,"Null Pointer", 50, 50, "Mid", 15.0, 1, "Rare", 10, 5, 15));
            MonsterRepo.addMonster(new Monster(17,"Crippling Anxiety", 100, 1000, "Mid", 0.0, 1, "Ultra Common", 5, 5, 250));
            MonsterRepo.addMonster(new Monster(18,"Norton Antivirus", 100, 1000, "Close", 1000.0, 5, "Boss", 20, 20, 50));
        }

        if (WeaponRepo.getItemCount() == 0){
            WeaponRepo.addItem(new Weapon("Close", 1,1,"Dagger of Wood", 0.1, 0.5));
            WeaponRepo.addItem(new Weapon("Close", 2,3,"USB Of Antivirus", 5, 0.1));
            WeaponRepo.addItem(new Weapon("Close", 3,5,"Swiss Army C", 10, 0.3));
            WeaponRepo.addItem(new Weapon("Close", 4,5,"Stick of RAM", 10, 0.5));
            WeaponRepo.addItem(new Weapon("Mid", 190,95,"Sword of a Thousand Truths", 9999, 0.1));
            WeaponRepo.addItem(new Weapon("Mid", 10,10,"Linux", 10, 0.1));
            WeaponRepo.addItem(new Weapon("Mid", 15,5,"Katana", 20, 1));
            WeaponRepo.addItem(new Weapon("Mid", 20,2,"Solid State Dagger", 30, 0.5));
            WeaponRepo.addItem(new Weapon("Long", 55,3,"David Bowie", 10, 2.5));
            WeaponRepo.addItem(new Weapon("Long", 55,3,"Thrown Fedora", 10, 2.5));
            WeaponRepo.addItem(new Weapon("Long", 55,3,"Netscan", 10, 2.5));
            WeaponRepo.addItem(new Weapon("Long", 55,3,"HDD of Destiny", 10, 2.5));
        }
        //The order of effects (the first four ints) goes as follows: HP, Speed, Defense, Attack
        if(ConsumableRepo.getConsumableCount() == 0){
            ConsumableRepo.addConsumable(new ConsumableItem("Heal", 5, 0, 0, 0, "Hero", "Virus Scan", 5));
            ConsumableRepo.addConsumable(new ConsumableItem("Heal", 10, 0, 0, 0, "Hero", "Antivirus", 10));
            ConsumableRepo.addConsumable(new ConsumableItem("Heal", 20, 0, 0, 0, "Hero",  "Malwarebytes", 20));
            ConsumableRepo.addConsumable(new ConsumableItem("Heal", 40, 0, 0, 0, "Hero", "Downloaded RAM", 45));
            ConsumableRepo.addConsumable(new ConsumableItem("Heal", 40, 0, 0, 0, "Hero", "Max Potion", 45));
            ConsumableRepo.addConsumable(new ConsumableItem("Attack Up", 0, 0, 0, 10, "Hero", "Debug", 25 ));
            ConsumableRepo.addConsumable(new ConsumableItem("Defense Up", 0, 0, 10, 0, "Hero", "Virus Shield", 25 ));
            ConsumableRepo.addConsumable(new ConsumableItem("Speed Up", 0, 10, 0, 0, "Hero", "Coffee", 25 ));
            ConsumableRepo.addConsumable(new ConsumableItem("Attack Up", 0, 0, 0, 25, "Hero", "All Out Attack", 45 ));
            ConsumableRepo.addConsumable(new ConsumableItem("Defense Up", 0, 0, 25, 0, "Hero", "All Out Defense", 45 ));
            ConsumableRepo.addConsumable(new ConsumableItem("Speed Up", 0, 25, 0, 0, "Hero", "Dodgey!", 45 ));
            ConsumableRepo.addConsumable(new ConsumableItem("Monster Attack Debuff", 0, 0, 0, -10, "Monster", "Virus Attack Down", 25 ));
            ConsumableRepo.addConsumable(new ConsumableItem("Monster Defense Debuff", 0, 0, -10, 0, "Monster", "Virus Defense Down", 25 ));
            ConsumableRepo.addConsumable(new ConsumableItem("Monster Speed Debuff", 0, -10, 0, 0, "Monster", "Virus Speed Down", 25 ));
            ConsumableRepo.addConsumable(new ConsumableItem("Attack Up", 0, 0, 0, 100, "Hero", "ONE PAAAAANCH", 500 ));
        }
    }
    /**
     * Inserts a hero into the hero table
     * Gives the hero the default weapon in the inventory
     * Gives the hero three virus scan pots
     */
    public static void initializeHeroValues(){
        QuestRepo.addQuest(new Quest(1, "Begin", "Welcome to a mystical land of mystery, code and danger", 1, 0, false, 10, 10, "Swiss Army C", "Rarity", "Common"));
        QuestRepo.addQuest(new Quest(2, "Master Slayer", "Slay 5 viruses", 5, 0, false, 20, 20, "", "Type", "Virus"));
        QuestRepo.addQuest(new Quest(3, "Overcoming Your Fears", "Defeat Crippling Anxiety", 1, 0, false, 100, 100, "", "Type", "Crippling Anxiety"));
        QuestRepo.addQuest(new Quest(4, "Slay a boss", "You must face the most fearsome creature this land has to offer, a boss monster.", 1,0, false, 200, 200, "Solid State Dagger", "Rarity", "Boss"));

        Hero hero = new Hero("HERO");
        hero.setCurrentQuest(QuestRepo.getQuestByID(1));
        HeroRepo.addHero(hero);

        InventoryRepo.addItemToInventory(HeroRepo.getHeroByName("HERO").getActive());
        InventoryRepo.addItemToInventory(ConsumableRepo.getConsumableByName("Virus Scan"));
        InventoryRepo.addItemToInventory(ConsumableRepo.getConsumableByName("Virus Scan"));
        InventoryRepo.addItemToInventory(ConsumableRepo.getConsumableByName("Virus Scan"));

        // Creates a list of longs for the timers
        List<Long> timerList = new ArrayList<>();

        for (int i =0; i < 10; i++){
            Calendar temp = Calendar.getInstance();
            temp.add(Calendar.HOUR, -2);
            Long otherTemp = temp.getTimeInMillis();

            timerList.add(temp.getTimeInMillis());
        }

        TimerRepo.addTimers( timerList, "HERO");
    }
}
