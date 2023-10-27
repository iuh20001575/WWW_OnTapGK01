package vn.edu.iuh.fit.backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "candidate")
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Candidate.getCandidates", query = "FROM Candidate"),
        @NamedQuery(name = "Candidate.getCandidatesUsingEmail", query = "FROM Candidate WHERE email is not null"),
        @NamedQuery(name = "Candidate.getByRole", query = "SELECT DISTINCT c FROM Candidate c JOIN c.experiences e WHERE  e.role = :role")
})
public class Candidate {
    @Id
    @Column(name = "can_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 255, name = "full_name", nullable = false)
    private String fullName;
    @Column(length = 255, nullable = false)
    private String email;
    @Column(length = 15, nullable = false)
    private  String phone;
    @OneToMany(mappedBy = "candidate", cascade = {CascadeType.PERSIST})
    @ToString.Exclude
    private List<Experience> experiences;

    public Candidate(String fullName, String email, String phone) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }

    public Candidate(String fullName, String email, String phone, List<Experience> experiences) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.experiences = experiences;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
