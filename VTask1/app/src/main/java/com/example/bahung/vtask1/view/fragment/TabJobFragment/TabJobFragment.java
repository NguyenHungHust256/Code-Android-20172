package com.example.bahung.vtask1.view.fragment.TabJobFragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bahung.vtask1.R;
import com.example.bahung.vtask1.controller.adapter.TabJobAdapter;
import com.example.bahung.vtask1.view.activity.MainActivity;
import com.example.bahung.vtask1.controller.adapter.TabJobAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabJobFragment extends Fragment {

    View mView;
    ViewPager viewPager;
    TabLayout tabLayout;
    public TabJobFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_tab_job, container, false);

        ((MainActivity)getActivity()).getSupportActionBar().setElevation(0);

        viewPager = (ViewPager) mView.findViewById(R.id.viewpager);
        tabLayout =(TabLayout) mView.findViewById(R.id.tabLayout);
        TabJobAdapter adapter = new TabJobAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return mView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
