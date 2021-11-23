package com.example.studentregistration.ui.home;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.studentregistration.Website;
import com.example.studentregistration.R;
import com.example.studentregistration.login.LoginActivity;
import com.example.studentregistration.login.MainLogin;

public class Home extends Fragment {

    View view;
    Button RegisNew, ExistingRegis, CSTWebsite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

       RegisNew = view.findViewById(R.id.NewRegis);
       ExistingRegis = view.findViewById(R.id.ExistingStudent);
       CSTWebsite = view.findViewById(R.id.cstwebsite);

       RegisNew.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getContext(), LoginActivity.class);
               startActivity(intent);
           }
       });

       ExistingRegis.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getContext(), MainLogin.class);
               startActivity(intent);
           }
       });

       CSTWebsite.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getContext(), Website.class);
               startActivity(intent);
           }
       });


        return view;
    }
}