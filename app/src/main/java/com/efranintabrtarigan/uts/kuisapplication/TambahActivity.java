package com.efranintabrtarigan.uts.kuisapplication;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class TambahActivity  extends AppCompatActivity {

    private EditText editNama, editNIM;
    private Spinner spinnerProdi;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        dbHelper = new DatabaseHelper(this);
        editNama = findViewById(R.id.editNama);
        editNIM = findViewById(R.id.editNIM);
        spinnerProdi = findViewById(R.id.spinnerProdi);
    }

    public void simpanMahasiswa(View view) {
        String nama = editNama.getText().toString();
        String nim = editNIM.getText().toString();
        String prodi = spinnerProdi.getSelectedItem().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAMA, nama);
        values.put(DatabaseHelper.COLUMN_NIM, nim);
        values.put(DatabaseHelper.COLUMN_PRODI, prodi);
        db.insert(DatabaseHelper.TABLE_MAHASISWA, null, values);
        db.close();

        finish();
    }
}

