/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package net.impactdevs.joker.backend;

import com.example.Joke;
import com.example.Joker;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.NotFoundException;

import java.util.ArrayList;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.joker.impactdevs.net",
    ownerName = "backend.joker.impactdevs.net",
    packagePath=""
  )
)
public class MyEndpoint {

    private Joker mJoker = new Joker();

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod (name = "sayHi")
    public MyBean sayHi (@Named ("name") String name) {

        MyBean response = new MyBean();
        response.setData( "Hi, " + name );
        return response;
    }

    @ApiMethod (name = "getJoke")
    public MyBean getJoke (@Named ("index") int index) throws NotFoundException {

        try {

            Joke joke = mJoker.getJoke( index );
            MyBean jokeResponse = new MyBean();
            jokeResponse.setJoke( joke );
            return jokeResponse;

        } catch (IndexOutOfBoundsException e) {
            throw new NotFoundException( "Joke not found with an inde: "  + index);
        }
    }

    //Todo:
    public ArrayList<Joke> listJokes() {
        return new ArrayList<>(  );
    }
}
