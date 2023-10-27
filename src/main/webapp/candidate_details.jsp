<%@ page import="vn.edu.iuh.fit.backend.models.Experience" %>
<%@ page import="java.util.List" %><%

    Object experiencesO = request.getAttribute("experiences");

    if (experiencesO == null) {
        request.getRequestDispatcher("control-servlet?action=candidate-details").forward(request, response);

        return;
    }

    request.removeAttribute("experiencesO");

    List<Experience> experiences = (List<Experience>) experiencesO;

%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Candidate Details</title>
</head>
<body>
    <h1>Candidate Details</h1>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>From Date</th>
            <th>To Date</th>
            <th>Work Description</th>
            <th>Role</th>
            <th>Company</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Experience experience : experiences) {
        %>
        <tr>
            <td><%= experience.getId() %></td>
            <td><%= experience.getFromDate() %></td>
            <td><%= experience.getToDate() %></td>
            <td><%= experience.getWorkDescription() %></td>
            <td><%= experience.getRole() %></td>
            <td><%= experience.getCompanyName() %></td>
        </tr>
        <% } %>
        </tbody>
    </table>
</body>
</html>
