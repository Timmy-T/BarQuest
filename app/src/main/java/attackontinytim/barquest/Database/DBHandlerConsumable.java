package attackontinytim.barquest.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Scott Perryman on 3/07/2017.
 */

public class DBHandlerConsumable extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "BarDatabase";
    // Contacts table name
    private static final String TABLE_CONSUMABLE= "Consumable";
    // Consumable Table Columns names
    private static final String KEY_NAME = "Name";
    private static final String KEY_EFFECT = "Effect";
    private static final String KEY_EFFECTSTRENGTH = "EffectStrength";
    private static final String KEY_VALUE = "Value";

    public DBHandlerConsumable(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONSUMABLE + "("
                + KEY_NAME + " TEXT PRIMARY KEY,"
                + KEY_EFFECT + " TEXT, "
                + KEY_EFFECTSTRENGTH + " INT,"
                + KEY_VALUE + " DOUBLE)";

        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONSUMABLE);

        // Creating tables again
        onCreate(db);

    }

    /**
     * Function that adds an consumable to the Consumable table in the database
     * @param consumable An consumable object to add to the database
     */
    public void addConsumable(ConsumableItem consumable) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME , consumable.getName());
        values.put(KEY_EFFECT , consumable.getEffect());
        values.put(KEY_EFFECTSTRENGTH , consumable.getEffectStrength());
        values.put(KEY_VALUE , consumable.getValue());

        // Inserting Row
        db.insert(TABLE_CONSUMABLE, null, values);
        db.close(); // Closing database connection
    }

    /**
     * Returns an consumable object with the given name
     * @param name Name of the consumable
     * @return Consumable object
     */
    public ConsumableItem getConsumableByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONSUMABLE, new String[] {
                        KEY_NAME ,
                        KEY_EFFECT ,
                        KEY_EFFECTSTRENGTH ,
                        KEY_VALUE}, KEY_NAME + "=?",
                new String[] { String.valueOf(name) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        ConsumableItem contact = new ConsumableItem(cursor);
        cursor.close();
        // return consumable
        return contact;
    }

    /**
     *  Returns a List of all consumables in the consumables table
     * @return List containing all consumables
     */
    public List<ConsumableItem> getAllConsumables() {
        List<ConsumableItem> conList = new ArrayList<ConsumableItem>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_CONSUMABLE;
        SQLiteDatabase db = this.getReadableDatabase();
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
        // return contact list
        return conList;
    }

    /**
     *  Returns number of consumables in the database
     * @return Number of consumable records in the consumable table
     */
    public int getConsumableCount() {
        String countQuery = "SELECT * FROM " + TABLE_CONSUMABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }

    /**
     * Updates a given consumable's values in the consumable table
     * @param consumable Consumable to update, update is made based on consumable name
     * @return Returns result of the update command
     */
    public int updateConsumable(ConsumableItem consumable) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME , consumable.getName());
        values.put(KEY_EFFECT , consumable.getEffect());
        values.put(KEY_EFFECTSTRENGTH , consumable.getEffectStrength());
        values.put(KEY_VALUE , consumable.getValue());

        // updating row
        return db.update(TABLE_CONSUMABLE, values, KEY_NAME + " = ?",
                new String[]{String.valueOf(consumable.getName())});
    }

    /**
     * Deletes an consumable record from the database
     * @param consumable ConsumableItem to be deleted from database
     */
    public void deleteConsumable(ConsumableItem consumable) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONSUMABLE, KEY_NAME + " = ?",
                new String[] { String.valueOf(consumable.getName()) });
        db.close();
    }
}