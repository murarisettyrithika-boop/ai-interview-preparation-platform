package com.interview.platform.service;

import com.interview.platform.model.AnswerRecord;
import com.interview.platform.model.EvaluationResult;
import com.interview.platform.model.Interview;
import com.interview.platform.model.InterviewSession;
import com.interview.platform.model.PerformanceReport;
import com.interview.platform.model.Question;
import com.interview.platform.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Coordinates interview flow: start, submit, next, and final report.
 * All evaluation happens in Java services, not in the browser.
 */
@Service
public class InterviewService {

    private final QuestionBankService questionBankService;
    private final AnswerEvaluator answerEvaluator;
    private final PerformanceAnalyzer performanceAnalyzer;

    public InterviewService(QuestionBankService questionBankService,
                            AnswerEvaluator answerEvaluator,
                            PerformanceAnalyzer performanceAnalyzer) {
        this.questionBankService = questionBankService;
        this.answerEvaluator = answerEvaluator;
        this.performanceAnalyzer = performanceAnalyzer;
    }

    public InterviewSession startInterview(String studentName, String category, int questionCount) {
        String safeName = studentName == null || studentName.isBlank() ? "Student" : studentName.trim();
        String selectedCategory = questionBankService.normalizeCategory(category);
        ArrayList<Question> selected = questionBankService.selectQuestions(selectedCategory, questionCount);

        Student student = new Student(safeName, selectedCategory, selected.size());
        Interview interview = new Interview(selected);

        InterviewSession session = new InterviewSession();
        session.setStudent(student);
        session.setInterview(interview);
        return session;
    }

    public AnswerRecord submitAnswer(InterviewSession session, String answerText) {
        Interview interview = session.getInterview();
        Question question = interview.getCurrentQuestion();
        if (question == null || interview.isAwaitingNext()) {
            return session.getLastAnswer();
        }
        String answer = answerText == null ? "" : answerText;
        EvaluationResult evaluation = answerEvaluator.evaluate(question, answer);
        AnswerRecord record = new AnswerRecord(question, answer, evaluation);
        session.getStudent().addAnswer(record);
        interview.markAnswered();
        session.setLastAnswer(record);
        return record;
    }

    public boolean nextQuestion(InterviewSession session) {
        Interview interview = session.getInterview();
        if (!interview.isAwaitingNext()) {
            return false;
        }
        if (interview.hasMoreQuestions()) {
            interview.moveToNext();
            session.setLastAnswer(null);
            return true;
        }
        interview.moveToNext();
        PerformanceReport report = performanceAnalyzer.analyze(session.getStudent());
        session.setReport(report);
        return false;
    }

    public PerformanceReport completeIfNeeded(InterviewSession session) {
        if (session.getReport() != null) {
            return session.getReport();
        }
        PerformanceReport report = performanceAnalyzer.analyze(session.getStudent());
        session.setReport(report);
        return report;
    }
}
