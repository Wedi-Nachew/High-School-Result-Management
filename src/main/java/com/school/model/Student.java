package com.school.model;

import java.util.Map;

public class Student {
    private String name;
    private int age;
    private String gender;
    private int grade;
    private String section;
    private Map<String, Double> subjectsScores;

    public Student(String name, int age, String gender, int grade, String section, Map<String, Double> subjectsScores) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.grade = grade;
        this.section = section;
        this.subjectsScores = subjectsScores;
    }

    // Getters and setters
    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }

    public int getAge() {
        return age;
    }
    public void setAge(int age) { this.age = age; }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) { this.gender = gender; }

    public int getGrade() {
        return grade;
    }
    public void setGrade(int grade) { this.grade = grade; }

    public String getSection() {
        return section;
    }
    public void setSection(String section) { this.section = section; }

    public Map<String, Double> getSubjectsScores() {
        return subjectsScores;
    }
    public void setSubjectsScores(Map<String, Double> subjectsScores) {
        this.subjectsScores = subjectsScores;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", grade=" + grade +
                ", section='" + section + '\'' +
                ", subjectsScores=" + subjectsScores +
                '}';
    }
}
