package com.efranintabrtarigan.uts.kuisapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateStudentActivity extends AppCompatActivity {

    private EditText editTextName, editTextNIM;
    private Spinner spinnerProgram;
    private Button buttonUpdate, buttonDelete;
    private StudentViewModel studentViewModel;

    private Student currentStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        // Initialize views
        editTextName = findViewById(R.id.edit_text_name);
        editTextNIM = findViewById(R.id.edit_text_nim);
        spinnerProgram = findViewById(R.id.spinner_program);
        buttonUpdate = findViewById(R.id.button_update);
        buttonDelete = findViewById(R.id.button_delete);

        // Initialize ViewModel
        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

        // Set up spinner with program data
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.programs_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProgram.setAdapter(adapter);

        // Get Student object from Intent
        Intent intent = getIntent();
        currentStudent = (Student) intent.getSerializableExtra("student");

        if (currentStudent != null) {
            // Pre-fill fields with student data
            editTextName.setText(currentStudent.getName());
            editTextNIM.setText(currentStudent.getNim());
            spinnerProgram.setSelection(adapter.getPosition(currentStudent.getStudyProgram()));
        } else {
            Toast.makeText(this, "Error loading student data", Toast.LENGTH_SHORT).show();
            finish();  // Close activity if student data is not available
        }

        // Handle update button click
        buttonUpdate.setOnClickListener(v -> updateStudent());

        // Handle delete button click
        buttonDelete.setOnClickListener(v -> deleteStudent());
    }

    private void updateStudent() {
        String name = editTextName.getText().toString().trim();
        String nim = editTextNIM.getText().toString().trim();
        String program = spinnerProgram.getSelectedItem().toString();

        if (name.isEmpty() || nim.isEmpty() || program.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Update currentStudent object
        currentStudent.setName(name);
        currentStudent.setNim(nim);
        currentStudent.setStudyProgram(program);

        // Update student in the database using ViewModel
        studentViewModel.update(currentStudent);

        // Set result and close activity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result_message", "Student updated successfully");
        setResult(RESULT_OK, resultIntent);
        finish();  // Close activity
    }

    private void deleteStudent() {
        // Delete the student from the database using ViewModel
        studentViewModel.delete(currentStudent);

        // Set result and close activity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result_message", "Student deleted successfully");
        setResult(RESULT_OK, resultIntent);
        finish();  // Close activity
    }
}
