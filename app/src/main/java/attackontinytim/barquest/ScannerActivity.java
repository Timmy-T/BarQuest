package attackontinytim.barquest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import attackontinytim.barquest.Database.Weapon;

import static attackontinytim.barquest.MainActivity.MAIN_RETURN_CODE;

public class ScannerActivity extends AppCompatActivity {

    public Hero hero;

	// This is called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanner_layout);


        Bundle bundle = getIntent().getExtras();
        hero = bundler.unbundleHero(bundle);

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scan a barcode");
        integrator.setBeepEnabled(false);

        integrator.setOrientationLocked(true);
        integrator.initiateScan();

    }

    // This is called when the activity is ended via result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() != null) {
                int numberValue = scannerHash(result.getContents());

                Bundle bundle = bundler.generateBundle(hero);
                bundle.putInt("MonsterHash", numberValue);

                Intent intent = new Intent("attackontinytim.barquest.BattleActivity");
                intent.putExtras(bundle);
                startActivityForResult(intent,  MAIN_RETURN_CODE);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

	// This is what is called when back is pressed
    @Override
    public void onBackPressed() {
		// it ends the acitivity
        end();
    }

	// This is the act of ending the activity
    private void end(){
        Bundle bundle = bundler.generateBundle(hero);

        setResult(RESULT_OK,getIntent().putExtras(bundle));
        finish();
    }

    public void ScannerActivity(View view) {
    }

    /**
     * Turns the value scanned into an integer value
     * @param value Ascii string
     * @return integer value that is either the number scanned or an ascii string converted to
     * numbers
     */
    private int scannerHash(String value){
        try{
            int number = Integer.parseInt(value);
            return number;
        }
        catch (NumberFormatException e){
            int number = 0;
            for(int i = 0; i < value.length(); i++){
                number += (int)value.charAt(i);
            }
            return number;
        }
    }
}
