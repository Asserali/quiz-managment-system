/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quiz.managment.system;

public class TestEssayQuiz {
    public static void main(String[] args) {
        EssayQuiz essayQuiz = new EssayQuiz("202", "History", "Medium");

        essayQuiz.addQuestion("Describe the causes of World War II.", null, null);
        essayQuiz.addQuestion("Explain the significance of the French Revolution.", null, null);

        System.out.println("Testing Adding and Retrieving Questions:");
        System.out.println("Questions: " + essayQuiz.getQuestions());
    }
}
