package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import net.impactdevs.joker.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by Ian on 11/20/2015.
 */
public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {

    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground (Pair<Context, String>... params) {
        if (myApiService == null) {  // Only do this once
//            MyApi.Builder builder = new MyApi.Builder( AndroidHttp.newCompatibleTransport(),
//                                                       new AndroidJsonFactory(),
//                                                       null )
//                    // options for running against local devappserver
//                    // - 10.0.2.2 is localhost's IP address in Android emulator
//                    // - turn off compression when running against local devappserver
//                    .setRootUrl( "http://10.0.2.2:8080/_ah/api/" )
//                    .setGoogleClientRequestInitializer( new GoogleClientRequestInitializer() {
//                        @Override
//                        public void initialize (
//                                AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
//                            abstractGoogleClientRequest.setDisableGZipContent( true );
//                        }
//                    } );

            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://builditbigger-1136.appspot.com/_ah/api/");

            // end options for devappserver
            myApiService = builder.build();
        }

        context = params[0].first;
        String name = params[0].second;

        try {
            return myApiService.sayHi( name ).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute (String result) {
        Toast.makeText( context, result, Toast.LENGTH_LONG ).show();
    }
}
