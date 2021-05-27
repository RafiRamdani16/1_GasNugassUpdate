package com.example.GasNugass;



import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class MyListTugasAdapter extends ListAdapter<MyListTugas, MyListTugasAdapter.ViewHolder> {
    // RecyclerView recyclerView;
    public MyListTugasAdapter(DiffUtil.ItemCallback<MyListTugas> diffCallback) {
        super(diffCallback);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item_tugas, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyListTugas tugas = getItem(position) ;
        holder.textView.setText(tugas.getNamaTugas());
        holder.imageView.setImageResource(tugas.getImgId());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("NamaTugas",tugas.getNamaTugas());
                bundle.putString("Tanggal",tugas.getTanggal());
                bundle.putString("Deadline",tugas.getDeadline());
                bundle.putString("Catatan",tugas.getCatatan());
                Intent  intent = new Intent(view.getContext(),Detail.class);
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });
    }


    static class WordDiff extends DiffUtil.ItemCallback<MyListTugas> {
        @Override
        public boolean areItemsTheSame(@NonNull MyListTugas oldItem, @NonNull MyListTugas newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull MyListTugas oldItem, @NonNull MyListTugas newItem) {
            return oldItem.getNamaTugas().equals(newItem.getNamaTugas());
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public TextView tanggal;
        public TextView deadline;
        public TextView catatan;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {

            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.deadline = (TextView) itemView.findViewById(R.id.deadline);
            this.tanggal = (TextView) itemView.findViewById(R.id.tanggal);
            this.catatan = (TextView) itemView.findViewById(R.id.catatan);
            this.textView = (TextView) itemView.findViewById(R.id.textView);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}
