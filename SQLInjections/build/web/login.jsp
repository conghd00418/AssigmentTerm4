<%-- 
    Document   : login
    Created on : Dec 13, 2018, 4:52:56 PM
    Author     : Delll
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="POST" action="LoginServlet">
            Username: <input type="text" name="username"></br>
            Password: <input type="password" name="password"></br>
            <input type="submit" value="Login">
    </body>
</html>
