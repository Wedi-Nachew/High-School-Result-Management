package com.school.model;

import java.util.Map;

public class Transcript {
    private Student student;
    private double GPA;
    private String remarks;

    public Transcript(Student student) {
        this.student = student;
        this.GPA = calculateGPA(student.getSubjectsScores());
        this.remarks = generateRemarks(this.GPA);
    }

    private double calculateGPA(Map<String, Double> subjectsScores) {
        if(subjectsScores == null || subjectsScores.isEmpty()){
            return 0.0;
        }
        double total = 0.0;
        for(Double score : subjectsScores.values()){
            total += score;
        }
        return total / subjectsScores.size();
    }

    private String generateRemarks(double gpa) {
        if(gpa >= 90){
            return "Excellent";
        } else if(gpa >= 75){
            return "Good";
        } else if(gpa >= 60){
            return "Average";
        } else {
            return "Needs Improvement";
        }
    }

    public Student getStudent() {
        return student;
    }
    public double getGPA() {
        return GPA;
    }
    public String getRemarks() {
        return remarks;
    }

    @Override
    public String toString() {
        return "Transcript{" +
                "student=" + student.getName() +
                ", GPA=" + GPA +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
