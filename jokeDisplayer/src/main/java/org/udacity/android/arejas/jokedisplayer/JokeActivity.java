package org.udacity.android.arejas.jokedisplayer;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE_EXTRA = "joke";

    private TextView jokeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        jokeText = (TextView) findViewById(R.id.joke);

        String text = null;

        try {
            text = getIntent().getExtras().getString(JOKE_EXTRA);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (text == null) {
            text = getString(R.string.error);
        }

        try {
            jokeText.setText(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
