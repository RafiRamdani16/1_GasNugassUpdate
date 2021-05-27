package com.example.GasNugass;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

 class TugasRepository {
    private TugasDAO mTugasDao;
    private LiveData<List<MyListTugas>> mAllTugas;


    TugasRepository(Application application){
        TugasRoomDatabase dbTugas =TugasRoomDatabase.getDatabase(application);
        mTugasDao = dbTugas.tugasDao();
        mAllTugas =mTugasDao.getAlphabetizedTugas();
    }

    LiveData<List<MyListTugas>> getAllTugas() {
        return mAllTugas;
    }

    void insert(MyListTugas tugas) {
        TugasRoomDatabase.databaseWriteExecutor.execute(() -> {
            mTugasDao.insert(tugas);
        });
    }

    void delete(String Dnama){
        TugasRoomDatabase.databaseWriteExecutor.execute(()->{
            mTugasDao.delete(Dnama);
        });
    }


}

