package com.example.studentregistration.ui.home;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.studentregistration.ChangeCourse;
import com.example.studentregistration.ExitStudent.ExistingStudentregis;
import com.example.studentregistration.NewStudent.NewStudentRegistration;
import com.example.studentregistration.R;
import com.example.studentregistration.StdCard;
import com.example.studentregistration.login.LoginActivity;
import com.example.studentregistration.login.MainLogin;

public class Home extends Fragment {

    View view;
    Button RegisNew, ExistingRegis, GenerateCard, ChangeCourse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

       RegisNew = view.findViewById(R.id.NewRegis);
       ExistingRegis = view.findViewById(R.id.ExistingStudent);
       GenerateCard = view.findViewById(R.id.CardId);
       ChangeCourse = view.findViewById(R.id.Change);

       RegisNew.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getContext(), NewStudentRegistration.class);
               startActivity(intent);
           }
       });

       ExistingRegis.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getContext(), ExistingStudentregis.class);
               startActivity(intent);
           }
       });

       GenerateCard.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getContext(), StdCard.class);
               startActivity(intent);
           }
       });

       ChangeCourse.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getContext(), ChangeCourse.class);
               startActivity(intent);
           }
       });

        return view;
    }
}