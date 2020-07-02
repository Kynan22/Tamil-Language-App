package au.com.codycodes.tpk.tamizhpallikoodam;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ResultSplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result_splash);
        int score = QuestionActivity.getScore();
        Intent myIntent = getIntent();
        String correct = myIntent.getStringExtra("correct");
        TextView txtResponse = findViewById(R.id.txtResponse);
        TextView txtScore = findViewById(R.id.txtScore);
        txtResponse.setText(correct);
        txtScore.setText("Score: " + String.valueOf(score));

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(ResultSplashActivity.this, QuestionActivity.class);
                ResultSplashActivity.this.startActivity(mainIntent);
                ResultSplashActivity.this.finish();

            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}
