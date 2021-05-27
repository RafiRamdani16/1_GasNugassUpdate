package com.example.GasNugass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


public class TambahTugas extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    EditText KNama;
    EditText KTanggal;
    EditText KDeadline;
    EditText KCatatan;
    Button submit;
    CheckBox Prioritas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_tugas);
        KNama = findViewById(R.id.K_Name);
        KTanggal = findViewById(R.id.K_Tanggal);
        KDeadline = findViewById(R.id.K_Deadline);
        KCatatan = findViewById(R.id.K_Catatan);
        submit = findViewById(R.id.button);



        submit.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(KNama.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String tugas = KNama.getText().toString();
                String Tanggal = KTanggal.getText().toString();
                String Deadline = KDeadline.getText().toString();
                String Catatan = KCatatan.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY, tugas);
                replyIntent.putExtra("Tanggal", Tanggal);
                replyIntent.putExtra("Deadline", Deadline);
                replyIntent.putExtra("Catatan", Catatan);

                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}