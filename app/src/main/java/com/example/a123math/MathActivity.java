package com.example.a123math;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MathActivity extends AppCompatActivity {
    private List<Integer> usedNums = new ArrayList<Integer>();
    private List<Integer> missed = new ArrayList<Integer>();
    private int randNum = 0;
    EditText ans;
    Button endBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //set title and first number value
        final int levelVal = this.getIntent().getIntExtra("keyLevel", 0);
        TextView num1 = findViewById(R.id.num1);
        num1.setText("" + levelVal);
        TextView title = findViewById(R.id.mathTitle);
        title.setText(levelVal + "s");
        ImageButton backBtn = findViewById(R.id.back);
        endBtn = findViewById(R.id.end);

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

        Button goBtn = findViewById(R.id.gogo2);
        ans = findViewById(R.id.answer);
        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hasFinished(levelVal);
            }
        });

        // Get random number
        getNum();

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

    // Get random number
    private void getNum(){
        TextView num2 = findViewById(R.id.num2);
        Random rand = new Random();
        randNum = rand.nextInt(13);
        while(usedNums.contains(randNum)){
            randNum = rand.nextInt(13);
            if(usedNums.size() == 13){ break;}
        }
        usedNums.add(randNum);
        num2.setText(Integer.toString(randNum));
    }

    private void hasFinished(int levelVal){
        if(!ans.getText().toString().equals("")) {
            int num3 = Integer.parseInt(ans.getText().toString());
            getResult(num3, levelVal, randNum);
            ans.setText("");
            if (usedNums.size() == 13) {
                ans.setEnabled(false);
                TextView er = findViewById(R.id.endResult);
                int correct = 13 - missed.size();
                er.setText("You got " + correct + " out of " + 13 + " correct");
                er.setBackgroundColor(Color.parseColor("#C0F3E9"));
                endBtn.setVisibility(View.VISIBLE);
            } else {
                getNum();
            }
        }
    }

    private void getResult(int num3, int levelVal, int randNum){
        TextView result = findViewById(R.id.result);
        int correctAns = levelVal * randNum;
        if(num3 == correctAns){
            result.setTextColor(Color.parseColor("#1D9641"));
            result.setText("CORRECT!");
        } else {
            result.setTextColor(Color.parseColor("#C2171D"));
            result.setText("Incorrect. The answer is " + correctAns + ".");
            missed.add(num3);
        }
    }

    private void endGame(int levelVal){
        Intent i = new Intent(MathActivity.this,SetUpTestActivity.class);
        i.putExtra("keyLevel", levelVal);
        startActivity(i);
    }
}