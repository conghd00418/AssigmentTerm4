<%-- 
    Document   : viewemp
    Created on : Dec 13, 2018, 4:53:11 PM
    Author     : Delll
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:if test="${sessionScope.username==null}">
    <jsp:forward page="login.jsp"></jsp:forward>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello, ${sessionScope.username}</h1>
    </body>
</html>
