package com.letssolvetogether.omr.firstrun;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.letssolvetogether.omr.home.ui.HomeActivity;

public class FirstRunActivity extends AppCompatActivity {

    private SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
    }
}
