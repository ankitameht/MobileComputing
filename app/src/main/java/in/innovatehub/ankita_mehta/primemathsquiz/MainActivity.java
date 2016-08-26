package in.innovatehub.ankita_mehta.primemathsquiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    /*Member variable specific to UI components*/
    private ImageButton mTrueButton;
    private ImageButton mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mHintButton;
    private ImageButton mCheatButton;
    private TextView mTV;
    private TextView mTimer;
    private TextView mLevel;

    /*Other Variables*/
    private String mNum;
    private static Integer mScore = 0;
    private static boolean mTimerOver;
    /* static text variables*/
    private static final String TAG = "Math_Quiz";
    private static final String STATE_NUM = "Random_Number";
    private static final String STATE_SCORE = "Score";
    public final static String EXTRA_MESSAGE = "primemathsquiz.MainActivity.MESSAGE";
    private static final int REQUEST_CODE_CHEAT = 1;
    private boolean mRecievedCheat;

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
                mTimer.setText("  Time Left!! " + millisUntilFinished / 1000);
                mTimerOver = false;
            }

            public void onFinish() {
                mTimer.setText("Times Up :(");
                mTrueButton.setEnabled(false);
                mFalseButton.setEnabled(false);
                mTimerOver = true;
            }
        };
        c.start();
        return c;
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "Inside On stop");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "Inside On Resume");
        Log.d(TAG, "Did user cheat?"+mRecievedCheat);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "Inside On Destroy");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "Inside On Pause");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        Log.d(TAG, "Inside onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final CountDownTimer[] c = new CountDownTimer[1];
        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
            mNum = savedInstanceState.getString(STATE_NUM);
            mScore = savedInstanceState.getInt(STATE_SCORE);
            setTheView(mNum);
            setTheLevel(mScore);
            c[0] = setTheTimer();

        } else {
            //Set number as new question
            mNum = (new QuestionBank()).numberToSet();
            mScore = 0;
            setTheView(mNum);
            setTheLevel(mScore);
            c[0] = setTheTimer();
        }


        //Button Functionality
        mTrueButton = (ImageButton) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTimerOver){
                    Toast toast = Toast.makeText(MainActivity.this, R.string.time_finish, Toast.LENGTH_SHORT);
                    toast.show();
                }else if(mRecievedCheat){
                    Toast toast = Toast.makeText(MainActivity.this, R.string.cheat_result, Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    if ((new QuestionBank().isPrime(mNum))) {
                        Toast toast = Toast.makeText(MainActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT);
                        toast.show();
                        mScore++;
                    } else {
                        Toast toast = Toast.makeText(MainActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
                setTheLevel(mScore);
                mTrueButton.setEnabled(false);
                mFalseButton.setEnabled(false);
            }
        });

        mFalseButton = (ImageButton) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTimerOver){
                    Toast toast = Toast.makeText(MainActivity.this, R.string.time_finish, Toast.LENGTH_SHORT);
                    toast.show();
                }else if(mRecievedCheat){
                    Toast toast = Toast.makeText(MainActivity.this, R.string.cheat_result, Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    if ((new QuestionBank().isPrime(mNum))) {

                        Toast toast = Toast.makeText(MainActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(MainActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT);
                        toast.show();
                        mScore++;
                    }
                }
                setTheLevel(mScore);
                mTrueButton.setEnabled(false);
                mFalseButton.setEnabled(false);
            }
        });

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mNum = (new QuestionBank()).numberToSet();
                setTheView(mNum);
                c[0].cancel();
                c[0] = setTheTimer();
                mRecievedCheat = false;
                mTrueButton.setEnabled(true);
                mFalseButton.setEnabled(true);
            }
        });

        mCheatButton = (ImageButton)findViewById(R.id.cheatButton);
        mCheatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d(TAG, "Cheat Pressed");
                boolean b =  (new QuestionBank().isPrime(mNum));
                Intent i = CheatActivity.newIntent(MainActivity.this, b);
                startActivityForResult(i,REQUEST_CODE_CHEAT);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_CODE_CHEAT){
            if(data ==  null){
                return;
            }
            mRecievedCheat = CheatActivity.wasCheatShown(data);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putString(STATE_NUM, mNum);
        savedInstanceState.putInt(STATE_SCORE, mScore);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore state members from saved instance
        savedInstanceState.putInt(STATE_SCORE, mScore);
        savedInstanceState.putString(STATE_NUM, mNum);
    }

    /** Called when the user clicks the Hint button */
    public void giveHint(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, HintActivity.class);
        TextView numToSet = (TextView) findViewById(R.id.numberToSet);
        String message = numToSet.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

}
