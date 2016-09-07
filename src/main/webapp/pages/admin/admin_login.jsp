<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@ include file="/WEB-INF/pages/fragments/header.jspf" %>
<body>
<div class="container">
    <form class="form-signin" method="post" action="<c:url value="/admin?command=accept_login_form&user_type=admin"/>">
        <h2 class="form-signin-heading">Please log in</h2>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email address"
               autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" >
        <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
    </form>
</div>
</body>
</html>

