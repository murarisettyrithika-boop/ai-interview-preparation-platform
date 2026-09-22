package com.interview.platform.academic;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Academic demonstration of Scanner-based keyboard input.
 *
 * The website does not use Scanner. HTML forms POST the student's name,
 * category, question count, and answers to Spring Boot controllers.
 * Those values are the web equivalent of scanner.nextLine().
 *
 * This class can be run independently for viva explanation:
 *   java com.interview.platform.academic.ScannerInputDemo
 */
public class ScannerInputDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Academic Scanner Input Demo ===");
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter a practice answer: ");
        String answer = scanner.nextLine();

        ArrayList<String> expected = new ArrayList<>(List.of(
                "class", "object", "inheritance", "polymorphism", "encapsulation"));
        String normalized = answer.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9\\s]", " ");

        int matched = 0;
        for (String keyword : expected) {
            if (normalized.contains(keyword)) {
                matched++;
                System.out.println("✓ " + keyword);
            } else {
                System.out.println("✗ " + keyword);
            }
        }
        System.out.println(name + " matched " + matched + " / " + expected.size() + " keywords.");
        scanner.close();
    }
}
