package at.breitenfellner.jokes_androidlib;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import at.breitenfellner.jokes.Jokes;

public class JokeActivity extends AppCompatActivity {

    static public final String EXTRA_JOKE = Jokes.Joke.class.getName();

    static public Intent getJokeIntent(Context context, Jokes.Joke joke) {
        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra(EXTRA_JOKE, joke);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        Intent myIntent = getIntent();
        if (myIntent.hasExtra(EXTRA_JOKE)) {
            Jokes.Joke joke = (Jokes.Joke) myIntent.getSerializableExtra(EXTRA_JOKE);
            TextView jokeText = (TextView)findViewById(R.id.joke_text);
            TextView jokeAuthor = (TextView)findViewById(R.id.joke_author);
            jokeText.setText(joke.text);
            jokeAuthor.setText(joke.author);
        }
    }
}
