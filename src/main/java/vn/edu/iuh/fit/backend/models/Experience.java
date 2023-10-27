package vn.edu.iuh.fit.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.iuh.fit.backend.enums.Roles;

import java.time.LocalDate;

@Entity
@Table(name = "experience")
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Experience.getByCandidateId", query = "FROM Experience where candidate.id = :candidateId")
})
public class Experience {
    @Id
    @Column(name = "exp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "from_date", nullable = false)
    private LocalDate fromDate;
    @Column(name = "to_date", nullable = false)
    private LocalDate toDate;
    @Column(name = "work_desc", length = 400, nullable = false)
    private String workDescription;
    @Column(nullable = false)
    private Roles role;
    @Column(name = "company", length = 120, nullable = false)
    private String companyName;
    @ManyToOne
    @JoinColumn(name = "can_id")
    private Candidate candidate;

    public Experience(LocalDate fromDate, LocalDate toDate, String workDescription, Roles role, String companyName, Candidate candidate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.workDescription = workDescription;
        this.role = role;
        this.companyName = companyName;
        this.candidate = candidate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "id=" + id +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", workDescription='" + workDescription + '\'' +
                ", role=" + role +
                ", companyName='" + companyName + '\'' +
                ", candidate=" + candidate +
                '}';
    }
}
