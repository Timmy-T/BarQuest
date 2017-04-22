package attackontinytim.barquest.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim Buesking on 4/17/2017.
 */

public class ConsumableRepo {
    private static final String TABLE_CONSUMABLE= "Consumable";
    // Consumable Table Columns names
    private static final String KEY_NAME = "Name";
    private static final String KEY_EFFECT = "Effect";
    private static final String KEY_EFFECTSTRENGTH = "EffectStrength";
    private static final String KEY_VALUE = "Value";


    public static String getTableName(){
        return TABLE_CONSUMABLE;
    }

    public static String createTable(){
        return "CREATE TABLE " + TABLE_CONSUMABLE + "("
                + KEY_NAME + " TEXT PRIMARY KEY,"
                + KEY_EFFECT + " TEXT, "
                + KEY_EFFECTSTRENGTH + " INT,"
                + KEY_VALUE + " DOUBLE)";
    }


    /**
     * Function that adds an consumable to the Consumable table in the database
     * @param consumable An consumable object to add to the database
     */
    public static void addConsumable(ConsumableItem consumable) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME , consumable.getName());
        values.put(KEY_EFFECT , consumable.getEffect());
        values.put(KEY_EFFECTSTRENGTH , consumable.getEffectStrength());
        values.put(KEY_VALUE , consumable.getValue());

        // Inserting Row
        db.insert(TABLE_CONSUMABLE, null, values);
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection
    }

    /**
     * Returns an consumable object with the given name
     * @param name Name of the consumable
     * @return Consumable object
     */
    public static ConsumableItem getConsumableByName(String name) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.query(TABLE_CONSUMABLE, new String[] {
                        KEY_NAME ,
                        KEY_EFFECT ,
                        KEY_EFFECTSTRENGTH ,
                        KEY_VALUE}, KEY_NAME + "=?",
                new String[] { String.valueOf(name) }, null, null, null, null);

        try {
            if (cursor != null)
                cursor.moveToFirst();

            ConsumableItem con = new ConsumableItem(cursor);
            cursor.close();
            // return item
            return con;
        }
        catch (Exception ex){return null;}
        finally {
            DatabaseManager.getInstance().closeDatabase(); // Closing database connection
        }
    }

    /**
     *  Returns a List of all consumables in the consumables table
     * @return List containing all consumables
     */
    public static List<ConsumableItem> getAllConsumables() {
        List<ConsumableItem> conList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_CONSUMABLE;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ConsumableItem consumable = new ConsumableItem(cursor);
                // Adding contact to list
                conList.add(consumable);
            } while (cursor.moveToNext());
        }

        cursor.close();

        DatabaseManager.getInstance().closeDatabase(); // Closing database connection

        // return contact list
        return conList;
    }

    /**
     *  Returns number of consumables in the database
     * @return Number of consumable records in the consumable table
     */
    public static int getConsumableCount() {
        String countQuery = "SELECT * FROM " + TABLE_CONSUMABLE;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        // return count
        int result = cursor.getCount();
        cursor.close();

        DatabaseManager.getInstance().closeDatabase(); // Closing database connection

        return result;
    }

    /**
     * Updates a given consumable's values in the consumable table
     * @param consumable Consumable to update, update is made based on consumable name
     * @return Returns result of the update command
     */
    public static int updateConsumable(ConsumableItem consumable) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME , consumable.getName());
        values.put(KEY_EFFECT , consumable.getEffect());
        values.put(KEY_EFFECTSTRENGTH , consumable.getEffectStrength());
        values.put(KEY_VALUE , consumable.getValue());

        // updating row
        int result = db.update(TABLE_CONSUMABLE, values, KEY_NAME + " = ?",
                new String[]{String.valueOf(consumable.getName())});

        DatabaseManager.getInstance().closeDatabase(); // Closing database connection

        return result;
    }

    /**
     * Deletes an consumable record from the database
     * @param consumable ConsumableItem to be deleted from database
     */
    public static void deleteConsumable(ConsumableItem consumable) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(TABLE_CONSUMABLE, KEY_NAME + " = ?",
                new String[] { String.valueOf(consumable.getName()) });
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection

    }
}
