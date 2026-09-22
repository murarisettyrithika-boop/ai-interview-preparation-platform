package com.interview.platform.service;

import com.interview.platform.model.AnswerRecord;
import com.interview.platform.model.CategoryStats;
import com.interview.platform.model.EvaluationResult;
import com.interview.platform.model.PerformanceReport;
import com.interview.platform.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Calculates percentage, performance grade, and category-wise analysis.
 */
@Service
public class PerformanceAnalyzer {

    private final ScoreManager scoreManager;
    private final FeedbackGenerator feedbackGenerator;

    public PerformanceAnalyzer(ScoreManager scoreManager, FeedbackGenerator feedbackGenerator) {
        this.scoreManager = scoreManager;
        this.feedbackGenerator = feedbackGenerator;
    }

    public String grade(double percentage) {
        if (percentage >= 90) {
            return "Excellent";
        }
        if (percentage >= 75) {
            return "Very Good";
        }
        if (percentage >= 60) {
            return "Good";
        }
        if (percentage >= 40) {
            return "Needs Improvement";
        }
        return "More Practice Required";
    }

    public ArrayList<CategoryStats> categoryPerformance(List<AnswerRecord> answers) {
        Map<String, double[]> totals = new LinkedHashMap<>();
        Map<String, Integer> counts = new LinkedHashMap<>();

        for (AnswerRecord record : answers) {
            String category = record.getCategory();
            totals.putIfAbsent(category, new double[]{0, 0});
            counts.putIfAbsent(category, 0);
            double[] pair = totals.get(category);
            pair[0] += record.getScoreObtained();
            pair[1] += record.getMaxMarks();
            counts.put(category, counts.get(category) + 1);
        }

        ArrayList<CategoryStats> stats = new ArrayList<>();
        for (Map.Entry<String, double[]> entry : totals.entrySet()) {
            String category = entry.getKey();
            double score = scoreManager.roundToOne(entry.getValue()[0]);
            int max = (int) entry.getValue()[1];
            stats.add(new CategoryStats(category, score, max, counts.get(category)));
        }
        return stats;
    }

    public PerformanceReport analyze(Student student) {
        List<AnswerRecord> answers = student.getAnswers();
        int good = 0;
        int partial = 0;
        int weak = 0;
        int matched = 0;
        int totalKeywords = 0;

        for (AnswerRecord record : answers) {
            EvaluationResult evaluation = record.getEvaluation();
            if (evaluation.isGood()) {
                good++;
            } else if (evaluation.isPartial()) {
                partial++;
            } else {
                weak++;
            }
            matched += evaluation.getMatchedCount();
            totalKeywords += evaluation.getTotalKeywords();
        }

        double totalScore = scoreManager.roundToOne(student.getTotalScore());
        int maximumScore = student.getMaximumScore();
        double percentage = scoreManager.calculatePercentage(totalScore, maximumScore);
        String performanceGrade = grade(percentage);
        student.setPercentage(percentage);
        student.setPerformance(performanceGrade);

        double keywordMatchPercentage = totalKeywords == 0
                ? 0
                : scoreManager.roundToOne((matched * 100.0) / totalKeywords);

        ArrayList<CategoryStats> categoryStats = categoryPerformance(answers);
        ArrayList<String> strengths = feedbackGenerator.strengths(categoryStats, good, answers.size(), percentage);
        ArrayList<String> areas = feedbackGenerator.areasToImprove(categoryStats, weak);
        ArrayList<String> suggestions = feedbackGenerator.personalizedSuggestions(
                student.getName(), percentage, performanceGrade, categoryStats, weak);

        return new PerformanceReport(
                student,
                answers.size(),
                good,
                partial,
                weak,
                totalScore,
                maximumScore,
                percentage,
                performanceGrade,
                keywordMatchPercentage,
                categoryStats,
                strengths,
                areas,
                suggestions
        );
    }
}
