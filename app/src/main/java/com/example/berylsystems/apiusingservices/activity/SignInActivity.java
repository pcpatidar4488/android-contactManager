package com.example.berylsystems.apiusingservices.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.berylsystems.apiusingservices.R;
import com.example.berylsystems.apiusingservices.object_preference.AppUser;
import com.example.berylsystems.apiusingservices.object_preference.LocalRepositories;
import com.example.berylsystems.apiusingservices.network.api_call.ApiCallServices;
import com.example.berylsystems.apiusingservices.network.pojo.response.signin.ResponseSignIn;
import com.example.berylsystems.apiusingservices.shared_preference.Preferences;
import com.example.berylsystems.apiusingservices.utils.Cv;
import com.example.berylsystems.apiusingservices.utils.EventBussHandler;
import com.example.berylsystems.apiusingservices.utils.Utility;
import com.example.berylsystems.apiusingservices.utils.Validation;

import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignInActivity extends EventBussHandler {

    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @Bind(R.id.username)
    EditText username;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.submit)
    Button submit;
    ProgressDialog mProgressDialog;
    AppUser appUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait");


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utility.isConnected(getApplicationContext())) {
                    if (!Validation.isNameFormatValid(username.getText().toString())) {
                        Snackbar.make(coordinatorLayout, "Please enter valid user name", Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    Utility.closeKeyPad(SignInActivity.this);
                    mProgressDialog.show();
                    appUser = LocalRepositories.getAppUser(SignInActivity.this);
                    appUser.userName = username.getText().toString();
                    appUser.password = password.getText().toString();
                    LocalRepositories.saveAppUser(SignInActivity.this, appUser);
                    ApiCallServices.action(getApplicationContext(), Cv.ACTION_SIGN_IN);
                } else {
                    Snackbar.make(coordinatorLayout, "Please check you internet connection first", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Subscribe
    public void post_api(ResponseSignIn response) {
        mProgressDialog.dismiss();
        MainActivity.contacts = response.getContact();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Subscribe
    public void time_out(String timeout) {
        mProgressDialog.dismiss();
        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
    }

}
