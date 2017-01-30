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

public class Quizs extends Activity {


    RadioButton option1;
    RadioButton option2;
    RadioButton option3;
    RadioButton option4;
    RadioButton radioButton;
    Button submit;
    TextView yourAnswer;
    TextView correctAnswer;
    ArrayList<Question> questions;
    int pos = 0;
    String selection;
    String topic;
    int total_questions;
    int total_correct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent launchingIntent = getIntent();
        topic = launchingIntent.getStringExtra("topic");
        Bundle b = launchingIntent.getExtras();
        pos = b.getInt("pos");
        total_questions = b.getInt("total questions");
        total_correct = b.getInt("total correct");


        if (topic.equals("Math")) {
            questions = getMathQuestions();
        } else if (topic.equals("Physics")) {
            questions = getPhysicsQuestions();
        } else {
            questions = getMarvelQuestions();
        }
        setContentView(R.layout.activity_question);

        nextQuestion(pos);
        yourAnswer = (TextView) findViewById(R.id.your_answer_text);
        correctAnswer = (TextView) findViewById(R.id.correct_answer_text);

        submit = (Button) findViewById(R.id.submit_btn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showAnswer = new Intent(Quizs.this, Answer.class);
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
                int radioGroupID = radioGroup.getCheckedRadioButtonId();
                if (radioGroupID != -1) {
                    radioButton = (RadioButton) findViewById(radioGroupID);
                    selection = radioButton.getText().toString();
                    showAnswer.putExtra("pos", pos);
                    showAnswer.putExtra("size", questions.size());
                    showAnswer.putExtra("answer", selection);
                    showAnswer.putExtra("correct", questions.get(pos).getAnswer());
                    showAnswer.putExtra("questions", questions);
                    showAnswer.putExtra("topic", topic);
                    if (selection.equals(questions.get(pos).getAnswer())) {
                        total_correct++;
                    }
                    total_questions++;
                    showAnswer.putExtra("total questions", total_questions);
                    showAnswer.putExtra("total correct", total_correct);
                    boolean hasMoreQuestions = true;
                    if (pos + 1 >= questions.size()) {
                        hasMoreQuestions = false;
                    }
                    showAnswer.putExtra("end", hasMoreQuestions);
                    startActivity(showAnswer);
                    finish();
                }
            }
        });
    }

    public void nextQuestion(int pos) {
        TextView question = (TextView) findViewById(R.id.question_title_text_view);
        question.setText("" + questions.get(pos).getQuestion());
        option1 = (RadioButton) findViewById(R.id.radioButton1);
        option1.setText("" + questions.get(pos).getOption1());
        option2 = (RadioButton) findViewById(R.id.radioButton2);
        option2.setText("" + questions.get(pos).getOption2());
        option3 = (RadioButton) findViewById(R.id.radioButton3);
        option3.setText("" + questions.get(pos).getOption3());
        option4 = (RadioButton) findViewById(R.id.radioButton4);
        option4.setText("" + questions.get(pos).getOption4());

    }

    public ArrayList<Question> getMathQuestions() {
        ArrayList<Question> questions = new ArrayList<Question>();
        questions.add(new Question("answer 1", "1", "2", "3", "4", "1"));
        questions.add(new Question("answer 1", "1", "2", "3", "4", "1"));
        questions.add(new Question("answer 1", "1", "2", "3", "4", "1"));
        return questions;
    }

    public ArrayList<Question> getPhysicsQuestions() {
        ArrayList<Question> questions = new ArrayList<Question>();
        questions.add(new Question("answer 1", "1", "2", "3", "4", "1"));
        questions.add(new Question("answer 1", "1", "2", "3", "4", "1"));
        questions.add(new Question("answer 1", "1", "2", "3", "4", "1"));
        return questions;
    }

    public ArrayList<Question> getMarvelQuestions() {
        ArrayList<Question> questions = new ArrayList<Question>();
        questions.add(new Question("answer 1", "1", "2", "3", "4", "1"));
        questions.add(new Question("answer 1", "1", "2", "3", "4", "1"));
        questions.add(new Question("answer 1", "1", "2", "3", "4", "1"));
        return questions;
    }

}
