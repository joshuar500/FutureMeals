<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title></title>
</head>
<link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css" />
<body>
<sf:form method="post" action="${pageContext.request.contextPath}/results" commandName="search">
    <table>
        <tr>
            <td>Search: </td><td><sf:input name="term" type="term" path="term"></sf:input></td>
        </tr>
        <td><input value="Search" type="submit" /></td>
        </tr>
    </table>
</sf:form>
</body>
</html>