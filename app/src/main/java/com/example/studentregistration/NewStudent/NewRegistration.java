package com.example.studentregistration.NewStudent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.studentregistration.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class NewRegistration extends Fragment implements AdapterView.OnItemSelectedListener{

    View view;
    EditText regName, regEmail, regPhoneNo, regPassword;
    CheckBox c1, c2, c3;
    Button regBtn;
    Spinner spinner;
    String item;
    RadioButton male, female;
    FirebaseDatabase database;
    DatabaseReference reference;
    RegistrationDetails registrationDetails;
    int i =0;

    String[] department = {"Choose Department", "IT", "Civel", "Geology", "ECE"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_new_registration, container, false);

        regName = view.findViewById(R.id.reg_name);
        regEmail = view.findViewById(R.id.reg_email);
        regPhoneNo = view.findViewById(R.id.reg_phoneNo);
        regPassword = view.findViewById(R.id.reg_password);
        regBtn = view.findViewById(R.id.reg_btn);
        male = view.findViewById(R.id.radioButton);
        female = view.findViewById(R.id.radioButton2);
        c1 = view.findViewById(R.id.ourcollege);
        c2 = view.findViewById(R.id.gedu);
        c3 = view.findViewById(R.id.sherabtse);
        spinner = view.findViewById(R.id.spinner);
        registrationDetails = new RegistrationDetails();

        String d1 = "Cst";
        String d2 = "Gedu";
        String d3 = "Sherabtse";


        reference = database.getInstance().getReference().child("Registration Detail");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()){
                    i = (int)datasnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        spinner.setOnItemSelectedListener(this);

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, department);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m1 = male.getText().toString();
                String m2 = female.getText().toString();

                registrationDetails.setName(regName.getText().toString());
                registrationDetails.setEmail(regEmail.getText().toString());
                registrationDetails.setPass(regPassword.getText().toString());
                registrationDetails.setPhone(regPhoneNo.getText().toString());
                SaveValue(item);

                reference.child(String.valueOf(i+1)).setValue(registrationDetails);

                if (c1.isChecked()){
                    registrationDetails.setDay(d1);
                    reference.child(String.valueOf(i+1)).setValue(registrationDetails);
                }
                else{
                  //
                }
                if (c2.isChecked()){
                    registrationDetails.setDay(d2);
                    reference.child(String.valueOf(i+1)).setValue(registrationDetails);
                }
                else{
                    //
                }
                if (c3.isChecked()){
                    registrationDetails.setDay(d3);
                    reference.child(String.valueOf(i+1)).setValue(registrationDetails);
                }
                else{
                    //
                }


                if (male.isChecked()){
                    registrationDetails.setGender(m1);
                    reference.child(String.valueOf(i+1)).setValue(registrationDetails);
                }
                else{
                    registrationDetails.setGender(m2);
                    reference.child(String.valueOf(i+1)).setValue(registrationDetails);
                }
                Toast.makeText(getContext(),"Uploaded Successfuly", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = spinner.getSelectedItem().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //
    }

    void SaveValue(String item){
        if (item == "Choose Department"){
            Toast.makeText(getContext(),"Select Department",Toast.LENGTH_SHORT).show();
        }
        else{
            registrationDetails.setDepartment(item);
            reference.child(String.valueOf(i+1)).setValue(registrationDetails);
        }
    }
}



