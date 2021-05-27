package com.example.GasNugass;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TugasViewModel extends AndroidViewModel {

    private TugasRepository mRepository;

    private final LiveData<List<MyListTugas>> mAllTugas;

    public TugasViewModel (Application application) {
        super(application);
        mRepository = new TugasRepository(application);
        mAllTugas = mRepository.getAllTugas();
    }

    LiveData<List<MyListTugas>> getAllWords() { return mAllTugas; }
    public void insert(MyListTugas tugas) { mRepository.insert(tugas); }
    public void delete(String Dnama){mRepository.delete(Dnama);}
}
