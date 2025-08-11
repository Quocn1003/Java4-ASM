
package com.slide5.Service;

import com.slide5.Entity.Video;

import java.util.List;

import com.slide5.DAO.FavouriteDAO;

public class FavouriteService implements FavouriteDAO {
    // Implement the methods defined in FavouriteDAO interface
    @Override
    public void addFavourite(String userId, String videoId) {
        javax.persistence.EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("java4");
        javax.persistence.EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            com.slide5.Entity.User user = em.find(com.slide5.Entity.User.class, userId);
            com.slide5.Entity.Video video = em.find(com.slide5.Entity.Video.class, videoId);
            com.slide5.Entity.Favorite favorite = new com.slide5.Entity.Favorite();
            favorite.setUser(user);
            favorite.setVideo(video);
            favorite.setLikeDate(new java.util.Date());
            em.persist(favorite);
            em.getTransaction().commit();
        } finally {
            em.close();
            emf.close();
        }
    }

    @Override
    public void removeFavourite(String userId, String videoId) {
        javax.persistence.EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("java4");
        javax.persistence.EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            javax.persistence.Query query = em
                    .createQuery("DELETE FROM Favorite f WHERE f.user.id = :userId AND f.video.id = :videoId");
            query.setParameter("userId", userId);
            query.setParameter("videoId", videoId);
            query.executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
            emf.close();
        }
    }

    @Override
    public List<Video> getFavouriteVideosByUserId(String userId) {
        javax.persistence.EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("java4");
        javax.persistence.EntityManager em = emf.createEntityManager();
        try {
            javax.persistence.Query query = em.createQuery("SELECT f.video FROM Favorite f WHERE f.user.id = :userId",
                    Video.class);
            query.setParameter("userId", userId);
            List<Video> videos = query.getResultList();
            return videos;
        } finally {
            em.close();
            emf.close();
        }
    }

    @Override
    public boolean isFavourite(String userId, String videoId) {
        javax.persistence.EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("java4");
        javax.persistence.EntityManager em = emf.createEntityManager();
        try {
            javax.persistence.Query query = em
                    .createQuery("SELECT COUNT(f) FROM Favorite f WHERE f.user.id = :userId AND f.video.id = :videoId");
            query.setParameter("userId", userId);
            query.setParameter("videoId", videoId);
            Long count = (Long) query.getSingleResult();
            return count > 0;
        } finally {
            em.close();
            emf.close();
        }
    }

}
