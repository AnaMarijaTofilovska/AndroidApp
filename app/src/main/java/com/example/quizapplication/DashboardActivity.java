package com.example.quizapplication;

import static com.example.quizapplication.MainActivity.listof;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    int timerValue=20;
    ProgressBar progressBar;
    List<QuizList>allQuestionsList;
    QuizList quizList;
    int index=0;
    TextView card_question,optionA,optionB,optionC,optionD;
    CardView cardOA,cardOB,cardOC,cardOD;
    int correctCount=0;
    int wrongCount=0;
   LinearLayout nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Hooks(); //function that initializes variables

        allQuestionsList=listof;//The allQuestionsList is set to the listof list ( from the previous activity)
        Collections.shuffle(allQuestionsList); // it shuffles the questions randomly
        quizList=listof.get(index); //The quizList is set to the first question in the list

        setAllData(); //function to set data to variables

        countDownTimer=new CountDownTimer(20000,1000) { //count down timer with duration of 20sec
            @Override
            public void onTick(long millisUntilFinished) {
                timerValue=timerValue-1;  //updates the timer accordingly to count down timer
                progressBar.setProgress(timerValue); //and progress bar too
            }

            @Override
            public void onFinish() { //if count down timer finishes alert dialog box is shown
                Dialog dialog=new Dialog(DashboardActivity.this);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.timer_dialog);

                dialog.findViewById(R.id.btn_tryAgain).setOnClickListener(new View.OnClickListener() { //dialog button
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(DashboardActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                });
                dialog.show();;

            }
        }.start();
    }

    private void setAllData() {   //responsible for setting the question and answer options to the respective text views
        card_question.setText(quizList.getQuestion());
        optionA.setText(quizList.getoA());
        optionB.setText(quizList.getoB());
        optionC.setText(quizList.getoC());
        optionD.setText(quizList.getoD());
    }

    private void Hooks() { //initializing variables
        progressBar=findViewById(R.id.quiz_timer);
        card_question=findViewById(R.id.card_question);
        optionA=findViewById(R.id.card_optionA);
        optionB=findViewById(R.id.card_optionB);
        optionC=findViewById(R.id.card_optionC);
        optionD=findViewById(R.id.card_optionD);

        cardOA=findViewById(R.id.cardA);
        cardOB=findViewById(R.id.cardB);
        cardOC=findViewById(R.id.cardC);
        cardOD=findViewById(R.id.cardD);

        nextButton=findViewById(R.id.nextButton);
    }
    public void Correct(CardView cardView){ //counting correct answers
        cardView.setBackgroundColor(getResources().getColor(R.color.pink));
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctCount++;
                index++;
                quizList=listof.get(index);
                resetColor();
                setAllData();
            }
        });

    }
    public void Wrong(CardView cardOA){ //counting wrong questions
        cardOA.setBackgroundColor(getResources().getColor(R.color.red));
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongCount++;
                if(index<listof.size()-1){
                    index++;
                    quizList=listof.get(index);
                    resetColor();
                    setAllData();

                }
                else {
                    GameWon();
                }
            }
        });

    }
    private void GameWon() {
        Intent intent=new Intent(DashboardActivity.this,WonActivity.class);
        intent.putExtra("Right_Answer_Count",correctCount);
        intent.putExtra("Wrong_Answer_Count",wrongCount);
        startActivity(intent);

    }
    public void enableButton(){
        cardOA.setClickable(true);
        cardOB.setClickable(true);
        cardOC.setClickable(true);
        cardOD.setClickable(true);
    }
    public void disableButton(){
        cardOA.setClickable(false);
        cardOB.setClickable(false);
        cardOC.setClickable(false);
        cardOD.setClickable(false);
    }
    public void resetColor(){
        cardOA.setBackgroundColor(getResources().getColor(R.color.white));
        cardOB.setBackgroundColor(getResources().getColor(R.color.white));
        cardOC.setBackgroundColor(getResources().getColor(R.color.white));
        cardOD.setBackgroundColor(getResources().getColor(R.color.white));
    }

    public void OptionClick(View view) {
        if(quizList.getoA().equals(quizList.getAnswer())){
            cardOA.setCardBackgroundColor(getResources().getColor(R.color.pink));
            if(index<listof.size()-1){
                Correct(cardOA);
            }
            else{
                GameWon();
            }
        }
        else{
            Wrong(cardOA);
        }
    }

    public void OptionClickB(View view) {
        if(quizList.getoB().equals(quizList.getAnswer())){
            cardOB.setCardBackgroundColor(getResources().getColor(R.color.pink));
            if(index<listof.size()-1){
               Correct(cardOB);
            }
            else{
                GameWon();
            }
        }
        else{
            Wrong(cardOB);
        }
    }

    public void OptionClickC(View view) {
        if(quizList.getoC().equals(quizList.getAnswer())){
            cardOC.setCardBackgroundColor(getResources().getColor(R.color.pink));
            if(index<listof.size()-1){
                Correct(cardOC);
            }
            else{
                GameWon();
            }
        }
        else{
            Wrong(cardOC);
        }
    }

    public void OptionClickD(View view) {
        if(quizList.getoD().equals(quizList.getAnswer())){
            cardOD.setCardBackgroundColor(getResources().getColor(R.color.pink));
            if(index<listof.size()-1){
                Correct(cardOD);
            }
            else{
                GameWon();
            }
        }
        else{
            Wrong(cardOD);
        }
    }
}