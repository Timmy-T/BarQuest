package attackontinytim.barquest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class InventoryActivity extends AppCompatActivity {

    public Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_layout);

        Bundle bundle = getIntent().getExtras();
        player = bundle.getParcelable("attackontinytim.barquest.Player");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 1:
                    Intent intent = new Intent();
                    intent.putExtra("attackontinytim.barquest.Player", player);
                    setResult(RESULT_OK, intent);
                    finish();
            }
        }
    }
}
