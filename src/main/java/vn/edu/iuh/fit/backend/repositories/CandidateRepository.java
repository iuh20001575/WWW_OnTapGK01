package vn.edu.iuh.fit.backend.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.edu.iuh.fit.backend.db.Connection;
import vn.edu.iuh.fit.backend.enums.Roles;
import vn.edu.iuh.fit.backend.models.Candidate;

import java.util.List;
import java.util.Optional;

public class CandidateRepository {
    private final EntityManager entityManager = Connection.getInstance().getEntityManager();
    private final EntityTransaction transaction = entityManager.getTransaction();

    public boolean insert(Candidate candidate) {
        try {
            transaction.begin();

            entityManager.persist(candidate);

            transaction.commit();

            return true;
        } catch (Exception e) {
            transaction.rollback();

            e.printStackTrace();
        }

        return false;
    }

    public List<Candidate> getCandidates() {
        return entityManager.createNamedQuery("Candidate.getCandidates", Candidate.class)
                .getResultList();
    }

    public Optional<Candidate> findById(long id) {
        try {
            Candidate candidate = entityManager.find(Candidate.class, id);

            return Optional.of(candidate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  Optional.empty();
    }

    public List<Candidate> getByRole(Roles roles) {
        return entityManager.createNamedQuery("Candidate.getByRole", Candidate.class)
                .setParameter("role", roles)
                .getResultList();
    }

    public List<Candidate> getCandidatesUsingEmail() {
        return entityManager.createNamedQuery("Candidate.getCandidatesUsingEmail", Candidate.class)
                .getResultList();

    }
}
