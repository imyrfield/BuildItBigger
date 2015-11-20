package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.Joke;
import com.example.Joker;


public class MainActivity extends ActionBarActivity {

    private Joker mJoker = new Joker();
    private int   index  = 0;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        mJoker.initializeJokeList();

    }


    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
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

    public void tellJoke(View view){

        Joke joke = mJoker.getJoke( index );
        index++;

        if (joke != null) {
            Toast.makeText( this, joke.getSetup(), Toast.LENGTH_SHORT ).show();
            Toast.makeText( this, joke.getPunchLine(), Toast.LENGTH_SHORT ).show();
        } else {
            Toast.makeText( this, "No more jokes", Toast.LENGTH_SHORT ).show();
        }
    }


}
