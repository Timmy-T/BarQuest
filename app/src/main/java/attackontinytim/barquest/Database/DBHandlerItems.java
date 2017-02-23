package attackontinytim.barquest.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim Buesking on 2/22/2017.
 */

public class DBHandlerItems extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "BarDatabase";
    // Contacts table name
    private static final String TABLE_ITEMS= "Items";
    // Item Table Columns names
    private static final String KEY_NAME = "Name";
    private static final String KEY_ATTACKTYPE = "AttackType";
    private static final String KEY_ATTACK = "Attack";
    private static final String KEY_CRITRATE = "CriticalRate";
    private static final String KEY_VALUE = "Rarity";
    private static final String KEY_WEIGHT= "Speed";

    public DBHandlerItems(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_ITEMS + "("
                + KEY_NAME + " TEXT PRIMARY KEY,"
                + KEY_ATTACKTYPE + " TEXT, "
                + KEY_ATTACK + " INT,"
                + KEY_CRITRATE + " INT, "
                + KEY_VALUE + " DOUBLE,"
                + KEY_WEIGHT+ " DOUBLE)";

        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);

        // Creating tables again
        onCreate(db);

    }

    /**
     * Function that adds an item to the Item table in the database
     * @param item An item object to add to the database
     */
    public void addItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME , item.getName());
        values.put(KEY_ATTACKTYPE , item.getAttackType());
        values.put(KEY_ATTACK , item.getAttack());
        values.put(KEY_CRITRATE , item.getCriticalRate());
        values.put(KEY_VALUE , item.getValue());
        values.put(KEY_WEIGHT, item.getWeight());

        // Inserting Row
        db.insert(TABLE_ITEMS, null, values);
        db.close(); // Closing database connection
    }

    /**
     * Returns an itme object with the given name
     * @param name Name of the item
     * @return Item object
     */
    public Item getItemByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ITEMS, new String[] {
                        KEY_NAME ,
                        KEY_ATTACKTYPE ,
                        KEY_ATTACK ,
                        KEY_CRITRATE ,
                        KEY_VALUE ,
                        KEY_WEIGHT}, KEY_NAME + "=?",
                new String[] { String.valueOf(name) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Item contact = new Item(cursor);
        cursor.close();
        // return item
        return contact;
    }

    /**
     *  Returns a List of all items in the items table
     * @return List containing all items
     */
    public List<Item> getAllItems() {
        List<Item> itemList = new ArrayList<Item>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_ITEMS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Item item = new Item(cursor);
                // Adding contact to list
                itemList.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return contact list
        return itemList;
    }

    /**
     *  Returns number of items in the database
     * @return Number of item records in the item table
     */
    public int getItemCount() {
        String countQuery = "SELECT * FROM " + TABLE_ITEMS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }

    /**
     * Updates a given item's values in the item table
     * @param item Item to update, update is made based on item name
     * @return Returns result of the update command
     */
    public int updateItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME , item.getName());
        values.put(KEY_ATTACKTYPE , item.getAttackType());
        values.put(KEY_ATTACK , item.getAttack());
        values.put(KEY_CRITRATE , item.getCriticalRate());
        values.put(KEY_VALUE , item.getValue());
        values.put(KEY_WEIGHT, item.getWeight());

        // updating row
        return db.update(TABLE_ITEMS, values, KEY_NAME + " = ?",
                new String[]{String.valueOf(item.getName())});
    }

    /**
     * Deletes an item record from the database
     * @param item Item to be deleted from database
     */
    public void deleteItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ITEMS, KEY_NAME + " = ?",
                new String[] { String.valueOf(item.getName()) });
        db.close();
    }
}