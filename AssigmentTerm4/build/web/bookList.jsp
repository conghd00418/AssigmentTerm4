<%-- 
    Document   : bookList
    Created on : Dec 25, 2018, 4:31:03 PM
    Author     : Delll
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Library Manager</title>
    </head>
    <body>
        <h1>Books list</h1>
        <jsp:useBean class="com.wpsj.model.ProductFinderBean" id="finder" scope="request"/>
        <table>
            <tr>
                <td>Code</td>
                <td>Name</td>
                <td>Author</td>
                <td>Status</td>
                <td>History</td>
                <td>Action</td>
            </tr>
            
            <c:forEach items="${finder.products}" var ="product">
                <tr>
                    <td>
                        <c:out value="${product.getCode()}"/>
                    </td>
                    <td>
                        <c:out value="${product.getName()}"/>
                    </td>
                    <td>
                        <c:out value="${product.getAuthor()}"/>
                    </td>
                    <td>
                        <c:out value="${product.getStatus()}"/>
                    </td>
                    <td>
                        <form action="ProductHistoryFinder">
                            <input type="submit" value="Look History" />
                            <input type="hidden" name="id" value="${product.getId().toString()}" />
                        </form>
                    </td>
                    <td>
                        <form action="ProductSearch">
                            <c:choose>
                                <c:when test="${product.getStatus() == 'Chưa mượn'}">
                                    <input type="submit" value="Mượn sách" />
                                    <input type="hidden" name="status" value="Borrow" />
                                </c:when>
                                <c:otherwise>
                                    <input type="submit" value="Trả sách"/>
                                    <input type="hidden" name="status" value="Return" />
                                </c:otherwise>
                            </c:choose>
                                    <input type="hidden" name="id" value="${product.getId().toString()}" />
                                    
                        </form>
                    </td>
                </tr>
                </c:forEach>
        </table>
    </body>
</html>
