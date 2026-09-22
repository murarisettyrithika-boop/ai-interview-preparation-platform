package com.interview.platform.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Stores one interview question, its category, difficulty,
 * expected keywords, and maximum marks.
 */
public class Question {

    private final int id;
    private final String text;
    private final String category;
    private final String difficulty;
    private final ArrayList<String> expectedKeywords;
    private final int maxMarks;

    public Question(int id, String text, String category, String difficulty,
                    List<String> expectedKeywords, int maxMarks) {
        this.id = id;
        this.text = text;
        this.category = category;
        this.difficulty = difficulty;
        this.expectedKeywords = new ArrayList<>(expectedKeywords);
        this.maxMarks = maxMarks;
    }

    public static Question of(int id, String text, String category, String difficulty,
                              int maxMarks, String... keywords) {
        return new Question(id, text, category, difficulty, Arrays.asList(keywords), maxMarks);
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public ArrayList<String> getExpectedKeywords() {
        return new ArrayList<>(expectedKeywords);
    }

    public int getKeywordCount() {
        return expectedKeywords.size();
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public List<String> viewKeywords() {
        return Collections.unmodifiableList(expectedKeywords);
    }
}
