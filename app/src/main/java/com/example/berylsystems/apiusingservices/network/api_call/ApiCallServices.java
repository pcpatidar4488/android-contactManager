package com.example.berylsystems.apiusingservices.network.api_call;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.example.berylsystems.apiusingservices.object_preference.AppUser;
import com.example.berylsystems.apiusingservices.utils.Cv;
import com.example.berylsystems.apiusingservices.object_preference.LocalRepositories;
import com.example.berylsystems.apiusingservices.network.pojo.response.signin.ResponseSignIn;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ApiCallServices extends IntentService {

    private Api api;

    public ApiCallServices() {
        super("ApiCallServices");
    }

    public static void action(Context context, String action) {
        Intent intent = new Intent(context, ApiCallServices.class);
        intent.setAction(action);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        api = ApiClient.getClient();
        if (intent != null) {
            final String action = intent.getAction();
            if (Cv.ACTION_SIGN_IN.equals(action)) {
                handleSignIn();
            }
        }
    }

    private void handleSignIn() {
        AppUser appUser = LocalRepositories.getAppUser(this);
        api.postApi(appUser.userName, appUser.password).enqueue(new Callback<ResponseSignIn>() {
            @Override
            public void onResponse(Call<ResponseSignIn> call, Response<ResponseSignIn> r) {
                if (r.isSuccessful()) {
                    ResponseSignIn body = r.body();
                    EventBus.getDefault().post(body);
                } else {
                    EventBus.getDefault().post(Cv.TIMEOUT);
                }
            }

            @Override
            public void onFailure(Call<ResponseSignIn> call, Throwable t) {
                try {
                    EventBus.getDefault().post(t.getMessage());
                } catch (Exception ex) {
                    EventBus.getDefault().post(Cv.TIMEOUT);
                }
            }
        });
    }
}
