package attackontinytim.barquest.Database;

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
            MonsterRepo.addMonster(new Monster(1,"Bob", 100, 50, "Close", 5.0, 1, "Common", 5, 5, 5));
            MonsterRepo.addMonster(new Monster(2,"Tim", 50, 10, "Long", 5.0, 1, "Rare", 15, 15, 15));
            MonsterRepo.addMonster(new Monster(3,"MatLab", 200, 100, "Mid", 5.0, 1, "Uncommon", 50, 5, 25));
            MonsterRepo.addMonster(new Monster(4,"Jimbo", 10000, 5000, "Mid", 5.0, 1, "Common", 45, 55, 35));
        }

        if (WeaponRepo.getItemCount() == 0){
            WeaponRepo.addItem(new Weapon("Close", 1,1,"Dagger of Wood", 0.1, 0.5));
            WeaponRepo.addItem(new Weapon("Mid", 190,95,"Sword of a Thousand Truths", 9999, 0.1));
            WeaponRepo.addItem(new Weapon("Long", 55,3,"David Bowie", 10, 2.5));
        }

        if(ConsumableRepo.getConsumableCount() == 0){
            ConsumableRepo.addConsumable(new ConsumableItem("Heal", 5, "Potion", 5));
            ConsumableRepo.addConsumable(new ConsumableItem("Heal", 10, " Mega Potion", 10));
            ConsumableRepo.addConsumable(new ConsumableItem("Heal", 20, "Ultra Potion", 20));
            ConsumableRepo.addConsumable(new ConsumableItem("Heal", 40, "Max Potion", 45));
        }
    }
}
