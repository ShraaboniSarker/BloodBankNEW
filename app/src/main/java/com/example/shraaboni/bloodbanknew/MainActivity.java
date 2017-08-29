package com.example.shraaboni.bloodbanknew;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    private EditText nameET;
    private Spinner bloodSP;
    private RadioGroup genderRG;
    private EditText emailET;
    private EditText passwordET;
    DatabaseReference databaseReference;
    Person person;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailET= (EditText) findViewById(R.id.emailET);
        passwordET= (EditText) findViewById(R.id.passwordET);
        nameET= (EditText) findViewById(R.id.nameET);
        bloodSP= (Spinner) findViewById(R.id.bloodSP);
        genderRG= (RadioGroup) findViewById(R.id.genderRG);
        firebaseAuth=FirebaseAuth.getInstance();
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.blood_Group,R.layout.support_simple_spinner_dropdown_item);
        bloodSP.setAdapter(adapter);
    }



    public void signUp(View view) {

        final String email=emailET.getText().toString();
        final String password=passwordET.getText().toString();
        final String name=nameET.getText().toString();
        final String bloodtype=bloodSP.getSelectedItem().toString();
        int buttonID=genderRG.getCheckedRadioButtonId();
        RadioButton button = (RadioButton)findViewById(buttonID);
        final String gender=button.getText().toString();
        Toast.makeText(this, "called", Toast.LENGTH_SHORT).show();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //Toast.makeText(MainActivity.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                    databaseReference = FirebaseDatabase.getInstance().getReference("user");
                    //DatabaseReference currentUserId = databaseReference.child(firebaseAuth.getCurrentUser().getUid());
                    key=databaseReference.push().getKey();

                    Log.d("keyvalue",key);
                    person = new Person(name,bloodtype,gender,email,password,key);

                    //databaseReference.push().setValue(person);
                    databaseReference.child(key).setValue(person);

                    startActivity(new Intent( MainActivity.this,AccountActivity.class).putExtra("email",email)
                            .putExtra("name",name)
                            .putExtra("bloodgrp",bloodtype)
                            .putExtra("gender",gender)
                    .putExtra("key",key));
                }
                else{
                    Toast.makeText(MainActivity.this, "unsuccessfull", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void moveToLogin(View view) {
        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
        intent.putExtra("key",key);
        startActivity(intent);

    }

}
