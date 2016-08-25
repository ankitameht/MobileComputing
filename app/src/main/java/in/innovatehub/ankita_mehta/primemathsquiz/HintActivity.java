package in.innovatehub.ankita_mehta.primemathsquiz;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HintActivity extends AppCompatActivity {

    ArrayList<Integer> primeFact = new ArrayList<Integer>();


    // A function to print all prime factors of a given number n
    void primeFactors(int n)
    {
        // Print the number of 2s that divide n
        while (n%2 == 0)
        {
            primeFact.add(2);
            //printf("%d ", 2);
            n = n/2;
        }

        // n must be odd at this point.  So we can skip one element (Note i = i +2)
        for (int i = 3; i <= Math.sqrt(n); i = i+2)
        {
            // While i divides n, print i and divide n
            while (n%i == 0)
            {
                primeFact.add(i);
                //printf("%d ", i);
                n = n/i;
            }
        }

        // This condition is to handle the case whien n is a prime number
        // greater than 2
        if (n > 2)
            primeFact.add(n);
            //printf ("%d ", n);
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

        //getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, MainActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
