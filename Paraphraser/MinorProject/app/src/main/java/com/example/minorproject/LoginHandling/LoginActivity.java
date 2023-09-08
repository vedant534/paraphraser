package com.example.minorproject.LoginHandling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.minorproject.LoadFileActivity;
import com.example.minorproject.R;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText,passwordEditText;
    private Button signUpButton,signInButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initializing the Views
        sharedPreferences = this.getSharedPreferences(getString(R.string.login_preference_file), Context.MODE_PRIVATE);

        initViews();

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(valid()){
                    Intent intent = new Intent(LoginActivity.this, LoadFileActivity.class);
                    startActivity(intent);
                }
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, NewUserActivity.class);
                startActivity(intent);
            }
        });
    }

    boolean valid(){
        String username = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if(TextUtils.isEmpty(username)){
            Toast.makeText(LoginActivity.this,"Username can't be empty",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this,"Password can't be empty",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.equals(password,sharedPreferences.getString(username,""))){
            return true;
        }
        else{
            Toast.makeText(LoginActivity.this,"Either username or password is incorrect",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    void initViews()
    {
        signInButton = (Button) findViewById(R.id.signin_button);
        signUpButton = (Button) findViewById(R.id.signup_button);

        emailEditText = (EditText) findViewById(R.id.email_editText);
        passwordEditText = (EditText) findViewById(R.id.password_editText);
    }
}