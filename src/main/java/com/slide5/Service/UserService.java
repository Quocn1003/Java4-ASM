package com.slide5.Service;

import java.util.List;

import javax.persistence.EntityManager;

import com.slide5.DAO.UserDAO;
import com.slide5.Entity.User;
import com.slide5.Utils.XJPA;

public class UserService implements UserDAO {
    private EntityManager entityManager = XJPA.getEntityManager();
    // Implement the methods defined in UserDAO interface
    @Override
    public User findById(String id) {
        // Implementation logic to find a user by ID
        User user = entityManager.find(User.class, id);
        return user; // Return the found user or null if not found
        
    }

    @Override
    public User findByEmail(String email) {
        // Implementation logic to find a user by email
        String query = "SELECT u FROM User u WHERE u.email = :email";
        return entityManager.createQuery(query, User.class)
                            .setParameter("email", email)
                            .getSingleResult();
    }

    @Override
    public void save(User user) {
        // Implementation logic to save a user
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(User user) {
        // Implementation logic to update a user
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(String id) {
        // Implementation logic to delete a user by ID
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public List<User> findAll() {
        // Implementation logic to find all users
        String query = "SELECT u FROM User u";
        return entityManager.createQuery(query, User.class).getResultList();
    }

}
