/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quiz.managment.system;

import java.util.ArrayList;
import java.util.List;

public class TestQuizManager {
    public static void main(String[] args) {
        QuizManager quizManager = new QuizManager();

        quizManager.createQuiz("101", "Science", "Easy", "Multiple Choice");
        quizManager.createQuiz("202", "History", "Medium", "Essay");

        System.out.println("Testing Adding and Retrieving Quizzes:");
        System.out.println("Quiz IDs: " + quizManager.getAllQuizzes());

        quizManager.addStudent("ST001", "John Doe");

        System.out.println("\nTesting Adding and Retrieving Students:");
        System.out.println("Student ID: " + quizManager.getStudent("ST001").getName());

        System.out.println("\nTesting Grading a MultipleChoiceQuiz:");
        MultipleChoiceQuiz mcQuiz = (MultipleChoiceQuiz) quizManager.getQuiz("101");
        mcQuiz.addQuestion("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, "4");

        List<String> studentAnswers = new ArrayList<>();
        studentAnswers.add("4");

        quizManager.gradeQuiz("101", "001", studentAnswers);

        System.out.println("\nTesting Viewing Grades:");
        quizManager.getStudent("ST001").viewGrades();
    }
}
