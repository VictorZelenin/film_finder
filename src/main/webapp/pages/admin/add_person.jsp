<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@ include file="/WEB-INF/pages/fragments/header.jspf" %>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-offset-4 col-md-6">
            <h2>Adding New Acting Person</h2>
        </div>
    </div>
    <br/>
    <form action="/admin?command=accept_adding_person" enctype="multipart/form-data" method="post">
        <div class="row">
            <div class="form-group">
                <label class="control-label col-md-offset-1 col-md-1" for="first_name">Имя: </label>
                <div class="col-md-3">
                    <input type="text" class="form-control" id="first_name" name="first_name">
                </div>
                <label class="control-label col-md-offset-1 col-md-1" for="last_name">Фамилия: </label>
                <div class="col-md-3">
                    <input type="text" class="form-control" id="last_name" name="last_name">
                </div>
            </div>
        </div>
        <br/>
        <div class="row">
            <div class="form-group">
                <label class="control-label col-md-offset-3 col-md-1">Пол:</label>
                <div class="col-md-4">
                    <select class="form-control" name="gender">
                        <option value="male" selected>Мужской</option>
                        <option value="female">Женский</option>
                    </select>
                </div>
            </div>
        </div>
        <br/>
        <div class="row">
            <label class="control-label col-md-offset-3 col-md-1">Рост</label>
            <div class="col-md-4">
                <input value="50" class="form-control" type="number" min="50" max="250" name="height">
            </div>
        </div>
        <br/>
        <div class="row">
            <label class="control-label col-md-offset-3 col-md-1">Страна</label>
            <div class="col-md-4">
                <input class="form-control" type="text" name="country">
            </div>
        </div>
        <br/>
        <div class="row">
            <div class="form-group">
                <label class="control-label col-md-offset-1 col-md-1" for="age">Возраст: </label>
                <div class="col-md-3">
                    <input value="0" type="number" min="0" max="120" class="form-control" id="age" name="age">
                </div>
                <label class="control-label col-md-offset-1 col-md-1" for="death_date">Дата смерти: </label>
                <div class="col-md-3">
                    <input type="date" class="form-control" id="death_date" name="death_date">
                </div>
            </div>
        </div>
        <br/>
        <div class="row">
            <label class="control-label col-md-offset-3 col-md-1">Кількість фільмів:</label>
            <div class="col-md-4">
                <input value="0" class="form-control" type="number" min="0" name="movies_number">
            </div>
        </div>
        <br/>
        <div class="row">
            <label class="control-label col-md-offset-3 col-md-1">Жанры:</label>
            <div class="col-md-4">
                <select class="form-control" multiple="multiple" name="genres">
                    <c:forEach var="genre" items="${genres}">
                        <option value="${genre}">${genre}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <br/>
        <div class="row">
            <label class="control-label col-md-offset-3 col-md-1">Амплуа:</label>
            <div class="col-md-4">
                <select class="form-control" multiple="multiple" name="roles" title="roles">
                    <c:forEach var="role" items="${roles}">
                        <option value="${role}">${role}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <br/>
        <div class="row">
            <div class="form-group">
                <label class="col-md-offset-3 col-md-2">Загрузите фото:</label>
                <label class="col-md-1 btn btn-primary btn-file">
                    Upload photo
                    <input name="client_image" type="file" style="display: none;">
                </label>
            </div>
        </div>
        <br/>
        <br/>
        <div class="row">
            <button type="submit" class="btn btn-primary col-md-offset-9">Добавить</button>
        </div>
    </form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="../src/main/webapp/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>
