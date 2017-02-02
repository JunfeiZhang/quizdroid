package edu.washington.jz39.quizdroid;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DescFragment extends Fragment {

    private Button beginBtn;
    private TextView beginDesc;
    private String topic;
    private String desc;
    private Questions qs;
    private Fragment display = null;



    public DescFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_desc, container, false);

        topic = getArguments().getString("TOPIC");
        desc = getArguments().getString("DESC");
        qs = new Questions(topic);

        beginDesc = (TextView) view.findViewById(R.id.beginDesc);
        beginDesc.setText(desc);

        beginBtn = (Button) view.findViewById(R.id.beginBtn);
        beginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment next = new QuestionFragment();

                FragmentManager fm = getActivity().getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("TOPIC", topic);
                bundle.putSerializable("QUESTIONS", qs);
                next.setArguments(bundle);
                ft.replace(R.id.fragment_placeholder, next);
                ft.addToBackStack(null);
                ft.commit();
            }
        });


        return view;
    }
}
