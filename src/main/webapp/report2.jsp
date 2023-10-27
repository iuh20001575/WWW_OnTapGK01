<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.backend.models.Candidate" %><%

    Object candidatesO = request.getAttribute("candidates");

    if (candidatesO == null) {
        request.getRequestDispatcher("control-servlet?action=report2").forward(request, response);

        return;
    }

    request.removeAttribute("candidates");

    List<Candidate> candidates = (List<Candidate>) candidatesO;

%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Candidates</title>
</head>
<body>
<main>
    <h1>Candidates</h1>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>FullName</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Detail</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Candidate candidate : candidates) {
        %>
        <tr>
            <td><%= candidate.getId() %></td>
            <td><%= candidate.getFullName() %></td>
            <td><%= candidate.getEmail() %></td>
            <td><%= candidate.getPhone() %></td>
            <td>
                <a href="control-servlet?action=candidate-details&id=<%= candidate.getId() %>">View</a>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</main>
</body>
</html>
