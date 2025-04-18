/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quiz.managment.system;

import java.util.ArrayList;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        QuizManager quizManager = new QuizManager();

        quizManager.createQuiz("101", "Science", "Easy", "Multiple Choice");
        quizManager.createQuiz("202", "History", "Medium", "Essay");

        MultipleChoiceQuiz mcQuiz = (MultipleChoiceQuiz) quizManager.getQuiz("101");
        mcQuiz.addQuestion("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, "4");

        EssayQuiz essayQuiz = (EssayQuiz) quizManager.getQuiz("202");
        essayQuiz.addQuestion("Describe the causes of World War II.", null, null);

        quizManager.addStudent("ST001", "John Doe");

        List<String> mcAnswers = new ArrayList<>();
        mcAnswers.add("4"); 

        List<String> essayAnswers = new ArrayList<>();
        essayAnswers.add("World War II was caused by..."); 

        quizManager.gradeQuiz("101", "ST001", mcAnswers); 
        quizManager.gradeQuiz("202", "ST001", essayAnswers); 

        System.out.println("Viewing Grades for Student:");
        quizManager.getStudent("ST001").viewGrades();
    }
}

