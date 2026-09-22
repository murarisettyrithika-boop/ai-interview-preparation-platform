package com.interview.platform.service;

import com.interview.platform.model.EvaluationResult;
import com.interview.platform.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KeywordMatcherTest {

    private KeywordMatcher matcher;

    @BeforeEach
    void setUp() {
        matcher = new KeywordMatcher();
    }

    @Test
    void matchesOopKeywordsFromSampleAnswer() {
        String answer = "OOP is based on classes and objects. It supports inheritance and polymorphism.";
        KeywordMatcher.MatchResult result = matcher.matchAll(
                answer, List.of("class", "object", "inheritance", "polymorphism", "encapsulation"));

        assertEquals(4, result.getMatchedCount());
        assertEquals(1, result.getMissingKeywords().size());
        assertTrue(result.getMissingKeywords().contains("encapsulation"));
    }

    @Test
    void ignoresPunctuationAndCase() {
        assertTrue(matcher.matches(matcher.normalize("Java uses a JVM!"), "jvm"));
    }
}

class AnswerEvaluatorTest {

    private AnswerEvaluator evaluator;

    @BeforeEach
    void setUp() {
        KeywordMatcher matcher = new KeywordMatcher();
        ScoreManager scores = new ScoreManager();
        FeedbackGenerator feedback = new FeedbackGenerator();
        evaluator = new AnswerEvaluator(matcher, scores, feedback);
    }

    @Test
    void goodAnswerScoresNearMaximum() {
        Question question = Question.of(1, "What is OOP?", "OOP", "Easy", 2,
                "class", "object", "inheritance", "polymorphism", "encapsulation");
        EvaluationResult result = evaluator.evaluate(question,
                "OOP uses class, object, inheritance, polymorphism and encapsulation.");
        assertEquals(EvaluationResult.GOOD_ANSWER, result.getStatus());
        assertEquals(2.0, result.getScore());
    }
}
