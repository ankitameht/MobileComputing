package in.innovatehub.ankita_mehta.primemathsquiz;

import android.content.Context;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String STATE_CHEATED = "primemathsquiz.CheatActivity.cheatState";
    private final String TAG = "Inside Cheat Activity";
    private final static String EXTRA_MESSAGE = "primemathsquiz.CheatActivity.Number";
    public final static String IS_CHEATED = "primemathsquiz.CheatActivity.IsCheated";
    private final static String ANSWER_IS_TRUE = "primemathsquiz.CheatActivity.Answer";
    private boolean isCheated = false;

    private TextView mCheatAnswerTV;
    private Button mShowCheatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        if (savedInstanceState != null) {
            isCheated = savedInstanceState.getBoolean(STATE_CHEATED);
        } else {
            //Set number as new question
            isCheated = false;
        }

        Log.d(TAG,"Inside OnCreate");
        isCheated = getIntent().getBooleanExtra(ANSWER_IS_TRUE,false);
        Log.d(TAG, "Received: "+isCheated);

        mCheatAnswerTV = (TextView) findViewById(R.id.cheat_answer_tv);
        mShowCheatButton = (Button) findViewById(R.id.show_cheat);

        mShowCheatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(isCheated){
                    mCheatAnswerTV.setText(R.string.true_button);
                    mCheatAnswerTV.setFreezesText(true);
                }else{
                    mCheatAnswerTV.setText(R.string.false_button);
                    mCheatAnswerTV.setFreezesText(true);
                }
                setAnswerResult(true);
            }
        });
    }

    private void setAnswerResult(boolean b){
        Intent intent = new Intent();
        intent.putExtra(IS_CHEATED,b);
        setResult(RESULT_OK, intent);
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

    public static Intent newIntent(Context context, boolean b){
        Intent intent = new Intent(context, CheatActivity.class);
        intent.putExtra(ANSWER_IS_TRUE, b);
        return intent;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putBoolean(STATE_CHEATED, isCheated);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore state members from saved instance
        savedInstanceState.putBoolean(STATE_CHEATED, isCheated);
        //savedInstanceState.putString(STATE_NUM, mNum);
    }

    public static boolean wasCheatShown(Intent i){
        return i.getBooleanExtra(IS_CHEATED, false);
    }

}
