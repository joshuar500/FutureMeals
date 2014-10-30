<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:forEach var="recipe" items="${searchRecipes}">
    <c:out value="${recipe.name}" /><br />
</c:forEach>

</body>
</html>