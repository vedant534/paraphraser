package com.example.minorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EndActivity extends AppCompatActivity {

    private Button newFileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        initViews();
    }

    void initViews(){
        newFileButton = (Button) findViewById(R.id.newFileButton);
        newFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EndActivity.this,LoadFileActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}