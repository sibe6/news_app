package com.example.palautettava_harjoitustyo;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;


public class SettingsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_settings));
        setContentView(R.layout.settings_activity);
        MainActivity.fa.finish();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.settings, new SettingsFragment()).commit();
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }


    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        Intent myIntent = new Intent(SettingsActivity.this, MainActivity.class);
        SettingsActivity.this.startActivity(myIntent);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent myIntent = new Intent(SettingsActivity.this, MainActivity.class);
        SettingsActivity.this.startActivity(myIntent);
    }
}

