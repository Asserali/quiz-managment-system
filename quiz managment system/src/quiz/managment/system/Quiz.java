/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quiz.managment.system;

/**
 *
 * @author Asser
 */
abstract class Quiz {

    private String quizID;
    private String topic;
    private String difficultyLevel;

    public Quiz(String quizID, String topic, String difficultyLevel) {
        this.quizID = quizID;
        this.topic = topic;
        this.difficultyLevel = difficultyLevel;
    }

    public String getQuizID() {
        return quizID;
    }

    public String getTopic() {
        return topic;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public abstract void addQuestion(String question, String[] choices, String correctAnswer);

    Object getQuestions() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
