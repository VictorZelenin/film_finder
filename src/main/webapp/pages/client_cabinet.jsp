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
            <img src="<c:url value="${sessionScope.client.clientPhoto}"/>"
                 class="img-responsive client_photo"/>

        </div>
        <div class="col-md-4">
            <h1>${sessionScope.client.name}</h1>
            <br/>
            <h2>Info: </h2>
            <p><b>Email:</b> ${sessionScope.client.email}</p>
            <p><b>Gender:</b> ${sessionScope.client.gender}</p>
            <br/>
            <button type="button" name="editButton" class="btn-primary">Изменить</button>
        </div>
    </div>
</div>
<div class="container-fluid chosen_content">
    <div class="row">
        <div class="col-md-offset-2 col-md-6">
            <h1>Избранные фильмы</h1>
            <ul class="media-list">
                <c:forEach var="movie" items="${sessionScope.movieList}">
                    <li><a>${movie.title}</a></li>
                </c:forEach>
            </ul>
        </div>
        <div class="col-md-4">
            <h1>Marks</h1>
            <ul class="media-list">
                <c:forEach var="mark" items="${sessionScope.markList}">
                    <li><a>${mark}</a></li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/pages/fragments/footer.jspf" %>
</body>
</html>