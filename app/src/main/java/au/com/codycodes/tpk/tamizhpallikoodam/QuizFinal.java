package au.com.codycodes.tpk.tamizhpallikoodam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizFinal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_quiz_final);
        int score = QuestionActivity.getScore();
        Quiz quiz = new Quiz();
        if(score > quiz.getHighscore()){


        }
        Button btnExit = findViewById(R.id.btnExit);
        TextView txtFinalScore = findViewById(R.id.txtFinalScore);
        txtFinalScore.setText(getString(R.string.finalscore) + " " + String.valueOf(score));

        Intent getData = new Intent(QuizFinal.this, GetData.class);
        startActivity(getData);
        QuestionActivity.resetQuestionNo();
        QuestionActivity.resetScore();

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exitIntent = new Intent(QuizFinal.this, HomeActivity.class);
                startActivity(exitIntent);
            }
        });


    }
}
