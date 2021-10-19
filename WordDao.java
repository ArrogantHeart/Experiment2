package com.example.experiment2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.experiment2.word.WordContent;

import java.util.List;
@Dao
public interface WordDao {
    @Query("select * from WordBean")
    List<WordBean> getAll();

    @Query("select * from WordBean where word= :word")
    WordBean findByWord(String word);

    @Query("select * from WordBean where word like '%' || :word || '%' ")
    WordBean check(String word);

    @Update
    void update(WordBean wordBean);
    @Insert
    void insert(WordBean wordBean);
    @Delete
    void delete(WordBean wordBean);

}
