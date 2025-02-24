package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class WonActivity extends AppCompatActivity {
    TextView resultLabel;
    int Right_Answer_Count,Wrong_Answer_Count;
    private Button restartbutton;

    //Declaring the List View Container and data to be displayed into list
    ListView listView;
    String [] quizQuestions= {
            "Which is the largest country in the world?",
            "Which country has the largest population in the world?",
            "Ljubljana is the capital of which country?",
            "Which is the hottest continent on Earth?",
            "The United States consists of how many states?",
            "In which country would you find the Leaning Tower of Pisa?"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);

        resultLabel=findViewById(R.id.resultLabel);
        Right_Answer_Count=getIntent().getIntExtra("Right_Answer_Count",0);
        Wrong_Answer_Count=getIntent().getIntExtra("Wrong_Answer_Count",0);


        TextView resultLabel=(TextView) findViewById(R.id.resultLabel);


        int score=getIntent().getIntExtra("Right_Answer_Count",0);
        //Using SharedPrefrences to store scores and i am making it private so i can only access it
        SharedPreferences settings= getSharedPreferences("quizapplication", Context.MODE_PRIVATE);


        resultLabel.setText(score+" /7");

        //Here I am working on my restart button
        restartbutton=findViewById(R.id.restartbutton);
        final MediaPlayer mediaPlayer= MediaPlayer.create(this,R.raw.replaysound);
        restartbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WonActivity.this,MainActivity.class);
                startActivity(intent);
                mediaPlayer.start();
            }
        });

        //Here I am working on the List View Container for The Quiz Questions
        listView=findViewById(R.id.listview);

        //Creating array adapter which will hold: context,layout that it will display,and the array list
        ArrayAdapter adapter=new ArrayAdapter<String>(
          this,
          android.R.layout.simple_list_item_1,
                quizQuestions
        );
        //the adapter connects the data to listview
        listView.setAdapter(adapter);







    }
}