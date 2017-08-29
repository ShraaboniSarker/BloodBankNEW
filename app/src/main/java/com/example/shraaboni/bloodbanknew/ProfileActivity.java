package com.example.shraaboni.bloodbanknew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {


    DatabaseReference dataref;
    ArrayList<String> infolist;
    String email;
    String name;
    String bloodgrp;
    String gender;
    EditText nameET ;
    EditText bloodET ;
    EditText bleedingdateTV ;
    EditText phonenoET ;
    EditText addressET ;
    EditText genderET ;
    String key;
   // DatabaseReference childref;
    String address;
    String bleedingdate;
    String phoneno;
    Person person;
    DatabaseReference testDT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        nameET= (EditText) findViewById(R.id.name);
        bloodET= (EditText) findViewById(R.id.bloodgrp);
        genderET= (EditText) findViewById(R.id.gender);
        addressET = (EditText) findViewById(R.id.address);
        bleedingdateTV = (EditText) findViewById(R.id.bleedingdate);
        phonenoET = (EditText) findViewById(R.id.phoneno);
        dataref = FirebaseDatabase.getInstance().getReference("Updated");


       // childref= dataref.child(key);
        testDT=FirebaseDatabase.getInstance().getReference("user");
        final Query query=testDT.orderByChild("email").equalTo(email);
       // Log.d("chck>>","emIL: "+emailET.getText().toString());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Toast.makeText(LoginActivity.this, "called", Toast.LENGTH_SHORT).show();
                //Log.d("chck>>","emIL: "+dataSnapshot);
                if (dataSnapshot.exists()){
                    for (DataSnapshot single: dataSnapshot.getChildren()){
                        Person person=single.getValue(Person.class);
                        name=person.getName();
                        bloodgrp=person.getBloodtype();
                        gender=person.getGender();
//                        address=person.getAddress();
//                        bleedingdate=person.getLastbleedingdate();
//                        phoneno=person.getMobile();
//                        String USERID = person.getUserKey();
//
//                        key=USERID;
//                       // Log.d("tst>>",key);
//                       /* startActivity(new Intent(LoginActivity.this, AccountActivity.class)
//                                .putExtra("name",name)
//                                .putExtra("bloodgrp",bloodgrp)
//                                .putExtra("gender",gender)
//                                .putExtra("key",userId));*/

                    }

                    nameET.setText(name);
                    bloodET.setText(bloodgrp);
                    genderET.setText(gender);
//                    addressET.setText(address);
//                    bleedingdateTV.setText(bleedingdate);
//                    phonenoET.setText(phoneno);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
       // testDT.child(key).setValue(person);
    }

    public void UpdateInfo(View view) {

         String userid=dataref.push().getKey();
        bleedingdate = bleedingdateTV.getText().toString();
        address = addressET.getText().toString();
        phoneno = phonenoET.getText().toString();
        Person person = new Person(name,phoneno,bloodgrp,gender,address,bleedingdate,email,userid);
        dataref.child(userid).setValue(person);
        final Query query=testDT.orderByChild("email").equalTo(email);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot single: dataSnapshot.getChildren()){
                        Person person=single.getValue(Person.class);
                        name=person.getName();
                        bloodgrp=person.getBloodtype();
                        gender=person.getGender();
                        address=person.getAddress();
                        bleedingdate=person.getLastbleedingdate();
                        phoneno=person.getMobile();

                  //      Log.d("address",address);
                        addressET.setText(address);
                        bleedingdateTV.setText(bleedingdate);
                        phonenoET.setText(phoneno);

                    }



                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        }
}
