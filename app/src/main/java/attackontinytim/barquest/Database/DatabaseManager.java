package attackontinytim.barquest.Database;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tim Buesking on 4/17/2017.
 * This code is originally based on a tutorial located at http://instinctcoder.com/android-studio-sqlite-database-multiple-tables-example/
 */
public class DatabaseManager {
    private Integer openCounter = 0;

    private static DatabaseManager instance;
    private static SQLiteOpenHelper dbHelper;
    private SQLiteDatabase myDB;

    /**
     * Initiliazes an instance of the database manager class
     * @param helper The SQL Lite database helper object to be synchronized
     */
    public static synchronized void initializeInstance(SQLiteOpenHelper helper) {
        if (instance == null) {
            instance = new DatabaseManager();
            dbHelper = helper;
        }
    }

    /**
     *  Gets the instance of the databasemanger class
     * @return The datamanager class
     */
    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException(DatabaseManager.class.getSimpleName() +
                    " Database is not open call initializeInstance(..).");
        }
        return instance;
    }

    /**
     * Opens the database
     * @return Returns a writeable database
     */
    public synchronized SQLiteDatabase openDatabase() {
        openCounter+=1;
        if(openCounter == 1) {
            // Opening new database
            myDB = dbHelper.getWritableDatabase();
        }
        return myDB;
    }

    /**
     * Closes the database
     */
    public synchronized void closeDatabase() {
        openCounter-=1;
        if(openCounter == 0) {
            // Closing database
            myDB.close();

        }
    }
}