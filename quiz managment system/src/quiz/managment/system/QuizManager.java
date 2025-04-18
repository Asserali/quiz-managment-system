/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quiz.managment.system;

import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Asser
 */
class QuizManager {
    
    
    

    private Map<String, Quiz> quizzes = new HashMap<>();
    private Map<String, Student> students = new HashMap<>();

    public void createQuiz(String quizID, String topic, String difficultyLevel, String quizType) {
        if (quizType.equalsIgnoreCase("Multiple Choice")) {
            quizzes.put(quizID, new MultipleChoiceQuiz(quizID, topic, difficultyLevel));
        } else if (quizType.equalsIgnoreCase("Essay")) {
            quizzes.put(quizID, new EssayQuiz(quizID, topic, difficultyLevel));
        } else {
            throw new IllegalArgumentException("Invalid quiz type!");
        }
    }

    public Quiz getQuiz(String quizID) {
        return quizzes.get(quizID);
    }

    public void addStudent(String studentID, String name) {
        students.put(studentID, new Student(studentID, name));
    }

    public Student getStudent(String studentID) {
        return students.get(studentID);
    }

    public Collection<Quiz> getAllQuizzes() {
        return quizzes.values();
    }

    public void gradeQuiz(String quizID, String studentID, List<String> studentAnswers) {
    Quiz quiz = quizzes.get(quizID);

    if (quiz == null) {
        System.out.println("Invalid Quiz ID!");
        return;
    }

        int totalScore = 0;
    int maxScore = 0;

    if (quiz instanceof MultipleChoiceQuiz) {
        MultipleChoiceQuiz mcQuiz = (MultipleChoiceQuiz) quiz;
        List<String> questions = mcQuiz.getQuestions();

        for (int i = 0; i < questions.size(); i++) {
            if (mcQuiz.gradeQuestion(studentAnswers.get(i), i)) {
                totalScore++;
            }
        }
        maxScore = questions.size();
    } else if (quiz instanceof EssayQuiz) {
        EssayQuiz essayQuiz = (EssayQuiz) quiz;
        List<String> questions = essayQuiz.getQuestions();

        for (int i = 0; i < questions.size(); i++) {
            String studentAnswer = studentAnswers.get(i);
            String question = questions.get(i);

            boolean validScore = false;
            while (!validScore) {
                String scoreInput = JOptionPane.showInputDialog(null,
                        "Grade Essay Question: \n"
                                + "Question: " + question + "\n"
                                + "Student's Answer: " + studentAnswer + "\n"
                                + "Enter a score (0-5):");

                try {
                    int essayScore = Integer.parseInt(scoreInput);
                    if (essayScore >= 0 && essayScore <= 5) {
                        totalScore += essayScore;
                        maxScore += 5;
                        validScore = true; // exit the loop
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid score! Please enter a score between 0 and 5.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid score.");
                }
            }
        }
    }

    

    JOptionPane.showMessageDialog(null, "Quiz graded!\n"
            + "Student ID: " + studentID + "\n"
            + "Score: " + totalScore + "/" + maxScore);
}
}
