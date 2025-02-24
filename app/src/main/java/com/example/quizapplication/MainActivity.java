package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<QuizList> listof; //declare some variables and initialize the 'listof' ArrayList which is public so it can be accesed from other classes in app

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listof=new ArrayList<>(); //initializing new instance of ArrayList with QuizList objects for quiz questions and options
        listof.add(new QuizList("Which is the largest country in the world?","China","USA","Germany","Russia","Russia"));
        listof.add(new QuizList("Which country has the largest population in the world?","China","USA","Germany","Russia","China"));
        listof.add(new QuizList("Ljubljana is the capital of which country?","Macedonia","Slovenia","Bulgaria","Serbia","Slovenia"));
        listof.add(new QuizList("Which is the hottest continent on Earth?","Europe","Asia","Africa","Oceania","Africa"));
        listof.add(new QuizList("The United States consists of how many states?","10","35","30","50","50"));
        listof.add(new QuizList("In which country would you find the Leaning Tower of Pisa?","China","Italy","Germany","Russia","Italy"));

        new Handler().postDelayed(new Runnable() { //i use handler to delay exe of next of the code in run by 1500 miliseconds
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,DashboardActivity.class); //Inent object to navigate users to quiz to user
                startActivity(intent);
            }
        },1500);
    }
}