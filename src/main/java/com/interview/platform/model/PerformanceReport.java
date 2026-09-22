package com.interview.platform.model;

import java.util.ArrayList;

/**
 * Final interview report produced by Java performance analysis.
 */
public class PerformanceReport {

    private final Student student;
    private final int totalQuestions;
    private final int goodAnswers;
    private final int partialAnswers;
    private final int weakAnswers;
    private final double totalScore;
    private final int maximumScore;
    private final double percentage;
    private final String performanceGrade;
    private final double overallKeywordMatchPercentage;
    private final ArrayList<CategoryStats> categoryStats;
    private final ArrayList<String> strengths;
    private final ArrayList<String> areasToImprove;
    private final ArrayList<String> suggestions;

    public PerformanceReport(Student student,
                             int totalQuestions,
                             int goodAnswers,
                             int partialAnswers,
                             int weakAnswers,
                             double totalScore,
                             int maximumScore,
                             double percentage,
                             String performanceGrade,
                             double overallKeywordMatchPercentage,
                             ArrayList<CategoryStats> categoryStats,
                             ArrayList<String> strengths,
                             ArrayList<String> areasToImprove,
                             ArrayList<String> suggestions) {
        this.student = student;
        this.totalQuestions = totalQuestions;
        this.goodAnswers = goodAnswers;
        this.partialAnswers = partialAnswers;
        this.weakAnswers = weakAnswers;
        this.totalScore = totalScore;
        this.maximumScore = maximumScore;
        this.percentage = percentage;
        this.performanceGrade = performanceGrade;
        this.overallKeywordMatchPercentage = overallKeywordMatchPercentage;
        this.categoryStats = categoryStats;
        this.strengths = strengths;
        this.areasToImprove = areasToImprove;
        this.suggestions = suggestions;
    }

    public Student getStudent() {
        return student;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public int getGoodAnswers() {
        return goodAnswers;
    }

    public int getPartialAnswers() {
        return partialAnswers;
    }

    public int getWeakAnswers() {
        return weakAnswers;
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

    public int getRoundedPercentage() {
        return (int) Math.round(percentage);
    }

    public String getPerformanceGrade() {
        return performanceGrade;
    }

    public double getOverallKeywordMatchPercentage() {
        return overallKeywordMatchPercentage;
    }

    public ArrayList<CategoryStats> getCategoryStats() {
        return categoryStats;
    }

    public ArrayList<String> getStrengths() {
        return strengths;
    }

    public ArrayList<String> getAreasToImprove() {
        return areasToImprove;
    }

    public ArrayList<String> getSuggestions() {
        return suggestions;
    }
}
