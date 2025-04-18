/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quiz.managment.system;

import java.util.*;

/**
 *
 * @author Asser
 */
public class Student {

    private String studentID;
    private String name;
    private Map<String, Integer> grades;
//quiz id w el daraga
    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.grades = new HashMap<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public void addGrade(String quizID, int score) {
        grades.put(quizID, score);
    }

    public Map<String, Integer> viewGrades() {
        return grades; 
    }
}

