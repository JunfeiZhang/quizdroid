package edu.washington.jz39.quizdroid;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

public class Quizs extends Activity {

    String topic;

    private FragmentTransaction ft = getFragmentManager().beginTransaction();
    private Fragment display = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizs);
        Intent launchingIntent = getIntent();
        topic = launchingIntent.getStringExtra("topic");

        display = new DescFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TOPIC", topic);
        display.setArguments(bundle);
        ft.replace(R.id.fragment_placeholder, display);
        ft.commit();
    }
}
