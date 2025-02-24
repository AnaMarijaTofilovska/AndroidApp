package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//CREATING REGISTER, USING SQLITE DATABASE
public class SQLiteActivity extends AppCompatActivity {

    EditText username,password,repassword;
    Button singup,signin;
    DBHelper DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        repassword=(EditText) findViewById(R.id.repassword);
        singup=(Button) findViewById(R.id.singup);
        signin=(Button) findViewById(R.id.singin);
        DB=new DBHelper(this);
//Creating Listeners for Buttons,what should happen when user clicks on button
        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String repass=repassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals("")) //if boxes are left blank
                    Toast.makeText(SQLiteActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                else { //if boxes have data, we check if user exsists or not
                    if(pass.equals(repass)){  //if passwords match
                        Boolean chekusers=DB.chekuser(user); //check in database if user exsists
                        if(chekusers==false){ //if user not exsists, we insert the data
                            Boolean insert=DB.insertData(user,pass);
                            if(insert==true){ //if data has been written in database,users can play the quiz
                                Toast.makeText(SQLiteActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext() ,MainActivity.class);
                                startActivity(intent);
                            }else { //if insetion failed
                                Toast.makeText(SQLiteActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{ //if we already have that user in database
                            Toast.makeText(SQLiteActivity.this, "User already exists!Please sign in",Toast.LENGTH_SHORT).show();
                        }
                    }else { //if passwords do not match
                        Toast.makeText(SQLiteActivity.this,"Passwords not matching!",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
//Initializing second button
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),loginActivity.class); //redirecting to login page if sign in button is clicked
                    startActivity(intent);


            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),loginActivity.class);
                startActivity(intent);
            }
        });

    }


}