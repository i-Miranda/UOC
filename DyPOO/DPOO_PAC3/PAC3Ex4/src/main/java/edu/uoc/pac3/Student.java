package edu.uoc.pac3;

import java.util.HashMap;
import java.util.Map;

public class Student {

    private String name;
    private Grades grades;
    private String email;

    public Student(String name, Grades grades, String email) {
        this.name = name;
        this.grades = grades;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public double getGrades() { return grades.calculateAverage(); }

    public String getEmail() {
        return email;
    }
}
