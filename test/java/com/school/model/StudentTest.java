package com.school.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.school.model.Student;

public class StudentTest {

    @Test
    public void testStudentGettersAndSetters() {
        Map<String, Double> scores = new HashMap<>();
        scores.put("Maths", 95.0);
        scores.put("English", 88.0);

        Student student = new Student("Tekle Beyene", 16, "Male", 10, "A", scores);
        assertEquals("Tekle Beyene", student.getName());
        assertEquals(16, student.getAge());
        assertEquals("Male", student.getGender());
        assertEquals(10, student.getGrade());
        assertEquals("A", student.getSection());
        assertEquals(95.0, student.getSubjectsScores().get("Maths"), 0.0);
        assertEquals(88.0, student.getSubjectsScores().get("English"), 0.0);

        // Test setters
        student.setName("Eden Desta");
        student.setAge(17);
        student.setGender("Female");
        student.setGrade(11);
        student.setSection("B");
        scores.put("Maths", 100.0);
        student.setSubjectsScores(scores);

        assertEquals("Eden Desta", student.getName());
        assertEquals(17, student.getAge());
        assertEquals("Female", student.getGender());
        assertEquals(11, student.getGrade());
        assertEquals("B", student.getSection());
        assertEquals(100.0, student.getSubjectsScores().get("Maths"), 0.0);
    }

    @Test
    public void testStudentToString() {
        Map<String, Double> scores = new HashMap<>();
        scores.put("Maths", 90.0);
        scores.put("Science", 85.0);
        Student student = new Student("Filmon", 15, "Male", 9, "C", scores);
        String result = student.toString();
        assertTrue("toString should contain the student name", result.contains("Filmon"));
        assertTrue("toString should contain the grade", result.contains("9"));
    }
}
