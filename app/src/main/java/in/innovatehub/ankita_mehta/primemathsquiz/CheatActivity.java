package in.innovatehub.ankita_mehta.primemathsquiz;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private final String TAG = "Inside Cheat Activity";
    public final static String EXTRA_MESSAGE = "primemathsquiz.CheatActivity.Number";
    public final static String IS_CHEATED = "primemathsquiz.CheatActivity.IsCheated";
    private boolean isCheated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        Log.d(TAG,"Inside OnCreate");
        String s = getIntent().getStringExtra(EXTRA_MESSAGE);
        Log.d(TAG, "Recieved:"+s);
        if(Integer.parseInt(s)>=0) {
            isCheated = true;
        }
        setAnswerResult(isCheated);
        /*
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setTextSize(40);

        boolean val = (new QuestionBank()).isPrime(message);
        textView.setTextColor(Color.RED);
        textView.setText("Ah! could not solve "+message+": Click "+val);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_cheat);
        layout.addView(textView);
        */
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

    public static Intent newIntent(Context context, String mNum){
        Intent intent = new Intent(context, CheatActivity.class);
        intent.putExtra(EXTRA_MESSAGE, mNum);
        return intent;
    }

    public static boolean wasCheatShown(Intent i){
        return i.getBooleanExtra(IS_CHEATED, false);
    }

    public void sentCheat(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, MainActivity.class);
        TextView numToSet = (TextView) findViewById(R.id.numberToSet);
        String message = numToSet.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
