package edu.washington.jz39.quizdroid;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {
    private RadioGroup choices;
    private RadioButton choice;
    private TextView questionTextView;
    private Button submitButton;
    private String selectedAnswer;
    private Questions qs;
    public static String answer = "answer";

    public QuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_question, container, false);

        qs = (Questions) getArguments().getSerializable("QUESTIONS");
        Questions.Question q = qs.getQ();
        choice = (RadioButton) view.findViewById(R.id.choice1);
        choice.setText(q.getOption(0));

        submitButton = (Button) view.findViewById(R.id.submitButton);
        submitButton.setVisibility(View.INVISIBLE);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment next = new AnswerFragment();

                Bundle bundle = new Bundle();
                bundle.putString(answer, selectedAnswer);

                next.setArguments(bundle);

                FragmentManager fm = getActivity().getFragmentManager();
                FragmentTransaction tx = fm.beginTransaction();
                tx.replace(R.id.fragment_placeholder, next);
                tx.addToBackStack(null);
                tx.commit();
            }
        });

        choices = (RadioGroup) view.findViewById(R.id.choices);
        choices.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                submitButton.setVisibility(View.VISIBLE);
                // Gets the id of the RadioButton selected
                int id = choices.getCheckedRadioButtonId();
                // Gets the actual radio button that is clicked (so we can grab text)
                RadioButton rb = (RadioButton) view.findViewById(id);
                selectedAnswer = rb.getText().toString();
            }
        });

        return view;
    }

}
