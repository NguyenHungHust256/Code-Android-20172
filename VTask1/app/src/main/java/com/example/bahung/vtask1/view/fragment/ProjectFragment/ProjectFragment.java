package com.example.bahung.vtask1.view.fragment.ProjectFragment;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bahung.vtask1.R;
import com.example.bahung.vtask1.controller.adapter.ProjectAdapter;
import com.example.bahung.vtask1.controller.dialogfragment.CreateProjectDialog;
import com.example.bahung.vtask1.model.ProjectModel;
import com.example.bahung.vtask1.view.activity.MainActivity;
import com.example.bahung.vtask1.view.fragment.TabJobFragment.TabJobFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends Fragment implements AdapterView.OnItemClickListener,CreateProjectDialog.CreateProject {
    View mView;
    ListView lvProject;
    ProjectAdapter projectAdapter;

    private ArrayList<ProjectModel> datas;
    public ProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_project, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXa();
        floatingButtonCreateProject();
        setListViewProject();

    }

    private void floatingButtonCreateProject() {
        FloatingActionButton fab =  mView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }
    private void showDialog() {
        FragmentManager fm = getFragmentManager();
        CreateProjectDialog dialog = CreateProjectDialog.newInstance(null);
        // SETS the target fragment for use later when sending results
        dialog.setTargetFragment(ProjectFragment.this, 300);
        dialog.show(fm, "fragment_edit_name");


    }

    private void createDataToTest() {
        datas.clear();
        datas.add(new ProjectModel("Nghien cuu BlockChain",R.drawable.user_nav));
        datas.add(new ProjectModel("Nihongo",R.drawable.user_nav));
        datas.add(new ProjectModel("Du An VTask",R.drawable.user_nav));
        datas.add(new ProjectModel("Nghien cuu BlockChain",R.drawable.user_nav));
        datas.add(new ProjectModel("Nihongo",R.drawable.user_nav));
        datas.add(new ProjectModel("Du An VTask",R.drawable.user_nav));
        datas.add(new ProjectModel("Nghien cuu BlockChain",R.drawable.user_nav));
        datas.add(new ProjectModel("Nihongo",R.drawable.user_nav));
        datas.add(new ProjectModel("Du An VTask",R.drawable.user_nav));
        datas.add(new ProjectModel("Du An VTask",R.drawable.user_nav));
        datas.add(new ProjectModel("Nghien cuu BlockChain",R.drawable.user_nav));
        datas.add(new ProjectModel("Nihongo",R.drawable.user_nav));
        datas.add(new ProjectModel("Du An VTask",R.drawable.user_nav));
    }
    private void setListViewProject() {
        datas = new ArrayList<>();
        createDataToTest();
        projectAdapter = new ProjectAdapter(getActivity(), datas);
        lvProject.setAdapter(projectAdapter);
//        lvProject.setDivider(null);
        lvProject.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((MainActivity)(getActivity())).replaceFragment(R.id.myLayout, new TabJobFragment());
    }
    private void anhXa() {
        lvProject = mView.findViewById(R.id.lv_project);
    }

    @Override
    public void sendTitleProjectForData(String title) {
        datas.add(new ProjectModel(title ,R.drawable.user_nav));
        projectAdapter.notifyDataSetChanged();
    }
}
