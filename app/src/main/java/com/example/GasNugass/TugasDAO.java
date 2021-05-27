package com.example.GasNugass;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TugasDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(MyListTugas tugas);

    @Query("DELETE FROM DataTugas Where NamaTugas = :Dnama")
    void delete(String Dnama);

    @Query("SELECT * FROM DataTugas ORDER BY Deadline ASC")
    LiveData<List<MyListTugas>> getAlphabetizedTugas();
}
