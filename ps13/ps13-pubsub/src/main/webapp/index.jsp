<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JMS Topic Publisher</title>
</head>
<body>
<h1>JMS Topic Publisher</h1>
<form action="publish" method="get">
    <label for="message">Message:</label>
    <input type="text" id="message" name="message" value="Wysyłana wiadomość">
    <button type="submit">Publish Message</button>
</form>
<%
    String sentMessage = request.getParameter("message");
    if (sentMessage != null) {
%>
<p><strong>Message published:</strong> <%= sentMessage %></p>
<%
    }
%>
</body>
</html>
