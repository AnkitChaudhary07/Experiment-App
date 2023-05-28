package com.example.listrecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    String arr[] = {"Helloo fnsdj dfnonfoenfo wefnofe feofneng","Olaa fnefoei enfeernfeof","Kaim Choo knfkle wejfjfe ewnfe fe eernf voenvo3","Konnichinev edineoifne efno fep ifn","Helloo ewbfnonfo wefnoenfe fwoofneng","Olaa fnernfenfoei wejfnoe","Kaim Choo kfnel ewnfe fe ewrofnoernf voenvo3","Konnichiwa ejvnev edineoifne efveno elfnwe fep ifn"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        //Built-in Array Adapter
        //ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arr);
        //listView.setAdapter(ad);

        //Custom Adapter
        CustomAdapter ca = new CustomAdapter(this,R.layout.ankit_layout,arr);
        listView.setAdapter(ca);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Opening your " + i + " mail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}