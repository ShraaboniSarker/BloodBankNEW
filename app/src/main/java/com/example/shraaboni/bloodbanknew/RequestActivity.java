package com.example.shraaboni.bloodbanknew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RequestActivity extends AppCompatActivity {


    EditText requestET;
    EditText bagET;
    EditText areaET;
    String Request;
    String noofbags;
    String area;
    DatabaseReference dataref;
    String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        Intent intent= getIntent();
        key=intent.getStringExtra(key);
        areaET= (EditText) findViewById(R.id.area);
        requestET= (EditText) findViewById(R.id.request);
        bagET= (EditText) findViewById(R.id.bag);

    }

    public void PostRequest(View view) {
        dataref= FirebaseDatabase.getInstance().getReference("Request");
        String reqId =dataref.push().getKey();
        Request = requestET.getText().toString();
        noofbags = bagET.getText().toString();
        area= areaET.getText().toString();
        Log.d("et>>","request: "+Request+" bags: "+noofbags+" area: "+area);
        Request request = new Request(Request,noofbags,area);
        dataref.child(reqId).setValue(request);
        Toast.makeText(this, "Your Request will be broadcast very soon !!", Toast.LENGTH_SHORT).show();
    }
}
