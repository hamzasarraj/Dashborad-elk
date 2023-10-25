package com.vermeg.dashboard.repositories;

import com.vermeg.dashboard.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
