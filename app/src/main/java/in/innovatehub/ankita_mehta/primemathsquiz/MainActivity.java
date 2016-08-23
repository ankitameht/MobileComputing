package in.innovatehub.ankita_mehta.primemathsquiz;

import android.graphics.Color;
import android.os.CountDownTimer;
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
    private TextView mTimer;
    private TextView mLevel;

    /*Other Variables*/
    private String mNum;
    private static Integer mScore = 0;
    /* static text variables*/
    private static final String TAG = "Math_Quiz";
    private static final String STATE_NUM = "Random_Number";
    private static final String STATE_SCORE = "Score";

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    /* This sets the random number on screen using text view */
    public void setTheView(String rand_n) {
        Log.d(TAG, "Inside setTheView");
        mTV = (TextView) findViewById(R.id.numberToSet);
        mTV.setText(rand_n);
    }
    public void setTheLevel(Integer level){
        Log.d(TAG, "Inside setTheLevel");
        mLevel = (TextView) findViewById(R.id.level);
        mLevel.setText("You are at Level: "+level.toString());
    }

    public CountDownTimer setTheTimer(){
        Log.d(TAG, "Inside Set the timer");
        mTimer = (TextView) findViewById(R.id.timer);
        CountDownTimer c = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                mTimer.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                mTimer.setText("done!");
            }
        };
        c.start();
        return c;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        Log.d(TAG, "Inside onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final CountDownTimer[] c = new CountDownTimer[1];
        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
            // Restore value of members from saved state
            //mCurrentScore = savedInstanceState.getInt(STATE_SCORE);
            mNum = savedInstanceState.getString(STATE_NUM);
            mScore = savedInstanceState.getInt(STATE_SCORE);
            setTheView(mNum);
            setTheLevel(mScore);
            c[0] = setTheTimer();

        } else {
            // Probably initialize members with default values for a new instance
            //Set number as new question
            mNum = (new QuestionBank()).numberToSet();
            mScore = 0;
            setTheView(mNum);
            setTheLevel(mScore);
            c[0] = setTheTimer();
        }

        //Button Functionality
        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((new QuestionBank().isPrime(mNum))) {
                    Toast toast = Toast.makeText(MainActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT);
                    toast.show();
                    mScore++;
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
                    mScore++;
                }
            }
        });
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mNum = (new QuestionBank()).numberToSet();
                setTheView(mNum);
                setTheLevel(mScore);
                c[0].cancel();
                c[0] = setTheTimer();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        //savedInstanceState.putInt(STATE_SCORE, mCurrentScore);
        savedInstanceState.putString(STATE_NUM, mNum);
        savedInstanceState.putInt(STATE_SCORE, mScore);
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
