package com.example.multicounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int counterAllInt;
    int counter1Int;
    int counter2Int;
    int counter3Int;
    int counter4Int;

    int savedCounterAllInt;
    int savedCounter1Int;
    int savedCounter2Int;
    int savedCounter3Int;
    int savedCounter4Int;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Declaration of all buttons on the activity
        Button plus11;
        Button plus12;
        Button plus13;
        Button plus14;
        Button resetAll;
        Button reset1;
        Button reset2;
        Button reset3;
        Button reset4;

        //Declaration of all textViews on the app
        TextView counterAll;
        TextView counter1;
        TextView counter2;
        TextView counter3;
        TextView counter4;

        //Initialization of variables on the layout
        //Buttons
        plus11 = findViewById(R.id.plus11);
        plus12 = findViewById(R.id.plus12);
        plus13 = findViewById(R.id.plus13);
        plus14 = findViewById(R.id.plus14);
        resetAll = findViewById(R.id.resetAllButton);
        reset1 = findViewById(R.id.reset1);
        reset2 = findViewById(R.id.reset2);
        reset3 = findViewById(R.id.reset3);
        reset4 = findViewById(R.id.reset4);

        //TextViews
        counterAll = findViewById(R.id.counterAll);
        counter1 = findViewById(R.id.counter1);
        counter2 = findViewById(R.id.counter2);
        counter3 = findViewById(R.id.counter3);
        counter4 = findViewById(R.id.counter4);

        if(savedInstanceState != null){
            savedCounterAllInt = savedInstanceState.getInt("savedCounterAllInt");
            savedCounter1Int = savedInstanceState.getInt("savedCounter1Int");
            savedCounter2Int = savedInstanceState.getInt("savedCounter2Int");
            savedCounter3Int = savedInstanceState.getInt("savedCounter3Int");
            savedCounter4Int = savedInstanceState.getInt("savedCounter4Int");

            counterAll.setText(Integer.toString(savedCounterAllInt));
            counter1.setText(Integer.toString(savedCounter1Int));
            counter2.setText(Integer.toString(savedCounter2Int));
            counter3.setText(Integer.toString(savedCounter3Int));
            counter4.setText(Integer.toString(savedCounter4Int));
        } else {
            counter1Int = 0;
            counter2Int = 0;
            counter3Int = 0;
            counter4Int = 0;
        }


        //Click listeners for the counters
        plus11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter1Int++;
                counterAllInt = counter1Int + counter2Int + counter3Int + counter4Int;

                counter1.setText(Integer.toString(counter1Int));
                counterAll.setText(Integer.toString(counterAllInt));
            }
        });

        plus12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter2Int++;
                counterAllInt = counter1Int + counter2Int + counter3Int + counter4Int;

                counter2.setText(Integer.toString(counter2Int));
                counterAll.setText(Integer.toString(counterAllInt));
            }
        });

        plus13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter3Int++;
                counterAllInt = counter1Int + counter2Int + counter3Int + counter4Int;

                counter3.setText(Integer.toString(counter3Int));
                counterAll.setText(Integer.toString(counterAllInt));
            }
        });

        plus14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter4Int++;
                counterAllInt = counter1Int + counter2Int + counter3Int + counter4Int;

                counter4.setText(Integer.toString(counter4Int));
                counterAll.setText(Integer.toString(counterAllInt));
            }
        });


        //Reset buttons
        reset1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter1Int = 0;
                counterAllInt = counter1Int + counter2Int + counter3Int + counter4Int;

                counter1.setText(Integer.toString(counter1Int));
                counterAll.setText(Integer.toString(counterAllInt));
            }
        });

        reset2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter2Int = 0;
                counterAllInt = counter1Int + counter2Int + counter3Int + counter4Int;

                counter2.setText(Integer.toString(counter2Int));
                counterAll.setText(Integer.toString(counterAllInt));
            }
        });

        reset3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter3Int = 0;
                counterAllInt = counter1Int + counter2Int + counter3Int + counter4Int;

                counter3.setText(Integer.toString(counter3Int));
                counterAll.setText(Integer.toString(counterAllInt));
            }
        });

        reset4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter4Int = 0;
                counterAllInt = counter1Int + counter2Int + counter3Int + counter4Int;

                counter4.setText(Integer.toString(counter4Int));
                counterAll.setText(Integer.toString(counterAllInt));
            }
        });

        resetAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                counter1Int = 0;
                counter2Int = 0;
                counter3Int = 0;
                counter4Int = 0;
                counterAllInt = counter1Int + counter2Int + counter3Int + counter4Int;

                counter1.setText(Integer.toString(counter1Int));
                counter2.setText(Integer.toString(counter2Int));
                counter3.setText(Integer.toString(counter3Int));
                counter4.setText(Integer.toString(counter4Int));
                counterAll.setText(Integer.toString(counterAllInt));
            }
        });
        };

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("savedCounterAllInt", counterAllInt);
        savedInstanceState.putInt("savedCounter1Int", counter1Int);
        savedInstanceState.putInt("savedCounter2Int", counter2Int);
        savedInstanceState.putInt("savedCounter3Int", counter3Int);
        savedInstanceState.putInt("savedCounter4Int", counter4Int);

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        counterAllInt = savedInstanceState.getInt("savedCounterAllInt");
        counter1Int = savedInstanceState.getInt("savedCounter1Int");
        counter2Int = savedInstanceState.getInt("savedCounter2Int");
        counter3Int = savedInstanceState.getInt("savedCounter3Int");
        counter4Int = savedInstanceState.getInt("savedCounter4Int");
    }
}