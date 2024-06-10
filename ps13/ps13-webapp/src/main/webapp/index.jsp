<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JMS Message Producer</title>
</head>
<body>
<h1>JMS Message Producer</h1>
<form action="send" method="get">
    <label for="message">Message:</label>
    <input type="text" id="message" name="message" value="Wysyłana wiadomość">
    <button type="submit">Send Message</button>
</form>
<%
    String sentMessage = request.getParameter("message");
    if (sentMessage != null) {
%>
<p><strong>Message sent:</strong> <%= sentMessage %></p>
<%
    }
%>
</body>
</html>
