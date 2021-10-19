package com.example.experiment2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class WordBean {
    @PrimaryKey
    public int id;
    public String word;
    public String meaning;
    public String sample;

    public WordBean() {}

    @Ignore
    public WordBean(int id, String word, String meaning, String sample) {
        this.id = id;
        this.word = word;
        this.meaning = meaning;
        this.sample = sample;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    public int getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public String getMeaning() {
        return meaning;
    }

    public String getSample() {
        return sample;
    }
}
