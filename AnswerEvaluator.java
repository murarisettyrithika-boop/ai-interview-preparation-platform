package com.interview.platform.service;

import com.interview.platform.model.EvaluationResult;
import com.interview.platform.model.Question;
import org.springframework.stereotype.Service;

/**
 * Evaluates answer quality from keyword-match percentage.
 */
@Service
public class AnswerEvaluator {

    private final KeywordMatcher keywordMatcher;
    private final ScoreManager scoreManager;
    private final FeedbackGenerator feedbackGenerator;

    public AnswerEvaluator(KeywordMatcher keywordMatcher,
                           ScoreManager scoreManager,
                           FeedbackGenerator feedbackGenerator) {
        this.keywordMatcher = keywordMatcher;
        this.scoreManager = scoreManager;
        this.feedbackGenerator = feedbackGenerator;
    }

    public EvaluationResult evaluate(Question question, String studentAnswer) {
        KeywordMatcher.MatchResult match = keywordMatcher.matchAll(
                studentAnswer, question.viewKeywords());

        double matchPercentage = match.getMatchPercentage();
        double score = scoreManager.calculateQuestionScore(matchPercentage, question.getMaxMarks());
        String status = classify(matchPercentage);
        String qualityFeedback = feedbackGenerator.qualityMessage(status);
        String aiFeedback = feedbackGenerator.questionFeedback(question, match, status);

        return new EvaluationResult(
                match.getMatchedKeywords(),
                match.getMissingKeywords(),
                matchPercentage,
                score,
                question.getMaxMarks(),
                status,
                qualityFeedback,
                aiFeedback
        );
    }

    public String classify(double matchPercentage) {
        if (matchPercentage >= 80) {
            return EvaluationResult.GOOD_ANSWER;
        }
        if (matchPercentage >= 50) {
            return EvaluationResult.PARTIALLY_CORRECT;
        }
        return EvaluationResult.NEEDS_IMPROVEMENT;
    }
}
