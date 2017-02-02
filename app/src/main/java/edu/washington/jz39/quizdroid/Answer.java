package edu.washington.jz39.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Answer extends Activity {

    RadioGroup radioGroup;
    RadioButton option1;
    RadioButton option2;
    RadioButton option3;
    RadioButton option4;
    RadioButton radioButton;
    Button next;
    Button finish;
    Button submit;

    ArrayList<Question> questions;
    int pos;
    String topic;
    int total_questions;
    int total_correct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Bundle b = this.getIntent().getExtras();
        pos = b.getInt("pos");
        int size = b.getInt("size");
        String theirAnswer = b.getString("answer");
        String correct = b.getString("correct");
        topic = b.getString("topic");
        boolean hasMoreQuestions = b.getBoolean("end");
        total_questions = b.getInt("total questions");
        total_correct = b.getInt("total correct");

        if (topic.equals("Math")) {
            questions = Question.getMathQuestions();
        } else if (topic.equals("Physics")) {
            questions = Question.getPhysicsQuestions();
        } else {
            questions = Question.getMarvelQuestions();
        }

        setContentView(R.layout.activity_answer);

        TextView yourAnswer = (TextView) findViewById(R.id.your_answer_text);
        TextView correctAnswer = (TextView) findViewById(R.id.correct_answer_text);
//        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        next = (Button) findViewById(R.id.next);
        finish = (Button) findViewById(R.id.finish);
//        int selected_id = radioGroup.getCheckedRadioButtonId();
//        RadioButton answer = (RadioButton) findViewById(selected_id);

        option1 = (RadioButton) findViewById(R.id.radioButton1);
        option2 = (RadioButton) findViewById(R.id.radioButton2);
        option3 = (RadioButton) findViewById(R.id.radioButton3);
        option4 = (RadioButton) findViewById(R.id.radioButton4);


        yourAnswer.setText(theirAnswer);
        correctAnswer.setText(correct);
        TextView total = (TextView) findViewById(R.id.total_correct_text);
        total.setText("You have " + total_correct + " out of " + total_questions + " correct");
        if (hasMoreQuestions) {
            next.setVisibility(View.VISIBLE);
            finish.setVisibility(View.GONE);
        } else {
            next.setVisibility(View.GONE);
            finish.setVisibility(View.VISIBLE);
        }

        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent showAnswer = new Intent(Answer.this, Quizs.class);
                showAnswer.putExtra("pos", pos + 1);
                showAnswer.putExtra("size", questions.size());
                showAnswer.putExtra("correct", questions.get(pos).getAnswer());
                showAnswer.putExtra("questions", questions);
                showAnswer.putExtra("topic", topic);
                showAnswer.putExtra("total correct", total_correct);
                showAnswer.putExtra("total questions", total_questions);
                startActivity(showAnswer);
                finish();
            }
        });

        finish = (Button) findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startOver = new Intent(Answer.this, MainActivity.class);
                startOver.putExtra("pos", 0);
                startActivity(startOver);
                finish();
            }
        });

    }

}
