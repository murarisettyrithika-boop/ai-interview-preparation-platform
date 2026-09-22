package com.interview.platform.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Stores student identity, submitted answers, running score, and performance.
 */
public class Student {

    private String name;
    private String selectedCategory;
    private int requestedQuestionCount;
    private final ArrayList<AnswerRecord> answers = new ArrayList<>();
    private double totalScore;
    private int maximumScore;
    private double percentage;
    private String performance;

    public Student() {
    }

    public Student(String name, String selectedCategory, int requestedQuestionCount) {
        this.name = name;
        this.selectedCategory = selectedCategory;
        this.requestedQuestionCount = requestedQuestionCount;
    }

    public void addAnswer(AnswerRecord record) {
        answers.add(record);
        totalScore += record.getScoreObtained();
        maximumScore += record.getMaxMarks();
        percentage = maximumScore == 0 ? 0 : (totalScore / maximumScore) * 100.0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public int getRequestedQuestionCount() {
        return requestedQuestionCount;
    }

    public void setRequestedQuestionCount(int requestedQuestionCount) {
        this.requestedQuestionCount = requestedQuestionCount;
    }

    public List<AnswerRecord> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public ArrayList<AnswerRecord> getAnswerList() {
        return answers;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public int getMaximumScore() {
        return maximumScore;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public int getAnsweredCount() {
        return answers.size();
    }
}
