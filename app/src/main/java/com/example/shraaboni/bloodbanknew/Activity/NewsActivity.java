package com.example.shraaboni.bloodbanknew.Activity;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.shraaboni.bloodbanknew.R;
import com.example.shraaboni.bloodbanknew.Model.Request;
import com.example.shraaboni.bloodbanknew.Adapter.RequestAdapter;
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
    String email;
    String key;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mToggle;
    //Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Intent intent = getIntent();
        key= intent.getStringExtra(key);
        email = intent.getStringExtra("email");
        myListView = (ListView) findViewById(R.id.list_me);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawer,R.string.open,R.string.close);
        mDrawer.addDrawerListener(mToggle);
        mToggle.syncState();
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("Supper Donor");
//        toolbar.setSubtitle("News Panel");
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return  true;
        }

        int id = item.getItemId();
        if(id == R.id.Profile){

        }else if(id == R.id.Request)
        {
            startActivity(new Intent(NewsActivity.this,RequestActivity.class).putExtra("key",key));
        }
        else if(id == R.id.Mapview){}
        else if(id == R.id.Logout){}


        return super.onOptionsItemSelected(item);
    }
}
