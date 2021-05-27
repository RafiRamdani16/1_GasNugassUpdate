package com.example.GasNugass;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DataTugas")
public class MyListTugas {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "NamaTugas")
    private String NamaTugas;

    private String Tanggal;
    @ColumnInfo(name = "Deadline")
    private String Deadline;
    private String Catatan;
    private int imgId;

    public MyListTugas(String NamaTugas, int imgId, String Tanggal, String Deadline, String Catatan) {
        this.NamaTugas = NamaTugas;
        this.imgId = imgId;
        this.Tanggal = Tanggal;
        this.Deadline = Deadline;
        this.Catatan = Catatan;
    }
    public String getNamaTugas() {
        return NamaTugas;
    }
    public String getTanggal(){return Tanggal;}
    public String getDeadline(){return Deadline;}
    public void setDeadline(String Deadline){this.Deadline = Deadline;}
    public String getCatatan(){return Catatan;}
    public void setCatatan(String Catatan){this.Catatan = Catatan;}
    public int getImgId() {
        return imgId;
    }

}