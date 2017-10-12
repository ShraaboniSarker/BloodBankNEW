package com.example.shraaboni.bloodbanknew.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shraaboni.bloodbanknew.Model.Constants;
import com.example.shraaboni.bloodbanknew.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class FrontPage extends AppCompatActivity {
    Button signup ;
    Button login;
    LoginButton login_button;
    CallbackManager callback;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_front_page);
        init();
        login_button.setReadPermissions("email, publish_actions");
        loginWithFB();
//        new FancyShowCaseView.Builder(this)
//                .focusOn(signup)
//                .focusOn(login)
//                .title("Tap Screen To Proceed")
//                .focusShape(FocusShape.ROUNDED_RECTANGLE)
//                .build()
//                .show();
//        signup.setOnClickListener(this);
//        login.setOnClickListener(this);
        setUpWindowAnimation();

    }
    private void loginWithFB() {
        LoginManager.getInstance().registerCallback(callback, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
               startActivity(new Intent(FrontPage.this,NewsActivity.class));
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callback.onActivityResult(requestCode, resultCode, data);
    }
    private void init() {
        callback = CallbackManager.Factory.create();
        login_button= (LoginButton) findViewById(R.id.login_button);
        signup = (Button) findViewById(R.id.signup);
        login = (Button) findViewById(R.id.login);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setUpWindowAnimation() {

        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.LEFT);
        slide.setDuration(1000);
        getWindow().setReenterTransition(slide);
        getWindow().setReturnTransition(slide);
        getWindow().setAllowEnterTransitionOverlap(false);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void gotoLogin(View view) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent intent = new Intent(FrontPage.this,LoginActivity.class);
        intent.putExtra(Constants.KEY_TYPE,Constants.Animtype.ExplodeJava);
        intent.putExtra(Constants.KEY_TITLE,"Explode Animation");
        intent.putExtra(Constants.KEY_NAME,"Explode by code");
        startActivity(intent,options.toBundle());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void gotoSignup(View view) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent intent = new Intent(FrontPage.this,MainActivity.class);
        intent.putExtra(Constants.KEY_TYPE,Constants.Animtype.ExplodeJava);
        intent.putExtra(Constants.KEY_TITLE,"Explode Animation");
        intent.putExtra(Constants.KEY_NAME,"Explode by code");
        startActivity(intent,options.toBundle());

    }

}
