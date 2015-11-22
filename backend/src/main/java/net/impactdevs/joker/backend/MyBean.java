package net.impactdevs.joker.backend;

import com.example.Joke;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {

    private String myData;
    private Joke   mJoke;


    public String getData () {
        return myData;
    }

    public void setData (String data) {
        myData = data;
    }

    public Joke getJoke () {
        return mJoke;
    }

    public void setJoke (Joke joke) {
        mJoke = joke;
    }
}