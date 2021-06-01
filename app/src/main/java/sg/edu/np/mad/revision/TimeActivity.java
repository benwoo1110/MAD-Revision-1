package sg.edu.np.mad.revision;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TimeActivity extends AppCompatActivity {

    private boolean isPlayer1;
    private Player player;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        Intent data = getIntent();
        int duration = data.getIntExtra(MainActivity.DURATION_KEY, -1);

        if (duration < 0) {
            Toast.makeText(TimeActivity.this, "Duration must be a positive number!", Toast.LENGTH_SHORT).show();
            finish();
        }

        TextView player1Time = findViewById(R.id.textViewPlayer1Time);
        TextView player2time = findViewById(R.id.textViewPlayer2Time);
        ListView timingsList = findViewById(R.id.listViewTime);
        Button next = findViewById(R.id.buttonNext);

        List<String> timings = new ArrayList<>();
        ArrayAdapter<String> timingsAdapter = new ArrayAdapter<>(TimeActivity.this, android.R.layout.simple_list_item_1, timings);
        timingsList.setAdapter(timingsAdapter);

        player1Time.setText(String.valueOf(duration));
        player2time.setText(String.valueOf(duration));

        Player player1 = new Player("Player 1", player1Time);
        Player player2 = new Player("Player 2", player2time);

        isPlayer1 = true;
        player = player1;

        next.setOnClickListener(v -> {
            timer.cancel();
            timingsAdapter.add(String.format("%s left %s sec", player.name, player.getDurationLeft()));
            player = (isPlayer1 ^= true) ? player1 : player2;
            startTimer(player.getDurationLeft());
        });

        startTimer(duration);
    }

    private void startTimer(int duration) {
        timer = new CountDownTimer(duration*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                player.timingText.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                player.timingText.setText("0");
                Toast.makeText(TimeActivity.this, player.name + " time is up!", Toast.LENGTH_SHORT).show();
            }
        };
        timer.start();
    }
}