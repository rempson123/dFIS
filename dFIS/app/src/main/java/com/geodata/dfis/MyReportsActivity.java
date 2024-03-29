package com.geodata.dfis;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.geodata.dfis.Fragments.DraftsFragment;
import com.geodata.dfis.Fragments.SentFragment;

public class MyReportsActivity extends AppCompatActivity {

    private static final String TAG = "MyReportsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reports);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation_report);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new DraftsFragment()).commit();
        }

        String sentTabIntent = "sentTab";
        String sentTab = getIntent().getStringExtra("sentTabFragment");

        if(sentTabIntent.equals(sentTab)){
            bottomNav.setSelectedItemId(R.id.nav_sent);
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_draft:
                            selectedFragment = new DraftsFragment();
                            break;
                        case R.id.nav_sent:
                            selectedFragment = new SentFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                Intent intent = new Intent(MyReportsActivity.this, NavigationDrawerActivity.class);
                startActivity(intent);
                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MyReportsActivity.this, NavigationDrawerActivity.class);
        startActivity(intent);
        finishAffinity();
    }

}
