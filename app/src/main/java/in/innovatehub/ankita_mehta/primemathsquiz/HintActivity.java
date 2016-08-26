package in.innovatehub.ankita_mehta.primemathsquiz;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HintActivity extends AppCompatActivity {

    ArrayList<Integer> primeFact = new ArrayList<Integer>();
    private final String TAG = "Inside Hint Activity";

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

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setTextSize(40);

        Integer num = Integer.parseInt(message);
        primeFactors(num);
        String s = "";
        for (int i =0; i<primeFact.size(); i++){
            s += String.valueOf(primeFact.get(i))+"X";
        }
        textView.setTextColor(Color.BLUE);
        textView.setText("The prime factors for "+message+" are: "+s+"1");

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_hint);
        layout.addView(textView);

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
