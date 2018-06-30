package com.example.bahung.vtask1.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bahung.vtask1.R;
import com.example.bahung.vtask1.controller.adapter.ProjectAdapter;
import com.example.bahung.vtask1.controller.dialogfragment.CreateProjectDialog;
import com.example.bahung.vtask1.model.ProjectModel;
import com.example.bahung.vtask1.view.fragment.ProjectFragment.ProjectFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView1, navigationView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar();



        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView1 =  findViewById(R.id.nav_view1);
        navigationView1.setNavigationItemSelectedListener(this);

        navigationView2 =  findViewById(R.id.nav_view2);
        navigationView2.setNavigationItemSelectedListener(this);

        replaceFragment(R.id.myLayout, new ProjectFragment());
    }

    private void toolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Dự án");
        setSupportActionBar(toolbar);
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
        } else if(id == R.id.action_more){

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.END);
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



    public void replaceFragment(int id, Fragment fragment) {
        String name = fragment.getClass().getName();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(id, fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        transaction.addToBackStack(name);
        transaction.commit();
    }
}
