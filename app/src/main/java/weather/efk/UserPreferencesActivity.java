package weather.efk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UserPreferencesActivity extends AppCompatActivity {

    EditText coldF, coldT, comfortableF, comfortableT, warmT, warmF, hotT, hotF;
    Button savePref_btn, predict_btn;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preferences);

        coldF = findViewById(R.id.coldF);
        comfortableF = findViewById(R.id.comfortableF);
        hotF = findViewById(R.id.hotF);
        coldT = findViewById(R.id.coldT);
        comfortableT = findViewById(R.id.comfortableT);
        hotT = findViewById(R.id.hotT);
        spinner = findViewById(R.id.spinner);
        savePref_btn = findViewById(R.id.savePref_button);
        predict_btn = findViewById(R.id.predict_button);

        ArrayAdapter<CharSequence>adapter= ArrayAdapter.createFromResource(this, R.array.preferences, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinner.setAdapter(adapter);

        savePref_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast t = Toast.makeText(getApplicationContext(), "Save Complete!", Toast.LENGTH_SHORT);
                t.show();
            }
        });

        String coldFT = coldF.getText().toString();
        String coldTT = coldT.getText().toString();
        String comfortableFT = comfortableF.getText().toString();
        String comfortableTT = comfortableT.getText().toString();
        String hotTT = hotT.getText().toString();
        String hotFT = hotF.getText().toString();
    }
}