package attackontinytim.barquest.Database;

/**
 * Created by Tim Buesking on 4/20/2017.
 */

public class QuestRepo {
    private static final String TABLE_QUESTS= "Quest";
    private static final String QUEST_ID = "ID";

    public static String getTableName(){
        return TABLE_QUESTS;
    }

    public static String createTable(){
        return "CREATE TABLE " + TABLE_QUESTS + "("
                    + QUEST_ID + " INTEGER PRIMARY KEY)" ;
        }
    }

