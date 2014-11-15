<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title></title>
    <link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-1.11.1.min.js"></script>
    <script type="text/javascript">
        // this method of checking if passwords are the same
        // is dangerous because somebody can either have js
        // turned off, or they can manipulate the js so the
        // form still submits
        // after the key is pressed, check passwords match
        // can the form be submitted?
        function onLoad() {
            $("#password").keyup(checkPasswordsMatch);
            $("#confirmPassword").keyup(checkPasswordsMatch);
            $("#details").submit(canSubmit);
        }
        // the user can or cannot submit
        function canSubmit() {
            var password = $("#password").val();
            var confirmPassword = $("#confirmPassword").val();
            if (password != confirmPassword) {
                return false;
            } else {
                return true;
            }
        }
        function checkPasswordsMatch() {
            var password = $("#password").val();
            var confirmPassword = $("#confirmPassword").val();
            if(password.length > 3 || confirmPassword.length > 3) {
                if (password == confirmPassword) {
                    $("#matchpass").text("<fmt:message key='MatchedPasswords.user.password' />");
                    $("#matchpass").addClass("valid");
                    $("#matchpass").removeClass("error");
                } else {
                    $("#matchpass").text("<fmt:message key='UnmatchedPasswords.user.password' />");
                    $("#matchpass").addClass("error");
                    $("#matchpass").removeClass("valid");
                }
            }
        }
        $(document).ready(onLoad);
    </script>
</head>
<body>
<h2>Create new account</h2>
<sf:form id="details" method="post" action="${pageContext.request.contextPath}/createaccount" commandName="user">
    <table>
        <tr>
                <%--re-align the sf errors--%>
            <td>Username: </td><td><sf:input name="username" type="text" path="username"></sf:input><br /><sf:errors path="username" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Email: </td><td><sf:input name="email" type="text" path="email"></sf:input><br /><sf:errors path="email" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Password: </td><td><sf:input id="password" name="password" type="text" path="password"></sf:input><br /><sf:errors path="password" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Confirm Password: </td><td><input id="confirmPassword" name="confirmPassword" type="text" /><br /><div id="matchpass"></div></td>
        </tr>
        <tr>
            <td><input value="Create Account" type="submit" /></td>
        </tr>
    </table>
</sf:form>
</body>
</html>