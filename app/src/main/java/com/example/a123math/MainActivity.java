package com.example.a123math;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Button b1 = findViewById(R.id.b1);
        Button b2 = findViewById(R.id.b2);
        Button b3 = findViewById(R.id.b3);
        Button b4 = findViewById(R.id.b4);
        Button b5 = findViewById(R.id.b5);
        Button b6 = findViewById(R.id.b6);
        Button b7 = findViewById(R.id.b7);
        Button b8 = findViewById(R.id.b8);
        Button b9 = findViewById(R.id.b9);
        Button b10 = findViewById(R.id.b10);
        Button b11 = findViewById(R.id.b11);
        Button b12 = findViewById(R.id.b12);
        Button exit = findViewById(R.id.exit);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int levelChoice = Integer.parseInt(b1.getText().toString());
                startMath(levelChoice);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int levelChoice = Integer.parseInt(b2.getText().toString());
                startMath(levelChoice);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int levelChoice = Integer.parseInt(b3.getText().toString());
                startMath(levelChoice);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int levelChoice = Integer.parseInt(b4.getText().toString());
                startMath(levelChoice);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int levelChoice = Integer.parseInt(b5.getText().toString());
                startMath(levelChoice);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int levelChoice = Integer.parseInt(b6.getText().toString());
                startMath(levelChoice);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int levelChoice = Integer.parseInt(b7.getText().toString());
                startMath(levelChoice);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int levelChoice = Integer.parseInt(b8.getText().toString());
                startMath(levelChoice);
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int levelChoice = Integer.parseInt(b9.getText().toString());
                startMath(levelChoice);
            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int levelChoice = Integer.parseInt(b10.getText().toString());
                startMath(levelChoice);
            }
        });
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int levelChoice = Integer.parseInt(b11.getText().toString());
                startMath(levelChoice);
            }
        });
        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int levelChoice = Integer.parseInt(b12.getText().toString());
                startMath(levelChoice);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });
    }

    private void startMath(int levelChoice) {
        Intent i = new Intent(MainActivity.this,SetUpTestActivity.class);
        i.putExtra("keyLevel", levelChoice);
        startActivity(i);
    }
}