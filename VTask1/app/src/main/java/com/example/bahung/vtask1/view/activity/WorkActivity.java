package com.example.bahung.vtask1.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.bahung.vtask1.R;
import com.example.bahung.vtask1.controller.adapter.TabJobAdapter;

public class WorkActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        setToolbar();
        setTabLayout();
    }

    private void setToolbar() {
//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setTitle("Nghien cuu blockchain");

    }

    private void setTabLayout() {
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabLayout);
        TabJobAdapter adapter = new TabJobAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

//    public void addFragment(int id, Fragment fragment) {
//        String name = fragment.getClass().getName();
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(id, fragment);
//        transaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
//        transaction.addToBackStack(name);
//        transaction.commit();
//    }
}
