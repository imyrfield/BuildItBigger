package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.test.InstrumentationTestCase;
import android.util.Log;
import android.util.Pair;

import net.impactdevs.joker.backend.myApi.model.Joke;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ian on 11/21/2015.
 */
public class testEndpointsAsyncTask
        extends InstrumentationTestCase {

    private static boolean called;
    private static boolean noResult;
    MainActivity ma      = new MainActivity();
    Context      context = ma.getBaseContext();
    private Integer        index     = 0;
    private String         setup     = "";
    private String         punchline = "";
    private CountDownLatch signal    = null;

    protected void setUp () throws Exception {
        super.setUp();
        called = false;

        // create  a signal to let us know when our task is done.
        signal = new CountDownLatch( 1 );
    }

    protected void tearDown () throws Exception {
        super.tearDown();
    }

    @SuppressWarnings( "unchecked" )
    public final void testSomeAsyncTask () throws Throwable {

        runTestOnUiThread( new Runnable() {

            @Override
            public void run () {
                new EndpointsAsyncTask() {
                    @Override
                    protected void onPostExecute (Joke result) {
                        Log.d( "testEndpointsAsyncTask", "running onPostExecute" );
                        if (result != null) {
                            noResult = false;

                            setup = result.getSetup();
                            punchline = result.getPunchLine();

                            Log.d( "testEndpointsAsyncTask", setup );
                            Log.d( "testEndpointsAsyncTask", punchline );
                        } else {
                            noResult = true;
                            Log.d( "testEndpointsAsyncTask", "result == null" );
                        }

                        called = true;
                        signal.countDown();
                    }
                }.execute( new Pair<Context, Integer>( context, index ) );
            }
        } );

    /* The testing thread will wait here until the UI thread releases it
     * above with the countDown() or 30 seconds passes and it times out.
     */
        signal.await( 30, TimeUnit.SECONDS );

        // The task is done, and now you can assert some things!
        assertTrue( called );
        assertEquals( "Knock, Knock", setup );
        assertEquals( "Nobody's home!", punchline );
        assertFalse( noResult );
    }

}
