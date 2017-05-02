package attackontinytim.barquest.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim Buesking on 4/24/2017.
 */

public class TimerRepo {
    private static final String TABLE_TIMER = "Timer";
    // Weapon Table Columns names
    private static final String KEY_NAME = "Name";
    private static final String KEY_TIMER0 = "Timer0";
    private static final String KEY_TIMER1 = "Timer1";
    private static final String KEY_TIMER2 = "Timer2";
    private static final String KEY_TIMER3 = "Timer3";
    private static final String KEY_TIMER4 = "Timer4";
    private static final String KEY_TIMER5 = "Timer5";
    private static final String KEY_TIMER6 = "Timer6";
    private static final String KEY_TIMER7 = "Timer7";
    private static final String KEY_TIMER8 = "Timer8";
    private static final String KEY_TIMER9 = "Timer9";


    public static String getTableName() {
        return TABLE_TIMER;
    }

    public static String createTable() {
        return "CREATE TABLE " + TABLE_TIMER + "("
                + KEY_NAME + " TEXT PRIMARY KEY,"
                + KEY_TIMER0 + " REAL,"
                + KEY_TIMER1 + " REAL,"
                + KEY_TIMER2 + " REAL,"
                + KEY_TIMER3 + " REAL,"
                + KEY_TIMER4 + " REAL,"
                + KEY_TIMER5 + " REAL,"
                + KEY_TIMER6 + " REAL,"
                + KEY_TIMER7 + " REAL,"
                + KEY_TIMER8 + " REAL,"
                + KEY_TIMER9 + " REAL)";
    }

    /**
     * Returns a list of all timers as a long value
     * @param HeroName Name of the hero used as a unique key for querying table
     * @return List of ten Longs
     */
    public static List<Long> getAllTimers(String HeroName) {
        List<Long> timerList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_TIMER + " WHERE " + KEY_NAME + " = '" + HeroName + "'";
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through single row
        if (cursor.moveToFirst()) {
            for (int i = 1; i < cursor.getColumnCount(); i++) {
                timerList.add(cursor.getLong(i));
            }
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection
        // return timer list
        return timerList;
    }


    /**
     * Creates a database entry for storing timers
     * @param timers List of ten Long values to be added to the database
     * @param heroName Name of the hero
     */
    public static void addTimers(List<Long> timers, String heroName) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, heroName);
        values.put(KEY_TIMER0, timers.get(0));
        values.put(KEY_TIMER1, timers.get(1));
        values.put(KEY_TIMER2, timers.get(2));
        values.put(KEY_TIMER3, timers.get(3));
        values.put(KEY_TIMER4, timers.get(4));
        values.put(KEY_TIMER5, timers.get(5));
        values.put(KEY_TIMER6, timers.get(6));
        values.put(KEY_TIMER7, timers.get(7));
        values.put(KEY_TIMER8, timers.get(8));
        values.put(KEY_TIMER9, timers.get(9));

        // Inserting Row
        db.insert(TABLE_TIMER, null, values);
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection
    }

    /**
     * Updates all timers in the database
     * Requires having a list of ten numbers that are Long
     * @param timers List of Longs that has 10 values
     * @param heroName The name of the hero to be used as a unique key
     * @return Returns the result value of the update query
     */
    public static int updateTimer(List<Long> timers, String heroName) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, heroName);
        values.put(KEY_TIMER0, timers.get(0));
        values.put(KEY_TIMER1, timers.get(1));
        values.put(KEY_TIMER2, timers.get(2));
        values.put(KEY_TIMER3, timers.get(3));
        values.put(KEY_TIMER4, timers.get(4));
        values.put(KEY_TIMER5, timers.get(5));
        values.put(KEY_TIMER6, timers.get(6));
        values.put(KEY_TIMER7, timers.get(7));
        values.put(KEY_TIMER8, timers.get(8));
        values.put(KEY_TIMER9, timers.get(9));

        // updating row
        int result = db.update(TABLE_TIMER, values, KEY_NAME + " = ?",
                new String[]{heroName});

        DatabaseManager.getInstance().closeDatabase(); // Closing database connection

        return result;
    }

    public static int updateTimer(attackontinytim.barquest.Timer[] timers, String heroName){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, heroName);
        values.put(KEY_TIMER0, timers[0].getTime().getTimeInMillis());
        values.put(KEY_TIMER1, timers[1].getTime().getTimeInMillis());
        values.put(KEY_TIMER2, timers[2].getTime().getTimeInMillis());
        values.put(KEY_TIMER3, timers[3].getTime().getTimeInMillis());
        values.put(KEY_TIMER4, timers[4].getTime().getTimeInMillis());
        values.put(KEY_TIMER5, timers[5].getTime().getTimeInMillis());
        values.put(KEY_TIMER6, timers[6].getTime().getTimeInMillis());
        values.put(KEY_TIMER7, timers[7].getTime().getTimeInMillis());
        values.put(KEY_TIMER8, timers[8].getTime().getTimeInMillis());
        values.put(KEY_TIMER9, timers[9].getTime().getTimeInMillis());

        // updating row
        int result = db.update(TABLE_TIMER, values, KEY_NAME + " = ?",
                new String[]{heroName});

        DatabaseManager.getInstance().closeDatabase(); // Closing database connection

        return result;
    }
}
