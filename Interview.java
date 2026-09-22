package com.interview.platform.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Orchestrates one interview session: selected questions and current index.
 */
public class Interview {

    private final ArrayList<Question> questions;
    private int currentIndex;
    private boolean awaitingNext;

    public Interview(ArrayList<Question> questions) {
        this.questions = questions;
        this.currentIndex = 0;
        this.awaitingNext = false;
    }

    public Question getCurrentQuestion() {
        if (currentIndex < 0 || currentIndex >= questions.size()) {
            return null;
        }
        return questions.get(currentIndex);
    }

    public int getCurrentNumber() {
        return currentIndex + 1;
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public boolean hasMoreQuestions() {
        return currentIndex < questions.size() - 1;
    }

    public boolean isComplete() {
        return currentIndex >= questions.size();
    }

    public void markAnswered() {
        awaitingNext = true;
    }

    public void moveToNext() {
        currentIndex++;
        awaitingNext = false;
    }

    public boolean isAwaitingNext() {
        return awaitingNext;
    }

    public List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);
    }

    public ArrayList<Question> getQuestionList() {
        return questions;
    }
}
