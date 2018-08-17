package com.pharmavet.imperial.pharmavetdist.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.pharmavet.imperial.pharmavetdist.R;

public class LoginActivity extends BaseActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // For future use
        Log.d(TAG, "Launching...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        super.setupSideNavAndToolbar();
        startActivity(new Intent(this, AllCompaniesActivity.class));
    }
}
