package com.school.service;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.school.model.Student;
import com.school.service.TranscriptGenerator;

public class TranscriptGeneratorTest {
    
    // Use the same predefined subjects as in your application
    private final String[] predefinedSubjects = {"Amharic", "Biology", "Chemistry", "English", "Maths", "Physics", "Tigrigna"};
    private final String transcriptFileName = "Alice's Transcript.txt";
    
    @Test
    public void testExportTranscript() {
        Map<String, Double> scores = new HashMap<>();
        for (String subject : predefinedSubjects) {
            scores.put(subject, 90.0);
        }
        Student student = new Student("Alice", 16, "Female", 9, "A", scores);
        TranscriptGenerator generator = new TranscriptGenerator();
        generator.exportTranscript(student);

        // Check that the transcript file was created
        File transcriptFile = new File(transcriptFileName);
        assertTrue("Transcript file should exist after export", transcriptFile.exists());

        // Clean up: Delete the transcript file after test (optional)
        transcriptFile.delete();
    }
}
