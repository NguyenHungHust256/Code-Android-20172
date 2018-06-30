package com.example.bahung.vtasknet.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bahung.vtasknet.R;
import com.example.bahung.vtasknet.model.Group;

import java.util.ArrayList;

/**
 * Created by bahung on 17/03/2018.
 **/

public class MenuAdapter extends BaseAdapter{
    ArrayList<Group> dataGroup;
    Context context;

    public MenuAdapter(ArrayList<Group> dataGroup, Context context) {
        this.dataGroup = dataGroup;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataGroup.size();
    }

    @Override
    public Object getItem(int position) {
        return dataGroup.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_custom_nav, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtNameGroup.setText(dataGroup.get(position).getName());
        holder.txtGmailGroup.setText(dataGroup.get(position).getGmail());
        return convertView;
    }
}

class ViewHolder {
    public ImageView imgGroup;
    public TextView txtNameGroup;
    public TextView txtGmailGroup;
    public TextView txtCounterNotify;

    public ViewHolder(View view) {
        imgGroup = view.findViewById(R.id.image_item_group);
        txtNameGroup = view.findViewById(R.id.txtNameGroup);
        txtGmailGroup = view.findViewById(R.id.txtGmailGroup);
        txtCounterNotify = view.findViewById(R.id.txtCounterGroup);
    }
}