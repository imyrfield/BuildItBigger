package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.Joker;


public class MainActivity extends ActionBarActivity {

    private Joker mJoker = new Joker();
    private int   index  = 0;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

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
        Log.d( "MainActivity", "tellJoke (line 50): " );
        new EndpointsAsyncTask().execute( new Pair<Context, Integer>( this,
                                                                     index ) );
        index++;

//        Joke joke = mJoker.getJoke( index );
//        index++;
//
//        if (joke != null) {
////            Toast.makeText( this, joke.getSetup(), Toast.LENGTH_SHORT ).show();
////            Toast.makeText( this, joke.getPunchLine(), Toast.LENGTH_SHORT ).show();
//
//            // Pass Intent to Android Library
//            Intent intent = new Intent(
//                    this, JokeActivity.class );
//            intent.putExtra( "setup", joke.getSetup() );
//            intent.putExtra( "punchline", joke.getPunchLine() );
//            startActivity( intent );
//        } else {
//            Toast.makeText( this, "No more jokes", Toast.LENGTH_SHORT ).show();
//        }
    }


}
