package vn.edu.iuh.fit.frontend.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.backend.enums.Roles;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.models.Experience;
import vn.edu.iuh.fit.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.backend.repositories.ExperienceRepository;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/control-servlet"})
public class ControlServlet extends HttpServlet {
    private final CandidateRepository candidateRepository;
    private final ExperienceRepository experienceRepository;

    public ControlServlet() {
        candidateRepository = new CandidateRepository();
        experienceRepository = new ExperienceRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "candidates":
                handleGetCandidates(req, resp);
                break;
            case "candidate-details":
                handleGetCandidateDetails(req, resp);
                break;
            case "report1":
                handleGetReport1(req, resp);
                break;
            case "report2":
                handleGetReport2(req, resp);
                break;
            default:
                req.getRequestDispatcher("NotFound.jsp").forward(req, resp);
        }
    }

    private void handleGetCandidates(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Candidate> candidates = candidateRepository.getCandidates();
        System.out.println(candidates);

        req.setAttribute("candidates", candidates);

        req.getRequestDispatcher("candidates.jsp").forward(req, resp);
    }

    private void handleGetCandidateDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter("id");

        if (parameter == null) {
            req.getRequestDispatcher("NotFound.jsp").forward(req, resp);
            return;
        }

        long id = Long.parseLong(parameter);

        List<Experience> experiences = experienceRepository.getByCandidateId(id);

        req.setAttribute("experiences", experiences);

        req.getRequestDispatcher("candidate_details.jsp").forward(req, resp);
    }

    private void handleGetReport1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter("role");

        if (parameter == null){
            req.getRequestDispatcher("NotFound.jsp").forward(req, resp);
            return;
        }

        List<Candidate> candidates = candidateRepository.getByRole(Roles.valueOf(parameter));

        req.setAttribute("candidates", candidates);

        req.getRequestDispatcher("report1.jsp").forward(req, resp);
    }

    private void handleGetReport2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Candidate> candidates = candidateRepository.getCandidatesUsingEmail();

        req.setAttribute("candidates", candidates);

        req.getRequestDispatcher("report2.jsp").forward(req, resp);
    }
}
