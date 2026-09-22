# AI Interview Preparation Platform

Java Spring Boot web application for technical interview practice. Student answers are evaluated with a **keyword matching algorithm implemented in Java**, then scored, graded, and explained with AI-style (rule-based) feedback.

This is **not** a neural-network chatbot. The intelligence is Java OOP + keyword matching.

## How to run

Requirements: Java 17+ and Maven.

```bash
cd ai-interview-preparation-platform
./mvnw spring-boot:run
```

If the Maven wrapper is not present:

```bash
mvn spring-boot:run
```

Open [http://localhost:8080](http://localhost:8080).

## Academic mapping

| Concept | Where it lives |
| --- | --- |
| Java OOP | `model/` and `service/` classes |
| ArrayList | Question bank, keywords, answers |
| Scanner vs web input | Console demo: `academic/ScannerInputDemo.java`. Website: HTML forms POST to Spring controllers (`studentName`, `answer`) |
| Keyword matching | `KeywordMatcher` |
| Evaluation & marks | `AnswerEvaluator`, `ScoreManager` |
| Percentage & grade | `PerformanceAnalyzer` |
| AI-style suggestions | `FeedbackGenerator` |

## Demo flow

Home → Start Interview → enter name → pick category and count → answer → Java evaluation → next → final report → restart.
