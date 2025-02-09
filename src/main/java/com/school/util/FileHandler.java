package com.school.util;

import com.school.model.Student;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileHandler {

    // Folder to store CSV files (adjust path if needed)
    private final String DATA_FOLDER = "src/main/resources/data/";

    public FileHandler() {
        ensureDataFolderExists();
    }

    // Ensure the data folder exists
    private void ensureDataFolderExists() {
        File folder = new File(DATA_FOLDER);
        if (!folder.exists() && !folder.mkdirs()) {
            System.err.println("Failed to create data folder: " + folder.getAbsolutePath());
        }
    }

    public void saveStudentsToCSV(List<Student> students, int grade) {
        ensureDataFolderExists(); // Ensure folder exists before writing
        String filename = DATA_FOLDER + "grade" + grade + ".csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Student student : students) {
                writer.write(convertStudentToCSV(student));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving students to CSV: " + e.getMessage());
        }
    }

    public List<Student> loadStudentsFromCSV(int grade) {
        List<Student> students = new ArrayList<>();
        String filename = DATA_FOLDER + "grade" + grade + ".csv";
        File file = new File(filename);
        if (!file.exists()) {
            return students;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Student student = parseStudentFromCSV(line);
                if (student != null) {
                    students.add(student);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading students from CSV: " + e.getMessage());
        }
        return students;
    }

    private String convertStudentToCSV(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(student.getName()).append(",");
        sb.append(student.getAge()).append(",");
        sb.append(student.getGender()).append(",");
        sb.append(student.getGrade()).append(",");
        sb.append(student.getSection()).append(",");
        // Format subjects as key:value; pairs
        Map<String, Double> subjectsScores = student.getSubjectsScores();
        for (Map.Entry<String, Double> entry : subjectsScores.entrySet()) {
            sb.append(entry.getKey()).append(":").append(entry.getValue()).append(";");
        }
        return sb.toString();
    }

    private Student parseStudentFromCSV(String line) {
        try {
            String[] parts = line.split(",", 6);
            String name = parts[0];
            int age = Integer.parseInt(parts[1]);
            String gender = parts[2];
            int grade = Integer.parseInt(parts[3]);
            String section = parts[4];
            String subjectsPart = parts[5];
            Map<String, Double> subjectsScores = new HashMap<>();
            String[] subjectEntries = subjectsPart.split(";");
            for (String entry : subjectEntries) {
                if (entry.trim().isEmpty()) continue;
                String[] subjectScore = entry.split(":");
                String subject = subjectScore[0];
                Double score = Double.parseDouble(subjectScore[1]);
                subjectsScores.put(subject, score);
            }
            return new Student(name, age, gender, grade, section, subjectsScores);
        } catch (Exception e) {
            System.err.println("Error parsing student from CSV: " + e.getMessage());
            return null;
        }
    }
}


