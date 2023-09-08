package com.example.minorproject.utteranceHandling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.minorproject.R;
import com.example.minorproject.sentenceHandling.Sentence;
import com.example.minorproject.sentenceHandling.SentenceActivity;
import com.example.minorproject.sentenceHandling.SentenceViewModel;

import java.util.List;

public class UtteranceActivity extends AppCompatActivity implements UtteranceAdapter.onUtteranceClickListener{

    private int position;
    private String sentence;
    private ViewModel viewModel;
    private RecyclerView recyclerView;
    private UtteranceAdapter adapter;
    private SentenceViewModel sentenceViewModel;
    private UtteranceViewModel utteranceViewModel;
    private final String TAG = UtteranceActivity.class.getSimpleName();

    public final static int UTTERANCE_RESULT_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utterance);

        Intent intent = getIntent();
        if(intent.hasExtra(SentenceActivity.sentenceKey)){
            sentence = intent.getStringExtra(SentenceActivity.sentenceKey);
        }
        else
            finish();

        if(intent.hasExtra(SentenceActivity.positionKey)){
            position = intent.getIntExtra(SentenceActivity.positionKey,-1);
        }
        else
            finish();

        initViews();

        utteranceViewModel = new ViewModelProvider(this).get(UtteranceViewModel.class);
        //sentenceViewModel = new ViewModelProvider().get(SentenceViewModel.class);

        utteranceViewModel.getUtterancesList().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                adapter.setDataset(strings);
            }
        });

        utteranceViewModel.loadUtterances(sentence);

    }

    void initViews(){
        recyclerView = (RecyclerView) findViewById(R.id.utteranceRecylerView);
        adapter = new UtteranceAdapter(this,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onUtteranceClick(int position) {
        Intent intent = new Intent();
        String utterance = utteranceViewModel.getUtterances().get(position);
        Log.d(TAG,utterance);
        //intent.putExtra(UTTERANCE_KEY,utterance);
        intent.setData(Uri.parse(utterance));
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
}