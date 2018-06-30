package com.example.bahung.vtask1.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bahung.vtask1.R;
import com.example.bahung.vtask1.model.Group;

import java.util.ArrayList;

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