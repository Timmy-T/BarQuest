package attackontinytim.barquest.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import attackontinytim.barquest.Hero;

/**
 * Created by Tim Buesking on 4/19/2017.
 */

public class HeroRepo {
    // Contacts table name
    private static final String TABLE_HERO = "Hero";
    // Hero Table Columns names
    private static final String KEY_ID = "ID";
    private static final String KEY_NAME = "Name";
    private static final String KEY_HP = "HP";
    private static final String KEY_XP = "XP";
    private static final String KEY_LEVEL = "Level";
    private static final String KEY_SPEED = "Speed";
    private static final String KEY_DEFENSE = "Defense";
    private static final String KEY_ATTACK = "Attack";
    private static final String KEY_MONEY = "Money";
    private static final String KEY_ACTIVE = "Active";
    private static final String KEY_AP = "AP";
    private static final String KEY_QUESTID = "QuestID";

    public static String getTableName() {
        return TABLE_HERO;
    }


    public static String createTable() {
        return "CREATE TABLE " + TABLE_HERO + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_HP + " INT,"
                + KEY_XP + " INT,"
                + KEY_LEVEL + " INT,"
                + KEY_SPEED + " INT,"
                + KEY_DEFENSE + " INT,"
                + KEY_ATTACK + " INT,"
                + KEY_MONEY + " DOUBLE,"
                + KEY_ACTIVE + " TEXT,"
                + KEY_AP + " INT,"
                + KEY_QUESTID + " INT)";
        }


    /**
     * Function that adds a hero to the Hero table in the database
     * @param hero A hero object to add to the database
     */
    public static void addHero(Hero hero) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, hero.getId());
        values.put(KEY_NAME, hero.getName()); // hero name
        values.put(KEY_HP, hero.getHP()); // hero hp
        values.put(KEY_XP, hero.getXP()); //hero xp
        values.put(KEY_LEVEL,  hero.getLevel());
        values.put(KEY_SPEED, hero.getSpeed());
        values.put(KEY_DEFENSE, hero.getDefense());
        values.put(KEY_ATTACK, hero.getAttack());
        values.put(KEY_MONEY, hero.getMoney());
        values.put(KEY_ACTIVE, hero.getActive().getName());
        values.put(KEY_AP, hero.getAP());
        values.put(KEY_QUESTID, hero.getCurrentQuest().getId());

        // Inserting Row

        db.insert(TABLE_HERO, null, values);
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection
    }

    /**
     * Returns a hero object with the given name
     * @param name Name of the hero
     * @return hero object
     */
    public static Hero getHeroByName(String name) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.query(TABLE_HERO, new String[] {
                        KEY_ID,
                        KEY_NAME,
                        KEY_HP,
                        KEY_XP,
                        KEY_LEVEL,
                        KEY_SPEED,
                        KEY_DEFENSE,
                        KEY_ATTACK,
                        KEY_MONEY,
                        KEY_ACTIVE,
                        KEY_AP, KEY_QUESTID}, KEY_NAME + "=?",
                new String[] { String.valueOf(name) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Hero contact = new Hero(cursor);
        cursor.close();
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection

        // return hero
        return contact;
    }

    /**
     * Returns a hero object with the given name
     * @param Id Of the hero
     * @return hero object
     */
    public static Hero getHeroByID(Integer Id) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.query(TABLE_HERO, new String[] {
                        KEY_ID,
                        KEY_NAME,
                        KEY_HP,
                        KEY_XP,
                        KEY_LEVEL,
                        KEY_SPEED,
                        KEY_DEFENSE,
                        KEY_ATTACK,
                        KEY_MONEY,
                        KEY_ACTIVE,
                        KEY_AP, KEY_QUESTID}, KEY_ID + "=?",
                new String[] { String.valueOf(Id) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Hero contact = new Hero(cursor);
        cursor.close();
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection

        // return hero
        return contact;
    }

    /**
     *  Returns a List of all heros in the hero table
     * @return List containing all heros
     */
    public static List<Hero> getAllHeros() {
        List<Hero> heroList = new ArrayList<Hero>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_HERO;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Hero hero = new Hero(cursor);
                // Adding contact to list
                heroList.add(hero);
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection

        // return contact list
        return heroList;
    }

    /**
     * Updates a given hero's values in the hero table
     * @param hero hero to update, update is made based on ID
     * @return Returns result of the update command
     */
    public static int updateHero(Hero hero) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, hero.getId());
        values.put(KEY_NAME, hero.getName()); // hero name
        values.put(KEY_HP, hero.getHP()); // hero hp
        values.put(KEY_XP, hero.getXP()); //hero xp
        values.put(KEY_LEVEL,  hero.getLevel());
        values.put(KEY_SPEED, hero.getSpeed());
        values.put(KEY_DEFENSE, hero.getDefense());
        values.put(KEY_ATTACK, hero.getAttack());
        values.put(KEY_MONEY, hero.getMoney());
        values.put(KEY_ACTIVE, hero.getActive().getName());
        values.put(KEY_AP, hero.getAP());
        values.put(KEY_QUESTID, hero.getCurrentQuest().getId());
        // updating row
        int result = db.update(TABLE_HERO, values, KEY_ID + " = ?",
                new String[]{String.valueOf(hero.getId())});

        DatabaseManager.getInstance().closeDatabase(); // Closing database connection

        return result;
    }

    /**
     * Deletes a hero record from the database
     * @param hero hero to be deleted from database
     */
    public static void deleteHero(Hero hero) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(TABLE_HERO, KEY_ID + " = ?",
                new String[] { String.valueOf(hero.getId()) });

        DatabaseManager.getInstance().closeDatabase(); // Closing database connection
    }
}


