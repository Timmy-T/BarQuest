package attackontinytim.barquest;

import android.os.Parcelable;
import android.os.Parcel;

import java.util.Calendar;
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
        time = Calendar.getInstance();
        time.add(Calendar.HOUR, -2);
    }

    public Timer(Calendar time) {
        this.time = time;
    }

    public Timer(long input) {
        time = Calendar.getInstance();
        time.setTimeInMillis(input);
    }

    /* *********** */
    /* GET-METHODS */
    /* *********** */


    public Calendar getTime() {
        return time;
    }

    public void setTime() {
        this.time = Calendar.getInstance();
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public void setTime(long input) { time.setTimeInMillis(input); }

    /* ************* */
    /* MISC. METHODS */
    /* ************* */

    public boolean checkIfPast() {
        Calendar current = Calendar.getInstance();
        if (time.compareTo(current) <= 0)
            return true;
        return false;
    }

    public void setTimer() {
        time.add(Calendar.HOUR, 2);
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
        time = Calendar.getInstance();
        time.setTimeInMillis(milliseconds);

    }

    @Override
    public int describeContents() {
        return 0;
    }

}
