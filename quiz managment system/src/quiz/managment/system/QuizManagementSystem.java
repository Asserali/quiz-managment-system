/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package quiz.managment.system;

import filess.QuizFileReader;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class QuizManagementSystem {
    private QuizManager quizManager;

    public QuizManagementSystem() {
        quizManager = new QuizManager();
        setupGUI();
    }

    private void showAddStudentDialog(JFrame parentFrame) {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField studentIDField = new JTextField();
        JTextField studentNameField = new JTextField();

        panel.add(new JLabel("Student ID:"));
        panel.add(studentIDField);
        panel.add(new JLabel("Student Name:"));
        panel.add(studentNameField);

        int result = JOptionPane.showConfirmDialog(parentFrame, panel, "Add Student", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String studentID = studentIDField.getText();
            String studentName = studentNameField.getText();

            if (studentID.isEmpty() || studentName.isEmpty()) {
                JOptionPane.showMessageDialog(parentFrame, "Both Student ID and Name are required!");
                return;
            }

            quizManager.addStudent(studentID, studentName);
            JOptionPane.showMessageDialog(parentFrame, "Student added successfully!");
        }
    }

    private void setupGUI() {
        JFrame frame = new JFrame("Quiz Management System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Buttons Panel (80% of the screen height)
        JPanel buttonsPanel = new JPanel(new GridLayout(6, 1));
        JButton createQuizBtn = new JButton("Create Quiz");
        JButton addQuestionBtn = new JButton("Add Questions");
        JButton gradeQuizBtn = new JButton("Grade Quiz");
        JButton viewQuizzesBtn = new JButton("View Quizzes");
        JButton exitBtn = new JButton("Exit");
        JButton addStudentBtn = new JButton("Add Student");

        Font boldFont = new Font("Arial", Font.BOLD, 16);
        createQuizBtn.setFont(boldFont);
        addQuestionBtn.setFont(boldFont);
        gradeQuizBtn.setFont(boldFont);
        viewQuizzesBtn.setFont(boldFont);
        exitBtn.setFont(boldFont);
        addStudentBtn.setFont(boldFont);
        JButton importMCQsBtn = new JButton("Import MCQs");
        JButton importQuizBtn = new JButton("Import Quiz from File");
        importQuizBtn.addActionListener(e -> importQuizFromFile());

importMCQsBtn.setFont(boldFont);
buttonsPanel.add(importMCQsBtn);

        buttonsPanel.add(createQuizBtn);
        buttonsPanel.add(addQuestionBtn);
        buttonsPanel.add(gradeQuizBtn);
        buttonsPanel.add(viewQuizzesBtn);
        buttonsPanel.add(addStudentBtn);
        buttonsPanel.add(exitBtn);

        // Footer Panel (20% of the screen height)
        JLabel footerLabel = new JLabel("Made by: Ali Hany, Asser Mohamed, Nour Walid", JLabel.CENTER);
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.add(footerLabel, BorderLayout.CENTER);

        // Set preferred sizes for the panels
        buttonsPanel.setPreferredSize(new Dimension(frame.getWidth(), (int) (frame.getHeight() * 0.8)));
        footerPanel.setPreferredSize(new Dimension(frame.getWidth(), (int) (frame.getHeight() * 0.2)));

        frame.add(buttonsPanel, BorderLayout.CENTER);
        frame.add(footerPanel, BorderLayout.SOUTH);

        // Add Action Listeners
        createQuizBtn.addActionListener(e -> showCreateQuizDialog(frame));
        addQuestionBtn.addActionListener(e -> showAddQuestionDialog(frame));
        gradeQuizBtn.addActionListener(e -> showGradeQuizDialog(frame));
        viewQuizzesBtn.addActionListener(e -> showViewQuizzesDialog(frame));
        exitBtn.addActionListener(e -> System.exit(0));
        addStudentBtn.addActionListener(e -> showAddStudentDialog(frame));

        frame.setVisible(true);
    }

    private void showCreateQuizDialog(JFrame parentFrame) {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        JTextField quizIDField = new JTextField();
        JTextField topicField = new JTextField();
        JComboBox<String> difficultyFieldComboBox = new JComboBox<>(new String[]{"Easy", "Medium", "Hard"});
        JComboBox<String> quizTypeComboBox = new JComboBox<>(new String[]{"Multiple Choice", "Essay"});

        panel.add(new JLabel("Quiz ID:"));
        panel.add(quizIDField);
        panel.add(new JLabel("Topic:"));
        panel.add(topicField);
        panel.add(new JLabel("Difficulty Level:"));
        panel.add(difficultyFieldComboBox);
        panel.add(new JLabel("Quiz Type:"));
        panel.add(quizTypeComboBox);

        int result = JOptionPane.showConfirmDialog(parentFrame, panel, "Create Quiz", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String quizIDText = quizIDField.getText();
            String topic = topicField.getText();
            String difficulty = (String) difficultyFieldComboBox.getSelectedItem();
            String quizType = (String) quizTypeComboBox.getSelectedItem();

            if (!isPositiveInteger(quizIDText)) {
                JOptionPane.showMessageDialog(parentFrame, "Invalid Quiz ID! Please enter a positive integer.");
                return;
            }

            int quizID = Integer.parseInt(quizIDText);
            quizManager.createQuiz(String.valueOf(quizID), topic, difficulty, quizType);
            JOptionPane.showMessageDialog(parentFrame, "Quiz created successfully!");
        }
    }
    
    private void importMCQsFromFile(MultipleChoiceQuiz quiz, File file) throws Exception {
        System.out.println("Entered here");
    try (Scanner scanner = new Scanner(file)) {
        while (scanner.hasNextLine()) {
            String question = scanner.nextLine();
            if (!scanner.hasNextLine()) throw new Exception("Invalid file format!");
            String optionA = scanner.nextLine().substring(3).trim();
            System.out.println(optionA);
            String optionB = scanner.nextLine().substring(3).trim();
            String optionC = scanner.nextLine().substring(3).trim();
            String optionD = scanner.nextLine().substring(3).trim();
            String correctAnswer = scanner.nextLine().substring(15).trim();

            quiz.addQuestion(question, new String[]{optionA, optionB, optionC, optionD}, correctAnswer);
            if (scanner.hasNextLine()) scanner.nextLine(); // Skip blank line between questions
        }
    }
}

    private void showAddQuestionDialog(JFrame parentFrame) {
    JPanel panel = new JPanel(new GridLayout(3, 2));
    JTextField quizIDField = new JTextField();
    JTextField questionField = new JTextField();

    panel.add(new JLabel("Quiz ID:"));
    panel.add(quizIDField);
    panel.add(new JLabel("Question:"));
    panel.add(questionField);

    int result = JOptionPane.showConfirmDialog(parentFrame, panel, "Add Question", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
        String quizIDText = quizIDField.getText();
        if (!isPositiveInteger(quizIDText)) {
            JOptionPane.showMessageDialog(parentFrame, "Invalid Quiz ID! Please enter a positive integer.");
            return;
        }

        int quizID = Integer.parseInt(quizIDText);
        Quiz quiz = quizManager.getQuiz(String.valueOf(quizID));
        if (quiz != null) {
            if (quiz instanceof MultipleChoiceQuiz) {
                JTextField optionAField = new JTextField();
                JTextField optionBField = new JTextField();
                JTextField optionCField = new JTextField();
                JTextField optionDField = new JTextField();
                JTextField correctAnswerField = new JTextField();

                JPanel mcPanel = new JPanel(new GridLayout(6, 2));
                mcPanel.add(new JLabel("Option A:"));
                mcPanel.add(optionAField);
                mcPanel.add(new JLabel("Option B:"));
                mcPanel.add(optionBField);
                mcPanel.add(new JLabel("Option C:"));
                mcPanel.add(optionCField);
                mcPanel.add(new JLabel("Option D:"));
                mcPanel.add(optionDField);
                mcPanel.add(new JLabel("Correct Answer (A/B/C/D):"));
                mcPanel.add(correctAnswerField);

                int mcResult = JOptionPane.showConfirmDialog(parentFrame, mcPanel, "Add MC Question", JOptionPane.OK_CANCEL_OPTION);
                if (mcResult == JOptionPane.OK_OPTION) {
                    String question = questionField.getText();
                    String[] options = {
                        optionAField.getText(),
                        optionBField.getText(),
                        optionCField.getText(),
                        optionDField.getText()
                    };

                    // Ensure none of the options are empty
                    for (String option : options) {
                        if (option.isEmpty()) {
                            JOptionPane.showMessageDialog(parentFrame, "All options must be filled!");
                            return;
                        }
                    }

                    String correctAnswer = correctAnswerField.getText().toUpperCase();
                    if (!Arrays.asList("A", "B", "C", "D").contains(correctAnswer)) {
                        JOptionPane.showMessageDialog(parentFrame, "Invalid correct answer! Must be A, B, C, or D.");
                        return;
                    }

                    quiz.addQuestion(question, options, correctAnswer);
                    JOptionPane.showMessageDialog(parentFrame, "Multiple Choice Question added successfully!");
                }
            } else if (quiz instanceof EssayQuiz) {
                String question = questionField.getText();
                if (question.isEmpty()) {
                    JOptionPane.showMessageDialog(parentFrame, "Question cannot be empty!");
                    return;
                }

                quiz.addQuestion(question, null, null);
                JOptionPane.showMessageDialog(parentFrame, "Essay Question added successfully!");
            }
        } else {
            JOptionPane.showMessageDialog(parentFrame, "Quiz not found! Please check the Quiz ID.");
        }
    }
}


    private void showGradeQuizDialog(JFrame parentFrame) {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JTextField quizIDField = new JTextField();
        JTextField studentIDField = new JTextField();

        panel.add(new JLabel("Quiz ID:"));
        panel.add(quizIDField);
        panel.add(new JLabel("Student ID:"));
        panel.add(studentIDField);

        int result = JOptionPane.showConfirmDialog(parentFrame, panel, "Grade Quiz", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String quizID = quizIDField.getText();
            String studentID = studentIDField.getText();

            Quiz quiz = quizManager.getQuiz(quizID);
            if (quiz == null) {
                JOptionPane.showMessageDialog(parentFrame, "Invalid Quiz ID!");
                return;
            }

            Student student = quizManager.getStudent(studentID);
            if (student == null) {
                String studentName = JOptionPane.showInputDialog(parentFrame, "Student not found. Enter student name to add:");
                if (studentName == null || studentName.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(parentFrame, "Student name is required!");
                    return;
                }
                quizManager.addStudent(studentID, studentName);
                student = quizManager.getStudent(studentID);
            }

            String studentName = student.getName();
            int confirmation = JOptionPane.showConfirmDialog(parentFrame,
                    "Grading Quiz for Student:\n" +
                            "Name: " + studentName + "\n" +
                            "ID: " + studentID + "\n" +
                            "Quiz ID: " + quizID + "\n\nContinue?",
                    "Grade Quiz Confirmation", JOptionPane.YES_NO_OPTION);

            if (confirmation != JOptionPane.YES_OPTION) {
                return;
            }

            List<String> studentAnswers = new ArrayList<>();
            if (quiz instanceof MultipleChoiceQuiz) {
                MultipleChoiceQuiz mcQuiz = (MultipleChoiceQuiz) quiz;
                for (int i = 0; i < mcQuiz.getQuestions().size(); i++) {
                    String question = mcQuiz.getQuestions().get(i);
                    String[] options = mcQuiz.getOptions().get(i);

                    JPanel questionPanel = new JPanel(new GridLayout(5, 1));
                    questionPanel.add(new JLabel("Question: " + question));
                    questionPanel.add(new JLabel("A: " + options[0]));
                    questionPanel.add(new JLabel("B: " + options[1]));
                    questionPanel.add(new JLabel("C: " + options[2]));
                    questionPanel.add(new JLabel("D: " + options[3]));

                    String studentAnswer = JOptionPane.showInputDialog(parentFrame, questionPanel, "Enter your answer (A/B/C/D):");
                    studentAnswers.add(studentAnswer);
                }
            } else if (quiz instanceof EssayQuiz) {
                EssayQuiz essayQuiz = (EssayQuiz) quiz;
                for (int i = 0; i < essayQuiz.getQuestions().size(); i++) {
                    String question = essayQuiz.getQuestions().get(i);

                    JPanel questionPanel = new JPanel(new BorderLayout());
                    questionPanel.add(new JLabel("Question: " + question), BorderLayout.NORTH);

                    String studentAnswer = JOptionPane.showInputDialog(parentFrame, questionPanel, "Enter your answer:");
                    studentAnswers.add(studentAnswer);
                }
            }

            quizManager.gradeQuiz(quizID, studentID, studentAnswers);
            JOptionPane.showMessageDialog(parentFrame, "Quiz graded successfully for " + studentName + "!");
        }
    }

    private void showViewQuizzesDialog(JFrame parentFrame) {
        StringBuilder quizzes = new StringBuilder("Available Quizzes:\n");
        for (Quiz quiz : quizManager.getAllQuizzes()) {
            quizzes.append("Quiz ID: ").append(quiz.getQuizID()).append(", Topic: ").append(quiz.getTopic()).append("\n");
        }
        JOptionPane.showMessageDialog(parentFrame, quizzes.toString());
    }

    private boolean isPositiveInteger(String str) {
        try {
            int num = Integer.parseInt(str);
            return num > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
        
    

   private void importQuizFromFile() {
    try {
        // Create a file instance using the file located in the `filess` package
        File quizFile = new File("src/quiz/managment/system/filess/quiz.txt");

        // Use the custom file reader to read and process the file
        QuizFileReader reader = new QuizFileReader();
        reader.readQuizFile(quizFile);

        // Process the questions and store them in the QuizManager
        Quiz quiz = quizManager.getQuiz("1"); // Assuming you want to add questions to quiz ID "1"

        if (quiz == null) {
            JOptionPane.showMessageDialog(null, "Quiz with ID '1' not found!");
            return;
        }

        for (QuizFileReader.Question question : reader.getQuestions()) {
            if (quiz instanceof MultipleChoiceQuiz) {
                quiz.addQuestion(question.text, question.options, question.correctAnswer);
            } else {
                JOptionPane.showMessageDialog(null, "This feature supports only Multiple Choice Quizzes.");
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Quiz imported successfully!");
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error reading quiz file: " + e.getMessage());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error importing quiz: " + e.getMessage());
    }
}
    public static void main(String[] args) {
        new QuizManagementSystem();
    }
}