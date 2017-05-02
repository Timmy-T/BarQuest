package attackontinytim.barquest.Database;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import attackontinytim.barquest.Tutorial;

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 29;
    // Database Name
    private static final String DATABASE_NAME = "BarDatabase";
    // Contacts table name

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MonsterRepo.createTable());
        db.execSQL(WeaponRepo.createTable());
        db.execSQL(ConsumableRepo.createTable());

        // TODO: Handle this better, this is for ensuring the Hero and inventory aren't wiped
        try {
            db.execSQL(HeroRepo.createTable());
        } catch (Exception ex) {}

        try {
            db.execSQL(InventoryRepo.createTable());
        } catch (Exception ex) {}

        try {
            db.execSQL(QuestRepo.createTable());
        } catch (Exception ex) {}

        try {
            db.execSQL(TimerRepo.createTable());
        } catch (Exception ex) {}


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + MonsterRepo.getTableName());
        db.execSQL("DROP TABLE IF EXISTS " + WeaponRepo.getTableName());
        db.execSQL("DROP TABLE IF EXISTS " + ConsumableRepo.getTableName());

        db.execSQL("DROP TABLE IF EXISTS " + HeroRepo.getTableName());
        db.execSQL("DROP TABLE IF EXISTS " + InventoryRepo.getTableName());
        db.execSQL("DROP TABLE IF EXISTS " + QuestRepo.getTableName());
        db.execSQL("DROP TABLE IF EXISTS " + TimerRepo.getTableName());

        // Creating tables again
        onCreate(db);
    }


    public static void resetData() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + HeroRepo.getTableName());
        db.execSQL("DROP TABLE IF EXISTS " + InventoryRepo.getTableName());
        db.execSQL("DROP TABLE IF EXISTS " + QuestRepo.getTableName());
        db.execSQL("DROP TABLE IF EXISTS " + TimerRepo.getTableName());

        db.execSQL(HeroRepo.createTable());
        db.execSQL(InventoryRepo.createTable());
        db.execSQL(QuestRepo.createTable());
        db.execSQL(TimerRepo.createTable());

        InsertDataValues.initializeHeroValues();

        DatabaseManager.getInstance().closeDatabase();
        Tutorial.setAllTrue();
    }
}
