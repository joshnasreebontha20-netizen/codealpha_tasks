import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private double grade;

    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }
}

public class StudentGradeManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.println("=== Student Grade Management System ===");

        System.out.print("Enter number of students: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // consume newline

        for (int i = 1; i <= n; i++) {
            System.out.println("\nStudent " + i);

            System.out.print("Enter student name: ");
            String name = scanner.nextLine();

            System.out.print("Enter grade: ");
            double grade = scanner.nextDouble();
            scanner.nextLine(); // consume newline

            students.add(new Student(name, grade));
        }

        // Calculate statistics
        double total = 0;
        double highest = students.get(0).getGrade();
        double lowest = students.get(0).getGrade();

        for (Student student : students) {
            double grade = student.getGrade();
            total += grade;

            if (grade > highest) {
                highest = grade;
            }

            if (grade < lowest) {
                lowest = grade;
            }
        }

        double average = total / students.size();

        // Display Report
        System.out.println("\n=================================");
        System.out.println("      STUDENT SUMMARY REPORT");
        System.out.println("=================================");

        System.out.printf("%-20s %-10s\n", "Student Name", "Grade");
        System.out.println("---------------------------------");

        for (Student student : students) {
            System.out.printf("%-20s %-10.2f\n",
                    student.getName(),
                    student.getGrade());
        }

        System.out.println("---------------------------------");
        System.out.printf("Average Score : %.2f\n", average);
        System.out.printf("Highest Score : %.2f\n", highest);
        System.out.printf("Lowest Score  : %.2f\n", lowest);

        scanner.close();
    }
    }
    }
}
