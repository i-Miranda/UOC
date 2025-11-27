package edu.uoc.pac3;

import java.util.HashMap;
import java.util.Map;

public class Student {

    private String name;
    private double[] grades;
    private String email;
    private Map<String, Student> database = new HashMap<>();

    public Student(String name, double[] grades, String email) {
        this.name = name;
        this.grades = grades;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public double[] getGrades() {
        return grades;
    }

    public String getEmail() {
        return email;
    }

    public double calculateAverage() {
        double sum = 0;

        for (double grade : grades) {
            sum += grade;
        }

        return grades.length == 0 ? 0 : sum / grades.length;
    }

    public void save(Student student) {
        database.put(student.getEmail(), student);
        System.out.println("Saving " + student.getName() + " to database...");
    }

    public Student findStudentByEmail(String email) {
        System.out.println("Searching for student with email: " + email);
        Student student = database.get(email);

        if (student == null) {
            System.out.println("No student found with that email.");
        } else {
            System.out.println("Student found: " + student.getName());
        }

        return student;
    }

    public void listAll() {
        System.out.println("=== All students in database ===");
        for (Student s : database.values()) {
            System.out.println("- " + s.getName() + " (" + s.getEmail() + ")");
        }
    }

}
