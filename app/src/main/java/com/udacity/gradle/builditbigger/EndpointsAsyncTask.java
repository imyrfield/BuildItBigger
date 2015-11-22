package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import net.impactdevs.joker.backend.myApi.MyApi;
import net.impactdevs.joker.backend.myApi.model.Joke;

import java.io.IOException;

import impactdevs.net.jokeactivity.JokeActivity;

/**
 * Created by Ian on 11/20/2015.
 */
public class EndpointsAsyncTask
        extends AsyncTask<Pair<Context, Integer>, Void, Joke> {
    
    private static MyApi myApiService = null;
    private Context context;
    
    @Override
    protected Joke doInBackground (Pair<Context, Integer>... params) {
        Log.d( "EndpointsAsyncTask", "doInBackground (line 30): " );
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder( AndroidHttp.newCompatibleTransport(),
                                                       new AndroidJsonFactory(),
                                                       null )
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    
                    //                    .setRootUrl( "http://10.0.2.2:8080/_ah/api/" )
                    //                    .setGoogleClientRequestInitializer( new GoogleClientRequestInitializer() {
                    //                        @Override
                    //                        public void initialize (
                    //                                AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                    //                            abstractGoogleClientRequest.setDisableGZipContent( true );
                    //                        }
                    //                    } );
                    
                    // end options for devappserver
                    
                    .setRootUrl( "https://builditbigger-1136.appspot.com/_ah/api/" );
            myApiService = builder.build();
        }
        
        context = params[0].first;
        int index = params[0].second;
        Log.d("EndpointsAsyncTask", "doInBackground (line 57): " + index);
        
        try {
            return myApiService.getJoke( index ).execute().getJoke();
            //            return myApiService.sayHi( name ).execute().getData();
        } catch (IOException e) {
            return null;
        }
    }
    
    @Override
    protected void onPostExecute (Joke result) {
        
        Log.d( "EndpointsAsyncTask", "onPostExecute (line 66): ");

        if (result == null) {
            Log.d( "EndpointsAsyncTask", "onPostExecute (line 71): result is null" );
            Toast.makeText( context, "No result", Toast.LENGTH_LONG ).show();
            return;
        }
        
        // Pass Intent to Android Library
        Intent intent = new Intent( context, JokeActivity.class );
        intent.putExtra( "setup", result.getSetup() );
        intent.putExtra( "punchline", result.getPunchLine() );
        context.startActivity( intent );
    }
}
