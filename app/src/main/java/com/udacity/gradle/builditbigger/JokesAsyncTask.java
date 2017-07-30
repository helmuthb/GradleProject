package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import at.breitenfellner.jokes.Jokes;
import at.breitenfellner.jokes.backend.jokesApi.JokesApi;
import at.breitenfellner.jokes.backend.jokesApi.model.Joke;
import at.breitenfellner.jokes_androidlib.JokeActivity;

/**
 * Created by helmuth on 30/07/2017.
 */

public class JokesAsyncTask extends AsyncTask<Void, Void, Jokes.Joke> {
    private static JokesApi jokesApiService = null;

    @Override
    protected Jokes.Joke doInBackground(Void... params) {
        if (jokesApiService == null) {  // Only do this once
            JokesApi.Builder builder = new JokesApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            jokesApiService = builder.build();
        }
        try {
            Joke modelJoke = jokesApiService.getJoke().execute();
            return new Jokes.Joke(modelJoke.getText(), modelJoke.getAuthor());
        } catch (IOException e) {
            return new Jokes.Joke(e.getMessage(), null);
        }
    }
}
