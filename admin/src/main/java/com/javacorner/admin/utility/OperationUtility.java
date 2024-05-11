package com.javacorner.admin.utility;

import com.javacorner.admin.dao.UserDao;
import com.javacorner.admin.entity.User;
import jakarta.persistence.EntityNotFoundException;

public class OperationUtility {

    public static void usersOperations(UserDao userDao) {
        createUsers(userDao);
        updateUser(userDao);
        deleteUser(userDao);
        fetchUsers(userDao);
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

}
