package com.example.palautettava_harjoitustyo;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class Dialog extends DialogFragment {

    SharedPreferences mPrefs;
    SharedPreferences.Editor mEditor;

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        mPrefs = getActivity().getSharedPreferences("dialog_check", Context.MODE_PRIVATE);
        mEditor = mPrefs.edit();


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getLayoutInflater().inflate(R.layout.dialog_layout, null);
        builder.setView(view);

        CheckBox checkBox = view.findViewById(R.id.dialog_checkBox);
        Button button = view.findViewById(R.id.dialog_button);

        final AlertDialog alertDialog = builder.create();
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dismiss();
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    mEditor.putBoolean("isChecked", true);
                    mEditor.commit();
                }else{
                    mEditor.putBoolean("isChecked", false);
                    mEditor.commit();
                }
            }
        });
        return builder.create();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) dismiss();
    }
}
