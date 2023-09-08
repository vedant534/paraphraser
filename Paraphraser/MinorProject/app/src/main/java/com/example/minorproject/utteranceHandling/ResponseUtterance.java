package com.example.minorproject.utteranceHandling;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseUtterance {
    @SerializedName("similarUtterances")
    @Expose
    private List<String> utterances;

    public List<String> getUtterances(){
        return utterances;
    }
}
