package com.vermeg.dashboard.services;

import com.vermeg.dashboard.entities.Application;
import com.vermeg.dashboard.entities.Users;
import com.vermeg.dashboard.exceptions.ExistException;
import com.vermeg.dashboard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;


    public Users save(Users user) throws ExistException {

        boolean exist = userRepository.existsByEmail(user.getEmail());
        if (exist) {
            throw new ExistException("Email existe déja");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public Users update(Users user) throws ExistException {

        boolean exist = userRepository.existsByEmailAndId(user.getEmail(), user.getId());
        if (!exist) {
            exist = userRepository.existsByEmail(user.getEmail());
            if (exist) {
                throw new ExistException("Email existe déja");
            }
        }
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findOneByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Unauthorized"));
        return user;
    }

    public Users findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Users assignApplicationToUser(Long id, Application application) {
        Users user = userRepository.findById(id).orElse(null);
        user.getApplications().add(application);
        return userRepository.save(user);
    }

    public Users unassignApplicationToUser(Long id, Application application) {
        Users user = userRepository.findById(id).orElse(null);
        List<Application> applications = user.getApplications().stream().filter(app -> !app.getId().equals(application.getId())).collect(Collectors.toList());
        user.setApplications(applications);
        return userRepository.save(user);
    }
}
