package com.example;

/**
 * Created by Ian on 11/19/2015.
 */
public class Joke {

    private String setup;
    private String punchLine;

    public String getPunchLine () {
        return punchLine;
    }

    public void setPunchLine (String punchLine) {
        this.punchLine = punchLine;
    }

    public String getSetup () {
        return setup;
    }

    public void setSetup (String setup) {
        this.setup = setup;
    }
}
