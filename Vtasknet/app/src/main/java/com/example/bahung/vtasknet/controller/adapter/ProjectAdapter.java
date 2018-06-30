package com.example.bahung.vtasknet.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bahung.vtasknet.R;
import com.example.bahung.vtasknet.model.ProjectModel;

import java.util.ArrayList;

/**
 * Created by bahung on 18/06/2018.
 **/

public class ProjectAdapter extends BaseAdapter {
    Context context;
    ArrayList<ProjectModel> datas;

    public ProjectAdapter(Context context, ArrayList<ProjectModel> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        convertView = layoutInflater.inflate(R.layout.custom_item_project, parent, false);

        ImageView img = convertView.findViewById(R.id.img);
        TextView title = convertView.findViewById(R.id.txtTitle);

        img.setImageResource(datas.get(position).getImg());
        title.setText(datas.get(position).getTitle());
        return convertView;
    }
}
