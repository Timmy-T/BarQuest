package attackontinytim.barquest;

import android.database.Cursor;
import android.os.Parcelable;
import android.os.Parcel;

import java.util.List;

import attackontinytim.barquest.Database.QuestRepo;
import attackontinytim.barquest.Database.TimerRepo;
import attackontinytim.barquest.Database.Weapon;
import attackontinytim.barquest.Database.WeaponRepo;

public class Hero implements Parcelable {

    private static final double CH_SPD  = 2;
    private static final double CH_ATK  = 2;
    private static final double WEP_WT = 2;

    /* ********* */
    /* VARIABLES */
    /* ********* */

    /** Common variables for all Hero objects */
    private int id;
    private String name;
    private int HP;
    private int XP;
    private int level;
    private int speed;
    private int defense;
    private int attack;
    private double money;
    private int AP;
    private Weapon active;
    private Quest currentQuest;
    private Timer[] scanTimers;

     /* ************ */
    /* CONSTRUCTORS */
    /* ************ */

    /** Obligatory default constructor */
    public Hero() {}

    /** Construct a Hero object with the provided name (fresh hero character) */
    public Hero(String name) {
        this.id = 0;
        this.name = name;
        this.HP = 100;
        this.XP = 99 ;
        this.money = 10;
        this.AP = 10;
        this.level = 1;
        this.speed = 5;
        this.defense = 5;
        this.attack = 5;
        // Default cuz reasons
        this.active =  new Weapon("Close", 1,1,"Dagger of Wood", 0.1, 0.5);
        this.currentQuest = new Quest();

        /*this.scanTimers = new Timer[10];
        for (int i = 0; i < 10; i++) {
            this.scanTimers[i].setTime(Calendar.getInstance());
        }*/
        getScannerTimeFromDB();

    }

    /** Construct a Hero object with the provided stats (for testing) */
    public Hero(int id, String name, int HP, int XP, int level, int speed, int defense, int attack, double money, Weapon active, int AP, Quest currentQuest, long[] timers) {
        this.id = id;
        this.name = name;
        this.HP = HP;
        this.XP = XP;
        this.AP = AP;
        this.level = level;
        this.speed = speed;
        this.defense = defense;
        this.attack = attack;
        this.money = money;

        // Default cuz reasons
        this.active = active;
        this.currentQuest = currentQuest;

        //  Timers
        /*this.scanTimers = new Timer[10];
        for (int i = 0; i < 10; i++) {
            this.scanTimers[i].setTime(timers[i]);
        }*/

        getScannerTimeFromDB();

    }

    public void getScannerTimeFromDB() {
        Timer[] tempTimers = new Timer[10];

        List<Long> timerList = TimerRepo.getAllTimers(this.name);
        for(int i=0; i<timerList.size(); i++){
            tempTimers[i] = new Timer(timerList.get(i));
        }
        this.scanTimers = tempTimers;
    }

    public Hero(int id, String name, int HP, int XP, int level, int speed, int defense, int attack, double money, Weapon active, int AP, Quest currentQuest, Timer[] timers) {
        this.id = id;
        this.name = name;
        this.HP = HP;
        this.XP = XP;
        this.AP = AP;
        this.level = level;
        this.speed = speed;
        this.defense = defense;
        this.attack = attack;
        this.money = money;

        // Default cuz reasons
        this.active = active;
        this.currentQuest = currentQuest;

        //  Timers
        /*this.scanTimers = new Timer[10];

        for (int i = 0; i < 10; i++) {
            this.scanTimers[i].setTime(timers[i].getTime());
        }*/
        getScannerTimeFromDB();

    }

    /** Copy Constructor makes a deep copy of the hero */
    public Hero cloneHero() {
        return new Hero(
                this.getId(),
                this.getName(),
                this.getHP(),
                this.getXP(),
                this.getLevel(),
                this.getSpeed(),
                this.getDefense(),
                this.getAttack(),
                this.getMoney(),
                this.getActive(),
                this.getAP(),
                this.getCurrentQuest(),
                this.getScanTimers());
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public int getAP() { return AP; }

    public void setAP(int AP) { this.AP = AP; }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public Weapon getActive() {
        return active;
    }
    public Quest getCurrentQuest() {return currentQuest; }
    public Timer[] getScanTimers() { return scanTimers; }

    public void setActive(Weapon active) {
        this.active = active;
    }
    public void setCurrentQuest(Quest currentQuest) {
        this.currentQuest = currentQuest;
    }
    public void setScanTimers(Timer[] incomingTimers) { this.scanTimers = incomingTimers; }

    public static Creator<Hero> getCREATOR() {
        return CREATOR;
    }



    /* ************* */
    /* MISC. METHODS */
    /* ************* */

    public int getAtkSpd(){
        int atk_spd = (int)Math.round(CH_SPD * this.getSpeed());

        if ((int)Math.round(WEP_WT * this.getActive().getWeight()) > this.getAttack())
            atk_spd -= (int)Math.round(WEP_WT * this.getActive().getWeight());

        if ((int)Math.round(this.getAttack()/CH_ATK) > this.getSpeed())
            atk_spd += (int)Math.round(this.getAttack()/CH_ATK);

        return atk_spd;
    }

    /** Increment Hero Experience count by the provided amount */
    public void inc_experience(int expGain) {

        this.setXP( this.getXP() + expGain);

        // While loop in case multiple levels are gained
        while (this.getXP() >= 100) {
            this.setLevel( this.getLevel() + 1);
            this.setXP( this.getXP() - 100 );
            this.setAP( this.getAP() + 10);
        }
    }

    public boolean timerPress() {
        boolean pressed = false;
        int i = 0;

        this.getScannerTimeFromDB();

        while ((!pressed) && (i < 10))
        {
            if (this.scanTimers[i].checkIfPast()) {
                this.scanTimers[i].setTimer();
                TimerRepo.updateTimer(this.scanTimers, this.name);

                pressed = true;
            }
            i++;
        }

        return pressed;
    }

    public int getNumTimersReady() {
        int numTimers = 0;

        this.getScannerTimeFromDB();

       for (int i = 0; i < 10; i++)
        {
            if (this.scanTimers[i].checkIfPast()) {
                numTimers += 1;
            }
        }

        return numTimers;
    }

    // Framework Methods
    /////////////////////

    // Parcel Constructor
    public Hero(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.HP = in.readInt();
        this.XP = in.readInt();
        this.AP = in.readInt();
        this.level = in.readInt();
        this.speed = in.readInt();
        this.defense = in.readInt();
        this.attack = in.readInt();
        this.money = in.readDouble();
        this.active = in.readParcelable(Weapon.class.getClassLoader());
        //this.currentQuest =  in.readParcelable(Quest.class.getClassLoader());

        this.scanTimers = new Timer[10];

        for (int i = 0; i < 10; i++) {
            this.scanTimers[i].setTime(in.readLong());
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // This allows us to use parcelling for easier transmission of Hero objects
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TO-DO: Parcelelize weapon attributes
        dest.writeInt(this.getId());
        dest.writeString(this.getName());
        dest.writeInt(this.getHP());
        dest.writeInt(this.getXP());
        dest.writeInt(this.getAP());
        dest.writeInt(this.getLevel());
        dest.writeInt(this.getSpeed());
        dest.writeInt(this.getDefense());
        dest.writeInt(this.getAttack());
        dest.writeDouble(this.getMoney());
        dest.writeParcelable(this.getActive(), flags);
        //dest.writeParcelable(this.getCurrentQuest(), flags);
        for (int i = 0; i < 10; i++) {
            dest.writeDouble(this.scanTimers[i].getTime().getTimeInMillis());
        }
    }

    public Hero(Cursor cursor)
    {
        this.id = cursor.getInt(0);
        this.name = cursor.getString(1);
        this.HP = cursor.getInt(2);
        this.XP = cursor.getInt(3);
        this.level = cursor.getInt(4);
        this.speed = cursor.getInt(5);
        this.defense = cursor.getInt(6);
        this.attack = cursor.getInt(7);
        this.money = cursor.getDouble(8);
        this.active = WeaponRepo.getItemByName(cursor.getString(9));
        this.AP = cursor.getInt(10);
        this.currentQuest = QuestRepo.getQuestByID(cursor.getInt(11));

    }

    public static final Parcelable.Creator<Hero> CREATOR = new Parcelable.Creator<Hero>() {

        public Hero createFromParcel(Parcel in) {
            return new Hero(in);
        }

        public Hero[] newArray(int size) {
            return new Hero[size];
        }
    };
}
