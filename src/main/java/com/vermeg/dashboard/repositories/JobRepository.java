package com.vermeg.dashboard.repositories;

import com.vermeg.dashboard.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository  extends JpaRepository<Job, Long> {
}
