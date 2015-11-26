package com.example;

import java.util.ArrayList;

public class Joker {

    private static ArrayList<Joke> jokeList = new ArrayList<Joke>() {};

    static {
        jokeList.add( new Joke( "Knock Knock", "Knock, Knock", "Nobody's home!" ) );
        jokeList.add( new Joke( "Momma", "Your momma so fat..", "She likes cake..." ) );
        jokeList.add( new Joke( "Reindeer", "What do you call a freezing reindeer",
                                "Snow deer"
        ) );
    }

    public Joke getJoke (int index) {

        return index < jokeList.size() ? jokeList.get( index ) : null;
    }
}
