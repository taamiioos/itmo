<%@ page import="model.Point" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c2" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>LastResult</title>
    <link rel="stylesheet" type="text/css" href="css/lastResult.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
<div id="tableContainer">
    <table>
        <thead>
        <tr>
            <th>X</th>
            <th>Y</th>
            <th>R</th>
            <th>Flag</th>
            <th>Time</th>
            <th>Script Time(ms)</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Point> sessionList = (List<Point>) session.getAttribute("sessionList");
            if (sessionList != null && !sessionList.isEmpty()) {
                Point lastSession = sessionList.get(sessionList.size() - 1);
        %>
        <tr>
            <td><%= lastSession.getX() %></td>
            <td><%= lastSession.getY() %></td>
            <td><%= lastSession.getR() %></td>
            <td><%= lastSession.getFlag() %></td>
            <td><%= lastSession.getTime() %></td>
            <td><%= lastSession.getScriptTime() %></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <button onClick="window.location.replace('./index.jsp');" type="reset">←</button>
</div>
</body>
</html>
