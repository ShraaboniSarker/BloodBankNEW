package com.example.shraaboni.bloodbanknew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    private ListView myListView;
    private Request request;
    private ArrayList<Request> req;
    private RequestAdapter myadapter;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        myListView = (ListView) findViewById(R.id.list_me);
        req=new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Request");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("data>>","data: "+dataSnapshot);
                if (dataSnapshot.exists()){
                    for (DataSnapshot single: dataSnapshot.getChildren()){
                        Request request= single.getValue(Request.class);
                        Log.d("fb>>","req: "+request.getRequest()+" area: "+request.getArea()+" bag: "+request.getBags());
                        req.add(new Request(request.getRequest(),request.getBags(),request.getArea()));

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Log.d("size>>","size: "+req.size());
       myadapter= new RequestAdapter(this,req);
       myListView.setAdapter(myadapter);
    }
}
