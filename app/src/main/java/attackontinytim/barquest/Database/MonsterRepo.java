package attackontinytim.barquest.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import attackontinytim.barquest.MainActivity;

/**
 * Created by Tim Buesking on 4/17/2017.
 */

public class MonsterRepo {
    private static final String TABLE_MONSTERS = "Monsters";
    // Monster Table Columns names
    private static final String KEY_ID = "ID";
    private static final String KEY_NAME = "Name";
    private static final String KEY_HP = "HP";
    private static final String KEY_XP = "XP";
    private static final String KEY_ATTACKTYPE = "AttackType";
    private static final String KEY_MONEY = "Money";
    private static final String KEY_LEVEL = "Level";
    private static final String KEY_RARITY = "Rarity";
    private static final String KEY_SPEED = "Speed";
    private static final String KEY_DEFENSE = "Defense";
    private static final String KEY_ATTACK = "Attack";


    public static String getTableName() {
        return TABLE_MONSTERS;
    }

    public static String createTable() {
        return "CREATE TABLE " + TABLE_MONSTERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT, "
                + KEY_HP + " INT,"
                + KEY_XP + " INT, "
                + KEY_ATTACKTYPE + " TEXT,"
                + KEY_MONEY + " DOUBLE,"
                + KEY_LEVEL + " INT,"
                + KEY_RARITY + " TEXT,"
                + KEY_SPEED + " INT,"
                + KEY_DEFENSE + " INT,"
                + KEY_ATTACK + " INT)";
    }

    /**
     * Function that adds a monster to the Monster table in the database
     * @param monster A monster object to add to the database
     */
    public static void addMonster(Monster monster) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, monster.getId());
        values.put(KEY_NAME, monster.getName()); // Monster name
        values.put(KEY_HP, monster.getHP()); // Monster hp
        values.put(KEY_XP, monster.getXP()); //Monster xp
        values.put(KEY_ATTACKTYPE, monster.getAttackType());
        values.put(KEY_MONEY, monster.getMoney());
        values.put(KEY_LEVEL,  monster.getLevel());
        values.put(KEY_RARITY, monster.getRarity());
        values.put(KEY_SPEED, monster.getSpeed());
        values.put(KEY_DEFENSE, monster.getDefense());
        values.put(KEY_ATTACK, monster.getAttack());

        // Inserting Row

        db.insert(TABLE_MONSTERS, null, values);
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection
    }

    /**
     * Returns a monster object with the given name
     * @param name Name of the monster
     * @return Monster object
     */
    public static Monster getMonsterByName(String name) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.query(TABLE_MONSTERS, new String[] {
                        KEY_ID,
                        KEY_NAME,
                        KEY_HP,
                        KEY_XP,
                        KEY_ATTACKTYPE,
                        KEY_MONEY,
                        KEY_LEVEL,
                        KEY_RARITY,
                        KEY_SPEED,
                        KEY_DEFENSE,
                        KEY_ATTACK}, KEY_NAME + "=?",
                new String[] { String.valueOf(name) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Monster contact = new Monster(cursor);
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection

        // return monster
        return contact;
    }

    /**
     * Returns a monster object with the given name
     * @param Id Of the monster
     * @return Monster object
     */
    public static Monster getMonsterByID(Integer Id) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.query(TABLE_MONSTERS, new String[] {
                        KEY_ID,
                        KEY_NAME,
                        KEY_HP,
                        KEY_XP,
                        KEY_ATTACKTYPE,
                        KEY_MONEY,
                        KEY_LEVEL,
                        KEY_RARITY,
                        KEY_SPEED,
                        KEY_DEFENSE,
                        KEY_ATTACK}, KEY_ID + "=?",
                new String[] { String.valueOf(Id) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Monster contact = new Monster(cursor);
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection

        // return monster
        return contact;
    }

    /**
     *  Returns a List of all monsters in the monster table
     * @return List containing all monsters
     */
    public static List<Monster> getAllMonsters() {

        List<Monster> monsterList = new ArrayList<Monster>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_MONSTERS;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Monster monster = new Monster(cursor);
                // Adding contact to list
                monsterList.add(monster);
            } while (cursor.moveToNext());
        }

        DatabaseManager.getInstance().closeDatabase(); // Closing database connection

        // return contact list
        return monsterList;
    }

    /**
     *  Returns number of monsters in the database
     * @return Number of monster records in the monsters table
     */
    public static int getMonsterCount() {
        String countQuery = "SELECT * FROM " + TABLE_MONSTERS;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        // return count

        int count = cursor.getCount();
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection

        return count;
    }

    /**
     * Updates a given monster's values in the monster table
     * @param monster Monster to update, update is made based on ID
     * @return Returns result of the update command
     */
    public static int updateMonster(Monster monster) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, monster.getId());
        values.put(KEY_NAME, monster.getName()); // Monster name
        values.put(KEY_HP, monster.getHP()); // Monster hp
        values.put(KEY_XP, monster.getXP()); //Monster xp
        values.put(KEY_ATTACKTYPE, monster.getAttackType());
        values.put(KEY_MONEY, monster.getMoney());
        values.put(KEY_LEVEL,  monster.getLevel());
        values.put(KEY_RARITY, monster.getRarity());
        values.put(KEY_SPEED, monster.getSpeed());
        values.put(KEY_DEFENSE, monster.getDefense());
        values.put(KEY_ATTACK, monster.getAttack());

        // updating row
         int result = db.update(TABLE_MONSTERS, values, KEY_ID + " = ?",
                    new String[]{String.valueOf(monster.getId())});
         DatabaseManager.getInstance().closeDatabase(); // Closing database connection

        return result;
    }

    /**
     * Deletes a monster record from the database
     * @param monster Monster to be deleted from database
     */
    public static void deleteMonster(Monster monster) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(TABLE_MONSTERS, KEY_ID + " = ?",
                new String[] { String.valueOf(monster.getId()) });
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection

    }
}

