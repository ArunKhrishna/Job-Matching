package com.jobmatch.service;

import com.jobmatch.model.Candidate;
import com.jobmatch.model.Job;
import com.jobmatch.repository.CandidateRepository;
import com.jobmatch.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchingService {
    @Autowired
    private CandidateRepository candidateRepo;
    @Autowired
    private JobRepository jobRepo;

    public Candidate addCandidate(Candidate c) { return candidateRepo.save(c); }
    public Job addJob(Job j) { return jobRepo.save(j); }
    public List<Candidate> getAllCandidates() { return candidateRepo.findAll(); }
    public List<Job> getAllJobs() { return jobRepo.findAll(); }
    public List<Job> getMatchingJobs(Long candidateId) {
        Candidate candidate = candidateRepo.findById(candidateId).orElse(null);
        if (candidate == null) return List.of();
        List<String> skills = candidate.getSkills();
        return jobRepo.findAll().stream()
                .filter(job -> skills.containsAll(job.getRequiredSkills()))
                .collect(Collectors.toList());
    }
}
