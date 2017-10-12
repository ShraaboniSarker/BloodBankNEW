package com.example.shraaboni.bloodbanknew.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.shraaboni.bloodbanknew.R;

public class UserDetails extends AppCompatActivity {
    String email;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        Intent intent = getIntent();
        key= intent.getStringExtra(key);
        email = intent.getStringExtra(email);
    }

    public void goToMain(View view) {
        Toast.makeText(UserDetails.this, "Successfull", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(UserDetails.this,AccountActivity.class).putExtra("email",email).putExtra("key",key));
    }
}
