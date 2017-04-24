package attackontinytim.barquest.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim Buesking on 4/17/2017.
 */

public class WeaponRepo {
    private static final String TABLE_ITEMS= "Items";
    // Weapon Table Columns names
    private static final String KEY_NAME = "Name";
    private static final String KEY_ATTACKTYPE = "AttackType";
    private static final String KEY_ATTACK = "Attack";
    private static final String KEY_CRITRATE = "CriticalRate";
    private static final String KEY_VALUE = "Rarity";
    private static final String KEY_WEIGHT= "Speed";


    public static String getTableName(){
        return TABLE_ITEMS;
    }

    public static String createTable() {
        return "CREATE TABLE " + TABLE_ITEMS + "("
                + KEY_NAME + " TEXT PRIMARY KEY,"
                + KEY_ATTACKTYPE + " TEXT, "
                + KEY_ATTACK + " INT,"
                + KEY_CRITRATE + " INT, "
                + KEY_VALUE + " DOUBLE,"
                + KEY_WEIGHT+ " DOUBLE)";
    }


    /**
     * Function that adds an weapon to the Weapon table in the database
     * @param weapon An weapon object to add to the database
     */
    public static void addItem(Weapon weapon) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME , weapon.getName());
        values.put(KEY_ATTACKTYPE , weapon.getAttackType());
        values.put(KEY_ATTACK , weapon.getAttack());
        values.put(KEY_CRITRATE , weapon.getCriticalRate());
        values.put(KEY_VALUE , weapon.getValue());
        values.put(KEY_WEIGHT, weapon.getWeight());

        // Inserting Row
        db.insert(TABLE_ITEMS, null, values);
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection
    }

    /**
     * Returns an itme object with the given name
     * @param name Name of the item
     * @return Weapon object
     */
    public static Weapon getItemByName(String name) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.query(TABLE_ITEMS, new String[] {
                        KEY_NAME ,
                        KEY_ATTACKTYPE ,
                        KEY_ATTACK ,
                        KEY_CRITRATE ,
                        KEY_VALUE ,
                        KEY_WEIGHT}, KEY_NAME + "=?",
                new String[] { String.valueOf(name) }, null, null, null, null);

        try {
            if (cursor != null)
                cursor.moveToFirst();

            Weapon contact = new Weapon(cursor);
            cursor.close();
            // return item
            return contact;
        }
        catch (Exception ex){return null;}
        finally {
            DatabaseManager.getInstance().closeDatabase(); // Closing database connection
        }
    }

    /**
     *  Returns a List of all items in the items table
     * @return List containing all items
     */
    public static List<Weapon> getAllItems() {
        List<Weapon> weaponList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_ITEMS;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Weapon weapon = new Weapon(cursor);
                // Adding contact to list
                weaponList.add(weapon);
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection
        // return contact list
        return weaponList;
    }

    /**
     *  Returns number of items in the database
     * @return Number of item records in the item table
     */
    public static int getItemCount() {
        String countQuery = "SELECT * FROM " + TABLE_ITEMS;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);


        int count = cursor.getCount();
        cursor.close();
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection

        // return count
        return count;
    }

    /**
     * Updates a given weapon's values in the weapon table
     * @param weapon Weapon to update, update is made based on weapon name
     * @return Returns result of the update command
     */
    public static int updateItem(Weapon weapon) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME , weapon.getName());
        values.put(KEY_ATTACKTYPE , weapon.getAttackType());
        values.put(KEY_ATTACK , weapon.getAttack());
        values.put(KEY_CRITRATE , weapon.getCriticalRate());
        values.put(KEY_VALUE , weapon.getValue());
        values.put(KEY_WEIGHT, weapon.getWeight());

        // updating row
        int result = db.update(TABLE_ITEMS, values, KEY_NAME + " = ?",
                new String[]{String.valueOf(weapon.getName())});

        DatabaseManager.getInstance().closeDatabase(); // Closing database connection

        return result;
    }

    /**
     * Deletes an weapon record from the database
     * @param weapon Weapon to be deleted from database
     */
    public static void deleteItem(Weapon weapon) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(TABLE_ITEMS, KEY_NAME + " = ?",
                new String[] { String.valueOf(weapon.getName()) });

        DatabaseManager.getInstance().closeDatabase(); // Closing database connection
    }
}

