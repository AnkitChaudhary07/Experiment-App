package com.example.multiplicationtable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText editInput;
    private Button button;
    private TextView textView;
    String output = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editInput = findViewById(R.id.editInput);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = editInput.getText().toString();
                int n = Integer.parseInt(input);
                for(int i = 1; i <= 10; i++) {
                    output = output + n + " * " + i + " = " + n * i + "\n";
                }
                textView.setText(output);
            }
        });
    }
}