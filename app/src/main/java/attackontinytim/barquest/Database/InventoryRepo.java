package attackontinytim.barquest.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim Buesking on 4/19/2017.
 */

public class InventoryRepo {
    private static final String TABLE_INV = "Inventory";
    private static final String KEY_NAME = "Name";
    private static final String QUANTITY = "Quantity";
    private static final String TYPE = "Type";

    public static String getTableName() { return TABLE_INV;}

    public static String createTable(){
        return "CREATE TABLE " + TABLE_INV + "("
                + KEY_NAME + " TEXT PRIMARY KEY,"
                + QUANTITY + " Int, "
                + TYPE + " TEXT)";
    }

    /**
     * Adds a weapon to the inventory table
     * @param wep Weapon to be added to the inventory
     */
    public static void addItemToInventory(Weapon wep) {
        int quantity = getItemQuantity(wep);

        if (quantity == 0) {
            SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_NAME, wep.getName());
            values.put(QUANTITY, 1);
            values.put(TYPE, "WEP");

            // Inserting Row
            db.insert(TABLE_INV, null, values);
            DatabaseManager.getInstance().closeDatabase(); // Closing database connection
        }
        else{
            SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_NAME, wep.getName());
            values.put(QUANTITY, quantity + 1);
            values.put(TYPE, "WEP");

            // Inserting Row
            db.update(TABLE_INV, values, KEY_NAME + " = ?",
                    new String[]{String.valueOf(wep.getName())});

            DatabaseManager.getInstance().closeDatabase(); // Closing database connection
        }
    }

    /**
     * Adds a consumable item to the inventory table
     * @param con Consumable to be added to the inventory
     */
    public static void addItemToInventory(ConsumableItem con) {
        int quantity = getItemQuantity(con);

        if (quantity == 0) {
            SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_NAME, con.getName());
            values.put(QUANTITY, 1);
            values.put(TYPE, "CON");

            // Inserting Row
            db.insert(TABLE_INV, null, values);
            DatabaseManager.getInstance().closeDatabase(); // Closing database connection
        }
        else{
            SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_NAME, con.getName());
            values.put(QUANTITY, quantity + 1);
            values.put(TYPE, "CON");

            // Inserting Row
            db.update(TABLE_INV, values, KEY_NAME + " = ?",
                    new String[]{String.valueOf(con.getName())});

            DatabaseManager.getInstance().closeDatabase(); // Closing database connection
        }
    }

    /**
     * Removes a singular weapon the inventory
     * This will subtract a single instance of the weapon
     * If there is a singular weapon in quantity the row will be removed from the table
     * @param wep Weapon to be removed
     */
    public static void subtractItemFromInvetory(Weapon wep) {
        int quantity = getItemQuantity(wep);

        if (quantity == 1) {
            SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
            db.delete(TABLE_INV, KEY_NAME + " = ?",
                    new String[] { String.valueOf(wep.getName()) });
            DatabaseManager.getInstance().closeDatabase(); // Closing database connection
        }
        else if (quantity > 1){
            SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_NAME, wep.getName());
            values.put(QUANTITY, quantity - 1);
            values.put(TYPE, "WEP");

            // Inserting Row
            db.update(TABLE_INV, values, KEY_NAME + " = ?",
                    new String[]{String.valueOf(wep.getName())});

            DatabaseManager.getInstance().closeDatabase(); // Closing database connection
        }
    }

    /**
     * Removes a singular consumable the inventory
     * This will subtract a single instance of the consumable
     * If there is a singular consumable in quantity the row will be removed from the table
     * @param con Consumable to be removed
     */
    public static void subtractItemFromInvetory(ConsumableItem con) {
        int quantity = getItemQuantity(con);

        if (quantity == 1) {
            SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
            db.delete(TABLE_INV, KEY_NAME + " = ?",
                    new String[] { String.valueOf(con.getName()) });
            DatabaseManager.getInstance().closeDatabase(); // Closing database connection
        }
        else if (quantity > 1){
            SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_NAME, con.getName());
            values.put(QUANTITY, quantity - 1);
            values.put(TYPE, "CON");

            // Inserting Row
            db.update(TABLE_INV, values, KEY_NAME + " = ?",
                    new String[]{String.valueOf(con.getName())});

            DatabaseManager.getInstance().closeDatabase(); // Closing database connection
        }
    }

    /**
     * Returns the quantity of a specified weapon
     * @param wep Weapon to find the count of
     * @return integer from 0  to whatever
     */
    public static int getItemQuantity(Weapon wep) {
        int result = 0;

        String countQuery = "SELECT * FROM " + TABLE_INV + " WHERE " + KEY_NAME + " = '" + wep.getName() + "'";
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        // return count
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            result = cursor.getInt(cursor.getColumnIndex(QUANTITY));
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection

        return result;
    }


    /**
     * Returns the quantity of a specified consumable
     * @param con Consumable to find the count of
     * @return integer from 0  to whatever
     */
    public static int getItemQuantity(ConsumableItem con) {
        int result = 0;

        String countQuery = "SELECT * FROM " + TABLE_INV + " WHERE " + KEY_NAME + " = '" + con.getName() + "'";
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        // return count
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            result = cursor.getInt(cursor.getColumnIndex(QUANTITY));
        }
        cursor.close();
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection

        return result;
    }

    /**
     * Returns a list of all consumables in the inventory
     * If there is a quantity greater than 1 then multiple copies of that consumable will be returned
     * @return List of consumables
     */
    public static List<ConsumableItem> getAllConsumables() {
        List<ConsumableItem> conList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_INV + " WHERE " + TYPE + " = 'CON'";
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ConsumableItem consumable = ConsumableRepo.getConsumableByName(cursor.getString(0));
                for (int i=0; i < getItemQuantity(consumable); i++) {
                    // Adding contact to list
                    conList.add(consumable);
                }
            } while (cursor.moveToNext());
        }

        cursor.close();

        DatabaseManager.getInstance().closeDatabase(); // Closing database connection

        // return contact list
        return conList;
    }

    /**
     * Returns a list of all weapons in the inventory
     * If there is a quantity greater than 1 then multiple copies of that weapon will be returned
     * @return List of weapons
     */
    public static List<Weapon> getAllWeapons() {
        List<Weapon> wepList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_INV + " WHERE " + TYPE + " = 'WEP'";
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Weapon wep= WeaponRepo.getItemByName(cursor.getString(0));
                for (int i = 0; i < getItemQuantity(wep); i++){
                    // Adding contact to list
                    wepList.add(wep);
                }

            } while (cursor.moveToNext());
        }

        cursor.close();

        DatabaseManager.getInstance().closeDatabase(); // Closing database connection

        // return contact list
        return wepList;
    }

}
