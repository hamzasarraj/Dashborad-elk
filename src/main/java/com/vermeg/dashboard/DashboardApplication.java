package com.vermeg.dashboard;

import com.vermeg.dashboard.entities.Users;
import com.vermeg.dashboard.enums.Role;
import com.vermeg.dashboard.repositories.UserRepository;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Pipeline;
import org.gitlab4j.api.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
@EnableScheduling
public class DashboardApplication implements CommandLineRunner {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(DashboardApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Users user = new Users();
        user.setEmail("hhosni_tr@vermeg.com");
        user.setName("Hiba Hosni");
        user.setRole(Role.ADMIN);
        user.setPassword(passwordEncoder.encode("1234"));

        Users user1 = userRepository.findOneByEmail(user.getEmail()).orElse(null);
        if (Objects.isNull(user1)) {
            userRepository.save(user);
        }

        
        }
    }

