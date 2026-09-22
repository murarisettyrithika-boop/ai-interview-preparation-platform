package com.interview.platform.model;

/**
 * HttpSession payload for one student interview.
 */
public class InterviewSession {

    public static final String SESSION_KEY = "INTERVIEW_SESSION";

    private Student student;
    private Interview interview;
    private AnswerRecord lastAnswer;
    private PerformanceReport report;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Interview getInterview() {
        return interview;
    }

    public void setInterview(Interview interview) {
        this.interview = interview;
    }

    public AnswerRecord getLastAnswer() {
        return lastAnswer;
    }

    public void setLastAnswer(AnswerRecord lastAnswer) {
        this.lastAnswer = lastAnswer;
    }

    public PerformanceReport getReport() {
        return report;
    }

    public void setReport(PerformanceReport report) {
        this.report = report;
    }
}
