package com.example.bahung.menulikegmail;

import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    TextView cityPickerTextView;
    ListView citiesListView;
    ImageView pickerArrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.header).setVisibility(View.INVISIBLE);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        cityPickerTextView = (TextView) headerView.findViewById(R.id.cityPicker);
        cityPickerTextView.setText(cities[0]);
        cityPickerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleMenu();
            }
        });
        cityPickerTextView.bringToFront();

        pickerArrow = (ImageView) headerView.findViewById(R.id.pickerArrow);
        pickerArrow.setImageResource(R.drawable.ic_arrow_drop_down_white_24dp);

        citiesListView = (ListView) findViewById(R.id.citiesListView);
        citiesListView.setVisibility(View.INVISIBLE);

        final CityPickerAdapter adapter = new CityPickerAdapter(this, cities, userCity);
        citiesListView.setAdapter(adapter);
        citiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                userCity = cities[i];
                cityPickerTextView.setText(userCity);
                adapter.updateCurrentCity(userCity);
            }
        });
    }

    private void toggleMenu() {
        if (!isPickerShown) {
            pickerArrow.setImageResource(R.drawable.ic_arrow_drop_up_white_24dp);
            setMenuItemsVisible(false);
            citiesListView.setVisibility(View.VISIBLE);
            isPickerShown = true;
        } else {
            pickerArrow.setImageResource(R.drawable.ic_arrow_drop_down_white_24dp);
            setMenuItemsVisible(true);
            citiesListView.setVisibility(View.INVISIBLE);
            isPickerShown = false;
        }
    }

    private void setMenuItemsVisible(boolean b) {
        Menu menu = navigationView.getMenu();
        for (int i = 0; i < menu.size(); ++i) {
            menu.getItem(i).setVisible(b);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        navigationView.inflateMenu(R.menu.activity_main_drawer);
        return true;
    }
    }
}
