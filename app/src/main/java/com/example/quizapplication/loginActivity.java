package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//CREATING LOGIN APP USING SQLITE DATABASE
public class loginActivity extends AppCompatActivity {

    EditText username,password;
    Button btnlogin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//Initializing variables
        username=(EditText) findViewById(R.id.username1);
        password=(EditText) findViewById(R.id.password1);
        btnlogin=(Button) findViewById(R.id.signin1);
        DB=new DBHelper(this);
//When button is clicked
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();

                if(user.equals("")||pass.equals("")) //if fields are empty
                    Toast.makeText(loginActivity.this,"Please enter all the fields!", Toast.LENGTH_SHORT).show();
                else { //if fields are not empty
                    Boolean checkuserpass=DB.chekusernamepassword(user,pass);
                    if(checkuserpass==true){ //if user password exsists and  can start the quiz
                        Toast.makeText(loginActivity.this,"Sign in successful!",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else{ //if user doesn't exsists in database
                        Toast.makeText(loginActivity.this,"Invalid Credentials!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}