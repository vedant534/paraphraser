package com.example.minorproject.sentenceHandling;

public class Sentence {
    private String original,rephrased;

    public Sentence(String original,String rephrased){
        this.original = original;
        this.rephrased = rephrased;
    }

    public String getOriginal(){
        return original;
    }

    public String getRephrased(){
        return rephrased;
    }
}
