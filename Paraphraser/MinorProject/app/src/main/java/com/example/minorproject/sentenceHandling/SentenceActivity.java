package com.example.minorproject.sentenceHandling;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompatSideChannelService;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.View;

import com.example.minorproject.EndActivity;
import com.example.minorproject.LoadFileActivity;
import com.example.minorproject.R;
import com.example.minorproject.utteranceHandling.UtteranceActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SentenceActivity extends AppCompatActivity implements SentenceAdapter.onSentenceClickListener{

    private FloatingActionButton saveButton;
    private RecyclerView recyclerView;
    private SentenceAdapter adapter;
    private SentenceViewModel viewModel;
    private String TAG = SentenceActivity.class.getSimpleName();
    public final static String sentenceKey = "com.example.minorproject.sentenceHandling.sentenceKey";
    public final static String positionKey = "com.example.minorproject.sentenceHandling.positionKey";

    private static final int CREATE_FILE = 1;

    private int clickPosition;
    private String originalSentence;

    private Uri fileUri;

    private List<Sentence> mSentences;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence);

        initViews();
        
        Intent intent = getIntent();
        if(intent.hasExtra(LoadFileActivity.paragraphKey)){
            String paragraph = intent.getStringExtra(LoadFileActivity.paragraphKey);
            fileUri = intent.getData();
            setAdapterData(paragraph);
        }

    }
    
    void initViews(){
        recyclerView = (RecyclerView) findViewById(R.id.sentence_recylerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SentenceAdapter(this,this);
        recyclerView.setAdapter(adapter);

        saveButton = (FloatingActionButton) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createFile();
            }
        });
    }



    void setAdapterData(String paragraph){
        mSentences = SentenceUtils.paragraphToSentences(paragraph);
        //viewModel.getLiveDataSentences().setValue(sentences);
        adapter.changeDataSet(mSentences);
    }

    @Override
    public void onSentenceClick(int position) {
        clickPosition = position;
        Intent intent = new Intent(this, UtteranceActivity.class);
        originalSentence = mSentences.get(position).getOriginal();
        intent.putExtra(sentenceKey,originalSentence);
        intent.putExtra(positionKey,position);
        startActivityForResult(intent,UtteranceActivity.UTTERANCE_RESULT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG,requestCode + " " + resultCode);
        if(requestCode == UtteranceActivity.UTTERANCE_RESULT_CODE && resultCode == Activity.RESULT_OK){
            String utterance = data.getData().toString();
            //viewModel.getSentences().set(clickPosition,new Sentence(originalSentence,utterance));
            mSentences.set(clickPosition,new Sentence(originalSentence,utterance));
            adapter.changeDataSet(mSentences);
            //Log.d(TAG,viewModel.getSentences().get(clickPosition).getRephrased());

        }
        else if(requestCode == CREATE_FILE && resultCode == Activity.RESULT_OK){
            fileUri = data.getData();
            alterDocument();
        }
    }

    private void createFile() {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TITLE, "minorproject.txt");

        startActivityForResult(intent, CREATE_FILE);
    }

    private void alterDocument() {
        String paragraph = SentenceUtils.sentencesToParagraph(mSentences);
        try {
            ParcelFileDescriptor pfd = this.getContentResolver().
                    openFileDescriptor(fileUri, "w");
            Log.d(TAG,"Started writing paragraph");
            FileOutputStream fileOutputStream =
                    new FileOutputStream(pfd.getFileDescriptor());
            fileOutputStream.write((paragraph).getBytes());
            // Let the document provider know you're done by closing the stream.
            fileOutputStream.close();
            pfd.close();

            closeActivity();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void closeActivity(){
        Intent intent = new Intent(this, EndActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

}