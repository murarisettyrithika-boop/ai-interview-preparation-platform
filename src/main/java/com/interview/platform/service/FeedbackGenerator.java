package com.interview.platform.service;

import com.interview.platform.model.CategoryStats;
import com.interview.platform.model.EvaluationResult;
import com.interview.platform.model.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Generates intelligent, rule-based (AI-style) feedback from keyword matching.
 * This is not a neural-network API; suggestions are produced by Java logic.
 */
@Service
public class FeedbackGenerator {

    public String qualityMessage(String status) {
        return switch (status) {
            case EvaluationResult.GOOD_ANSWER ->
                    "Your answer contains most of the important concepts. Good understanding of the topic.";
            case EvaluationResult.PARTIALLY_CORRECT ->
                    "Your answer covers some important concepts. Try including additional technical points.";
            default ->
                    "Your answer is missing important concepts. Review the topic and try explaining the key concepts more clearly.";
        };
    }

    public String questionFeedback(Question question,
                                   KeywordMatcher.MatchResult match,
                                   String status) {
        String topic = question.getCategory();
        ArrayList<String> missing = match.getMissingKeywords();
        ArrayList<String> matched = match.getMatchedKeywords();

        StringBuilder builder = new StringBuilder();
        if (EvaluationResult.GOOD_ANSWER.equals(status)) {
            builder.append("Your answer demonstrates a good understanding of ")
                    .append(topic)
                    .append(". You correctly mentioned ");
            builder.append(join(matched, 3));
            builder.append(".");
            if (!missing.isEmpty()) {
                builder.append(" To make the answer more complete, include ")
                        .append(join(missing, 3))
                        .append(" and briefly explain why those ideas matter.");
            } else {
                builder.append(" You covered the expected keywords clearly. Practice explaining the same idea with a short example in 1–2 minutes.");
            }
        } else if (EvaluationResult.PARTIALLY_CORRECT.equals(status)) {
            builder.append("You showed partial understanding of ")
                    .append(topic)
                    .append(".");
            if (!matched.isEmpty()) {
                builder.append(" Strong points: ").append(join(matched, 4)).append(".");
            }
            if (!missing.isEmpty()) {
                builder.append(" Important missing concepts: ")
                        .append(join(missing, 4))
                        .append(". Add these keywords with a definition or example.");
            }
        } else {
            builder.append("This ")
                    .append(topic)
                    .append(" answer needs more technical depth.");
            if (!missing.isEmpty()) {
                builder.append(" Review: ")
                        .append(join(missing, 5))
                        .append(".");
            }
            builder.append(" Rewrite the answer as a short definition plus 2–3 key properties.");
        }
        return builder.toString();
    }

    public ArrayList<String> strengths(List<CategoryStats> stats, int goodAnswers, int total, double percentage) {
        ArrayList<String> strengths = new ArrayList<>();
        List<CategoryStats> strong = stats.stream()
                .filter(s -> s.getPercentage() >= 75)
                .sorted(Comparator.comparingDouble(CategoryStats::getPercentage).reversed())
                .toList();

        for (CategoryStats stat : strong) {
            strengths.add("Strong understanding of " + stat.getCategory() + " (" +
                    Math.round(stat.getPercentage()) + "%).");
        }
        if (goodAnswers > 0) {
            strengths.add("You produced " + goodAnswers + " strong answer"
                    + (goodAnswers == 1 ? "" : "s") + " out of " + total + " questions.");
        }
        if (percentage >= 75) {
            strengths.add("Good overall interview communication using core technical terms.");
        }
        if (strengths.isEmpty()) {
            strengths.add("You attempted every selected question, which is a solid interview habit.");
            strengths.add("Keep building keyword coverage so definitions become more complete.");
        }
        return strengths;
    }

    public ArrayList<String> areasToImprove(List<CategoryStats> stats, int weakAnswers) {
        ArrayList<String> areas = new ArrayList<>();
        List<CategoryStats> weak = stats.stream()
                .filter(s -> s.getPercentage() < 75)
                .sorted(Comparator.comparingDouble(CategoryStats::getPercentage))
                .toList();

        for (CategoryStats stat : weak) {
            areas.add(stat.getCategory() + " needs more revision (" +
                    Math.round(stat.getPercentage()) + "% keyword/score coverage).");
        }
        if (weakAnswers > 0) {
            areas.add("Practice explaining definitions out loud so fewer answers stay below 50% keyword match.");
        }
        if (areas.isEmpty()) {
            areas.add("Stretch yourself with harder follow-up questions and real project examples.");
        }
        return areas;
    }

    public ArrayList<String> personalizedSuggestions(String name,
                                                     double percentage,
                                                     String grade,
                                                     List<CategoryStats> stats,
                                                     int weakAnswers) {
        ArrayList<String> suggestions = new ArrayList<>();
        suggestions.add(name + ", your current performance grade is " + grade +
                " at " + Math.round(percentage) + "%. Continue practicing regularly to improve technical confidence.");

        List<CategoryStats> weakest = stats.stream()
                .sorted(Comparator.comparingDouble(CategoryStats::getPercentage))
                .limit(3)
                .toList();
        for (CategoryStats stat : weakest) {
            if (stat.getPercentage() < 85) {
                suggestions.add("Revise " + stat.getCategory() +
                        " and practice explaining technical definitions with examples.");
            }
        }

        if (weakAnswers >= 2) {
            suggestions.add("Try answering interview questions verbally within 1–2 minutes, then check whether you named the expected keywords.");
        } else {
            suggestions.add("After each strong answer, add one real-world example from a class project or lab.");
        }

        suggestions.add("Keep a short revision sheet of missed keywords after every practice session.");
        return suggestions;
    }

    private String join(List<String> items, int limit) {
        List<String> slice = items.stream().limit(limit).collect(Collectors.toList());
        if (slice.isEmpty()) {
            return "the core ideas";
        }
        if (slice.size() == 1) {
            return slice.get(0);
        }
        if (slice.size() == 2) {
            return slice.get(0) + " and " + slice.get(1);
        }
        return String.join(", ", slice.subList(0, slice.size() - 1))
                + ", and " + slice.get(slice.size() - 1);
    }

    public String encourage(String grade) {
        return switch (grade.toLowerCase(Locale.ROOT)) {
            case "excellent" -> "Outstanding work. You are interview-ready on the practiced topics.";
            case "very good" -> "Very encouraging result. Polish the weaker categories and you will sound even more confident.";
            case "good" -> "A solid foundation. Targeted revision will lift your score quickly.";
            case "needs improvement" -> "You have started well. Focus on missing keywords and rewrite short, complete answers.";
            default -> "More practice will help. Learn one topic deeply each day and reattempt the interview.";
        };
    }
}
