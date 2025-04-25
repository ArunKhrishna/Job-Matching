package com.jobmatch.controller;

import com.jobmatch.model.Candidate;
import com.jobmatch.model.Job;
import com.jobmatch.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MatchController {
    @Autowired
    private MatchingService service;

    @GetMapping("/")
    public String index() { return "index"; }

    @GetMapping("/candidates")
    public String candidates(Model model) {
        model.addAttribute("candidate", new Candidate());
        model.addAttribute("candidates", service.getAllCandidates());
        return "candidates";
    }

    @PostMapping("/candidates")
    public String addCandidate(@ModelAttribute Candidate candidate) {
        service.addCandidate(candidate);
        return "redirect:/candidates";
    }

    @GetMapping("/jobs")
    public String jobs(Model model) {
        model.addAttribute("job", new Job());
        model.addAttribute("jobs", service.getAllJobs());
        return "jobs";
    }

    @PostMapping("/jobs")
    public String addJob(@ModelAttribute Job job) {
        service.addJob(job);
        return "redirect:/jobs";
    }

    @GetMapping("/match")
    public String matchForm(Model model) {
        model.addAttribute("candidates", service.getAllCandidates());
        model.addAttribute("matches", List.of());
        return "matches";
    }

    @PostMapping("/match")
    public String doMatch(@RequestParam Long candidateId, Model model) {
        List<Job> matches = service.getMatchingJobs(candidateId);
        model.addAttribute("candidates", service.getAllCandidates());
        model.addAttribute("matches", matches);
        model.addAttribute("selected", candidateId);
        return "matches";
    }
}
