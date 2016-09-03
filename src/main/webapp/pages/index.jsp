<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@ page errorPage="../WEB-INF/pages/errors/not_found_error.jsp" %>--%>
<%@ include file="/WEB-INF/pages/fragments/header.jspf" %>

<body>
<jsp:include page="../WEB-INF/pages/navbar/navbar.jsp"/>

<div class="container-fluid content_container">
    <div class="row">
        <div class="col-md-3 hidden-sm hidden-xs">
            <ul class="media-list">
                За жанрами:
                <c:forEach var="genre" items="${genres}">
                    <li><a href="/controller?command=accept_search_form&genre=${genre}">${genre}</a></li>
                </c:forEach>
            </ul>
        </div>
        <div class="col-md-6 center-block">
            <jsp:include page="/WEB-INF/pages/movie_list.jsp"/>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="<c:url value="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/bootstrap-3.3.7-dist/js/bootstrap.js"/>"></script>
<%@include file="/WEB-INF/pages/fragments/footer.jspf" %>
</body>
</html>