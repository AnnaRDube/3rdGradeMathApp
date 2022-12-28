package com.example.a123math;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestActivity extends AppCompatActivity {
    public int missed = 0;
    private int randNum = 0;
    private int staticNum = 0;
    private int quesCount = 0;
    int counter = 0;
    int questNum = 0;
    EditText ans;
    Button endBtn;
    private boolean timeUp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final int questAmount = this.getIntent().getIntExtra("questAmount", 0);
        final int timerAmount = this.getIntent().getIntExtra("timerAmount", 0);
        final int levelVal = this.getIntent().getIntExtra("keyLevel", 0);
        staticNum = levelVal;
        TextView title = findViewById(R.id.testTV);
        title.setText(levelVal + "s Test");

        counter = timerAmount;
        questNum = questAmount;

        ImageButton backBtn = findViewById(R.id.back2);
        endBtn = findViewById(R.id.end2);

        // Start timer
        TextView timee = findViewById(R.id.timer);
        if(counter != 0) {
            new CountDownTimer((counter * 1000L), 1000) {
                public void onTick(long millisUntilFinished) {
                    String clockVal = "" + (counter / 60) + ":";
                    int secs = counter % 60;
                    if(secs < 10){
                        clockVal += "0" + secs;
                    }
                    else {
                        clockVal += "" + secs;
                    }
                    timee.setText(clockVal);
                    counter--;
                }

                public void onFinish() {
                    timee.setText("FINISH!");
                    missed += questNum - quesCount;
                    timeUp = true;
                    hasFinished(levelVal);
                }
            }.start();
        } else { timee.setText(String.valueOf("No Timer")); }

        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endGame(levelVal);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endGame(levelVal);
            }
        });

        // user hits go. go to next question or end
        Button goBtn = findViewById(R.id.gogo);
        ans = findViewById(R.id.numAns);
        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hasFinished(levelVal);
            }
        });

        // Get random number
        TextView num1 = findViewById(R.id.testNum1);
        num1.setText("" + levelVal);
        getNum(levelVal);

        // Get answer
        ans.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager mgr = (InputMethodManager) getApplication().getSystemService(Context.INPUT_METHOD_SERVICE);
                    mgr.hideSoftInputFromWindow(ans.getWindowToken(), 0);
                    hasFinished(levelVal);
                    return true;
                }
                return false;
            }
        });
    }

    private void hasFinished(int levelVal){
        if (!ans.getText().toString().equals("")) {
            int num3 = Integer.parseInt(ans.getText().toString());
            getResult(num3, randNum, staticNum);
            quesCount++;
        }
        if(quesCount == questNum || timeUp) {
            ans.setText("");
            ans.setEnabled(false);
            TextView er = findViewById(R.id.endResult2);
            float correct = questNum - missed;
            float score = (correct / questNum) * 100;
            er.setText("Grade: " + Math.round(score));
            er.setBackgroundColor(Color.parseColor("#C0F3E9"));
            endBtn.setVisibility(View.VISIBLE);
        } else if(!ans.getText().toString().equals("")){
            ans.setText("");
            getNum(levelVal);
        }
    }

    // Get random number
    private void getNum(int levelVal){
        TextView questOn = findViewById(R.id.questNumOn);
        String t = (quesCount + 1) + " of " + questNum;
        questOn.setText(t);
        TextView num2 = findViewById(R.id.testNum2);
        Random rand = new Random();
        randNum = rand.nextInt(13);
        num2.setText(Integer.toString(randNum));
    }

    private void getResult(int num3, int randNum, int staticNum){
        int correctAns = randNum * staticNum;
        if(num3 != correctAns){
            missed++;
        }
    }

    private void endGame(int levelVal){
        Intent i = new Intent(TestActivity.this,SetUpTestActivity.class);
        i.putExtra("keyLevel", levelVal);
        startActivity(i);
    }
}