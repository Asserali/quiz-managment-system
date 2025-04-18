/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quiz.managment.system;

import java.util.*;

public class IntegrationTest {

    public static void main(String[] args) {
        System.out.println("=== Integration Test: Quiz Management System ===");
        boolean allTestsPassed = true;

        QuizManager quizManager = new QuizManager();

        quizManager.createQuiz("101", "Math", "Easy", "Multiple Choice");
        MultipleChoiceQuiz mcQuiz = (MultipleChoiceQuiz) quizManager.getQuiz("101");
        mcQuiz.addQuestion("What is 5 + 3?", new String[]{"6", "7", "8", "9"}, "8");
        mcQuiz.addQuestion("What is 10 - 4?", new String[]{"5", "6", "7", "8"}, "6");

        quizManager.createQuiz("202", "History", "Medium", "Essay");
        EssayQuiz essayQuiz = (EssayQuiz) quizManager.getQuiz("202");
        essayQuiz.addQuestion("Explain the causes of World War I.", null, null);

        quizManager.addStudent("STU001", "Alice");

        List<String> mcAnswers = Arrays.asList("8", "7"); // Correct for Q1, Incorrect for Q2

        quizManager.gradeQuiz("101", "STU001", mcAnswers);

        List<String> essayAnswers = Collections.singletonList("World War I was caused by political alliances, militarism, and nationalism.");

        quizManager.gradeQuiz("202", "STU001", essayAnswers);

        Student student = quizManager.getStudent("STU001");
        if (student != null) {
            System.out.println("\n=== Student Grades ===");
            student.viewGrades();

            Map<String, Integer> grades = student.viewGrades();
            if (grades.containsKey("101") && grades.get("101") == 1) {
                System.out.println("Test Passed: Multiple Choice Quiz graded correctly.");
            } else {
                System.out.println("Test Failed: Multiple Choice Quiz grading is incorrect.");
                allTestsPassed = false;
            }

            if (grades.containsKey("202")) {
                System.out.println("Test Passed: Essay Quiz grading recorded.");
            } else {
                System.out.println("Test Failed: Essay Quiz grading is missing.");
                allTestsPassed = false;
            }
        } else {
            System.out.println("Test Failed: Student record not found.");
            allTestsPassed = false;
        }

        if (allTestsPassed) {
            System.out.println("\n=== All Integration Tests Passed! ===");
        } else {
            System.out.println("\n=== Some Integration Tests Failed. ===");
        }
    }
}
