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
class MultipleChoiceQuiz extends Quiz {

    private List<String> questions = new ArrayList<>();
    private List<String[]> options = new ArrayList<>();
    private List<String> correctAnswers = new ArrayList<>();

    public MultipleChoiceQuiz(String quizID, String topic, String difficultyLevel) {
        super(quizID, topic, difficultyLevel);
    }

    @Override
    public void addQuestion(String question, String[] choices, String correctAnswer) {
        questions.add(question);
        options.add(choices);
        correctAnswers.add(correctAnswer.toLowerCase());
    }

    public List<String> getQuestions() {
        return questions;
    }

    public List<String[]> getOptions() {
        return options;
    }

    public boolean gradeQuestion(String studentAnswer, int questionIndex) {
        if (questionIndex < 0 || questionIndex >= correctAnswers.size()) {
            return false;
        }
        return correctAnswers.get(questionIndex).equals(studentAnswer.toLowerCase());
    }
}
