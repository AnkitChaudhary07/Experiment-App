package com.example.trainingapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var button:Button;
    lateinit var textView:TextView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        button.setOnClickListener {
            var name:String = textView.text.toString();
            Toast.makeText(this,"good luck anku",Toast.LENGTH_SHORT).show();
        }
    }
}