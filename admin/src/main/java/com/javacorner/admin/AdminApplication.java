package com.javacorner.admin;

//import com.javacorner.admin.dao.*;
//import com.javacorner.admin.utility.OperationUtility;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class AdminApplication {

//	@Autowired
//	private UserDao userDao;
//
//	@Autowired
//	private CourseDao courseDao;
//
//	@Autowired
//	private InstructorDao instructorDao;
//
//	@Autowired
//	private StudentDao studentDao;
//
//	@Autowired
//	private RoleDao roleDao;

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Override
//	public void run(String... args) throws Exception {
////		OperationUtility.usersOperations(userDao);
////		OperationUtility.rolesOperations(roleDao);
////		OperationUtility.assignRolesToUsers(userDao, roleDao);
////		OperationUtility.instructorsOperations(userDao, instructorDao, roleDao);
////		OperationUtility.studentsOperations(userDao, studentDao, roleDao);
//		OperationUtility.coursesOperations(courseDao, instructorDao, studentDao);
//	}

}
