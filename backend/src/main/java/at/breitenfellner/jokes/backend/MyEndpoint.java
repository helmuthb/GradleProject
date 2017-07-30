/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package at.breitenfellner.jokes.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

import at.breitenfellner.jokes.Jokes;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "jokesApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.jokes.breitenfellner.at",
                ownerName = "backend.jokes.breitenfellner.at",
                packagePath = ""
        )
)
public class MyEndpoint {

    Jokes jokes;

    public MyEndpoint() {
        jokes = new Jokes();
    }
    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "getJoke")
    public Jokes.Joke getJoke() {
        return jokes.getJoke();
    }

}
