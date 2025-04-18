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
class EssayQuiz extends Quiz {

    private List<String> questions = new ArrayList<>();

    public EssayQuiz(String quizID, String topic, String difficultyLevel) {
        super(quizID, topic, difficultyLevel);
    }

    @Override
    public void addQuestion(String question, String[] choices, String correctAnswer) {

        questions.add(question);
    }

    public List<String> getQuestions() {
        return questions;
    }
}
