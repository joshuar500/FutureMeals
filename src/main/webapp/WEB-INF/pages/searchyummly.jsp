<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title></title>
</head>
<body>
<sf:form method="post" action="${pageContext.request.contextPath}/listrecipes" commandName="offer">
    <table>
        <tr>
            <td>Name: </td><td><sf:input name="name" type="text" path="name"></sf:input><br /><sf:errors path="name" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Email: </td><td><sf:input name="email" type="text" path="email"></sf:input><br /><sf:errors path="email" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Your offer: </td><td><sf:textarea name="text" type="text" path="text" rows="10" cols="20"></sf:textarea><br /><sf:errors path="text" cssClass="error" /></td>
        </tr>
        <tr>
            <td><input value="Create advert" type="submit" /></td>
        </tr>
    </table>
</sf:form>
</body>
</html>