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
        private static final int DATABASE_VERSION = 1;
        // Database Name
        private static final String DATABASE_NAME = "MonsterTableTwo";
        // Contacts table name
        private static final String TABLE_MONSTERS = "monstersTwo";
        // Monster Table Columns names
        private static final String KEY_NAME = "name";
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
            this.onUpgrade(this.getWritableDatabase(), 0, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_MONSTERS + "("
            + KEY_NAME + " INTEGER PRIMARY KEY, "
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
        public void addMonster(Monster monster) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
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

        public Monster getMonster(String name) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_MONSTERS, new String[] { KEY_NAME,
                            KEY_HP, KEY_XP }, KEY_NAME + "=?",
                    new String[] { String.valueOf(name) }, null, null, null, null);

            if (cursor != null)
                cursor.moveToFirst();

            Monster contact = new Monster(cursor);
            cursor.close();
            // return monster
            return contact;
        }
        public List<Monster> getAllMonsters() {
            List<Monster> monsterList = new ArrayList<Monster>();
            // Select All Query
            String selectQuery = "SELECT * FROM " + TABLE_MONSTERS;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Monster monster = new Monster(cursor);
                    // Adding contact to list
                    monsterList.add(monster);
                } while (cursor.moveToNext());
            }

            cursor.close();
            // return contact list
            return monsterList;
        }
        public int getMonsterCount() {
            String countQuery = "SELECT * FROM " + TABLE_MONSTERS;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            cursor.close();
            // return count
            return cursor.getCount();
        }
        public int updateMonster(Monster monster) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, monster.getName());
            values.put(KEY_HP, monster.getHP());
            values.put(KEY_XP, monster.getXP());
            // updating row
            return db.update(TABLE_MONSTERS, values, KEY_NAME + " = ?",
                    new String[]{String.valueOf(monster.getName())});
        }
        public void deleteMonster(Monster monster) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_MONSTERS, KEY_NAME + " = ?",
                    new String[] { String.valueOf(monster.getName()) });
            db.close();
        }
    }