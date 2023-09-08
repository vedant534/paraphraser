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
import android.widget.TextView;
import android.widget.Toast;

import com.example.minorproject.LoadFileActivity;
import com.example.minorproject.R;

public class NewUserActivity extends AppCompatActivity {

    private EditText emailEditText,passwordEditText,rePasswordEditText;
    private SharedPreferences sharedPreferences;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        sharedPreferences = this.getSharedPreferences(getString(R.string.login_preference_file), Context.MODE_PRIVATE);
        initViews();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(valid()){
                    Intent intent = new Intent(NewUserActivity.this,LoadFileActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean valid(){
        String username = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String rePassword = rePasswordEditText.getText().toString();

        if(TextUtils.isEmpty(username)){
            Toast.makeText(NewUserActivity.this,"Username can't be empty",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(NewUserActivity.this,"Password can't be empty",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.equals(password,rePassword)){
            saveData(username,password);
            return true;
        }
        else{
            Toast.makeText(NewUserActivity.this,"Password doesn't match",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void saveData(String username,String password){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(username, password);
        editor.apply();
    }

    private void initViews(){
        emailEditText = (EditText) findViewById(R.id.newEmailEditText);
        passwordEditText = (EditText) findViewById(R.id.newPasswordEditText);
        rePasswordEditText = (EditText) findViewById(R.id.rePasswordEditText);

        registerButton = (Button) findViewById(R.id.registerButton);

    }
}