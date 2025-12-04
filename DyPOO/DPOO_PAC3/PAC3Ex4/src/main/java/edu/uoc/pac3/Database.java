package edu.uoc.pac3;

import java.util.HashMap;
import java.util.Map;

public class Database {
    private Map<String, Student> map = new HashMap<>();

    public void save(Student student) {
        map.put(student.getEmail(), student);
        System.out.println("Saving " + student.getName() + " to database...");
    }

    public Student findStudentByEmail(String email) {
        System.out.println("Searching for student with email: " + email);
        Student student = map.get(email);

        if (student == null) {
            System.out.println("No student found with that email.");
        } else {
            System.out.println("Student found: " + student.getName());
        }

        return student;
    }

    public void listAll() {
        System.out.println("=== All students in database ===");
        for (Student s : map.values()) {
            System.out.println("- " + s.getName() + " (" + s.getEmail() + ")");
        }
    }

}
