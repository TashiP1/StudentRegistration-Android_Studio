package com.example.studentregistration.ExitStudent;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.studentregistration.NewStudent.RegistrationDetails;
import com.example.studentregistration.R;
import com.example.studentregistration.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ExitRegistration extends Fragment implements AdapterView.OnItemSelectedListener{

    View view;
    EditText regName, regEmail, regPhoneNo, regYear, DOB, ParentName, PNumber, EmailParent, CurrentAddress, cid, Mrepeat;
    Button regBtn;
    Spinner Dept, YearStudy, Semester;
    String Deptitem, yearitem, semitem;
    RadioButton male, female, Spring, Autumn;
    FirebaseDatabase database;
    DatabaseReference reference;
    ExistRegistrationDetails existRegistrationDetails;
    int i =0;

    private Button mLogOutBtn;
    private FirebaseAuth mAuth;

    String[] department = {"Choose Department", "BE IT", "BE Civil", "BE Geology", "BE ECE", "BE IC", "Architecture"};
    String[] yearofstudy = {"Choose Year", "1st year", "2nd year", "3rd year", "4th year", "5th year"};
    String[] semester = {"Choose Semester", "1st Sem", "2nd Sem", "3rd Sem", "4th Sem", "5th Sem", "6th Sem", "7th Sem", "8th Sem", "9th Sem, 10th Sem"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.exit_registration, container, false);

        regName = view.findViewById(R.id.reg_name);
        regEmail = view.findViewById(R.id.email);
        regPhoneNo = view.findViewById(R.id.reg_phoneNo);
        regYear = view.findViewById(R.id.year);
        DOB = view.findViewById(R.id.dob);
        ParentName = view.findViewById(R.id.pname);
        PNumber = view.findViewById(R.id.mobile_parents);
        EmailParent = view.findViewById(R.id.email_parent);
        CurrentAddress = view.findViewById(R.id.address_parent);
        cid = view.findViewById(R.id.cid);
        Mrepeat = view.findViewById(R.id.repeat);

        male = view.findViewById(R.id.male);
        female = view.findViewById(R.id.female);
        Spring = view.findViewById(R.id.spring);
        Autumn = view.findViewById(R.id.autumn);

        Dept = view.findViewById(R.id.programme);
        YearStudy = view.findViewById(R.id.yearOfStudy);
        Semester = view.findViewById(R.id.SE);

        existRegistrationDetails = new ExistRegistrationDetails();

        mAuth = FirebaseAuth.getInstance();
        mLogOutBtn = view.findViewById(R.id.log_out_btn);
        regBtn = view.findViewById(R.id.reg_btn);

        mLogOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(getContext() , LoginActivity.class));
                getActivity().finish();
            }
        });


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

        Dept.setOnItemSelectedListener(this);
        YearStudy.setOnItemSelectedListener(this);
        Semester.setOnItemSelectedListener(this);

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, department);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Dept.setAdapter(arrayAdapter);

        ArrayAdapter yearAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item,yearofstudy );
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        YearStudy.setAdapter(yearAdapter);

        ArrayAdapter SemesterAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, semester);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Semester.setAdapter(SemesterAdapter);


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m1 = male.getText().toString();
                String m2 = female.getText().toString();
                String S = Spring.getText().toString();
                String A = Autumn.getText().toString();

                existRegistrationDetails.setName(regName.getText().toString());
                existRegistrationDetails.setEmail(regEmail.getText().toString());
                existRegistrationDetails.setPhone(regPhoneNo.getText().toString());
                existRegistrationDetails.setRepeatModule(Mrepeat.getText().toString());

                existRegistrationDetails.setYear(regYear.getText().toString());
                existRegistrationDetails.setDOB(DOB.getText().toString());
                existRegistrationDetails.setParentName(ParentName.getText().toString());
                existRegistrationDetails.setParentNumber(PNumber.getText().toString());
                existRegistrationDetails.setEmailParent(EmailParent.getText().toString());
                existRegistrationDetails.setParentCurrentAddress(CurrentAddress.getText().toString());
                existRegistrationDetails.setCid(cid.getText().toString());

                SaveValueDept(Deptitem);
                SaveValueYear(yearitem);
                SaveValueSem(semitem);

                reference.child(String.valueOf(i+1)).setValue(existRegistrationDetails);

                if (Spring.isChecked()){
                    existRegistrationDetails.setAcademicSemister(S);
                    reference.child(String.valueOf(i+1)).setValue(existRegistrationDetails);
                }
                else{
                    existRegistrationDetails.setAcademicSemister(A);
                    reference.child(String.valueOf(i+1)).setValue(existRegistrationDetails);
                }
                Toast.makeText(getContext(),"Uploaded Successfuly", Toast.LENGTH_SHORT).show();

                if (male.isChecked()){
                    existRegistrationDetails.setGender(m1);
                    reference.child(String.valueOf(i+1)).setValue(existRegistrationDetails);
                }
                else{
                    existRegistrationDetails.setGender(m2);
                    reference.child(String.valueOf(i+1)).setValue(existRegistrationDetails);
                }
                Toast.makeText(getContext(),"Uploaded Successfuly", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Deptitem = Dept.getSelectedItem().toString();
        yearitem = YearStudy.getSelectedItem().toString();
        semitem = Semester.getSelectedItem().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //
    }

    void SaveValueDept(String item){
        if (item == "Choose Department"){
            Toast.makeText(getContext(),"Select Department",Toast.LENGTH_SHORT).show();
        }
        else{
            existRegistrationDetails.setDepartment(item);
            reference.child(String.valueOf(i+1)).setValue(existRegistrationDetails);
        }
    }

    void SaveValueYear(String item){
        if (item == "Choose Year"){
            Toast.makeText(getContext(),"Select year",Toast.LENGTH_SHORT).show();
        }
        else{
            existRegistrationDetails.setYear(item);
            reference.child(String.valueOf(i+1)).setValue(existRegistrationDetails);
        }
    }

    void SaveValueSem(String item){
        if (item == "Choose Semester"){
            Toast.makeText(getContext(),"Select Semester",Toast.LENGTH_SHORT).show();
        }
        else{
            existRegistrationDetails.setSemester(item);
            reference.child(String.valueOf(i+1)).setValue(existRegistrationDetails);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null){
            startActivity(new Intent(getContext() , LoginActivity.class));
            getActivity().finish();
        }
    }
}