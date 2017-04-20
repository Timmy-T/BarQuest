package attackontinytim.barquest.Database;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 11;
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
        // When the databases are updated
        try {
            db.execSQL(HeroRepo.createTable());
        } catch (Exception ex) {
        }

        try {
            db.execSQL(InventoryRepo.createTable());
        } catch (Exception ex) {
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + MonsterRepo.getTableName());
        db.execSQL("DROP TABLE IF EXISTS " + WeaponRepo.getTableName());
        db.execSQL("DROP TABLE IF EXISTS " + ConsumableRepo.getTableName());
        // db.execSQL("DROP TABLE IF EXISTS " + HeroRepo.getTableName());
        // Creating tables again
        onCreate(db);
    }
}

