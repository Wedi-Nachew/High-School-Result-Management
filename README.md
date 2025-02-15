# High-School-Result-Management

## Overview

The High School Students' Result Management System is a Java-based application designed to efficiently manage, store, and track student academic records. The system supports operations such as adding new student records, updating existing records, deleting records, searching for students, and generating formatted transcripts. Built using key software engineering tools and practices, this project integrates Model-Driven Development, Version Control (Git), Testing (JUnit 4), and Automated Build Tools (Apache Ant).

## Features

- **Grade-Level Categorization:**  
  Separate CSV files are maintained for each grade (9–12) for easy management.

- **Student Record Management:**  
  - Add new students with detailed information (name, age, gender, section, and predefined subject scores).  
  - Update existing student records.  
  - Delete student records.  
  - Search for student records by name.

- **Transcript Generation:**  
  Generate and export formatted transcripts (in TXT format) that include subject-wise scores, total, average, GPA, and remarks.

- **Reporting:**  
  Display all student records and transcripts in a neatly formatted table for quick review.

## Project Structure

HighSchoolResultManagement/  
│── src/  
│   ├── main/  
│   │   ├── java/  
│   │   │   ├── com/  
│   │   │   │   ├── school/  
│   │   │   │   │   ├── model/               # Data models (Student, Transcript, etc.)  
│   │   │   │   │   ├── service/             # Business logic (CRUD operations)  
│   │   │   │   │   ├── util/                # Utility classes (File handling, validation)  
│   │   │   │   │   ├── ui/                  # User interface (CLI menu handling)  
│   │   │   │   │   ├── main/                # Main application entry point  
│   │   ├── resources/  
│   │   │   ├── data/                        # CSV files for storing student records  
│   │   │    
│── test/  
│   ├── java/  
│   │   ├── com/  
│   │   │   ├── school/  
│   │   │   │   ├── service/                 # Unit tests for service layer  
│   │   │   │   ├── model/                   # Unit tests for models  
|   |   |   |   ├── util/                    # Unit tests for utilities  
│── build/                                   # Compiled class files  
│── lib/                                     # External libraries (if any)  
│── docs/                                    # UML diagrams, reports, documentation  
│── .gitignore                               # Ignore build files, CSVs, etc.  
│── build.xml                                # Apache Ant build script  
│── README.md                                # Project overview and setup instructions  

## Requirements

- **Java:** JDK 8 or above  
- **JUnit:** Version 4.12 (or compatible)  
- **Apache Ant:** For building and testing the project  
- **Git:** For version control and collaboration  
- **IDE (optional):** IntelliJ IDEA, Eclipse, or any text editor of your choice

## Setup and Installation

1. **Clone the Repository:**  
   Clone the repository from GitHub:
   ```bash
   git clone https://github.com/Wedi-Nachew/High-School-Result-Management.git
   ```
2. **Download Dependencies:**  
   Place the required libraries (e.g., `junit-4.13.2.jar` and `hamcrest-core-1.3.jar`) into the `lib/` directory.

3. **Verify Project Structure:**  
   Ensure your directories match the structure outlined above.

## Build and Test

### Using Apache Ant
- **Compile the project:** 
```bash
  ant compile
```
- **Compile the test cases classes:** 
```bash
  ant compile-test
```
- **Run JUnit tests:** 
    - Test results will be displayed in the terminal.
    - Detailed XML reports will be saved in the reports/test-results/ directory.
```bash
  ant test
```
- **Run the Project:** 
```bash
  ant run
```
- **Run the Project:** 
```bash
  ant run
```
- **Clean Build Artifacts:** 
```bash
  ant clean
```

## Branching Strategy & Collaboration

### **Main Branches:**

- **`main`:**  
  Contains stable, production-ready code.

- **`models`:**  
  Serves as the branch where modeling of the system are carried out before merging.

- **`develop`:**  
  Serves as the branch where implementation of the system are carried out merging.

- **`test`:**  
  Serves as the branch where testing of the system are carried out before merging.

### **Feature Branches:**

- **Naming Convention:**  
  Use `feature/<username>-<feature-description>` (e.g., `test/durye-util`).

- **Workflow:**  
  - Create a new branch from `develop`:
    ```bash
    git checkout -b feature/<username>-<feature-description>
    ```
  - Commit frequently with meaningful messages.
  - Push your branch to GitHub:
    ```bash
    git push origin feature/<username>-<feature-description>
    ```
  - Merge into `develop` after peer review.

## .gitignore

The `.gitignore` file (located at the project root) is configured to exclude build artifacts, IDE-specific files, OS-generated files, and temporary files. Refer to this file for details.

## Contributors

- **Team Members:** 
  - Filmon Gebrelibanos     UGR/170182/12
  - Tekle Beyene            UGR/170122/12
  - Mehari Desta            EITM/TUR189988/16


