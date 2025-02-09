package com.school.service;

import com.school.model.Student;
import com.school.util.FileHandler;
import java.util.List;

public class ResultService {
    private FileHandler fileHandler;

    public ResultService() {
        this.fileHandler = new FileHandler();
    }

    public void addStudent(Student student) {
        List<Student> students = fileHandler.loadStudentsFromCSV(student.getGrade());
        students.add(student);
        fileHandler.saveStudentsToCSV(students, student.getGrade());
    }

    public List<Student> getStudentsByGrade(int grade) {
        return fileHandler.loadStudentsFromCSV(grade);
    }

    public Student searchStudent(String name, int grade) {
        List<Student> gradeStudents = getStudentsByGrade(grade);
        for (Student s : gradeStudents) {
            if (s.getName().startsWith(name)) {
                return s;
            }
        }
        return null;
    }

    public void updateStudent(String name, int grade, Student updatedStudent) {
        List<Student> gradeStudents = fileHandler.loadStudentsFromCSV(grade);
        gradeStudents.removeIf(s -> s.getName().equalsIgnoreCase(name));
        gradeStudents.add(updatedStudent);
        fileHandler.saveStudentsToCSV(gradeStudents, grade);
    }

    public void deleteStudent(String name, int grade) {
        List<Student> gradeStudents = fileHandler.loadStudentsFromCSV(grade);
        gradeStudents.removeIf(s -> s.getName().equalsIgnoreCase(name));
        fileHandler.saveStudentsToCSV(gradeStudents, grade);
    }
}
