package com.example.minorproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minorproject.sentenceHandling.SentenceActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class LoadFileActivity extends AppCompatActivity {

    private final int loadFileCode = 200;
    String[] mimeTypes = {"application/pdf","text/plain","application/msword"};
    private String TAG = LoadFileActivity.class.getSimpleName();
    private TextView textView;

    private Uri uri;

    public static String paragraphKey = "com.example.minorproject.paragraphKey";
    public static String uriKey = "com.example.minorproject.uriKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_file);

        initViews();

    }

    void initViews(){
        textView = (TextView) findViewById(R.id.loadFile_textView);

        FloatingActionButton loadFileButtton = (FloatingActionButton) findViewById(R.id.loadFileButton);
        loadFileButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);

                //intent.setType("application/pdf");
                //intent.setType(mimeTypes.length == 1 ? mimeTypes[0] : "*/*");

                intent.setType("text/plain");

                //intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                startActivityForResult(intent,loadFileCode);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Activity Destroyed");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == loadFileCode && resultCode == Activity.RESULT_OK)
        {
            if(data != null){
                uri = data.getData();
                Log.d(TAG,uri.toString());
                //textView.setText(uri.toString());
                readFromUri(uri);
            }
            else
            {
                //Replace it with a snackbox
                Toast.makeText(this,"Invalid File Please Select Again",Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            textView.setText("Result not Ok");
        }
    }

    void readFromUri(Uri uri){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            BufferedReader reader = new BufferedReader(new InputStreamReader((Objects.requireNonNull(inputStream))));
            String line;
            while((line = reader.readLine()) != null){
                stringBuilder.append(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return;
        }

        Intent intent = new Intent(this, SentenceActivity.class);
        intent.putExtra(paragraphKey,stringBuilder.toString());
        intent.setData(uri);
        startActivity(intent);
    }
}