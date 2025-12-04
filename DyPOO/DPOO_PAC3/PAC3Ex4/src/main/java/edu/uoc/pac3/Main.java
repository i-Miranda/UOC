package edu.uoc.pac3;

public class Main {
    public static void main(String[] args) {
        double[] grades1 = {9.0, 8.5, 7.5, 10.0};
        Grades s1Grades = new Grades(grades1);
        Student s1 = new Student("David", s1Grades, "dgarciaso@uoc.edu");

        double[] grades2 = {6.0, 7.0, 8.0, 7.5};
        Grades s2Grades = new Grades(grades2);
        Student s2 = new Student("Carles", s2Grades, "cgallel94bcn@uoc.edu");

        Database db = new Database();

        // Save to database
        db.save(s1);
        db.save(s2);

        // List all from database
        db.listAll();

        // Get student from database
        Student found = db.findStudentByEmail("cgallel94bcn@uoc.edu");

        if (found != null) {
            System.out.println("Average grade for " + found.getName() + ": " + found.getGrades());
        } else {
            System.out.println("Student not found, cannot send email.");
        }
    }
}
