package edu.uoc.pac3;

public class Grades {
    private double[] grades;

    Grades(double[] grades) {
        this.grades = grades;
    }

    public double calculateAverage() {
        double sum = 0;

        for (double grade : grades) {
            sum += grade;
        }

        return grades.length == 0 ? 0 : sum / grades.length;
    }
}
