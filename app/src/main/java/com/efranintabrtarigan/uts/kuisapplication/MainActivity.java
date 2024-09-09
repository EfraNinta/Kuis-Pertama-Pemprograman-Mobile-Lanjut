package com.efranintabrtarigan.uts.kuisapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private DatabaseHelper dbHelper;
    private ArrayList<String> mahasiswaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        listView = findViewById(R.id.listView);
        mahasiswaList = new ArrayList<>();

        loadMahasiswa();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    private void loadMahasiswa() {
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_MAHASISWA, null);
        mahasiswaList.clear();
        while (cursor.moveToNext()) {
            String nama = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAMA));
            mahasiswaList.add(nama);
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mahasiswaList);
        listView.setAdapter(adapter);
    }

    public void tambahMahasiswa(View view) {
        Intent intent = new Intent(this, TambahActivity.class);
        startActivity(intent);
    }
}