<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../WEB-INF/pages/errors/not_found_error.jsp" %>
<%@ include file="/WEB-INF/pages/fragments/header.jspf" %>

<body>
<jsp:include page="../WEB-INF/pages/navbar/navbar.jsp"/>

<div class="container-fluid">
    <form method="post" action="/controller?command=accept_search_form">
        <div class="row">
            <div class="col-md-offset-2 col-md-3">
                <div class="form-group">
                    <label for="title">Название фильма</label>
                    <input class="form-control" type="text" id="title" name="title"/>
                </div>
                <div class="form-group">
                    <label for="imdb_rating">Оценка IMDB (>)</label>
                    <input class="form-control" type="text" id="imdb_rating" name="imdb_rating"/>
                </div>
                <div class="form-group">
                    <label for="actor">Актер</label>
                    <input class="form-control" type="text" id="actor" name="actor"/>
                </div>
                <div class="form-group">
                    <label for="runtime">Длительность фильма (>)</label>
                    <input class="form-control" type="text" id="runtime" name="runtime"/>
                </div>
                <div class="form-group">
                    <label for="movie_type">Тип</label>
                    <select class="form-control" id="movie_type" name="movie_type">
                        <!--for each-->
                        <option value="0" selected>Фильм</option>
                        <option value="1">Сериал</option>
                        <option value="2">Эпизод</option>
                    </select>
                </div>
            </div>
            <div class="col-md-offset-2 col-md-3">
                <div class="form-group">
                    <label for="country">Страна</label>
                    <input class="form-control" type="text" id="country" name="country"/>
                </div>
                <div class="form-group">
                    <label for="genre">Жанр (>= 1)</label>
                    <select class="form-control" id="genre" name="genre" multiple="multiple">
                        <!--for each-->
                        <c:forEach var="genre" items="${genres}">
                            <option value="${genre}">${genre}</option>
                        </c:forEach>
                    </select>
                    <p>Зажмите ctrl для множественного выбора</p>
                </div>
                <div class="form-group">
                    <label for="director">Режиссер</label>
                    <input class="form-control" type="text" id="director" name="director"/>
                </div>
                <div class="form-group">
                    <label for="clients_mark">Оценка клиентов (>)</label>
                    <input class="form-control" type="text" id="clients_mark" name="clients_mark"/>
                </div>
            </div>
        </div>
        <button type="submit" class="btn btn-primary search_submit">Поиск</button>
    </form>
</div>
<%@ include file="/WEB-INF/pages/fragments/footer.jspf" %>
</body>
</html>
