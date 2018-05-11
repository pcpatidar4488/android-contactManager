package com.example.punamchandpatidar.contactmanager.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.punamchandpatidar.contactmanager.R;
import com.example.punamchandpatidar.contactmanager.object_preference.AppUser;
import com.example.punamchandpatidar.contactmanager.object_preference.LocalRepositories;
import com.example.punamchandpatidar.contactmanager.network.api_call.ApiCallServices;
import com.example.punamchandpatidar.contactmanager.network.pojo.response.signin.ResponseSignIn;
import com.example.punamchandpatidar.contactmanager.shared_preference.Preferences;
import com.example.punamchandpatidar.contactmanager.utils.Cv;
import com.example.punamchandpatidar.contactmanager.utils.EventBussHandler;
import com.example.punamchandpatidar.contactmanager.utils.Utility;
import com.example.punamchandpatidar.contactmanager.utils.Validation;

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
    @Bind(R.id.get_map)
    Button get_map;
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

        get_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
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
