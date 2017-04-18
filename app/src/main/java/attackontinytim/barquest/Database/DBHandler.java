package attackontinytim.barquest.Database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 2;
    // Database Name
    private static final String DATABASE_NAME = "BarDatabase";
    // Contacts table name
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

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_MONSTERS + "("
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
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MONSTERS);

        // Creating tables again
        onCreate(db);

    }

    /**
     * Function that adds a monster to the Monster table in the database
     * @param monster A monster object to add to the database
     */
    public void addMonster(Monster monster) {
        SQLiteDatabase db = this.getWritableDatabase();
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
        db.close(); // Closing database connection
    }

    /**
     * Returns a monster object with the given name
     * @param name Name of the monster
     * @return Monster object
     */
    public Monster getMonsterByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
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
        cursor.close();
        // return monster
        return contact;
    }

    /**
     * Returns a monster object with the given name
     * @param Id Of the monster
     * @return Monster object
     */
    public Monster getMonsterByID(Integer Id) {
        SQLiteDatabase db = this.getReadableDatabase();
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
        cursor.close();
        // return monster
        return contact;
    }

    /**
     *  Returns a List of all monsters in the monster table
     * @return List containing all monsters
     */
    public List<Monster> getAllMonsters() {
        List<Monster> monsterList = new ArrayList<Monster>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_MONSTERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Monster monster = new Monster(cursor);
                // Adding contact to list
                monsterList.add(monster);
            } while (cursor.moveToNext());
        }

        // return contact list
        return monsterList;
    }

    /**
     *  Returns number of monsters in the database
     * @return Number of monster records in the monsters table
     */
        public int getMonsterCount() {
            String countQuery = "SELECT * FROM " + TABLE_MONSTERS;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            // return count
            return cursor.getCount();
        }

    /**
     * Updates a given monster's values in the monster table
     * @param monster Monster to update, update is made based on ID
     * @return Returns result of the update command
     */
        public int updateMonster(Monster monster) {
            SQLiteDatabase db = this.getWritableDatabase();

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
            return db.update(TABLE_MONSTERS, values, KEY_ID + " = ?",
                    new String[]{String.valueOf(monster.getId())});
        }

    /**
     * Deletes a monster record from the database
     * @param monster Monster to be deleted from database
     */
        public void deleteMonster(Monster monster) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_MONSTERS, KEY_ID + " = ?",
                    new String[] { String.valueOf(monster.getId()) });
            db.close();
        }
    }