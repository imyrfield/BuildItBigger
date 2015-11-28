package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import impactdevs.net.jokeactivity.JokeActivity;


public class MainActivity
        extends ActionBarActivity implements EndpointsAsyncTask.AsyncListener{

    private int   index  = 0;
    private String setup;
    private String punchLine;
    private ProgressDialog progress;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.menu_main, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected( item );
    }

    public void tellJoke (View view) {

        progress = new ProgressDialog( this );
        progress.setMessage( getString( R.string.progress_dialog_message ) );
        progress.setProgressStyle( ProgressDialog.STYLE_SPINNER );
        progress.setIndeterminate( true );
        progress.show();

        Log.d( "MainActivity", "tellJoke (line 50): " );
        new EndpointsAsyncTask().execute( new Pair<Context, Integer>( this, index ) );
        index++;
    }

    public void showJoke () {

        dismissProgress();

        Intent intent = new Intent( this, JokeActivity.class );
        intent.putExtra( "setup", setup );
        intent.putExtra( "punchline", punchLine );
        startActivity( intent );
    }

    public void showJokeList (View view) {

        Toast.makeText( this, "Feature Coming Soon!", Toast.LENGTH_LONG ).show();
    }

    @Override
    public void saveData (Bundle bundle) {

        setup = bundle.getString( "setup" );
        punchLine = bundle.getString( "punchline" );

        showJoke();
    }

    private void dismissProgress () {
        if (progress != null) {
            progress.dismiss();
        }
    }


}
