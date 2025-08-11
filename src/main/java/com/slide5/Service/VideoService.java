package com.slide5.Service;

import java.util.List;

import javax.persistence.EntityManager;

import com.slide5.DAO.VideoDAO;
import com.slide5.Entity.Video;
import com.slide5.Utils.XJPA;

public class VideoService implements VideoDAO {
    private EntityManager entityManager = XJPA.getEntityManager();

    @Override
    public Video findById(String id) {
        // Implementation logic to find a video by ID
        return entityManager.find(Video.class, id);
    }

    @Override
    public List<Video> findByUserId(String userId) {
        // Implementation logic to find videos by user ID
        String query = "SELECT v FROM Video v WHERE v.userId = :userId";
        return entityManager.createQuery(query, Video.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public void save(Video video) {
        // Implementation logic to save a video
        entityManager.getTransaction().begin();
        entityManager.persist(video);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Video video) {
        // Implementation logic to update a video
        entityManager.getTransaction().begin();
        entityManager.merge(video);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(String id) {
        // Implementation logic to delete a video by ID
        entityManager.getTransaction().begin();
        Video video = entityManager.find(Video.class, id);
        if (video != null) {
            entityManager.remove(video);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Video> findAll() {
        // Implementation logic to find all videos
        String query = "SELECT v FROM Video v";
        return entityManager.createQuery(query, Video.class).getResultList();
    }

    @Override
    public List<Video> findTop6() {
        String jpql = "SELECT v FROM Video v ORDER BY v.views DESC";
        return entityManager.createQuery(jpql, Video.class)
                .setMaxResults(6)
                .getResultList();
    }

    @Override
    public List<Video> findPage(int page, int size) {
        String jpql = "SELECT v FROM Video v ORDER BY v.views DESC";
        return entityManager.createQuery(jpql, Video.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public long count() {
        String jpql = "SELECT COUNT(v) FROM Video v";
        return entityManager.createQuery(jpql, Long.class)
                .getSingleResult();
    }

}
