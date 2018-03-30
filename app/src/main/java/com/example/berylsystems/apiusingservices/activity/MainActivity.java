package com.example.berylsystems.apiusingservices.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.berylsystems.apiusingservices.R;
import com.example.berylsystems.apiusingservices.adapter.MainActivityAdapter;
import com.example.berylsystems.apiusingservices.network.pojo.response.signin.Contact;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    public static ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager;
        MainActivityAdapter adapter = new MainActivityAdapter(MainActivity.this, contacts);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
    }
}
