package com.example.studentregistration.NewStudent;

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

import androidx.fragment.app.Fragment;

import com.example.studentregistration.R;
import com.example.studentregistration.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class NewRegistration extends Fragment implements AdapterView.OnItemSelectedListener{

    View view;
    EditText regyear, regName, Preschool, Index, CID, DOB, regPhone, HouseNo, ThramNo, village, Gewog, Dzongkhag, Fname, fatherd, Mname, motherId, Mobileparents, email_parents, address;
    Button regBtn;
    Spinner Dept, scholarshiptype;
    String Deptitem, Sitem;
    RadioButton male, female;
    FirebaseDatabase database;
    DatabaseReference reference;
    RegistrationDetails RegistrationDetails;

    private Button mLogOutBtn;
    private FirebaseAuth mAuth;

    String[] department = {"Choose Department", "BE IT", "BE Civil", "BE Geology", "BE ECE", "BE IC", "Architecture"};
    String[] scholarship = {"Scholarship Type", "Government Scholarship", "Self-Financed", "Other Scholarship"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_new_registration, container, false);

        regName = view.findViewById(R.id.reg_name);
        regyear = view.findViewById(R.id.year);
        Preschool = view.findViewById(R.id.PreSchool);
        Index = view.findViewById(R.id.index);
        CID = view.findViewById(R.id.cid);
        DOB = view.findViewById(R.id.dob);
        regPhone = view.findViewById(R.id.reg_phoneNo);
        HouseNo = view.findViewById(R.id.HouseNo);
        ThramNo = view.findViewById(R.id.ThramNo);
        village = view.findViewById(R.id.village);
        Gewog = view.findViewById(R.id.Gewog);
        Dzongkhag = view.findViewById(R.id.Dzongkhag);
        Fname = view.findViewById(R.id.FName);
        fatherd = view.findViewById(R.id.FID);
        Mname = view.findViewById(R.id.Mname);
        motherId = view.findViewById(R.id.MID);
        Mobileparents = view.findViewById(R.id.Mparent);
        email_parents = view.findViewById(R.id.email_parent);
        address = view.findViewById(R.id.address_parent);


        male = view.findViewById(R.id.male);
        female = view.findViewById(R.id.female);
        Dept = view.findViewById(R.id.programme);
        scholarshiptype = view.findViewById(R.id.scholarship);

        RegistrationDetails = new RegistrationDetails();

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

        Dept.setOnItemSelectedListener(this);
        scholarshiptype.setOnItemSelectedListener(this);

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, department);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Dept.setAdapter(arrayAdapter);

        ArrayAdapter TypeAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, scholarship);
        TypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        scholarshiptype.setAdapter(TypeAdapter);


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m1 = male.getText().toString();
                String m2 = female.getText().toString();
                String index1 = Index.getText().toString();
                String name = regName.getText().toString();
                String cidCard = CID.getText().toString();

                if ((index1.matches("") || index1.length() != 8) || name.matches("") || cidCard.matches("")){
                    Toast.makeText(getContext(),"Fill in all the details", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                    {
                    RegistrationDetails.setYear(regyear.getText().toString());
                    RegistrationDetails.setName(name);
                    RegistrationDetails.setPreviousSchool(Preschool.getText().toString());
                    RegistrationDetails.setStudentIndex(index1);
                    RegistrationDetails.setCID(cidCard);
                    RegistrationDetails.setDOB(DOB.getText().toString());
                    RegistrationDetails.setPhoneNo(regPhone.getText().toString());
                    RegistrationDetails.setHouseNo(HouseNo.getText().toString());
                    RegistrationDetails.setThramNo(ThramNo.getText().toString());
                    RegistrationDetails.setVillage(village.getText().toString());
                    RegistrationDetails.setDzongkhag(Dzongkhag.getText().toString());
                    RegistrationDetails.setGewog(Gewog.getText().toString());
                    RegistrationDetails.setFatherName(Fname.getText().toString());
                    RegistrationDetails.setFatherCID(fatherd.getText().toString());
                    RegistrationDetails.setMotherName(Mname.getText().toString());
                    RegistrationDetails.setMotherCID(motherId.getText().toString());
                    RegistrationDetails.setParentphoneNo(Mobileparents.getText().toString());
                    RegistrationDetails.setParentEmail(email_parents.getText().toString());
                    RegistrationDetails.setParrentAddress(address.getText().toString());


                    SaveValueDept(Deptitem);
                    SaveValueScholarship(Sitem);
                    reference = database.getInstance().getReference().child("New Student Registration Detail/"+ RegistrationDetails.getProgramme());

                    reference.child(RegistrationDetails.getStudentIndex()).setValue(RegistrationDetails);


                    if (male.isChecked()){
                        RegistrationDetails.setGender(m1);
                        reference.child(RegistrationDetails.getStudentIndex()).setValue(RegistrationDetails);
                    }
                    else{
                        RegistrationDetails.setGender(m2);
                        reference.child(RegistrationDetails.getStudentIndex()).setValue(RegistrationDetails);
                    }
                    Toast.makeText(getContext(),"Uploaded Successfuly", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Deptitem = Dept.getSelectedItem().toString();
        Sitem = scholarshiptype.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //
    }

    void SaveValueDept(String item){
        if (item == "Choose Department"){
            //
        }
        else{
            RegistrationDetails.setProgramme(item);
        }
    }

    void SaveValueScholarship(String item){
        if (item == "Scholarship Type"){
            //
        }
        else{
            RegistrationDetails.setScholarshipType(item);
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