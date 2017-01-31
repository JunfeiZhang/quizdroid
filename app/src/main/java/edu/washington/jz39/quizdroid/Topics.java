package edu.washington.jz39.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import static android.util.Log.i;

public class Topics extends Activity {
    Button beginMath;
    Button beginPhysics;
    Button beginMarvel;

    Button next;
    Button finish;

    RadioButton option1;
    RadioButton option2;
    RadioButton option3;
    RadioButton option4;

    Button submit;
    TextView yourAnswer;
    TextView correctAnswer;
    ArrayList<Question> questions;

    String topic;

    int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent launchingIntent = getIntent();
        Bundle b = launchingIntent.getExtras();
        topic = b.getString("topic");

        // show the appropriate topic overview page depending on what was clicked in MainActivity
        if (topic.equals("Math")) {
            setContentView(R.layout.topic_math);
            questions = getMathQuestions();
        } else if (topic.equals("Physics")) {
            setContentView(R.layout.topic_physics);
            questions = getPhysicsQuestions();
        } else {
            setContentView(R.layout.topic_marvel);
            questions = getMarvelQuestions();
        }


        beginMath = (Button) findViewById(R.id.math_begin);
        if (beginMath != null) {
            beginMath.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                i("TopicActivity", "begin math button clicked");
                    Intent startMath = new Intent(Topics.this, Quizs.class);
                    startMath.putExtra("topic", topic);
                    startActivity(startMath);
                    finish();
                }
            });
        }


        beginPhysics = (Button) findViewById(R.id.physics_begin);
        if (beginPhysics != null) {
            beginPhysics.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i("TopicActivity", "begin physics button clicked");
                    Intent startPhysics = new Intent(Topics.this, Quizs.class);
                    startPhysics.putExtra("topic", topic);
                    startActivity(startPhysics);
                    finish();
                }
            });
        }

        beginMarvel = (Button) findViewById(R.id.marvel_begin);
        if (beginMarvel != null) {
            beginMarvel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i("TopicActivity", "begin marvel button clicked");
                    Intent startMarvel = new Intent(Topics.this, Quizs.class);
                    startMarvel.putExtra("topic", topic);
                    startActivity(startMarvel);
                    finish();
                }
            });
        }

        submit = (Button) findViewById(R.id.submit);
        yourAnswer = (TextView) findViewById(R.id.your_answer_text);
        correctAnswer = (TextView) findViewById(R.id.correct_answer_text);
        next = (Button) findViewById(R.id.next);
        finish = (Button) findViewById(R.id.finish);
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
