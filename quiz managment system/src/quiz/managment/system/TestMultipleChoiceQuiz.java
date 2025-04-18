/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quiz.managment.system;

import java.util.Arrays;

public class TestMultipleChoiceQuiz {

    public static void main(String[] args) {
        MultipleChoiceQuiz mcQuiz = new MultipleChoiceQuiz("101", "Science", "Easy");

        mcQuiz.addQuestion("What is the capital of France?", new String[]{"Paris", "Berlin", "Madrid", "Rome"}, "Paris");
        mcQuiz.addQuestion("What planet is known as the Red Planet?", new String[]{"Mars", "Venus", "Earth", "Jupiter"}, "Mars");

        System.out.println("Testing Adding and Retrieving Questions:");
        System.out.println("Questions: " + mcQuiz.getQuestions());
        System.out.println("Options for Q1: " + Arrays.toString(mcQuiz.getOptions().get(0)));
        System.out.println("Options for Q2: " + Arrays.toString(mcQuiz.getOptions().get(1)));

        System.out.println("\nTesting Grading:");
        boolean grade1 = mcQuiz.gradeQuestion("Paris", 0);
        boolean grade2 = mcQuiz.gradeQuestion("Earth", 1);
        System.out.println("Grade for Q1 (Correct): " + grade1);
        System.out.println("Grade for Q2 (Incorrect): " + grade2);
    }
}
