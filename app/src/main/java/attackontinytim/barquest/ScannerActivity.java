package attackontinytim.barquest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ScannerActivity extends AppCompatActivity {

    public Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanner_layout);

        Bundle bundle = getIntent().getExtras();
        player = bundle.getParcelable("attackontinytim.barquest.Player");
    }
}
