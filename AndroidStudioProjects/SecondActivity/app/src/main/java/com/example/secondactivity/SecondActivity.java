package com.example.secondactivity;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        EditText mezuaBueltan;
        Button botoia;
        Button atzera;

        mezuaBueltan = findViewById(R.id.editText);
        botoia = findViewById(R.id.button2);
        atzera = findViewById(R.id.button3);




        Intent intent = getIntent();

        String mezuaStr = intent.getStringExtra(EXTRA_MESSAGE);

        TextView mezua;
        mezua = findViewById(R.id.textView2);

        mezua.setText(mezuaStr);



        botoia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mezuaStr;
                mezuaStr = mezuaBueltan.getText().toString();

                Intent intent = new Intent(SecondActivity.super.getApplicationContext(), MainActivity.class);
                intent.putExtra(EXTRA_MESSAGE, mezuaStr);
                startActivity(intent);
            }
        });

        atzera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishActivity(4);
            }
        });
    }



}
