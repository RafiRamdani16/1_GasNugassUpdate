package com.example.GasNugass;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {MyListTugas.class}, version = 1, exportSchema = false)
public abstract class TugasRoomDatabase extends RoomDatabase {

    public abstract TugasDAO tugasDao();

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                TugasDAO dao = INSTANCE.tugasDao();

                MyListTugas tugas1 = new MyListTugas("Statistika",R.drawable.ic_baseline_star_24,"19-04-2021","26-04-2021","Data Kelompok");
                dao.insert(tugas1);
//
                MyListTugas tugas = new MyListTugas("Website",R.drawable.ic_baseline_star_24,"19-04-2021","26-04-2021","Mockup");
                dao.insert(tugas);
            });
        }
    };

    private static volatile TugasRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static TugasRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TugasRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TugasRoomDatabase.class, "word_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
