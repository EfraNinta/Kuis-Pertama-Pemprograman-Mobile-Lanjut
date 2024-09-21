package com.efranintabrtarigan.uts.kuisapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddEditStudentActivity extends AppCompatActivity {
    public static final String EXTRA_NAME = "com.example.roomapp.EXTRA_NAME";
    public static final String EXTRA_NIM = "com.example.roomapp.EXTRA_NIM";
    public static final String EXTRA_PROGRAM = "com.example.roomapp.EXTRA_PROGRAM";

    private EditText editTextName;
    private EditText editTextNim;
    private Spinner spinnerProgram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        editTextName = findViewById(R.id.edit_text_name);
        editTextNim = findViewById(R.id.edit_text_nim);
        spinnerProgram = findViewById(R.id.spinner_program);

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.programs_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProgram.setAdapter(adapter);

        Button buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(v -> {
            saveStudent();
        });
    }

    private void saveStudent() {
        String name = editTextName.getText().toString();
        String nim = editTextNim.getText().toString();
        String program = spinnerProgram.getSelectedItem().toString();

        if (name.trim().isEmpty() || nim.trim().isEmpty()) {
            Toast.makeText(this, "Please insert all details", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_NIM, nim);
        data.putExtra(EXTRA_PROGRAM, program);

        setResult(RESULT_OK, data);
        finish();
    }
}
