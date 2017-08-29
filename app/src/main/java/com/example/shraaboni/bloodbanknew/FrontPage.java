package com.example.shraaboni.bloodbanknew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FrontPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);
    }
    public void gotoLogin(View view) {
        startActivity(new Intent(FrontPage.this,LoginActivity.class));
    }

    public void gotoSignup(View view) {
        startActivity(new Intent(FrontPage.this,MainActivity.class));
    }
}
