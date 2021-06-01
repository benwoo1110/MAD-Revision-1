package sg.edu.np.mad.revision;

import android.widget.TextView;

public class Player {
     final String name;
     final TextView timingText;

    public Player(String name, TextView timingText) {
        this.name = name;
        this.timingText = timingText;
    }

    int getDurationLeft() {
        return Integer.parseInt(timingText.getText().toString());
    }
}
