<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="../WEB-INF/pages/errors/not_found_error.jsp" %>
<%@ include file="/WEB-INF/pages/fragments/header.jspf" %>

<body>
<jsp:include page="../WEB-INF/pages/navbar/navbar.jsp"/>

<div class="container-fluid content_container">
    <div class="row">
        <div class="col-md-3 hidden-sm hidden-xs">
            <button>Расшренный поиск</button>
            <ul class="media-list">
                За жанрами:
                <c:forEach var="genre" items="${requestScope.genres}">
                    <li><a href="/">${genre}</a></li>
                </c:forEach>
            </ul>
        </div>
        <div class="col-md-6 center-block">
            <%-- TODO TAG for movie block--%>
            <jsp:include page="/WEB-INF/pages/movie_list.jsp"/>

            <ul class="movie-pager">
                <c:if test="${requestScope.previous_page != null}">
                    <li><a href="<c:url value="/controller?command=main&page=${previous_page}"/>">
                        <span aria-hidden="true">&larr;</span>
                            <%--<fmt:message key="previous"/>--%>
                    </a></li>
                </c:if>
                <c:if test="${requestScope.next_page != null}">
                    <li><a href="<c:url value="/controller?command=main&page=${next_page}"/>">
                            <%--<fmt:message key="next"/>--%>
                        <span aria-hidden="true">&rarr;</span>
                    </a></li>
                </c:if>
            </ul>
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