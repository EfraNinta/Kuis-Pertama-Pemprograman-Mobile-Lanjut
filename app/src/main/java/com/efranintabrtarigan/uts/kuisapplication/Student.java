package com.efranintabrtarigan.uts.kuisapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity(tableName = "students")
public class Student implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String nim;
    private String studyProgram;

    public Student(String name, String nim, String studyProgram) {
        this.name = name;
        this.nim = nim;
        this.studyProgram = studyProgram;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(String studyProgram) {
        this.studyProgram = studyProgram;
    }
}
