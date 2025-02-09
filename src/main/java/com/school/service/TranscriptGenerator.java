package com.school.service;

import com.school.model.Student;
import com.school.model.Transcript;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TranscriptGenerator {
    // Predefined subjects from the original code
    private static final String[] PREDEFINED_SUBJECTS = {"Amharic", "Biology", "Chemistry", "English", "Maths", "Physics", "Tigrigna"};

    public void exportTranscript(Student student) {
        Transcript transcript = new Transcript(student);
        String fileName = student.getName() + "'s Transcript.txt";
        int totalResult = 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(String.format("%10s%20s\n", "", student.getName()));
            writer.write(String.format("%7sSex: %-9s Grade: %d\n", "", student.getGender(), student.getGrade()));
            writer.write(String.format("%7sAge: %-9d Section: %s\n", "", student.getAge(), student.getSection()));
            writer.write(String.format("*-------------------------------------*\n"));
            writer.write(String.format("|\t%-16s\tResult\t      |\n", "Subject"));
            writer.write(String.format("---------------------------------------\n"));
            for (String subject : PREDEFINED_SUBJECTS) {
                int score = student.getSubjectsScores().get(subject) != null ? student.getSubjectsScores().get(subject).intValue() : 0;
                writer.write(String.format("|\t%-18s\t%-11d   |\n", subject, score));
                totalResult += score;
            }
            writer.write(String.format("---------------------------------------\n"));
            writer.write(String.format("|\t  %-15s %d\t          |\n", "Total", totalResult));
            writer.write(String.format("---------------------------------------\n"));
            writer.write(String.format("|\t %-15s %.2f\t          |\n", "Average", totalResult / (double)PREDEFINED_SUBJECTS.length));
            writer.write(String.format("*-------------------------------------*\n"));
            writer.flush();
            System.out.println("Successfully generated a transcript for " + student.getName());
        } catch (IOException e) {
            System.err.println("Error exporting transcript: " + e.getMessage());
        }
    }
}

