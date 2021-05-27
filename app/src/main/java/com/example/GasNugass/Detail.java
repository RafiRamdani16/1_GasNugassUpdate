package com.example.GasNugass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Detail extends AppCompatActivity {

    private TugasViewModel tugasViewModel;
    String namaTugas, tanggal, deadline, catatan;
    TextView kolomNama;
    TextView kolomTanggal;
    TextView kolomDeadline;
    TextView kolomCatatan;
    Button delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

         kolomNama =  findViewById(R.id.Name);
         kolomTanggal =  findViewById(R.id.Kolom_Tanggal);
         kolomDeadline =  findViewById(R.id.Kolom_Deadline);
         kolomCatatan =  findViewById(R.id.Kolom_Catatan);
         delete = findViewById(R.id.delete);
         getData();
         setData();

        tugasViewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(TugasViewModel.class);
        delete.setOnClickListener(View->{
            tugasViewModel.delete(namaTugas);
            finish();
        });

    }

    public void getData(){
        Bundle bundle = getIntent().getExtras();
        if(getIntent().hasExtra("NamaTugas")
                && getIntent().hasExtra("Tanggal")
                && getIntent().hasExtra("Deadline")
                && getIntent().hasExtra("Catatan")){

            namaTugas = bundle.getString("NamaTugas");
            tanggal = bundle.getString("Tanggal");
            deadline = bundle.getString("Deadline");
            catatan = bundle.getString("Catatan");
        }
        else{
            namaTugas = "-";
            tanggal = "-";
            deadline = "-";
            catatan = "-";
        }
    }

    public void setData(){
        kolomNama.setText(namaTugas);
        kolomTanggal.setText(tanggal);
        kolomDeadline.setText(deadline);
        kolomCatatan.setText(catatan);
    }
}