package com.interview.platform.service;

import com.interview.platform.model.Question;
import com.interview.platform.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

/**
 * Manages the ArrayList of interview questions and category selection.
 */
@Service
public class QuestionBankService {

    private final QuestionRepository questionRepository;

    public QuestionBankService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public int totalQuestions() {
        return questionRepository.size();
    }

    public ArrayList<String> categories() {
        return questionRepository.distinctCategories();
    }

    public ArrayList<Question> selectQuestions(String category, int count) {
        ArrayList<Question> pool = new ArrayList<>(questionRepository.findByCategory(normalizeCategory(category)));
        Collections.shuffle(pool);
        int limit = Math.min(Math.max(count, 1), pool.size());
        return new ArrayList<>(pool.subList(0, limit));
    }

    public String normalizeCategory(String category) {
        if (category == null || category.isBlank()) {
            return "All Categories";
        }
        return switch (category.trim().toLowerCase(Locale.ROOT)) {
            case "java" -> "Java";
            case "oop" -> "OOP";
            case "data structures", "dsa", "ds" -> "Data Structures";
            case "algorithms" -> "Algorithms";
            case "dbms", "dbms & sql", "sql" -> "DBMS & SQL";
            case "operating systems", "os" -> "Operating Systems";
            case "computer networks", "networks" -> "Computer Networks";
            case "general", "general technical", "mixed", "mixed technical interview" -> "Mixed Technical Interview";
            default -> category;
        };
    }
}
