package com.javacorner.admin.utility;

import com.javacorner.admin.dao.InstructorDao;
import com.javacorner.admin.dao.RoleDao;
import com.javacorner.admin.dao.UserDao;
import com.javacorner.admin.entity.Instructor;
import com.javacorner.admin.entity.Role;
import com.javacorner.admin.entity.User;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class OperationUtility {

    public static void usersOperations(UserDao userDao) {
        createUsers(userDao);
        updateUser(userDao);
        deleteUser(userDao);
        fetchUsers(userDao);
    }

    public static void rolesOperations(RoleDao roleDao) {
        createRoles(roleDao);
        updateRole(roleDao);
        deleteRole(roleDao);
        fetchRole(roleDao);

    }

    private static void createUsers(UserDao userDao) {
        User user1 = new User("user@gmail.com", "pass1");
        userDao.save(user1);
        User user2 = new User("user2@gmail.com", "pass2");
        userDao.save(user2);
        User user3 = new User("user3@gmail.com", "pass3");
        userDao.save(user3);
        User user4 = new User("user4@gmail.com", "pass4;");
        userDao.save(user4);
    }

    private static void updateUser(UserDao userDao) {
        User user = userDao.findById(2L).orElseThrow(() -> new EntityNotFoundException("User Not Found"));
        user.setEmail("newEmail@gmail.com");
        userDao.save(user);
    }

    private static void deleteUser(UserDao userDao) {
        User user = userDao.findById(3L).orElseThrow(() -> new EntityNotFoundException("User Not Found"));
        userDao.delete(user);
//        userDao.deleteById(3L);
    }

    private static void fetchUsers(UserDao userDao) {
        userDao.findAll().forEach(user -> System.out.println(user.toString()));
    }

    private static void createRoles(RoleDao roleDao) {
        Role role1 = new Role("Admin");
        roleDao.save(role1);
        Role role2 = new Role("Instructor");
        roleDao.save(role2);
        Role role3 = new Role("Student");
        roleDao.save(role3);
    }

    private static void updateRole(RoleDao roleDao) {
        Role role = roleDao.findById(1L).orElseThrow(() -> new EntityNotFoundException("Role Not Found"));
        role.setName("NewAdmin");
        roleDao.save(role);
    }

    private static void deleteRole(RoleDao roleDao) {
        roleDao.deleteById(2L);
    }

    private static void fetchRole(RoleDao roleDao) {
        roleDao.findAll().forEach(role -> System.out.println(role.toString()));
    }

    public static void assignRolesToUsers(UserDao userDao, RoleDao roleDao) {
        Role role = roleDao.findByName("Admin");
        if(role == null) throw new EntityNotFoundException("Role Not Found");
        List<User> users = userDao.findAll();
        users.forEach(user -> {
            user.assignRoleToUser(role);
            userDao.save(user);
        });
    }

    public static void instructorsOperations(UserDao userDao, InstructorDao instructorDao, RoleDao roleDao) {
        createInstructors(userDao, instructorDao, roleDao);
        updateInstructor(instructorDao);
        removeInstructor(instructorDao);
        fetchInstructors(instructorDao);
    }

    private static void createInstructors(UserDao userDao, InstructorDao instructorDao, RoleDao roleDao) {
        Role role = roleDao.findByName("Instructor");
        if(role == null) throw new EntityNotFoundException("Role Not Found");

        User user1 = new User("instructorUser1@gnail.com", "pass1");
        userDao.save(user1);
        user1.assignRoleToUser(role);
        Instructor instructor1 = new Instructor("instructor1FN", "instructor1LN", "Experienced Instructor", user1);
        instructorDao.save(instructor1);

        User user2 = new User("instructorUser2@gnail.com", "pass2");
        userDao.save(user2);
        user2.assignRoleToUser(role);
        Instructor instructor2 = new Instructor("instructor2FN", "instructor2LN", "Senior Instructor", user2);
        instructorDao.save(instructor2);

    }

    private static void updateInstructor(InstructorDao instructorDao) {
        Instructor instructor = instructorDao.findById(1L).orElseThrow(() -> new EntityNotFoundException("Instructor Not Found"));
        instructor.setSummary("Certified Instructor");
        instructorDao.save(instructor);

    }

    private static void removeInstructor(InstructorDao instructorDao) {
        instructorDao.deleteById(2L);
    }

    private static void fetchInstructors(InstructorDao instructorDao) {
        instructorDao.findAll().forEach(instructor -> System.out.println(instructor.toString()));
    }
}
