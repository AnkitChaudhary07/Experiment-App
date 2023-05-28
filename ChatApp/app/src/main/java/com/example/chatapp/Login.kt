package com.example.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import java.net.PasswordAuthentication

class Login : AppCompatActivity() {

    private lateinit var email: TextInputEditText;
    private lateinit var password: TextInputEditText;
    private lateinit var button_login: Button;
    private lateinit var click_signup_text: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        button_login = findViewById(R.id.button_login);
    }
}