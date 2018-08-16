package com.pharmavet.imperial.pharmavetdist.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.pharmavet.imperial.pharmavetdist.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // For future use
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d("BLA", Uri.parse("android.resource://com.pharmavet.imperial.pharmavetdist/mipmap/"+"htpcapsules").toString());
        startActivity(new Intent(LoginActivity.this, BaseActivity.class));
    }
}
