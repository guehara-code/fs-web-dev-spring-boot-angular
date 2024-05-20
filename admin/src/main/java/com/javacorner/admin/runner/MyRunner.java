package com.javacorner.admin.runner;

import com.javacorner.admin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        
        createRoles();
    }

    private void createRoles() {

        Arrays.asList("Admin", "Instructor", "Student").forEach(role -> roleService.createRole(role));
    }
}
