package attackontinytim.barquest;

import android.database.Cursor;
import android.os.Parcelable;
import android.os.Parcel;
import java.util.Random;

import attackontinytim.barquest.Database.Monster;
import attackontinytim.barquest.Database.QuestRepo;

public class Quest{
    
    /* ********* */
    /* VARIABLES */
    /* ********* */
    
    /** Common variables for all Quest objects */
    private int id; //  Quest ID
    private String name; // Name of the quest
    private String description; // Text description of the quest
    private int goal; // Total number of monsters to slay
    private int progress; // Number of monsters slain for the quest
    private boolean completed; // Boolean indicating if the quest has been completed
    private int XP; // XP amount to give as a reward can be 0
    private double Money; // Money to give as a reward can be 0
    private String itemName; // Name of the item the give as a reward, can be an empty string
    private String QuestType; // Type of quest can be by Rarity or Monster
    private String QuestTarget; // Target is the targeted rarity like Common, or a specific monster name

     /* ************ */
    /* CONSTRUCTORS */
    /* ************ */

    // Default constructor
    public Quest() {
        this.id = 0;
        this.name = "Default";
        this.description = "Default Quest";
        this.goal = 0;
        this.progress = 0;
        this.completed = true;
        this.XP = 0;
        this.Money = 0;
        this.itemName = "";
        this.QuestType = "Rarity";
        this.QuestTarget = "Common";
    }

    public Quest(int id, String name, String description, int goal, int progress, boolean completed, int XP, double money, String itemName, String questType, String questTarget) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.goal = goal;
        this.progress = progress;
        this.completed = completed;
        this.XP = XP;
        this.Money = money;
        this.itemName = itemName;
        this.QuestType = questType;
        this.QuestTarget = questTarget;
    }


    public Quest(Cursor cursor) {
        this.id = cursor.getInt(0);
        this.name = cursor.getString(1);
        this.description = cursor.getString(2);
        this.goal = cursor.getInt(3);
        this.progress = cursor.getInt(4);
        this.completed = cursor.getInt(5) > 0; // Check for boolean true or false
        this.XP = cursor.getInt(6);
        this.Money = cursor.getInt(7);
        this.itemName = cursor.getString(8);
        this.QuestType = cursor.getString(9);
        this.QuestTarget = cursor.getString(10);
    }
   

    /* *********** */
    /* GET-METHODS */
    /* *********** */


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public double getMoney() {
        return Money;
    }

    public void setMoney(double money) {
        Money = money;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQuestType() {
        return QuestType;
    }

    public void setQuestType(String questType) {
        QuestType = questType;
    }

    public String getQuestTarget() {
        return QuestTarget;
    }

    public void setQuestTarget(String questTarget) {
        QuestTarget = questTarget;
    }
    public String getName() {
        return name;
    }

    /**
     * Takes a monster after completing a battle and updates quest progress based on the monster
     * Currently checks for the monster rarity and the monster name
     * @param mon Monster that was killed
     */
    public void updateQuestProgress(Monster mon){
        if (this.getQuestType().equalsIgnoreCase("Rarity")){
            if (this.getQuestTarget().equalsIgnoreCase(mon.getRarity())){
                this.setProgress( this.getProgress() + 1 );
            }
        }
        else if (this.getQuestType().equalsIgnoreCase("Monster")){
            if (this.getQuestTarget().equalsIgnoreCase(mon.getName())){
                this.setProgress( this.getProgress() + 1 );
            }
        }

        QuestRepo.updateQuest(this);
    }


    /* ************* */
    /* MISC. METHODS */
    /* ************* */
    

    // Framework Methods
    /////////////////////
/*
    public static Creator<Quest> getCREATOR() {
        return CREATOR;
    }
    // Parcel Constructor
    public Quest(Parcel in) {
        int temp;

        this.id = in.readInt();
        this.currentCompleted = in.readInt();
        this.completionGoal = in.readInt();
        temp = in.readInt();

        if (temp == 0)
            this.canTurnInEarly = false;
        else
            this.canTurnInEarly = true;
    }

    @Override
    public int describeContents() {
        return 0;
    }
	
	// This allows us to use parcelling for easier transmission of Quest objects
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //TODO: Parcelelize Quest attributes
        int temp;
        if (!this.canTurnInEarly)
            temp = 0;
        else
            temp = 1;

        dest.writeInt(this.getId());
        dest.writeInt(this.getCurrentCompleted());
        dest.writeInt(this.getCompletionGoal());
        dest.writeInt(temp);
    }

    public static final Parcelable.Creator<Quest> CREATOR = new Parcelable.Creator<Quest>() {

        public Quest createFromParcel(Parcel in) {
            return new Quest(in);
        }

        public Quest[] newArray(int size) {
            return new Quest[size];
        }
    };*/
}