package impactdevs.net.jokeactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class JokeActivity
        extends AppCompatActivity {

    private String      setup;
    private String      punchline;
    private ViewFlipper mViewFlipper;
    private TextView setupView;
    private TextView punchlineView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_joke );

        mViewFlipper = (ViewFlipper) findViewById( R.id.viewFlipper );

        setup = getIntent().getStringExtra( "setup" );
        punchline = getIntent().getStringExtra( "punchline" );
        //todo:
        Log.d( "JokeActivity", "onCreate (line 25): " + setup);
        Log.d("JokeActivity", "onCreate (line 28): " + punchline);

        setupView     = (TextView) findViewById( R.id.textview_joke_setup );
        punchlineView = (TextView) findViewById( R.id.textview_joke_punchline );
        setupView.setText( setup );

    }


    public void revealPunchline (View view) {

        if (setupView.getVisibility() == View.VISIBLE) {
            mViewFlipper.showNext();
            punchlineView.setText( punchline );
        } else if (punchlineView.getVisibility() == View.VISIBLE) {
            mViewFlipper.showPrevious();
            setupView.setText( setup );
        }
    }
}
