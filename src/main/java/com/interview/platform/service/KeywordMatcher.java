package com.interview.platform.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Keyword matching algorithm used to evaluate student answers.
 *
 * Steps:
 * 1. Convert the answer to lowercase
 * 2. Remove punctuation
 * 3. Read expected keywords
 * 4. Compare each keyword with the normalized answer
 * 5. Count matches
 */
@Component
public class KeywordMatcher {

    public String normalize(String text) {
        if (text == null) {
            return "";
        }
        String lower = text.toLowerCase(Locale.ROOT);
        String withoutPunctuation = lower.replaceAll("[^a-z0-9+.#\\s-]", " ");
        return withoutPunctuation.replaceAll("\\s+", " ").trim();
    }

    public boolean matches(String normalizedAnswer, String keyword) {
        if (normalizedAnswer.isBlank() || keyword == null || keyword.isBlank()) {
            return false;
        }
        String normalizedKeyword = normalize(keyword);
        if (normalizedKeyword.isBlank()) {
            return false;
        }

        if (normalizedKeyword.contains(" ")) {
            return normalizedAnswer.contains(normalizedKeyword);
        }

        Pattern wordPattern = Pattern.compile(
                "\\b" + Pattern.quote(normalizedKeyword) + "(es|s|ing|ed|er)?\\b");
        if (wordPattern.matcher(normalizedAnswer).find()) {
            return true;
        }

        if (normalizedKeyword.length() >= 4 && normalizedAnswer.contains(normalizedKeyword)) {
            return true;
        }
        return false;
    }

    public MatchResult matchAll(String studentAnswer, List<String> expectedKeywords) {
        String normalizedAnswer = normalize(studentAnswer);
        ArrayList<String> matched = new ArrayList<>();
        ArrayList<String> missing = new ArrayList<>();
        LinkedHashSet<String> seen = new LinkedHashSet<>();

        for (String keyword : expectedKeywords) {
            String key = keyword.trim();
            if (key.isEmpty() || !seen.add(key.toLowerCase(Locale.ROOT))) {
                continue;
            }
            if (matches(normalizedAnswer, key)) {
                matched.add(key);
            } else {
                missing.add(key);
            }
        }
        return new MatchResult(matched, missing, normalizedAnswer);
    }

    public static class MatchResult {
        private final ArrayList<String> matchedKeywords;
        private final ArrayList<String> missingKeywords;
        private final String normalizedAnswer;

        public MatchResult(ArrayList<String> matchedKeywords,
                           ArrayList<String> missingKeywords,
                           String normalizedAnswer) {
            this.matchedKeywords = matchedKeywords;
            this.missingKeywords = missingKeywords;
            this.normalizedAnswer = normalizedAnswer;
        }

        public ArrayList<String> getMatchedKeywords() {
            return matchedKeywords;
        }

        public ArrayList<String> getMissingKeywords() {
            return missingKeywords;
        }

        public String getNormalizedAnswer() {
            return normalizedAnswer;
        }

        public int getMatchedCount() {
            return matchedKeywords.size();
        }

        public int getTotalKeywords() {
            return matchedKeywords.size() + missingKeywords.size();
        }

        public double getMatchPercentage() {
            int total = getTotalKeywords();
            if (total == 0) {
                return 0;
            }
            return (getMatchedCount() * 100.0) / total;
        }
    }
}
