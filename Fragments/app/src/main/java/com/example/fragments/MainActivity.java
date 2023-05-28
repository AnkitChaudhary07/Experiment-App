package com.example.fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottom_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottom_nav = findViewById(R.id.bottom_nav);
        /**Fragment Manager**/
        FragmentManager fragmentManager = getSupportFragmentManager();
        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.home) {
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView3, HomeFragment.class, null)
                            .setReorderingAllowed(true)
                            .addToBackStack("name")
                            .commit();
                } else if (id == R.id.cart) {
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView3, CartFragment.class, null)
                            .setReorderingAllowed(true)
                            .addToBackStack("name")
                            .commit();
                } else { //Profile
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView3, AccountFragment.class, null)
                            .setReorderingAllowed(true)
                            .addToBackStack("name")
                            .commit();
                }
                return true;
            }
        });
    }
}

//        /**HOME BUTTON**/
//        Button bttnHome = findViewById(R.id.bttnHome);
//        bttnHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                fragmentManager.beginTransaction()
//                        .replace(R.id.fragmentContainerView3, HomeFragment.class, null)
//                        .setReorderingAllowed(true)
//                        .addToBackStack("name")
//                        .commit();
//            }
//        });