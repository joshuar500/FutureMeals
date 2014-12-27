
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>

<c:forEach var="groceryList" items="${groceryList}">
    <c:forEach var="item" items="${groceryList}">
        <c:out value="${item}" /> <br />
    </c:forEach>
</c:forEach>


</body>
</html>
