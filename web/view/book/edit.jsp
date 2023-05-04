<%-- 
    Document   : edit
    Created on : Apr 25, 2023, 3:16:44 PM
    Author     : sonnt
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="edit" method="POST">
            <table border="1px"> 
                <tr>
                    <td>Book Id</td>
                    <td>Title</td>
                    <td>Date</td>
                    <td>Authors</td>
                </tr>
                <c:forEach items="${requestScope.books}" var="b"> 
                <tr>
                    <td>${b.id}<input type="hidden" name="bid" value="${b.id}"  /> </td>
                    <td><input type="text" value="${b.title}" name="title${b.id}"/></td>
                    <td><input type="date" value="${b.publisheddate}" name="date${b.id}"/></td>
                    <td>
                        <c:forEach items="${requestScope.authors}" var="a">
                            <input value="true" name="aid_${a.id}_${b.id}" type="checkbox"
                                   <c:forEach items="${b.authors}" var="aob">
                                       <c:if test="${aob.id eq a.id}">
                                           checked="checked"
                                       </c:if>
                                   </c:forEach>    
                                   /> ${a.name} <br/>
                        </c:forEach>
                    </td>
                </tr>
                </c:forEach>
            </table>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
