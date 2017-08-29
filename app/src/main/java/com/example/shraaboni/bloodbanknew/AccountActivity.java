package com.example.shraaboni.bloodbanknew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity {

    TextView nameTV;
    TextView bloodgrpTV;
    TextView genderTV;
    String name;
    String bloodgrp;
    String gender;
    String email;
    String key;
    DatabaseReference dataref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Intent intent = getIntent();
        key= intent.getStringExtra(key);
        email = intent.getStringExtra("email");
        name = intent.getStringExtra("name");
        bloodgrp = intent.getStringExtra("bloodgrp");
        gender = intent.getStringExtra("gender");
        nameTV = (TextView) findViewById(R.id.name);
        bloodgrpTV = (TextView) findViewById(R.id.bloodgrp);
        genderTV = (TextView) findViewById(R.id.gender);
        nameTV.setText(name);
        bloodgrpTV.setText(bloodgrp);
        genderTV.setText(gender);

        /*dataref = FirebaseDatabase.getInstance().getReference("user");
        dataref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    //infolist= new ArrayList<String>();
                    Person person = snapshot.getValue(Person.class);
                    name=person.getName();
                    bloodgrp=person.getBloodtype();
                    gender=person.getGender();

                }
                nameTV.setText(name);
                bloodgrpTV.setText(bloodgrp);
                genderTV.setText(gender);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }

    public void gotoProfile(View view) {

        startActivity(new Intent(AccountActivity.this, ProfileActivity.class).putExtra("email", email));
    }

    public void gotoAboutUs(View view) {
        startActivity(new Intent(AccountActivity.this, AboutUs.class).putExtra("email", email));

    }

    public void goToPost(View view) {
        startActivity(new Intent(AccountActivity.this, RequestActivity.class).putExtra("key", key));
    }

    public void goToNews(View view) {
        startActivity(new Intent(AccountActivity.this, NewsActivity.class));
    }
}
