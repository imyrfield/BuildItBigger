package com.example;

import java.util.ArrayList;

public class Joker {

    private ArrayList<Joke> jokeList;

    public void initializeJokeList () {
        jokeList = new ArrayList<Joke>() {};
        jokeList.add( setJoke( "Knock, Knock", "Nobody's home!" ) );
        //jokeList.add( setJoke( "", "" ) );
    }

    public Joke getJoke (int index) {

        return index < jokeList.size() ? jokeList.get( index ) : null;
    }

    private Joke setJoke (String setup, String punchLine) {

        Joke joke = new Joke();
        joke.setSetup( setup );
        joke.setPunchLine( punchLine );

        return joke;
    }
}
