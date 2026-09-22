package com.interview.platform.controller;

import com.interview.platform.model.AnswerRecord;
import com.interview.platform.model.Interview;
import com.interview.platform.model.InterviewSession;
import com.interview.platform.model.PerformanceReport;
import com.interview.platform.service.FeedbackGenerator;
import com.interview.platform.service.InterviewService;
import com.interview.platform.service.QuestionBankService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class InterviewController {

    private final InterviewService interviewService;
    private final QuestionBankService questionBankService;
    private final FeedbackGenerator feedbackGenerator;

    public InterviewController(InterviewService interviewService,
                               QuestionBankService questionBankService,
                               FeedbackGenerator feedbackGenerator) {
        this.interviewService = interviewService;
        this.questionBankService = questionBankService;
        this.feedbackGenerator = feedbackGenerator;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("questionCount", questionBankService.totalQuestions());
        model.addAttribute("categories", questionBankService.categories());
        return "index";
    }

    @GetMapping("/how-it-works")
    public String howItWorks() {
        return "redirect:/#how-it-works";
    }

    @GetMapping("/technologies")
    public String technologies() {
        return "redirect:/#technologies";
    }

    @GetMapping("/start")
    public String startPage(Model model) {
        model.addAttribute("maxAvailable", questionBankService.totalQuestions());
        model.addAttribute("categories", questionBankService.categories());
        return "start";
    }

    @PostMapping("/start")
    public String startInterview(@RequestParam String studentName,
                                 @RequestParam(defaultValue = "All Categories") String category,
                                 @RequestParam(defaultValue = "10") int questionCount,
                                 HttpSession httpSession,
                                 RedirectAttributes redirectAttributes) {
        if (studentName == null || studentName.isBlank()) {
            redirectAttributes.addFlashAttribute("error", "Please enter your name to begin.");
            return "redirect:/start";
        }
        InterviewSession session = interviewService.startInterview(studentName, category, questionCount);
        httpSession.setAttribute(InterviewSession.SESSION_KEY, session);
        return "redirect:/interview";
    }

    @GetMapping("/interview")
    public String interview(HttpSession httpSession, Model model) {
        InterviewSession session = current(httpSession);
        if (session == null) {
            return "redirect:/start";
        }
        Interview interview = session.getInterview();
        if (interview.isComplete()) {
            return "redirect:/result";
        }
        populateInterviewModel(model, session);
        if (interview.isAwaitingNext()) {
            return "feedback";
        }
        return "interview";
    }

    @PostMapping("/interview/submit")
    public String submit(@RequestParam(name = "answer", required = false) String answer,
                         HttpSession httpSession,
                         RedirectAttributes redirectAttributes) {
        InterviewSession session = current(httpSession);
        if (session == null) {
            return "redirect:/start";
        }
        if (answer == null || answer.isBlank()) {
            redirectAttributes.addFlashAttribute("error", "Please type your answer before submitting.");
            return "redirect:/interview";
        }
        AnswerRecord record = interviewService.submitAnswer(session, answer);
        httpSession.setAttribute(InterviewSession.SESSION_KEY, session);
        if (record != null) {
            return "redirect:/feedback";
        }
        return "redirect:/interview";
    }

    @GetMapping("/feedback")
    public String feedback(HttpSession httpSession, Model model) {
        InterviewSession session = current(httpSession);
        if (session == null) {
            return "redirect:/start";
        }
        if (session.getLastAnswer() == null) {
            return "redirect:/interview";
        }
        populateInterviewModel(model, session);
        return "feedback";
    }

    @PostMapping("/interview/next")
    public String next(HttpSession httpSession) {
        InterviewSession session = current(httpSession);
        if (session == null) {
            return "redirect:/start";
        }
        boolean more = interviewService.nextQuestion(session);
        httpSession.setAttribute(InterviewSession.SESSION_KEY, session);
        if (more) {
            return "redirect:/interview";
        }
        return "redirect:/result";
    }

    @GetMapping("/result")
    public String result(HttpSession httpSession, Model model) {
        InterviewSession session = current(httpSession);
        if (session == null) {
            return "redirect:/start";
        }
        if (session.getStudent().getAnsweredCount() == 0) {
            return "redirect:/interview";
        }
        PerformanceReport report = interviewService.completeIfNeeded(session);
        model.addAttribute("report", report);
        model.addAttribute("student", session.getStudent());
        model.addAttribute("encouragement", feedbackGenerator.encourage(report.getPerformanceGrade()));
        return "result";
    }

    @PostMapping("/restart")
    public String restart(HttpSession httpSession) {
        httpSession.removeAttribute(InterviewSession.SESSION_KEY);
        return "redirect:/start";
    }

    private InterviewSession current(HttpSession httpSession) {
        Object value = httpSession.getAttribute(InterviewSession.SESSION_KEY);
        if (value instanceof InterviewSession session) {
            return session;
        }
        return null;
    }

    private void populateInterviewModel(Model model, InterviewSession session) {
        Interview interview = session.getInterview();
        model.addAttribute("student", session.getStudent());
        model.addAttribute("interview", interview);
        model.addAttribute("question", interview.getCurrentQuestion());
        model.addAttribute("lastAnswer", session.getLastAnswer());
        model.addAttribute("progressPercent",
                (int) Math.round((session.getStudent().getAnsweredCount() * 100.0) / interview.getTotalQuestions()));
    }
}
