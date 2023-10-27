<%@ page import="javax.management.relation.Role" %>
<%@ page import="vn.edu.iuh.fit.backend.enums.Roles" %>
<%@ page import="vn.edu.iuh.fit.backend.models.Candidate" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: HaAnhThao
  Date: 27/10/2023
  Time: 04:47:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Show candidates by role</h1>

    <form action="control-servlet?action=report1">
        <input type="hidden" name="action" value="report1">
        <select name="role">
            <%
                for (Roles roles : Roles.values()) {
            %>
                <option value="<%=roles.name()%>"><%= roles.name() %></option>
            <% } %>
        </select>
        <button type="submit">View</button>
    </form>


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
            Object candidatesO = request.getAttribute("candidates");

            if (candidatesO != null) {
                List<Candidate> candidates = (List<Candidate>) candidatesO;

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
            } }
        %>
        </tbody>
    </table>
</body>
</html>
