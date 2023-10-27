package vn.edu.iuh.fit.backend.configs;

import com.thedeanda.lorem.LoremIpsum;
import jakarta.inject.Inject;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import vn.edu.iuh.fit.backend.db.Connection;
import vn.edu.iuh.fit.backend.enums.Roles;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.models.Experience;
import vn.edu.iuh.fit.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.backend.repositories.ExperienceRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ApplicationPath("/api")
public class ApplicationConfig extends Application {
    private final CandidateRepository candidateRepository = new CandidateRepository();
    private final ExperienceRepository experienceRepository = new ExperienceRepository();

    public void init() {
        Connection.getInstance().getEntityManager();

        Candidate candidate;
        Experience experience;
        Random random = new Random();

        for (int i = 1; i < 4; i++) {
            candidate = new Candidate(
                    LoremIpsum.getInstance().getName(),
                    LoremIpsum.getInstance().getEmail(),
                    LoremIpsum.getInstance().getPhone()
            );

            candidateRepository.insert(candidate);

            for (int j = 1; j < 4; j++) {
                experience = new Experience(
                        LocalDate.now(),
                        LocalDate.now(),
                        LoremIpsum.getInstance().getWords(5),
                        Roles.values()[random.nextInt(4)],
                        LoremIpsum.getInstance().getName(),
                        candidate
                );
                experienceRepository.insert(experience);
            }
        }
    }

    public ApplicationConfig() {
        init();
    }
}