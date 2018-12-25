<%-- 
    Document   : search
    Created on : Dec 24, 2018, 12:07:27 AM
    Author     : Delll
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <title>Find Book</title>
    </head>
    <body>
        <h1>Find Book</h1>
        <form action="ProductFinder">
            <span style="color:red;">
                <c:out value="${param.msg}"/>
            </span>
            <input name="text"/><input type="submit"/>
        </form>
    </body>
</html>
