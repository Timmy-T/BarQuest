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

     /* ************ */
    /* CONSTRUCTORS */
    /* ************ */
    
    // Default constructor
    public Quest() {
        this.id = 0;
        this.currentCompleted = 0;
        this.completionGoal = 0;
    }
    
    // Construct a Quest object with the provided id
    public Quest(int newID) {
        this.id = newID;
        this.currentCompleted = 0;
        this.completionGoal = 3 + (int)(Math.random() * ((10 - 3) + 1));
    }

    // Construct a Quest from all components
    public Quest(int id, int currentCompleted, int completionGoal) {
        this.id = id;
        this.currentCompleted = currentCompleted;
        this.completionGoal = completionGoal;
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

    public void setId(int id) {
        this.id = id;
    }
    public void setCurrentCompleted(int currentCompleted) {this.currentCompleted = currentCompleted;}
    public void setCompletionGoal(int completionGoal) {
        this.completionGoal = completionGoal;
    }
    
    /* ************* */
    /* MISC. METHODS */
    /* ************* */
    

    // Framework Methods
    /////////////////////

    // Parcel Constructor
    public Quest(Parcel in) {
        this.id = in.readInt();
        this.currentCompleted = in.readInt();
        this.completionGoal = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }
	
	// This allows us to use parcelling for easier transmission of Quest objects
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TO-DO: Parcelelize Quest attributes
        dest.writeInt(this.getId());
        dest.writeInt(this.getCurrentCompleted());
        dest.writeInt(this.getCompletionGoal());
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