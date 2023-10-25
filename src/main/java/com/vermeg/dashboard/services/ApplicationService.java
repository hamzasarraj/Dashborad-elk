package com.vermeg.dashboard.services;

import com.vermeg.dashboard.entities.Application;
import com.vermeg.dashboard.entities.Users;
import com.vermeg.dashboard.exceptions.ExistException;
import com.vermeg.dashboard.repositories.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;


    public Application save(Application application)  {

        return applicationRepository.save(application);

    }

    public List<Application> findAll() {
        return  applicationRepository.findAll();
    }


}
