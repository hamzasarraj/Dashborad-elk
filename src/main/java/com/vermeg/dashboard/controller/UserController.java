package com.vermeg.dashboard.controller;

import com.vermeg.dashboard.entities.Application;
import com.vermeg.dashboard.entities.Users;
import com.vermeg.dashboard.exceptions.ExistException;
import com.vermeg.dashboard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping
    public List<Users> findAll() {
        return userService.findAll();
    }
    @GetMapping("/{id}")
    public Users findById(@PathVariable Long id) {
        return userService.findById(id);
    }
    @PostMapping
    public Users save(@RequestBody Users user) throws ExistException {
        return userService.save(user);
    }

    @PutMapping
    public Users update(@RequestBody Users user) throws ExistException {
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }


    @PutMapping("/assign/{id}")
    public Users assign(@PathVariable Long id, @RequestBody Application application) {
        return  userService.assignApplicationToUser(id, application);
    }

    @PutMapping("/unassign/{id}")
    public Users unassign(@PathVariable Long id, @RequestBody Application application) {
        return  userService.unassignApplicationToUser(id, application);
    }
}
