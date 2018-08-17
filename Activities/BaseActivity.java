package com.pharmavet.imperial.pharmavetdist.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.pharmavet.imperial.pharmavetdist.App;
import com.pharmavet.imperial.pharmavetdist.ViewModels.DisplayItemsViewModel;
import com.pharmavet.imperial.pharmavetdist.R;
import com.pharmavet.imperial.pharmavetdist.Services.ImageRetriever;

import javax.inject.Inject;


public class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();
    @Inject
    ImageRetriever imageRetriever;
    @Inject
    DisplayItemsViewModel displayItemsViewModel;
    NavigationView navigationView;
    Toolbar toolbar;
    RelativeLayout fullLayout;
    FrameLayout frameLayout;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ((App) getApplication()).getMainComponent().inject(this);

      /*  displayItemsViewModel.getAll()
                .subscribeOn(Schedulers.computation())
                .doOnSuccess((s) -> {
                    // needs run on ui thread
                    Log.d("DB TEST", s.get(0).getImageUrl());
                   // imageView.setImageURI(Uri.parse(s.get(0).getImageUrl()));
                });*/
    }
    @Override
    public void setContentView(int layoutRes) {


        fullLayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.activity_base,null);
        frameLayout = fullLayout.findViewById(R.id.content_frame);
        getLayoutInflater().inflate(layoutRes, frameLayout, true);

        super.setContentView(fullLayout);

        // We do not use butterknife here as butterknife can ONLY BIND ONCE,
        // thus if we bind here no class that inherits from our base class can bind
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
    }

    /**
     * Needs to be called on every Activity that extends @{@link BaseActivity}
     */
    public void setupSideNavAndToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        navigationView.setNavigationItemSelectedListener(item -> {
            onNavItemClicked(item);
            return true;
        });


    }

    private void onNavItemClicked(MenuItem item) {
        item.setChecked(true); // This is to persist click state

        int id = item.getItemId();
        Log.d(TAG, "I am in nav items");
        if (id == R.id.nav_all_companies) {
            loadAllCompanies();
        }
    }

    private void loadAllCompanies() {
        startActivity(new Intent(BaseActivity.this, AllCompaniesActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                // This ensures nav drawer open animation
                // behaves properly for both right-to-left and left-to-right layouts
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
