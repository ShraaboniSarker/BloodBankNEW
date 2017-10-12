package com.example.shraaboni.bloodbanknew.Activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shraaboni.bloodbanknew.R;
import com.example.shraaboni.bloodbanknew.Model.Request;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

public class RequestActivity extends AppCompatActivity {


    EditText requestET;
    EditText bagET;
    EditText areaET;
    String Request;
    String noofbags;
    String area;
    DatabaseReference dataref;
    String key;
    public  Context context;
    JSONObject response, profile_pic_data, profile_pic_url;
    int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        Intent intent= getIntent();
        key=intent.getStringExtra(key);
        areaET= (EditText) findViewById(R.id.area);
        requestET= (EditText) findViewById(R.id.request);
        bagET= (EditText) findViewById(R.id.bag);
        context=this;
        Button button = (Button) findViewById(R.id.button);
        shareDialog = new ShareDialog(this);  // initialize facebook shareDialog.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle("Android Facebook Integration and Login Tutorial")
                            .setImageUrl(Uri.parse("https://www.studytutorial.in/wp-content/uploads/2017/02/FacebookLoginButton-min-300x136.png"))
                            .setContentDescription(
                                    "this is a blood donation app")
                            .setContentUrl(Uri.parse("https://www.facebook.com/groups/2363344107223078/?source=create_flow"))
                            .build();
                    shareDialog.show(linkContent);  // Show facebook ShareDialog
                }
            }
        });
    }
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
    public void PostRequest(View view) {
        dataref= FirebaseDatabase.getInstance().getReference("Request");
        String reqId =dataref.push().getKey();
        Request = requestET.getText().toString();
        noofbags = bagET.getText().toString();
        area= areaET.getText().toString();
        Log.d("et>>","request: "+Request+" bags: "+noofbags+" area: "+area);
        com.example.shraaboni.bloodbanknew.Model.Request request = new Request(Request,noofbags,area);
        dataref.child(reqId).setValue(request);
        Toast.makeText(this, "Your Request will be broadcast very soon !!", Toast.LENGTH_SHORT).show();
        Notification notification =new Notification.Builder(RequestActivity.this)
                .setSmallIcon(R.drawable.blood)
                .setContentTitle("BloodBank App")
                .setContentText(" need " + Request +" Blood "+ noofbags+ " bags in " +area)
                .setAutoCancel(true).build();
        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0,notification);
    }


}
