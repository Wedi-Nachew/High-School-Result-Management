package com.school.model;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.school.model.Student;
import com.school.model.Transcript;

public class TranscriptTest {

    @Test
    public void testCalculateGPA() {
        Map<String, Double> scores = new HashMap<>();
        scores.put("Maths", 80.0);
        scores.put("English", 90.0);
        scores.put("Science", 100.0);
        Student student = new Student("Bob", 16, "Male", 10, "A", scores);
        Transcript transcript = new Transcript(student);
        
        // GPA should be the average of the provided scores
        double expectedGPA = (80.0 + 90.0 + 100.0) / 3.0;
        assertEquals(expectedGPA, transcript.getGPA(), 0.001);
    }

    @Test
    public void testGenerateRemarks() {
        // Test a few GPA ranges to confirm remarks are generated appropriately.
        Map<String, Double> scores = new HashMap<>();
        // Test for Excellent
        scores.put("Maths", 95.0);
        scores.put("English", 95.0);
        Student student = new Student("Charlie", 17, "Male", 11, "B", scores);
        Transcript transcript = new Transcript(student);
        assertEquals("Excellent", transcript.getRemarks());

        // Test for Good
        scores.put("Maths", 80.0);
        scores.put("English", 80.0);
        student = new Student("Dana", 16, "Female", 10, "C", scores);
        transcript = new Transcript(student);
        assertEquals("Good", transcript.getRemarks());

        // Test for Average (you can adjust thresholds based on your implementation)
        scores.put("Maths", 65.0);
        scores.put("English", 65.0);
        student = new Student("Eva", 15, "Female", 9, "A", scores);
        transcript = new Transcript(student);
        assertEquals("Average", transcript.getRemarks());

        // Test for Needs Improvement
        scores.put("Maths", 50.0);
        scores.put("English", 50.0);
        student = new Student("Frank", 15, "Male", 9, "D", scores);
        transcript = new Transcript(student);
        assertEquals("Needs Improvement", transcript.getRemarks());
    }
}
