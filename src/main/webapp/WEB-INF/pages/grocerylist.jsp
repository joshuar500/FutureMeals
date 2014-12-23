
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>

<c:forEach var="result" items="${results}">
    <c:out value="${result.name}" /><br />
</c:forEach>

</body>
</html>
