package com.jobmatch.repository;

import com.jobmatch.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {}
