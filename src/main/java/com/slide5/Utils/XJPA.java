package com.slide5.Utils;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class XJPA {
    private final static EntityManager entityManager =
     Persistence.createEntityManagerFactory("java4").createEntityManager();
    public static EntityManager getEntityManager() {
        return entityManager;
    }
    public static void closeEntityManager() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
