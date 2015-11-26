package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import impactdevs.net.jokeactivity.JokeActivity;


public class MainActivity
        extends ActionBarActivity
        implements EndpointsAsyncTask.AsyncListener {

    private int index = 0;
    private InterstitialAd mInterstitialAd;
    private String         setup;
    private String         punchLine;
    private boolean        jokeReady;
    private ProgressDialog progress;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        mInterstitialAd = new InterstitialAd( this );
        mInterstitialAd.setAdUnitId( getResources().getString( R.string.interstitial_ad_unit_id ) );
        mInterstitialAd.setAdListener( new AdListener() {
            @Override
            public void onAdLoaded () {
                super.onAdLoaded();
            }

            @Override
            public void onAdClosed () {
                super.onAdClosed();

                requestInterstitial();

                if (jokeReady) {

                    showJoke();
                }
            }
        } );

        jokeReady = false;
        requestInterstitial();
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

    // TODO: make it an image button (dice)
    public void tellJoke (View view) {

        progress = new ProgressDialog( this );
        progress.setMessage( getString( R.string.progress_dialog_message ) );
        progress.setProgressStyle( ProgressDialog.STYLE_SPINNER );
        progress.setIndeterminate( true );
        progress.show();

        jokeReady = false;
        new EndpointsAsyncTask().execute( new Pair<Context, Integer>( this, index ) );
        index++;

        showInterstitial( view );
    }

    // Pass Intent to Android Library
    public void showJoke () {

        dismissProgress();

        Intent intent = new Intent( this, JokeActivity.class );
        intent.putExtra( "setup", setup );
        intent.putExtra( "punchline", punchLine );
        startActivity( intent );
    }

    public void showJokeList (View view) {

        Toast.makeText( this,
                        "To access this feature, please update to the paid version",
                        Toast.LENGTH_LONG ).show();
    }

    public void requestInterstitial () {

        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd( adRequest );
    }

    public void showInterstitial (View unusedView) {
        if (mInterstitialAd.isLoaded()) {

            mInterstitialAd.show();
        }
    }

    @Override
    public void saveData (Bundle bundle) {
        setup = bundle.getString( "setup" );
        punchLine = bundle.getString( "punchline" );
        jokeReady = true;
    }

    private void dismissProgress () {
        if (progress != null) {
            progress.dismiss();
        }
    }
}
