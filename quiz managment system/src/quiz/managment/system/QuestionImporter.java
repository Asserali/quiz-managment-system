/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quiz.managment.system;

/**
 *
 * @author Asser
 */
import java.io.*;
import java.util.*;

public class QuestionImporter {

    public static List<MCQuestion> importQuestions(String filePath) {
        List<MCQuestion> questions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 7) {
                    String questionID = parts[0];
                    String questionText = parts[1];
                    String[] options = {parts[2], parts[3], parts[4], parts[5]};
                    String correctAnswer = parts[6];

                    questions.add(new MCQuestion(questionID, questionText, options, correctAnswer));
                } else {
                    System.out.println("Invalid question format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return questions;
    }

    public static void main(String[] args) {
        // Example usage
        String filePath = "questions.txt"; // Path to your questions file
        List<MCQuestion> questions = importQuestions(filePath);

        for (MCQuestion q : questions) {
            System.out.println(q);
        }
    }
}

class MCQuestion {
    private String questionID;
    private String questionText;
    private String[] options;
    private String correctAnswer;

    public MCQuestion(String questionID, String questionText, String[] options, String correctAnswer) {
        this.questionID = questionID;
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return "ID: " + questionID + "\nQuestion: " + questionText +
               "\nA: " + options[0] + "\nB: " + options[1] +
               "\nC: " + options[2] + "\nD: " + options[3] +
               "\nCorrect Answer: " + correctAnswer + "\n";
    }
}
