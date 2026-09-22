package com.interview.platform.service;

import com.interview.platform.model.AnswerRecord;
import com.interview.platform.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Calculates marks for one answer and the running interview total.
 */
@Service
public class ScoreManager {

    public double calculateQuestionScore(double matchPercentage, int maxMarks) {
        return roundToOne((matchPercentage / 100.0) * maxMarks);
    }

    public double calculateTotalScore(List<AnswerRecord> answers) {
        double total = 0;
        for (AnswerRecord record : answers) {
            total += record.getScoreObtained();
        }
        return roundToOne(total);
    }

    public int calculateMaximumScore(List<AnswerRecord> answers) {
        int max = 0;
        for (AnswerRecord record : answers) {
            max += record.getMaxMarks();
        }
        return max;
    }

    public double calculatePercentage(double totalScore, int maximumScore) {
        if (maximumScore == 0) {
            return 0;
        }
        return roundToOne((totalScore / maximumScore) * 100.0);
    }

    public double calculatePercentage(Student student) {
        return calculatePercentage(student.getTotalScore(), student.getMaximumScore());
    }

    public double roundToOne(double value) {
        return Math.round(value * 10.0) / 10.0;
    }
}
