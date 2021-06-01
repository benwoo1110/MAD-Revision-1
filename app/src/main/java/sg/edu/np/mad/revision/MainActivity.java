package sg.edu.np.mad.revision;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String DURATION_KEY = "sg.edu.np.mad.revision.MainActivity.duration";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText durationText = findViewById(R.id.editTextDuration);
        Button startButton = findViewById(R.id.buttonStart);

        startButton.setOnClickListener(v -> {
            int duration;
            try {
                duration = Integer.parseInt(durationText.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Duration not a valid number!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (duration < 0) {
                Toast.makeText(MainActivity.this, "Duration must be a positive number!", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(MainActivity.this, TimeActivity.class);
            intent.putExtra(DURATION_KEY, duration);
            startActivity(intent);
        });
    }
}