<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--TODO edit this--%>
<html>
<%@ include file="/WEB-INF/pages/fragments/header.jspf" %>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-offset-5">
            <h1>Admin Panel</h1>
        </div>
        <hr/>
        <div class="row control-row">
            <div class="col-md-offset-2 col-md-2">
                <button class="btn-primary">
                    <a class="control" href="/admin?command=add_movie">Добавить фильм</a>
                </button>
            </div>
            <div class="col-md-offset-4 col-md-2">
                <button class="btn-primary">
                    <a class="control" href="/admin?command=edit_movie">Редактировать фильм</a>
                </button>
            </div>
        </div>

        <div class="row control-row">
            <div class="col-md-offset-2 col-md-3">
                <button class="btn-primary">
                    <a class="control" href="/admin?command=add_person">Добавить актера/режиссера/..</a>
                </button>
            </div>
            <div class="col-md-offset-3 col-md-3">
                <button class="btn-primary">
                    <a class="control" href="/admin?command=edit_person">Редактировать актера/режиссера/..</a>
                </button>
            </div>
        </div>

        <div class="row control-row">
            <div class="col-md-offset-2 col-md-2">
                <button class="btn-primary">
                    <a class="control" href="/admin?command=show_clients">Посмотреть клиентов</a>
                </button>
            </div>
            <div class="col-md-offset-4 col-md-2">
                <button class="btn-primary">
                    <a class="control" href="/admin?command=show_feedback">Читать отзывы..</a>
                </button>
            </div>
        </div>
    </div>
    <br>
    <br>
    <br>
    <br>
    <div class="row control-row">
        <div class="col-md-offset-10">
            <form method="post" action="/admin?command=log_out">
                <button class="btn-primary" type="submit">Exit</button>
            </form>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="<c:url value="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/bootstrap-3.3.7-dist/js/bootstrap.js"/>"></script>
</body>
</html>
