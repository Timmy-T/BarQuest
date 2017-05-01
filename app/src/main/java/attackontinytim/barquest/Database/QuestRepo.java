package attackontinytim.barquest.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import attackontinytim.barquest.Quest;

/**
 * Created by Tim Buesking on 4/20/2017.
 */

public class QuestRepo {
    private static final String TABLE_QUESTS= "Quest";
    private static final String KEY_ID = "ID";
    private static final String KEY_NAME = "Name";
    private static final String KEY_DESCRIPTION = "Description";
    private static final String KEY_GOAL = "Goal";
    private static final String KEY_PROGRESS = "Progress";
    private static final String KEY_COMPLETED = "Completed";
    private static final String KEY_XP = "XP";
    private static final String KEY_MONEY = "Money";
    private static final String KEY_ITEM = "Item";
    private static final String KEY_QUESTTYPE = "QuestType";
    private static final String KEY_QUESTTARGET = "QuestTarget";



    public static String getTableName(){
        return TABLE_QUESTS;
    }

    public static String createTable(){
        return "CREATE TABLE " + TABLE_QUESTS + "("
                    + KEY_ID + " INTEGER PRIMARY KEY,"
                    + KEY_NAME + " TEXT, "
                    + KEY_DESCRIPTION + " TEXT,"
                    + KEY_GOAL + " INT, "
                    + KEY_PROGRESS + " TEXT,"
                    + KEY_COMPLETED + " BOOLEAN,"
                    + KEY_XP + " INT,"
                    + KEY_MONEY + " DOUBLE,"
                    + KEY_ITEM + " TEXT,"
                    + KEY_QUESTTYPE + " TEXT,"
                    + KEY_QUESTTARGET + " TEXT)";
        }

    /**
     * Returns a Quest object with the given ID
     * @param Id Of the quest
     * @return Quest object
     */
    public static Quest getQuestByID(Integer Id) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.query(TABLE_QUESTS, new String[] {
                        KEY_ID,
                        KEY_NAME,
                        KEY_DESCRIPTION,
                        KEY_GOAL,
                        KEY_PROGRESS,
                        KEY_COMPLETED,
                        KEY_XP,
                        KEY_MONEY,
                        KEY_ITEM,
                        KEY_QUESTTYPE,
                        KEY_QUESTTARGET}, KEY_ID + "=?",
                new String[] { String.valueOf(Id) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Quest  quest = new Quest(cursor);
        cursor.close();
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection

        // return Quest
        return quest;
    }

     /**
     * Returns a Quest object with the given name
     * @param Id Of the quest
     * @return Quest object
     */
    public static Quest getQuestByName(String questName) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.query(TABLE_QUESTS, new String[]{
                        KEY_ID,
                        KEY_NAME,
                        KEY_DESCRIPTION,
                        KEY_GOAL,
                        KEY_PROGRESS,
                        KEY_COMPLETED,
                        KEY_XP,
                        KEY_MONEY,
                        KEY_ITEM,
                        KEY_QUESTTYPE,
                        KEY_QUESTTARGET}, KEY_NAME + "=?",
                new String[]{String.valueOf(questName)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            Quest quest = new Quest(cursor);
            cursor.close();
            DatabaseManager.getInstance().closeDatabase(); // Closing database connection

            // return Quest
            return quest;

        } else {
            cursor.close();
            DatabaseManager.getInstance().closeDatabase(); // Closing database connection
            return null;
        }
    }
    /**
     * Creates a quest in the database
     * @param quest
     */
    public static void addQuest(Quest quest){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, quest.getId());
        values.put(KEY_NAME, quest.getName()); // Quest name
        values.put(KEY_DESCRIPTION, quest.getDescription()); // Quest hp
        values.put(KEY_GOAL, quest.getGoal()); //Quest xp
        values.put(KEY_PROGRESS, quest.getProgress());
        values.put(KEY_COMPLETED, quest.isCompleted());
        values.put(KEY_XP,  quest.getXP());
        values.put(KEY_MONEY, quest.getMoney());
        values.put(KEY_ITEM, quest.getItemName());
        values.put(KEY_QUESTTYPE, quest.getQuestType());
        values.put(KEY_QUESTTARGET, quest.getQuestTarget());

        // Inserting Row
        db.insert(TABLE_QUESTS, null, values);
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection
    }

    /**
     *  Returns a List of all quests in the quest table
     * @return List containing all Quests
     */
    public static List<Quest> getAllQuest() {

        List<Quest> questList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_QUESTS;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Quest quest = new Quest(cursor);
                // Adding contact to list
                questList.add(quest);
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection

        // return contact list
        return questList;
    }

    /**
     *  Returns number of quests  in the database
     * @return Number of quests records in the quests table
     */
    public static int getQuestCount() {
        String countQuery = "SELECT * FROM " + TABLE_QUESTS;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        // return count

        int count = cursor.getCount();
        cursor.close();
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection

        return count;
    }

    /**
     * Updates a given Quest's values in the quest table
     * @param quest Quest to update, update is made based on ID
     * @return Returns result of the update command
     */
    public static int updateQuest(Quest quest) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, quest.getId());
        values.put(KEY_NAME, quest.getName()); // Quest name
        values.put(KEY_DESCRIPTION, quest.getDescription()); // Quest hp
        values.put(KEY_GOAL, quest.getGoal()); //Quest xp
        values.put(KEY_PROGRESS, quest.getProgress());
        values.put(KEY_COMPLETED, quest.isCompleted());
        values.put(KEY_XP,  quest.getXP());
        values.put(KEY_MONEY, quest.getMoney());
        values.put(KEY_ITEM, quest.getItemName());
        values.put(KEY_QUESTTYPE, quest.getQuestType());
        values.put(KEY_QUESTTARGET, quest.getQuestTarget());

        // updating row
        int result = db.update(TABLE_QUESTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(quest.getId())});
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection

        return result;
    }

    /**
     * Deletes a quest record from the database
     * @param quest Quest to be deleted from database
     */
    public static void deleteQuest(Quest quest) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(TABLE_QUESTS, KEY_ID + " = ?",
                new String[] { String.valueOf(quest.getId()) });
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection

    }
}


