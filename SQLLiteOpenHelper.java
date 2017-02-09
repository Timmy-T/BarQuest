package com.example.scott.app;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLLiteOpenHelper {

    public class DBHandler extends SQLiteOpenHelper {

        // Database Version
        private static final int DATABASE_VERSION = 1;
        // Database Name
        private static final String DATABASE_NAME = "MonsterTable";
        // Contacts table name
        private static final String TABLE_MONSTERS = "monsters";
        // Shops Table Columns names
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
    }
}
