package in.innovatehub.ankita_mehta.primemathsquiz;


import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class HintActivity extends AppCompatActivity {

    private static final String STATE_TOOK_HINT = "primemathsquiz.CheatActivity.hintState";
    private static final String STATE_MNUM = "primemathsquiz.CheatActivity.hintMNum";
    private static final String STATE_PRIMEFACT = "primemathsquiz.CheatActivity.primeFactors";
    private final ArrayList<Integer> primeFact = new ArrayList<>();
    private final String TAG = "Inside Hint Activity";

    private TextView mHintAnswerTV;
    private Button mShowHintButton;

    private boolean isTookHint = false;
    private String s = "";
    private String message = "";

    // A function to print all prime factors of a given number n
    void primeFactors(int n)
    {
        while (n%2 == 0)
        {
            primeFact.add(2);
            n = n/2;
        }
        for (int i = 3; i <= Math.sqrt(n); i = i+2)
        {
            while (n%i == 0)
            {
                primeFact.add(i);
                n = n/i;
            }
        }
        if (n > 2)
            primeFact.add(n);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        mHintAnswerTV = (TextView) findViewById(R.id.hint_answer_tv);
        mShowHintButton = (Button) findViewById(R.id.show_hint);

        if (savedInstanceState != null) {
            isTookHint = savedInstanceState.getBoolean(STATE_TOOK_HINT);
            s = savedInstanceState.getString(STATE_PRIMEFACT);
            message = savedInstanceState.getString(STATE_MNUM);
            // retain this fragment
            //setRetainInstance(true);
        } else {
            //Set number as new question
            isTookHint = false;
            s = "";
            message = "";
        }

        Intent intent = getIntent();
        message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        mShowHintButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d(TAG, "Hint Pressed");
                if(isTookHint) {
                    mHintAnswerTV.setTextSize(40);
                    //mHintAnswerTV.setTextColor(Color.BLUE);
                    mHintAnswerTV.setText("\n\nThe prime factors for " + message + " are: " + s + "1");
                    mHintAnswerTV.setFreezesText(true);
                    //layout.addView(textView);
                }else{
                    mHintAnswerTV.setTextSize(40);
                    Integer num = Integer.parseInt(message);
                    primeFactors(num);
                    for (int i = 0; i < primeFact.size(); i++) {
                        s += String.valueOf(primeFact.get(i)) + "X";
                    }
                    //mHintAnswerTV.setTextColor(Color.BLUE);
                    mHintAnswerTV.setText("\n\nThe prime factors for " + message + " are: " + s + "1");
                    mHintAnswerTV.setFreezesText(true);
                }
            }
        });
        //layout.addView(textView);

        Toast toast = Toast.makeText(HintActivity.this, R.string.hint_toast, Toast.LENGTH_SHORT);
        toast.show();

        //getActionBar().setDisplayHomeAsUpEnabled(true);
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

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putBoolean(STATE_TOOK_HINT, isTookHint);
        savedInstanceState.putString(STATE_MNUM, message);
        savedInstanceState.putString(STATE_PRIMEFACT,s);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore state members from saved instance
        savedInstanceState.putBoolean(STATE_TOOK_HINT, isTookHint);
        //savedInstanceState.putString(STATE_NUM, mNum);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
