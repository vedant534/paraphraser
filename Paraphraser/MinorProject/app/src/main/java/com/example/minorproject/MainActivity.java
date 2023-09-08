package com.example.minorproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.minorproject.utteranceHandling.UtteranceViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private final String BASE_URL = " https://81ea-2402-8100-2259-c6ec-85e4-7e0e-46fe-abe5.in.ngrok.io";
    private final String TAG = MainActivity.class.getSimpleName();

    private UtteranceViewModel viewModel;
    private EditText queryText;
    private FloatingActionButton sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        viewModel = new ViewModelProvider(this).get(UtteranceViewModel.class);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadUtterances();
            }
        });
    }

    public void initViews(){
        queryText = (EditText) findViewById(R.id.utteranceEditText);
        sendButton = (FloatingActionButton) findViewById(R.id.sendButton);
    }

    public void loadUtterances(){
        String query = queryText.getText().toString();
        if(TextUtils.isEmpty(query))
        {
            Toast.makeText(this,"Query cannot be empty",Toast.LENGTH_SHORT).show();
            return;
        }

        viewModel.loadUtterances(query);
    }
}