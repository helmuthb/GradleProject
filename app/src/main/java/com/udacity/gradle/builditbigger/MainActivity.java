package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import at.breitenfellner.jokes.Jokes;
import at.breitenfellner.jokes.backend.jokesApi.JokesApi;
import at.breitenfellner.jokes_androidlib.JokeActivity;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        Jokes.Joke joke = new Jokes().getJoke();
        // Toast.makeText(this, joke.text + "\n" + joke.author, Toast.LENGTH_SHORT).show();
        // Step 1
        // Intent intent = JokeActivity.getJokeIntent(this, joke);
        // startActivity(intent);
        JokesAsyncTask loadFromGce = new JokesAsyncTask() {
            @Override
            protected void onPostExecute(Jokes.Joke joke) {
                startActivity(JokeActivity.getJokeIntent(MainActivity.this, joke));
            }
        };
        loadFromGce.execute();
    }
}