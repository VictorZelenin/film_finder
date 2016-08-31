<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--TODO edit this--%>
<html>
<%@ include file="/WEB-INF/pages/fragments/header.jspf" %>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-offset-3">
            <h1>Панель администратора, welcome back ${admin.name}</h1>
        </div>
        <div class="row">
            <div class="col-md-offset-1 col-md-3">
                <a href="#">Добавить фильм</a>
                <br/>
                <a href="#">Редактировать фильм</a>
                <br/>
                <a href="#">Редактировать клиентов</a>
            </div>
            <div class="col-md-offset-1 col-md-3">
                <a href="#">Добавить Acting Person</a>
                <br/>
                <a href="#">Редактировать Acting Person</a>
                <br/>
                <a href="#">Читать отзывы...</a>
            </div>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="<c:url value="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/bootstrap-3.3.7-dist/js/bootstrap.js"/>"></script>
</body>
</html>
