/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quiz.managment.system;

public class TestStudent {
    public static void main(String[] args) {
        Student student = new Student("ST001", "John Doe");

        student.addGrade("101", 85);
        student.addGrade("202", 90);

        System.out.println("Testing Adding and Viewing Grades:");
        student.viewGrades(); 
        
    }
}
