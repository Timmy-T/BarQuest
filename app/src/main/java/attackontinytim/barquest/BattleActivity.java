package attackontinytim.barquest;

//import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
//import android.os.Parcel;

public class BattleActivity extends AppCompatActivity /*implements Parcelable*/{

    public Player player;
    private Battle battle;

    /*@Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mData);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<BattleActivity> CREATOR = new Parcelable.Creator<BattleActivity>() {
        public BattleActivity createFromParcel(Parcel in) {
            return new BattleActivity(in);
        }

        public BattleActivity[] newArray(int size) {
            return new BattleActivity[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private BattleActivity(Parcel in) {
        mData = in.readInt();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle_laout);

        Bundle bundle = getIntent().getExtras();
        Player player = bundle.getParcelable("attackontinytim.barquest.Player");
        battle = new Battle(player);

        TextView textView = (TextView) this.findViewById(R.id.textView2);
        textView.setText("Player Name: " + battle.player.getName());
    }
}
