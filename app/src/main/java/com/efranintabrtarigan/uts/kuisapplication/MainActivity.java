package com.efranintabrtarigan.uts.kuisapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int UPDATE_STUDENT_REQUEST = 2;
    private StudentViewModel studentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Set up Adapter
        final StudentAdapter adapter = new StudentAdapter();
        recyclerView.setAdapter(adapter);

        // Set up ViewModel
        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        studentViewModel.getAllStudents().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                adapter.setStudents(students);
            }
        });

        // Set up listener for item click to open UpdateStudentActivity
        adapter.setOnItemClickListener(new StudentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Student student) {
                Intent intent = new Intent(MainActivity.this, UpdateStudentActivity.class);
                intent.putExtra("student", (CharSequence) student);  // Pass the selected student
                startActivityForResult(intent, UPDATE_STUDENT_REQUEST);
            }
        });

        // Floating Action Button to add a new student
        FloatingActionButton buttonAddStudent = findViewById(R.id.button_add_student);
        buttonAddStudent.setOnClickListener(v -> {
            // Open AddEditStudentActivity to add new student
            Intent intent = new Intent(MainActivity.this, AddEditStudentActivity.class);
            startActivity(intent);  // Open without expecting result for now
        });
    }

    // Override onActivityResult to handle result from UpdateStudentActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == UPDATE_STUDENT_REQUEST && resultCode == RESULT_OK && data != null) {
            String message = data.getStringExtra("result_message");
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No changes were made", Toast.LENGTH_SHORT).show();
        }
    }
}
