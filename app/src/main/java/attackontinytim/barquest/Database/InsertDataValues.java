package attackontinytim.barquest.Database;

import attackontinytim.barquest.Hero;

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

        if(ConsumableRepo.getConsumableCount() == 0){
            ConsumableRepo.addConsumable(new ConsumableItem("Heal", 5, "Virus Scan", 5));
            ConsumableRepo.addConsumable(new ConsumableItem("Heal", 10, " Antivirus", 10));
            ConsumableRepo.addConsumable(new ConsumableItem("Heal", 20, "Malwarebytes", 20));
            ConsumableRepo.addConsumable(new ConsumableItem("Heal", 40, "Downloaded RAM", 45));
            ConsumableRepo.addConsumable(new ConsumableItem("Heal", 40, "Max Potion", 45));
        }
    }

    /**
     * Inserts a hero into the hero table
     * Gives the hero the default weapon in the inventory
     * Gives the hero three virus scan pots
     */
    public static void initializeHeroValues(){
        HeroRepo.addHero(new Hero("HERO"));
        InventoryRepo.addItemToInventory(HeroRepo.getHeroByName("HERO").getActive());
        InventoryRepo.addItemToInventory(ConsumableRepo.getConsumableByName("Virus Scan"));
        InventoryRepo.addItemToInventory(ConsumableRepo.getConsumableByName("Virus Scan"));
        InventoryRepo.addItemToInventory(ConsumableRepo.getConsumableByName("Virus Scan"));
    }
}
