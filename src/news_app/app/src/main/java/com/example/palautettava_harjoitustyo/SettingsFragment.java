package com.example.palautettava_harjoitustyo;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;


public class SettingsFragment extends PreferenceFragmentCompat {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.root_preferences);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    }


}
