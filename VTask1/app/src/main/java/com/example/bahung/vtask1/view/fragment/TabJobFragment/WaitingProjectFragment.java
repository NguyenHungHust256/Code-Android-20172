package com.example.bahung.vtask1.view.fragment.TabJobFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bahung.vtask1.R;
import com.example.bahung.vtask1.controller.adapter.ItemWaitingJobAdapter;
import com.example.bahung.vtask1.model.ItemJob;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaitingProjectFragment extends Fragment implements View.OnClickListener {
    RecyclerView rvWaitingProject;
    FloatingActionButton fab;
    ArrayList<ItemJob> datas = new ArrayList<>();
    public WaitingProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_waiting_project, container, false);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        anhXa(view);
        initData();
        ItemWaitingJobAdapter adapter = new ItemWaitingJobAdapter(getActivity(), datas);
        RecyclerView.LayoutManager giaoDien = new LinearLayoutManager(getActivity());
        rvWaitingProject.setLayoutManager(giaoDien);
        rvWaitingProject.setAdapter(adapter);
        fab.setOnClickListener(this);

    }

    private void initData() {
        datas.clear();
        datas.add(new ItemJob("Hoàn thành giao diện"));
        datas.add(new ItemJob("Hoàn thành giao diện"));
        datas.add(new ItemJob("Hoàn thành giao diện"));
        datas.add(new ItemJob("Hoàn thành giao diện"));
        datas.add(new ItemJob("Hoàn thành giao diện"));
    }

    private void anhXa(View view) {
        rvWaitingProject = view.findViewById(R.id.rv_waiting_project);
        fab = view.findViewById(R.id.fab);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.fab){
//            Intent intent = new Intent(getActivity(), CreateJobActivity.class);
//            getActivity().startActivity(intent);
        }
    }
}

