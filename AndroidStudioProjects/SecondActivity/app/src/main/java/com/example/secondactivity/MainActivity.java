package com.example.secondactivity;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button botonMensaje;
        EditText mezua;
        TextView mezua2;
        TextView mezua3;
        Intent intentBack = getIntent();
        String mezuaBueltan = intentBack.getStringExtra(EXTRA_MESSAGE);

        if (mezuaBueltan != null){
            mezua2 = findViewById(R.id.textView3);
            mezua3 = findViewById(R.id.textView4);
            mezua2.setText("Reply received");

            mezua3.setText(mezuaBueltan);
        }

        botonMensaje = findViewById(R.id.button);
        mezua = findViewById(R.id.editText1);

        botonMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mezuaStr;
                mezuaStr = mezua.getText().toString();

                Intent intent = new Intent(MainActivity.super.getApplicationContext(), SecondActivity.class);
                intent.putExtra(EXTRA_MESSAGE, mezuaStr);
                startActivity(intent);
            }
        });
    }



}