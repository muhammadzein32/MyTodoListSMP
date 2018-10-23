package com.blogspot.blogsetyaaji.mytodolist.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.blogspot.blogsetyaaji.mytodolist.model.Todo;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    // nama database
    private static final String NAMA_DATABASE = "db_todo";
    // versi database
    private static final int VERSI_DATABASE = 1;

    // construcotor
    public MyDatabaseHelper(Context context) {
        super(context, NAMA_DATABASE, null, VERSI_DATABASE);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // buat tabel pertama kaliketika aplikasi diinstal
        sqLiteDatabase.execSQL(Todo.BUAT_TABEL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // jika table ada sebelumnya, maka hapus table yang telah ada
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Todo.NAMA_TABEL);

        // buat tabel lagi
        onCreate(sqLiteDatabase);
    }

    // fungsi untuk menyimpan data
    public long simpanData(String nama, String deskripsi, String kategori) {
        // akses database untuk menambah data
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        // komponen untuk menyimpan value data
        ContentValues contentValues = new ContentValues();
        // masukkan data ke dalam contentValue
        contentValues.put(Todo.COLUMN_NAMA, nama);
        contentValues.put(Todo.COLUMN_DESKRIPSI, deskripsi);
        contentValues.put(Todo.COLUMN_KATEGORI, kategori);

        // masukkan data row
        long id = sqLiteDatabase.insert(Todo.NAMA_TABEL, null,
                contentValues);

        // tutup database
        sqLiteDatabase.close();

        // keluarkan hasil id dari proses menyimpan data
        return id;
    }
}
