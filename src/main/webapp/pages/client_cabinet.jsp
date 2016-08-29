<!DOCTYPE html>
<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/fragments/header.jspf" %>
<body>
<jsp:include page="../WEB-INF/pages/navbar/navbar.jsp"/>

<div class="container-fluid personal_info_container">
    <div class="row">
        <div class="col-md-offset-2 col-md-6">
            <c:choose>
                <c:when test="${sessionScope.client.clientPhoto != null}">
                    <img src="<c:url value="${sessionScope.client.clientPhoto}"/>"
                         class="img-responsive client_photo"/>
                </c:when>
                <c:otherwise>
                    <img src="<c:url value="/resources/images/clients/user.jpg"/>"
                         class="img-responsive client_photo"/>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="col-md-4">
            <h1>${sessionScope.client.name}</h1>
            <br/>
            <h2>Info: </h2>
            <p><b>Email:</b> ${sessionScope.client.email}</p>
            <p><b>Gender:</b> ${sessionScope.client.gender}</p>
            <br/>
            <form method="post" action="/controller?command=profile_edit">
                <button type="submit" name="editButton" class="btn-primary">Изменить</button>
            </form>
        </div>
    </div>
</div>
<div class="container-fluid chosen_content">
    <div class="row">
        <div class="col-md-offset-2 col-md-6">
            <h1>Избранные фильмы</h1>
            <c:choose>
                <c:when test="${movieList.size() != 0}">
                    <ul class="media-list">
                        <c:forEach var="movie" items="${movieList}">
                            <li><a>${movie.title}</a></li>
                        </c:forEach>
                    </ul>
                </c:when>
                <c:otherwise>
                    <h4>Фильмов не выбрано</h4>
                </c:otherwise>
            </c:choose>

        </div>
        <div class="col-md-4">
            <h1>Оценки</h1>
            <c:choose>
                <c:when test="${markList.size() != 0}">
                    <ul class="media-list">
                        <c:forEach var="mark" items="${markList}">
                            <li><a>${mark}</a></li>
                        </c:forEach>
                    </ul>
                </c:when>
                <c:otherwise>
                    <h4>Нету оценок</h4>
                </c:otherwise>
            </c:choose>

        </div>
    </div>
</div>

<%@ include file="/WEB-INF/pages/fragments/footer.jspf" %>
</body>
</html>