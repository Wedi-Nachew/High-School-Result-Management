package com.school.ui;

import com.school.model.Student;
import com.school.service.ResultService;
import com.school.service.TranscriptGenerator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MenuSystem {
    private ResultService resultService;
    private TranscriptGenerator transcriptGenerator;
    private Scanner scanner;
    // Default grade level
    private int currentGrade = 9;
    // Predefined subjects (from the original code)
    private static final String[] PREDEFINED_SUBJECTS = {"Amharic", "Biology", "Chemistry", "English", "Maths", "Physics", "Tigrigna"};

    public MenuSystem() {
        resultService = new ResultService();
        transcriptGenerator = new TranscriptGenerator();
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int choice = -1;
        while (choice != 8) {
            clearScreen();
            System.out.println("\n--------- High School Students' Result Management System ---------");
            System.out.println("Current Grade Level: " + currentGrade);
            System.out.println("1. Select Grade Level");
            System.out.println("2. Add Student");
            System.out.println("3. Display All Students");
            System.out.println("4. Display All Students' Transcripts");
            System.out.println("5. Search for a Student");
            System.out.println("6. Update Student Grades");
            System.out.println("7. Delete Student Record");
            System.out.println("8. Generate Transcript for Student");
            System.out.println("9. Exit");
            System.out.print("\nEnter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    selectGradeLevel();
                    break;
                case 2:
                    addStudent();
                    break;
                case 3:
                    displayAllStudents();
                    break;
                case 4:
                    displayAllTranscripts();
                    break;
                case 5:
                    searchStudent();
                    break;
                case 6:
                    updateStudentGrades();
                    break;
                case 7:
                    deleteStudent();
                    break;
                case 8:
                    generateTranscript();
                    break;
                case 9:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            promptEnterKey();
        }
    }

    private void selectGradeLevel() {
        System.out.print("Enter grade level (9-12): ");
        try {
            int grade = Integer.parseInt(scanner.nextLine());
            if (grade < 9 || grade > 12) {
                System.out.println("Invalid grade level. Defaulting to grade 9.");
                currentGrade = 9;
            } else {
                currentGrade = grade;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Defaulting to grade 9.");
            currentGrade = 9;
        }
    }

    private void addStudent() {
        clearScreen();
        System.out.println("Enter student details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Gender (Male/Female): ");
        String gender = scanner.nextLine();

        System.out.print("Section: ");
        String section = scanner.nextLine();

        Map<String, Double> subjectsScores = new HashMap<>();
        System.out.println("Enter the student's scores for the following subjects:");
        for (String subject : PREDEFINED_SUBJECTS) {
            System.out.print(subject + " score: ");
            double score = Double.parseDouble(scanner.nextLine());
            subjectsScores.put(subject, score);
        }
        Student student = new Student(name, age, gender, currentGrade, section, subjectsScores);
        resultService.addStudent(student);
        System.out.println("Student added successfully!");
    }

    private void displayAllStudents() {
        clearScreen();
        List<Student> students = resultService.getStudentsByGrade(currentGrade);
        if (students.isEmpty()) {
            System.out.println("No students found for grade " + currentGrade);
        } else {
            // Print table header
            System.out.println("----------------------------------------------------------------------------------------");
            System.out.printf("| %-20s | %-6s | %-4s | %-5s | %-7s ", "Name", "Gender", "Age", "Grade", "Section");
            for (String subject : PREDEFINED_SUBJECTS) {
                System.out.printf("| %-10s ", subject);
            }
            System.out.printf("| %-6s | %-7s |\n", "Total", "Average");
            System.out.println("----------------------------------------------------------------------------------------");

            // Print each student
            for (Student student : students) {
                int total = 0;
                System.out.printf("| %-20s | %-6s | %-4d | %-5d | %-7s ", student.getName(), student.getGender(),
                        student.getAge(), student.getGrade(), student.getSection());
                for (String subject : PREDEFINED_SUBJECTS) {
                    int score = student.getSubjectsScores().get(subject) != null ? student.getSubjectsScores().get(subject).intValue() : 0;
                    System.out.printf("| %-10d ", score);
                    total += score;
                }
                double average = total / (double) PREDEFINED_SUBJECTS.length;
                System.out.printf("| %-6d | %-7.2f |\n", total, average);
            }
            System.out.println("----------------------------------------------------------------------------------------");
        }
    }

    private void displayAllTranscripts() {
        clearScreen();
        List<Student> students = resultService.getStudentsByGrade(currentGrade);
        if (students.isEmpty()) {
            System.out.println("No students found for grade " + currentGrade);
        } else {
            for (Student student : students) {
                displayStudentTranscript(student);
            }
        }
    }

    private void displayStudentTranscript(Student student) {
        int total = 0;
        System.out.println("-----------------------------------------------------");
        System.out.printf("%-20s\n", student.getName());
        System.out.printf("Sex: %-8s Grade: %-3d\n", student.getGender(), student.getGrade());
        System.out.printf("Age: %-3d Section: %-3s\n", student.getAge(), student.getSection());
        System.out.println("*-------------------------------------*");
        System.out.printf("| %-16s | %-10s |\n", "Subject", "Result");
        System.out.println("---------------------------------------");
        for (String subject : PREDEFINED_SUBJECTS) {
            int score = student.getSubjectsScores().get(subject) != null ? student.getSubjectsScores().get(subject).intValue() : 0;
            System.out.printf("| %-16s | %-10d |\n", subject, score);
            total += score;
        }
        System.out.println("---------------------------------------");
        System.out.printf("| %-16s | %-10d |\n", "Total", total);
        System.out.printf("| %-16s | %-10.2f |\n", "Average", total / (double) PREDEFINED_SUBJECTS.length);
        System.out.println("*-------------------------------------*\n");
    }

    private void searchStudent() {
        clearScreen();
        System.out.print("Enter student's name to search: ");
        String name = scanner.nextLine();
        Student student = resultService.searchStudent(name, currentGrade);
        
        if (student != null) {
            displayStudentTranscript(student);
        } else {
            System.out.println("Student not found.");
        }
    }

    private void updateStudentGrades() {
        clearScreen();
        System.out.print("Enter student's name to update: ");
        String name = scanner.nextLine();
        Student oldStudent = resultService.searchStudent(name, currentGrade);
        if (oldStudent == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.println("Enter new details (press Enter to keep current value):");
        System.out.print("Name (" + oldStudent.getName() + "): ");
        String newName = scanner.nextLine();
        newName = newName.isEmpty() ? oldStudent.getName() : newName;

        System.out.print("Age (" + oldStudent.getAge() + "): ");
        String ageInput = scanner.nextLine();
        int newAge = ageInput.isEmpty() ? oldStudent.getAge() : Integer.parseInt(ageInput);

        System.out.print("Gender (" + oldStudent.getGender() + "): ");
        String newGender = scanner.nextLine();
        newGender = newGender.isEmpty() ? oldStudent.getGender() : newGender;

        System.out.print("Section (" + oldStudent.getSection() + "): ");
        String newSection = scanner.nextLine();
        newSection = newSection.isEmpty() ? oldStudent.getSection() : newSection;

        Map<String, Double> newSubjectsScores = new HashMap<>();
        System.out.println("Enter new scores for the subjects (press Enter to keep current score):");
        for (String subject : PREDEFINED_SUBJECTS) {
            Double currentScore = oldStudent.getSubjectsScores().get(subject);
            System.out.print(subject + " score (" + (currentScore != null ? currentScore : "N/A") + "): ");
            String scoreInput = scanner.nextLine();
            double newScore = (scoreInput.isEmpty() && currentScore != null) ? currentScore : Double.parseDouble(scoreInput);
            newSubjectsScores.put(subject, newScore);
        }
        Student updatedStudent = new Student(newName, newAge, newGender, currentGrade, newSection, newSubjectsScores);
        resultService.updateStudent(name, currentGrade, updatedStudent);
        System.out.println("Student updated successfully!");
    }

    private void deleteStudent() {
        clearScreen();
        System.out.print("Enter student's name to delete: ");
        String name = scanner.nextLine();
        resultService.deleteStudent(name, currentGrade);
        System.out.println("Student deleted successfully!");
    }

    private void generateTranscript() {
        clearScreen();
        System.out.print("Enter student's name to generate transcript: ");
        String name = scanner.nextLine();
        Student student = resultService.searchStudent(name, currentGrade);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        transcriptGenerator.exportTranscript(student);
    }

    // Clears the console screen using ANSI escape codes
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    // Simple prompt to pause until user presses Enter
    private void promptEnterKey(){
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
}
