package com.example.palautettava_harjoitustyo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    DrawerLayout dLayout;
    NavigationView navView;
    ActionBarDrawerToggle dToggle;
    public static Activity fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fa = this;

        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        Locale locale;
        locale = new Locale(defaultSharedPreferences.getString("language", ""));
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        MainActivity.this.getBaseContext().getResources().updateConfiguration(config, MainActivity.this.getBaseContext().getResources().getDisplayMetrics());

        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));

        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences("dialog_check", MODE_PRIVATE);
        boolean isChecked = preferences.getBoolean("isChecked", false);
        if(!isChecked){
            Dialog dialog = new Dialog();
            dialog.show(getSupportFragmentManager(), "dialog");
        }

        dLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);
        dToggle = new ActionBarDrawerToggle(this, dLayout, R.string.open, R.string.close);
        dLayout.addDrawerListener(dToggle);
        dToggle.syncState();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.main_news:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new newsFragment(), "news").commit();
                        break;
                    case R.id.latest_news:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new latestNewsFragment(), "latest").commit();
                        break;
                    case R.id.most_readed:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new mostReadFragment(), "most").commit();
                        break;
                    case R.id.settings_menu:
                        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (dToggle.onOptionsItemSelected(item)) {
            return true;
        } else if(item.getItemId() == R.id.menu_refresh){
            String a = (String) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView).getTag();
            if (a != null) {
                switch (a) {
                    case "news":
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new newsFragment(), "news").commit();
                        break;
                    case "latest":
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new latestNewsFragment(), "latest").commit();
                        break;
                    case "most":
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new mostReadFragment(), "most").commit();
                        break;
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (dLayout.isDrawerOpen(GravityCompat.START)) {
            dLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.refresh, menu);
        return true;
    }

}
