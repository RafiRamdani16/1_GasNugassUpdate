package com.example.GasNugass;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class ListTugas extends Fragment {
    private LiveData<List<MyListTugas>> mAllTugas;
    private TugasViewModel tugasViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_list_tugas, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.ListTugas);
        MyListTugasAdapter adapter = new MyListTugasAdapter(new MyListTugasAdapter.WordDiff());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        tugasViewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(TugasViewModel.class);
        tugasViewModel.getAllWords().observe(getViewLifecycleOwner(),myListTugas ->{
            adapter.submitList(myListTugas);
        } );
        return view;
    }

}