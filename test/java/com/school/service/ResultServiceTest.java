package com.school.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.school.model.Student;
import com.school.service.ResultService;

public class ResultServiceTest {
    
    private ResultService resultService;
    // Use a test grade (e.g., 9) for these tests
    private final int testGrade = 9;
    // Define the predefined subjects (ensure these keys match those used in your application)
    private final String[] predefinedSubjects = {"Amharic", "Biology", "Chemistry", "English", "Maths", "Physics", "Tigrigna"};

    @Before
    public void setUp() {
        resultService = new ResultService();
        // Optionally, clear the CSV file for the test grade to start fresh.
        // For example, delete the test CSV file if it exists:
        File file = new File("src/main/resources/data/grade" + testGrade + ".csv");
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testAddAndSearchStudent() {
        Map<String, Double> scores = new HashMap<>();
        // Add scores for each predefined subject
        for (String subject : predefinedSubjects) {
            scores.put(subject, 90.0);
        }
        Student student = new Student("John Doe", 16, "Male", testGrade, "A", scores);
        resultService.addStudent(student);
        
        Student found = resultService.searchStudent("John Doe", testGrade);
        assertNotNull("Student should be found after adding", found);
        assertEquals("Student name should match", "John Doe", found.getName());
        assertEquals("Student age should match", 16, found.getAge());
    }

    @Test
    public void testUpdateStudent() {
        Map<String, Double> scores = new HashMap<>();
        for (String subject : predefinedSubjects) {
            scores.put(subject, 85.0);
        }
        Student student = new Student("Jane Doe", 17, "Female", testGrade, "B", scores);
        resultService.addStudent(student);

        // Create updated student with a different age and updated scores
        Map<String, Double> updatedScores = new HashMap<>(scores);
        updatedScores.put("Maths", 95.0); // Updated Maths score
        Student updatedStudent = new Student("Jane Doe", 18, "Female", testGrade, "B", updatedScores);
        resultService.updateStudent("Jane Doe", testGrade, updatedStudent);

        Student found = resultService.searchStudent("Jane Doe", testGrade);
        assertNotNull("Student should be found after update", found);
        assertEquals("Updated student age should be 18", 18, found.getAge());
        assertEquals("Updated Maths score should be 95", 95.0, found.getSubjectsScores().get("Maths"), 0.0);
    }

    @Test
    public void testDeleteStudent() {
        Map<String, Double> scores = new HashMap<>();
        for (String subject : predefinedSubjects) {
            scores.put(subject, 80.0);
        }
        Student student = new Student("Bob Smith", 15, "Male", testGrade, "C", scores);
        resultService.addStudent(student);

        // Verify student exists
        Student foundBeforeDelete = resultService.searchStudent("Bob Smith", testGrade);
        assertNotNull("Student should be present before deletion", foundBeforeDelete);

        // Delete the student
        resultService.deleteStudent("Bob Smith", testGrade);

        Student foundAfterDelete = resultService.searchStudent("Bob Smith", testGrade);
        assertNull("Student should not be found after deletion", foundAfterDelete);
    }
    
    @Test
    public void testGetStudentsByGrade() {
        // Initially, no student should exist
        List<Student> students = resultService.getStudentsByGrade(testGrade);
        assertTrue("Initial student list should be empty", students.isEmpty());

        // Add a student and check again
        Map<String, Double> scores = new HashMap<>();
        for (String subject : predefinedSubjects) {
            scores.put(subject, 88.0);
        }
        Student student = new Student("Alice", 16, "Female", testGrade, "A", scores);
        resultService.addStudent(student);
        
        students = resultService.getStudentsByGrade(testGrade);
        assertFalse("Student list should not be empty after adding a student", students.isEmpty());
    }
}
