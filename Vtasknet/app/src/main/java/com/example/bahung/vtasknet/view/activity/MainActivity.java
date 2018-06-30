package com.example.bahung.vtasknet.view.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bahung.vtasknet.R;
import com.example.bahung.vtasknet.controller.adapter.ProjectAdapter;
import com.example.bahung.vtasknet.controller.dialogfragment.CreateProjectDialog;
import com.example.bahung.vtasknet.model.ProjectModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, CreateProjectDialog.CreateProject {
    ListView lvProject;
    private ArrayList<ProjectModel> datas;
    Toolbar toolbar;
    ProjectAdapter projectAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar();

        anhXa();
        setListViewProject();
        floatingButtonCreateProject();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void floatingButtonCreateProject() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void toolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void showDialog() {

            FragmentManager fm = getSupportFragmentManager();
            CreateProjectDialog alertDialog = CreateProjectDialog.newInstance(null);
            alertDialog.show(fm, "fragment_alert");

    }

    private void setListViewProject() {
        datas = new ArrayList<>();
        createDataToTest();

         projectAdapter = new ProjectAdapter(MainActivity.this, datas);
        lvProject.setAdapter(projectAdapter);

    }

    private void createDataToTest() {
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

    private void anhXa() {
        lvProject = findViewById(R.id.lv_project);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.subject_id:

                Toast.makeText(this, "Chủ đề Dự án", Toast.LENGTH_SHORT).show();
                break;
            case R.id.member_id:
                Toast.makeText(this, "Thành viên", Toast.LENGTH_SHORT).show();
                break;
            case R.id.notify_id:
                Toast.makeText(this, "Thông báo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.info_id:
                Toast.makeText(this, "Thông tin tài khoản", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting_id:
                Toast.makeText(this, "Cài đặt phần mềm", Toast.LENGTH_SHORT).show();
                break;
            case R.id.waiting_id:
                Toast.makeText(this, "Đang chờ duyệt", Toast.LENGTH_SHORT).show();
                break;
            case R.id.join_id:
                Toast.makeText(this, "Đã tham gia", Toast.LENGTH_SHORT).show();
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void sendTitleProjectForData(String title) {
        datas.add(new ProjectModel(title ,R.drawable.user_nav));
        projectAdapter.notifyDataSetChanged();
    }
}
