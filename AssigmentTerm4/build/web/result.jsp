<%-- 
    Document   : result
    Created on : Dec 24, 2018, 12:07:16 AM
    Author     : Delll
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Library</title>
    </head>

    <h1>Book List</h1>
    <body>
        <jsp:useBean class="com.wpsj.model.ProductFinderBean" id="finder" scope="request"/>
        <table>
            <tr>
                <td>OrderNo</td>
                <td>Name</td>
                <td>CreatedDTG</td>
                <td>Status</td>
            </tr>
            
            <c:forEach items="${finder.getProductHistories()}" var ="productHistory">
                <tr>
                    <td>
                        <c:out value="${productHistory.getOrderNo().toString()}"/>
                    </td>
                    <td>
                        <c:out value="${productHistory.getName()}"/>
                    </td>
                    <td>
                        <c:out value="${productHistory.getCreatedDTG().toString()}"/>
                    </td>
                    <td>
                        <c:out value="${productHistory.getStatus()}"/>
                    </td>
                </tr>
                </c:forEach>
        </table>
    </body>

</html>
