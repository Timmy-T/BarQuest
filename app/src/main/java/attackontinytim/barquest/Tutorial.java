package attackontinytim.barquest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by Tim Buesking on 5/1/2017.
 */

public class Tutorial {
    private static boolean homeBool = false;
    private static boolean scanBool = false;
    private static boolean battleBool = false;
    private static boolean characterBool = false;
    private static boolean levelUpBool = false;
    private static boolean inventoryBool = false;
    private static boolean shopBool = false;
    private static boolean questBool = false;


    public static AlertDialog.Builder getTutorialScreen(Activity activity, int title, int message) {
        AlertDialog.Builder diag = new AlertDialog.Builder(activity)
                .setTitle(activity.getString(title))
                .setMessage(activity.getString(message))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(android.R.drawable.dark_header);
        diag.show();
        return diag;
    }

    public static void homeTutorial(Activity activity) {
        if (homeBool) {
            homeBool = false;
            getTutorialScreen(activity, R.string.home_tut_title, R.string.home_tut_msg);
        }
    }

    public static void scanTutorial(Activity activity) {
        if (scanBool) {
            scanBool = false;
            getTutorialScreen(activity, R.string.scan_tut_title, R.string.scan_tut_msg);
        }
    }

    public static void battleTutorial(Activity activity) {
        if (battleBool) {
            battleBool = false;
            getTutorialScreen(activity, R.string.battle_tut_title, R.string.battle_tut_msg);
        }
    }

    public static void characterTutorial(Activity activity) {
        if (characterBool) {
            characterBool = false;
            getTutorialScreen(activity, R.string.char_tut_title, R.string.char_tut_msg);
        }
    }

    public static void levelUpTutorial(Activity activity) {
        if (levelUpBool) {
            levelUpBool = false;
            getTutorialScreen(activity, R.string.levelUp_tut_title, R.string.levelUp_tut_msg);
        }
    }

    public static void inventoryTutorial(Activity activity) {
        if (inventoryBool) {
            inventoryBool = false;
            getTutorialScreen(activity, R.string.inventory_tut_title, R.string.inventory_tut_msg);
        }
    }

    public static void shopTutorial(Activity activity) {
        if (shopBool) {
            shopBool = false;
            getTutorialScreen(activity, R.string.shop_tut_title, R.string.shop_tut_msg);
        }
    }

    public static void questTutorial(Activity activity) {
        if (questBool) {
            questBool = false;
            getTutorialScreen(activity, R.string.quest_tut_title, R.string.quest_tut_msg);
        }
    }

    public static void setAllTrue() {
        homeBool = true;
        scanBool = true;
        battleBool = true;
        characterBool = true;
        levelUpBool = true;
        inventoryBool = true;
        shopBool = true;
        questBool = true;
    }
}
