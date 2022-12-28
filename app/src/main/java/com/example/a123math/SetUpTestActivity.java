package com.example.a123math;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SetUpTestActivity extends AppCompatActivity {
    private boolean noLimit = false;
    TextView questionsNum;
    TextView timeNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_test);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final int levelVal = this.getIntent().getIntExtra("keyLevel", 0);
        TextView title = findViewById(R.id.setTitle);
        title.setText(levelVal + "s");

        ImageButton backBtn = findViewById(R.id.back3);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SetUpTestActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        questionsNum = findViewById(R.id.numOfQuest);
        ImageButton upBtn = findViewById(R.id.upAction);
        ImageButton downBtn = findViewById(R.id.downAction);
        upBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeVal(10, questionsNum, 9, 50);
            }
        });
        downBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeVal(-10, questionsNum, 10, 51);
            }
        });

        timeNum = findViewById(R.id.timeLimit);
        ImageButton upBtn2 = findViewById(R.id.upAction2);
        ImageButton downBtn2 = findViewById(R.id.downAction2);
        upBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeVal(1, timeNum, 0, 10);
            }
        });
        downBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeVal(-1, timeNum, 1, 11);
            }
        });


        CheckBox unlimitedTime = findViewById(R.id.checkBox);
        unlimitedTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noLimit = unlimitedTime.isChecked();
            }
        });


        Button startBtn = findViewById(R.id.start);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int questAmount = Integer.parseInt(questionsNum.getText().toString());
                int timerAmount = Integer.parseInt(timeNum.getText().toString());
                timerAmount *= 60;
                if(noLimit){timerAmount = 0;}

                Intent i = new Intent(SetUpTestActivity.this,TestActivity.class);
                i.putExtra("questAmount", questAmount);
                i.putExtra("timerAmount", timerAmount);
                i.putExtra("keyLevel", levelVal);
                startActivity(i);
            }
        });

        Button pBtn = findViewById(R.id.practice);
        pBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SetUpTestActivity.this,MathActivity.class);
                i.putExtra("keyLevel", levelVal);
                startActivity(i);
            }
        });

    }

    private void changeVal(int change, TextView tv, int min, int max){
        int num = Integer.parseInt(tv.getText().toString());
        if (num > min && num < max) {
            num += change;
            String tempNum = "" + num;
            tv.setText(tempNum);
        }
    }
}