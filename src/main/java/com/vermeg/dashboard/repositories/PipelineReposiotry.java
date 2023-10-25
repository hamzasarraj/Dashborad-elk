package com.vermeg.dashboard.repositories;

import com.vermeg.dashboard.entities.Pipeline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PipelineReposiotry extends JpaRepository<Pipeline, Long> {
}
