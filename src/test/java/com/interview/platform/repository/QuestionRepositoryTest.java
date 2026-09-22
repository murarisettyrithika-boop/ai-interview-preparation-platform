package com.interview.platform.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class QuestionRepositoryTest {

    @Test
    void loadsAtLeastFiftyQuestions() {
        QuestionRepository repository = new QuestionRepository();
        repository.loadQuestions();
        assertTrue(repository.size() >= 50, "Question bank must contain at least 50 questions");
        assertTrue(repository.size() >= 100, "Preferred bank size is 100+ questions, found " + repository.size());
    }
}
