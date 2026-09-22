package com.interview.platform.model;

public class CategoryStats {

    private final String category;
    private final double score;
    private final int maxScore;
    private final double percentage;
    private final int questionsAttempted;

    public CategoryStats(String category, double score, int maxScore, int questionsAttempted) {
        this.category = category;
        this.score = score;
        this.maxScore = maxScore;
        this.questionsAttempted = questionsAttempted;
        this.percentage = maxScore == 0 ? 0 : (score / maxScore) * 100.0;
    }

    public String getCategory() {
        return category;
    }

    public double getScore() {
        return score;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public double getPercentage() {
        return percentage;
    }

    public int getQuestionsAttempted() {
        return questionsAttempted;
    }
}
