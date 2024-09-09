package com.efranintabrtarigan.uts.kuisapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    private EditText editNama, editNIM;
    private Spinner spinnerProdi;
    private DatabaseHelper dbHelper;
    private long mahasiswaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        dbHelper = new DatabaseHelper(this);
        editNama = findViewById(R.id.editNama);
        editNIM = findViewById(R.id.editNIM);
        spinnerProdi = findViewById(R.id.spinnerProdi);

        mahasiswaId = getIntent().getLongExtra("id", -1);
        loadMahasiswaDetails();
    }

    private void loadMahasiswaDetails() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_MAHASISWA + " WHERE id = ?", new String[]{String.valueOf(mahasiswaId)});
        if (cursor.moveToFirst()) {
            String nama = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAMA));
            String nim = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NIM));
            String prodi = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PRODI));
            editNama.setText(nama);
            editNIM.setText(nim);
            // Set spinner position if needed
        }
        cursor.close();
    }

    public void updateMahasiswa(View view) {
        String nama = editNama.getText().toString();
        String nim = editNIM.getText().toString();
        String prodi = spinnerProdi.getSelectedItem().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAMA, nama);
        values.put(DatabaseHelper.COLUMN_NIM, nim);
        values.put(DatabaseHelper.COLUMN_PRODI, prodi);
        db.update(DatabaseHelper.TABLE_MAHASISWA, values, "id = ?", new String[]{String.valueOf(mahasiswaId)});
        db.close();

        finish();
    }

    public void hapusMahasiswa(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_MAHASISWA, "id = ?", new String[]{String.valueOf(mahasiswaId)});
        db.close();
        finish();
    }
}