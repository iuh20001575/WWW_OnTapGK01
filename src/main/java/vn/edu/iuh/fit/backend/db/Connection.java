package vn.edu.iuh.fit.backend.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class Connection {
    private static Connection instance;
    private EntityManager entityManager;

    private Connection() {
        entityManager = Persistence
                .createEntityManagerFactory("OnTapGK01")
                .createEntityManager();
    }

    public static Connection getInstance() {
        if (instance == null)
            instance = new Connection();

        return instance;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
