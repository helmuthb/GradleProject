package com.udacity.gradle.builditbigger;

import android.support.test.annotation.UiThreadTest;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import at.breitenfellner.jokes.Jokes;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * Created by helmuth on 30/07/2017.
 */

@RunWith(AndroidJUnit4.class)
public class JokesAsyncTaskTest {
    Jokes.Joke theJoke;

    @Test
    public void testAsyncTask() {
        theJoke = null;
        final JokesAsyncTask testTask = new JokesAsyncTask() {
            @Override
            protected void onPostExecute(Jokes.Joke joke) {
                synchronized (JokesAsyncTaskTest.this) {
                    theJoke = joke;
                }
            }
        };

        // start the activity on the main thread
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                testTask.execute();
            }
        });

        // sleep for 10 seconds
        try {
            Thread.sleep(10000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        // check if the joke landed
        synchronized (this) {
            Assert.assertFalse("The joke text is not empty", TextUtils.isEmpty(theJoke.text));
            Assert.assertFalse("The joke author is not empty", TextUtils.isEmpty(theJoke.author));
        }
    }
}
