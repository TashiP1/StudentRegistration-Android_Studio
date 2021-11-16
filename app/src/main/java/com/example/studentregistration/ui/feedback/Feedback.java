package com.example.studentregistration.ui.feedback;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentregistration.R;

public class Feedback extends Fragment {

    View view;
    Button submit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_feedback, container, false);

        final EditText edit_name = view.findViewById(R.id.StudentName);
        final EditText edit_Feedback = view.findViewById(R.id.StudentFeedback);
        submit = view.findViewById(R.id.submit);
        ModifyFeedack fdd = new ModifyFeedack();

        submit.setOnClickListener(v->
        {
            FeedbackDetail fd = new FeedbackDetail(edit_name.getText().toString(), edit_Feedback.getText().toString());
            fdd.add(fd).addOnSuccessListener(suc->{
               Toast.makeText(getContext(),"Thank You for Feedback!", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->{
                Toast.makeText(getContext(),""+er.getMessage(),Toast.LENGTH_SHORT).show();
            });
        });

        return view;
    }
}