package com.slide5.DAO;

import java.util.List;

import com.slide5.Entity.User;

public interface UserDAO {
    // Define methods for user data access operations
    User findById(String id);

    User findByEmail(String email);

    void save(User user);

    void update(User user);

    void delete(String id);

    List<User> findAll();

}
