package com.example.minorproject.utteranceHandling;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UtteranceQuery {
    @SerializedName("utterance")
    @Expose
    private String utterance;

    public UtteranceQuery(String utterance){
        this.utterance = utterance;
    }

    public String getUtterance(){
        return utterance;
    }
}
