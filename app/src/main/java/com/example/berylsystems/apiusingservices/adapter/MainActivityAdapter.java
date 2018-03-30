package com.example.berylsystems.apiusingservices.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.berylsystems.apiusingservices.R;
import com.example.berylsystems.apiusingservices.network.pojo.response.signin.Contact;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by BerylSystems on 19-Mar-18.
 */

public class MainActivityAdapter  extends RecyclerView.Adapter<MainActivityAdapter.ViewHolder> {

    Context context;
    ArrayList<Contact> contact;
    public MainActivityAdapter(Context context,ArrayList<Contact> contact){
        this.context=context;
        this.contact=contact;
    }

    @Override
    public MainActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_recycler, viewGroup, false);
        return new MainActivityAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainActivityAdapter.ViewHolder holder, int position) {
        holder.name.setText(contact.get(position).getName());
        holder.email.setText(contact.get(position).getEmail());
        holder.mobile.setText(contact.get(position).getMobile());
        holder.company.setText(contact.get(position).getCompany());
    }

    @Override
    public int getItemCount() {
        return contact.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.email)
        TextView email;
        @Bind(R.id.mobile)
        TextView mobile;
        @Bind(R.id.company)
        TextView company;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
