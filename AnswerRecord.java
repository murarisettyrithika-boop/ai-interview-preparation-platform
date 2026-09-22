package com.interview.platform.model;

import java.util.ArrayList;

/**
 * One submitted answer together with Java evaluation results.
 */
public class AnswerRecord {

    private final Question question;
    private final String studentAnswer;
    private final EvaluationResult evaluation;

    public AnswerRecord(Question question, String studentAnswer, EvaluationResult evaluation) {
        this.question = question;
        this.studentAnswer = studentAnswer;
        this.evaluation = evaluation;
    }

    public Question getQuestion() {
        return question;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public EvaluationResult getEvaluation() {
        return evaluation;
    }

    public double getScoreObtained() {
        return evaluation.getScore();
    }

    public int getMaxMarks() {
        return question.getMaxMarks();
    }

    public String getCategory() {
        return question.getCategory();
    }

    public String getStatus() {
        return evaluation.getStatus();
    }

    public ArrayList<String> getMatchedKeywords() {
        return evaluation.getMatchedKeywords();
    }

    public ArrayList<String> getMissingKeywords() {
        return evaluation.getMissingKeywords();
    }
}
