package com.interview.platform.model;

import java.util.ArrayList;

/**
 * Result of keyword matching and answer evaluation for one question.
 */
public class EvaluationResult {

    public static final String GOOD_ANSWER = "GOOD ANSWER";
    public static final String PARTIALLY_CORRECT = "PARTIALLY CORRECT";
    public static final String NEEDS_IMPROVEMENT = "NEEDS IMPROVEMENT";

    private final ArrayList<String> matchedKeywords;
    private final ArrayList<String> missingKeywords;
    private final int matchedCount;
    private final int totalKeywords;
    private final double matchPercentage;
    private final double score;
    private final int maxMarks;
    private final String status;
    private final String qualityFeedback;
    private final String aiFeedback;

    public EvaluationResult(ArrayList<String> matchedKeywords,
                            ArrayList<String> missingKeywords,
                            double matchPercentage,
                            double score,
                            int maxMarks,
                            String status,
                            String qualityFeedback,
                            String aiFeedback) {
        this.matchedKeywords = matchedKeywords;
        this.missingKeywords = missingKeywords;
        this.matchedCount = matchedKeywords.size();
        this.totalKeywords = matchedKeywords.size() + missingKeywords.size();
        this.matchPercentage = matchPercentage;
        this.score = score;
        this.maxMarks = maxMarks;
        this.status = status;
        this.qualityFeedback = qualityFeedback;
        this.aiFeedback = aiFeedback;
    }

    public ArrayList<String> getMatchedKeywords() {
        return matchedKeywords;
    }

    public ArrayList<String> getMissingKeywords() {
        return missingKeywords;
    }

    public int getMatchedCount() {
        return matchedCount;
    }

    public int getTotalKeywords() {
        return totalKeywords;
    }

    public double getMatchPercentage() {
        return matchPercentage;
    }

    public double getScore() {
        return score;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public String getStatus() {
        return status;
    }

    public String getQualityFeedback() {
        return qualityFeedback;
    }

    public String getAiFeedback() {
        return aiFeedback;
    }

    public boolean isGood() {
        return GOOD_ANSWER.equals(status);
    }

    public boolean isPartial() {
        return PARTIALLY_CORRECT.equals(status);
    }

    public boolean isWeak() {
        return NEEDS_IMPROVEMENT.equals(status);
    }
}
