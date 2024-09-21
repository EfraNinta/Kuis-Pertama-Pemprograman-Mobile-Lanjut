package com.efranintabrtarigan.uts.kuisapplication;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentRepository {

    private StudentDao studentDao;
    private LiveData<List<Student>> allStudents;

    public StudentRepository(Application application) {
        StudentDatabase database = StudentDatabase.getInstance(application);
        studentDao = database.studentDao();
        allStudents = studentDao.getAllStudents();
    }

    public void insert(Student student) {
        new InsertStudentAsyncTask(studentDao).execute(student);
    }

    public void update(Student student) {
        new UpdateStudentAsyncTask(studentDao).execute(student);
    }

    public void delete(Student student) {
        new DeleteStudentAsyncTask(studentDao).execute(student);
    }

    public LiveData<List<Student>> getAllStudents() {
        return allStudents;
    }

    private static class InsertStudentAsyncTask extends AsyncTask<Student, Void, Void> {
        private StudentDao studentDao;

        private InsertStudentAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.insert(students[0]);
            return null;
        }
    }

    private static class UpdateStudentAsyncTask extends AsyncTask<Student, Void, Void> {
        private StudentDao studentDao;

        private UpdateStudentAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.update(students[0]);
            return null;
        }
    }

    private static class DeleteStudentAsyncTask extends AsyncTask<Student, Void, Void> {
        private StudentDao studentDao;

        private DeleteStudentAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.delete(students[0]);
            return null;
        }
    }
}

