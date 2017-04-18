package attackontinytim.barquest;

import android.os.Parcelable;
import android.os.Parcel;
import java.util.Random;

public class Quest implements Parcelable {
    
    /* ********* */
    /* VARIABLES */
    /* ********* */
    
    /** Common variables for all Quest objects */
    private int id;
    private int currentCompleted;
    private int completionGoal;
    private boolean canTurnInEarly;

     /* ************ */
    /* CONSTRUCTORS */
    /* ************ */
    
    // Default constructor
    public Quest() {
        this.id = 0;
        this.currentCompleted = 0;
        this.completionGoal = 0;
        this.canTurnInEarly = true;
    }
    
    // Construct a Quest object with the provided id, random goal amount
    public Quest(int newID) {
        this.id = newID;
        this.currentCompleted = 0;
        this.completionGoal = 3 + (int)(Math.random() * ((10 - 3) + 1));
        this.canTurnInEarly = true;
    }

    // Construct a Quest object with the provided id, goal amount, and turnInEarly-able
    public Quest(int newID, int completionGoal, boolean canTurnInEarly) {
        this.id = newID;
        this.currentCompleted = 0;
        this.completionGoal = completionGoal;
        this.canTurnInEarly = canTurnInEarly;
    }

    // Construct a Quest from all components
    public Quest(int id, int currentCompleted, int completionGoal, boolean canTurnInEarly) {
        this.id = id;
        this.currentCompleted = currentCompleted;
        this.completionGoal = completionGoal;
        this.canTurnInEarly = canTurnInEarly;
    }
   
    
    
    
    
    /* *********** */
    /* GET-METHODS */
    /* *********** */

    public int getId() {
        return id;
    }
    public int getCurrentCompleted()  {
        return currentCompleted;
    }
    public int getCompletionGoal()  {
        return completionGoal;
    }
    public boolean getCanTurnInEarly() { return canTurnInEarly; }

    public void setId(int id) {
        this.id = id;
    }
    public void setCurrentCompleted(int currentCompleted) {this.currentCompleted = currentCompleted;}
    public void setCompletionGoal(int completionGoal) {
        this.completionGoal = completionGoal;
    }
    public void setCanTurnInEarly(boolean canTurnInEarly) { this.canTurnInEarly = canTurnInEarly; }
    
    /* ************* */
    /* MISC. METHODS */
    /* ************* */
    

    // Framework Methods
    /////////////////////

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
    };
}