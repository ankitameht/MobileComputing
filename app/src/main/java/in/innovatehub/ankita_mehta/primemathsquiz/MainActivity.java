package in.innovatehub.ankita_mehta.primemathsquiz;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    /*Member variable specific to UI components*/
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mTV;

    /*Other Variables*/
    public String mNum;
    /* static text variables*/
    private static final String TAG = "Math_Quiz";
    static final String STATE_NUM = "Random_Number";

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    /* This sets the random number on screen using text view */
    public void setTheView(String randn) {
        Log.d(TAG, "Inside settheView");
        mTV = (TextView) findViewById(R.id.numberToSet);
        mTV.setText(randn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        Log.d(TAG, "Inside onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
            // Restore value of members from saved state
            //mCurrentScore = savedInstanceState.getInt(STATE_SCORE);
            mNum = savedInstanceState.getString(STATE_NUM);
            setTheView(mNum);
        } else {
            // Probably initialize members with default values for a new instance
            //Set number as new question
            mNum = (new QuestionBank()).numberToSet();
            setTheView(mNum);
        }

        //Button functionalities
        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((new QuestionBank().isPrime(mNum))) {
                    Toast toast = Toast.makeText(MainActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Toast toast = Toast.makeText(MainActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((new QuestionBank().isPrime(mNum))) {
                    Toast toast = Toast.makeText(MainActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Toast toast = Toast.makeText(MainActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mNum = (new QuestionBank()).numberToSet();
                setTheView(mNum);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        //savedInstanceState.putInt(STATE_SCORE, mCurrentScore);
        savedInstanceState.putString(STATE_NUM, mNum);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        //savedInstanceState.putInt(STATE_SCORE, mCurrentScore);
        savedInstanceState.putString(STATE_NUM, mNum);
    }
}
