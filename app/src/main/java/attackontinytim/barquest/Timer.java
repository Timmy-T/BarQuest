package attackontinytim.barquest;

import android.os.Parcelable;
import android.os.Parcel;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Timer implements Parcelable {
    /* ********* */
    /* VARIABLES */
    /* ********* */

    /** Common variables for all Quest objects */
    private Calendar time;

     /* ************ */
    /* CONSTRUCTORS */
    /* ************ */

    // Default constructor
    public Timer() {
        this.time = Calendar.getInstance();
        this.time.add(Calendar.HOUR, -2);
    }

    public Timer(Calendar time) {
        this.time = time;
    }

    public Timer(long input) {
        this.time = Calendar.getInstance();
        this.time.setTimeInMillis(input);
    }

    public Timer(Timer toBeCopied) {
        this.time = toBeCopied.getTime();
    }

    /* *********** */
    /* GET-METHODS */
    /* *********** */


    public Calendar getTime() {
        return this.time;
    }

    public void setTime() {
        this.time = Calendar.getInstance();
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public void setTime(long input) { this.time.setTimeInMillis(input); }

    /* ************* */
    /* MISC. METHODS */
    /* ************* */

    public boolean checkIfPast() {
        Calendar current = Calendar.getInstance();

        Date temp = this.time.getTime();
        Date temp2 = current.getTime();

        int result = temp.compareTo(temp2);

        return result < 0;

    }

    public void setTimer() {
        this.time.add(Calendar.HOUR, 2);
    }

    // Framework Methods
    /////////////////////

    /*public static Creator<Hero> getCREATOR() {
        return CREATOR;
    }*/
    public final static Parcelable.Creator<Timer> CREATOR = new Parcelable.Creator<Timer>() {

        @Override
        public Timer createFromParcel(Parcel source) {
            return new Timer(source);
        }

        @Override
        public Timer[] newArray(int size) {
            return new Timer[size];
        }

    };

    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(time.getTimeInMillis());
        //out.writeString(time.getTimeZone().getID());
    }

    private Timer(Parcel in) {

        long milliseconds = in.readLong();
        //String timezone_id = in.readString();

        //time = new GregorianCalendar(TimeZone.getTimeZone(timezone_id));
        //time = new GregorianCalendar();
        this.time = Calendar.getInstance();
        this.time.setTimeInMillis(milliseconds);

    }

    @Override
    public int describeContents() {
        return 0;
    }

}
