package com.example.GasNugass;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.app.Activity.RESULT_OK;


public class HomeFragment extends Fragment {
    private TugasViewModel tugasViewModel;
    private MyListTugasAdapter.ViewHolder mWordViewModel;
    final int kodeGallery = 100, kodeKamera = 99;
    Uri imageUri;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    ImageView foto;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.ListHome);
         foto = view.findViewById(R.id.fotoProfile);
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentGallery, kodeGallery);
            }
        });
        MyListTugasAdapter adapter = new MyListTugasAdapter(new MyListTugasAdapter.WordDiff());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        tugasViewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(TugasViewModel.class);
        tugasViewModel.getAllWords().observe(getViewLifecycleOwner(),myListTugas ->{
            adapter.submitList(myListTugas);
        } );

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener( View -> {
            Intent intent = new Intent(getContext(), TambahTugas.class);
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
        });
        return view;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == kodeGallery && resultCode == RESULT_OK){
            imageUri = data.getData();
            foto.setImageURI(imageUri);
        }

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            MyListTugas tugas = new MyListTugas(data.getStringExtra(TambahTugas.EXTRA_REPLY)
                    ,R.drawable.ic_baseline_star_24
                    ,data.getStringExtra("Tanggal")
                    ,data.getStringExtra("Deadline")
                    ,data.getStringExtra("Catatan"));
            tugasViewModel.insert(tugas);
        } else if(requestCode != kodeGallery) {
            Toast.makeText(
                    getContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

}