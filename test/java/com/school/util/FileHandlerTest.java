package com.school.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.school.model.Student;
// import src.main.java.com.school.util.FileHandler;

public class FileHandlerTest {
    
    private FileHandler fileHandler;
    private final int testGrade = 9;
    private final String testDataFile = "src/main/resources/data/grade" + testGrade + ".csv";

    @Before
    public void setUp() {
        fileHandler = new FileHandler();
        // Remove the test CSV file if it exists to ensure a clean state.
        File file = new File(testDataFile);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testSaveAndLoadStudent() {
        Map<String, Double> scores = new HashMap<>();
        scores.put("Maths", 88.0);
        scores.put("English", 92.0);
        Student student = new Student("Test Student", 16, "Female", testGrade, "A", scores);

        // Save a single student record
        // Since FileHandler works with lists, create a list containing one student.
        fileHandler.saveStudentsToCSV(java.util.Arrays.asList(student), testGrade);
        
        // Now load the students for the test grade
        List<Student> loadedStudents = fileHandler.loadStudentsFromCSV(testGrade);
        assertFalse("Loaded student list should not be empty", loadedStudents.isEmpty());
        Student loadedStudent = loadedStudents.get(0);
        
        assertEquals("Student name should match", student.getName(), loadedStudent.getName());
        assertEquals("Student age should match", student.getAge(), loadedStudent.getAge());
        assertEquals("Student grade should match", student.getGrade(), loadedStudent.getGrade());
        assertEquals("Student Maths score should match", 
                     student.getSubjectsScores().get("Maths"), 
                     loadedStudent.getSubjectsScores().get("Maths"));
        assertEquals("Student English score should match", 
                     student.getSubjectsScores().get("English"), 
                     loadedStudent.getSubjectsScores().get("English"));
    }

    @After
    public void tearDown() {
        // Clean up the test CSV file after tests
        File file = new File(testDataFile);
        if (file.exists()) {
            file.delete();
        }
    }
}
