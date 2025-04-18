package filess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizFileReader {

    // Inner class to represent a question
    public static class Question {
        public String text;
        public String[] options;
        public String correctAnswer;

        public Question(String text, String[] options, String correctAnswer) {
            this.text = text;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }

    private List<Question> questions = new ArrayList<>();

    public void readQuizFile(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Read question text
                String questionText = line.trim();

                // Read the next 4 lines for options
                String[] options = new String[4];
                for (int i = 0; i < 4; i++) {
                    line = reader.readLine();
                    if (line == null) {
                        throw new IOException("Invalid file format: missing options.");
                    }
                    options[i] = line.trim();
                }

                // Read the correct answer
                line = reader.readLine();
                if (line == null || line.trim().isEmpty()) {
                    throw new IOException("Invalid file format: missing correct answer.");
                }
                String correctAnswer = line.trim();

                // Add the question to the list
                questions.add(new Question(questionText, options, correctAnswer));
            }
        }
    }

    public List<Question> getQuestions() {
        return questions;
    }
}