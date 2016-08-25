package in.innovatehub.ankita_mehta.primemathsquiz;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "primemathsquiz.CheatActivity.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setTextSize(40);

        boolean val = (new QuestionBank()).isPrime(message);
        textView.setTextColor(Color.RED);
        textView.setText("Ah! could not solve "+message+": Click "+val);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_cheat);
        layout.addView(textView);
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
