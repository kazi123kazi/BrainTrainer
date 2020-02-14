package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Integer> answers=new ArrayList<Integer>();

    Button startButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    RelativeLayout relativeLayout;


    TextView sumTextView;
    TextView resultTextView;
    TextView pointsTextView;
    TextView timerTextView;


    int locationOfAnswer;
    int score;
    int noOfQuestions;
    public void playAgain(View view)
    {
        score=0;
        noOfQuestions=0;
        timerTextView.setText("0s");
        pointsTextView.setText("0/0");
        resultTextView.setText(" ");
        playAgainButton.setVisibility(View.INVISIBLE);
        generate();

        new CountDownTimer(30100,1000)
        {

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your Score\n"+"  "+Integer.toString(score)+"/"+Integer.toString(noOfQuestions));

            }
        }.start();
    }



    public void generate()
   {

       Random rand=new Random();
       int a=rand.nextInt(21);
       int b=rand.nextInt(21);

       sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));

       locationOfAnswer=rand.nextInt(4);
       int incorrectAnswer;
       answers.clear();


       for (int i = 0; i < 4; i++) {
           if(i==locationOfAnswer)
           {
               answers.add(a+b);

           }
           else{
               incorrectAnswer=rand.nextInt(41);
               while(incorrectAnswer==a+b)
               {
                   incorrectAnswer=rand.nextInt(41);

               }
               answers.add(incorrectAnswer);
           }

       }//end of  for loop
       button0.setText(Integer.toString(answers.get(0)));
       button1.setText(Integer.toString(answers.get(1)));
       button2.setText(Integer.toString(answers.get(2)));
       button3.setText(Integer.toString(answers.get(3)));

   }

    public void chooseAnswer(View view)
    {
        if(view.getTag().toString().equals(Integer.toString(locationOfAnswer)))
        {
            score++;
            resultTextView.setText("Correct!");
        }
        else
        {
            resultTextView.setText("Incorrect!");
        }
        noOfQuestions++;
        pointsTextView.setText(Integer.toString(score)+"/"+Integer.toString(noOfQuestions));
        generate();

    }


    public void start(View view )
    {
        startButton.setVisibility(View.INVISIBLE);
        relativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);

        sumTextView = (TextView) findViewById(R.id.sumTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        relativeLayout=(RelativeLayout)findViewById(R.id.relativeLayout);





    }

}
