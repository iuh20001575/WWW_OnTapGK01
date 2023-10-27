package vn.edu.iuh.fit.backend.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.edu.iuh.fit.backend.db.Connection;
import vn.edu.iuh.fit.backend.models.Experience;

import java.util.List;

public class ExperienceRepository {
    private final EntityManager entityManager = Connection.getInstance().getEntityManager();
    private final EntityTransaction transaction = entityManager.getTransaction();

    public boolean insert(Experience experience) {
        try {
            transaction.begin();

            entityManager.persist(experience);

            transaction.commit();

            return true;
        } catch (Exception e) {
            transaction.rollback();

            e.printStackTrace();
        }

        return false;
    }

    public List<Experience> getByCandidateId(long candidateId) {
        return entityManager.createNamedQuery("Experience.getByCandidateId", Experience.class)
                .setParameter("candidateId", candidateId)
                .getResultList();
    }
}
