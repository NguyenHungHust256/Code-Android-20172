package com.example.bahung.vtask1.controller.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bahung.vtask1.R;
import com.example.bahung.vtask1.model.ItemJob;
import com.example.bahung.vtask1.view.activity.MainActivity;

import java.util.ArrayList;

/**
 * Created by bahung on 24/03/2018.
 **/

public class ItemWaitingJobAdapter extends RecyclerView.Adapter<ItemWaitingJobAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private ArrayList<ItemJob> itemJobData = new ArrayList<>();

    public ItemWaitingJobAdapter(Context context, ArrayList<ItemJob> itemJobData) {
        this.context = context;
        this.itemJobData = itemJobData;
    }

    @Override
    public ItemWaitingJobAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(this.context);
        view = inflater.inflate(R.layout.custom_item_job, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemWaitingJobAdapter.ViewHolder holder, int position) {
//        Bat su kien khi click vao 1 cai nao do
        holder.title.setOnClickListener(this);


    }

    @Override
    public int getItemCount() {
        return itemJobData.size();
    }

    @Override
    public void onClick(View v) {
//       if(v.getId() == R.id.title_job){
//           Intent intent = new Intent(context, MainChatActivity.class);
//           ((MainActivity) context).startActivity(intent);
//       }
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);

        }
    }
}
