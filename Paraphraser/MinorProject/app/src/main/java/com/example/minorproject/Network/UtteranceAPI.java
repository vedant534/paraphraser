package com.example.minorproject.Network;

import com.example.minorproject.utteranceHandling.ResponseUtterance;
import com.example.minorproject.utteranceHandling.UtteranceQuery;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UtteranceAPI {
    @POST("get-utterances")
    Call<ResponseUtterance> getUtterances(@Body UtteranceQuery query);
}
