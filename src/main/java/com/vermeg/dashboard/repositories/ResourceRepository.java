package com.vermeg.dashboard.repositories;

import com.vermeg.dashboard.entities.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
}
