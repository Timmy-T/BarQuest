package com.example.scott.app;
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
        private static final String DATABASE_NAME = "MonsterTable";
        // Contacts table name
        private static final String TABLE_MONSTERS = "monsters";
        // Monster Table Columns names
        private static final String KEY_NAME = "name";
        private static final String KEY_HP = "HP";
        private static final String KEY_XP = "XP";
        public DBHandler(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_CONTACTS_TABLE = "CREATE TABLE" + TABLE_MONSTERS + "("
            + KEY_NAME + "INTEGER PRIMARY KEY," + KEY_HP + " TEXT,"
            + KEY_XP + " TEXT " + ")";
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
            Monster contact = new Monster(cursor.getString(0),
                    Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)));
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
                    Monster monster = new Monster();
                    monster.setName(cursor.getString(0));
                    monster.setHP(Integer.parseInt(cursor.getString(1)));
                    monster.setXP(Integer.parseInt(cursor.getString(2)));
// Adding contact to list
                    monsterList.add(monster);
                } while (cursor.moveToNext());
            }
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

