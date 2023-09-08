package com.example.minorproject.sentenceHandling;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class SentenceViewModel extends ViewModel {
    private MutableLiveData<List<Sentence>> liveSentences = new MutableLiveData<List<Sentence>>();
    public MutableLiveData<List<Sentence>> getLiveDataSentences(){
        return liveSentences;
    }

    public List<Sentence> getSentences(){
        return liveSentences.getValue();
    }
}
