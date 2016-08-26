<%--
  Created by IntelliJ IDEA.
  User: victor
  Date: 24.08.16
  Time: 2:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://example.com/functions" %>
<html>
<%@ include file="/WEB-INF/pages/fragments/header.jspf" %>
<body>
<jsp:include page="../WEB-INF/pages/navbar/navbar.jsp"/>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-6">
            <h1>${movie.title}</h1>
            <div class="movie_image">
                <img src="${movie.posterURL}" class="img-responsive" width="300" height="400">
            </div>
            <p>${requestScope.movie.plot}</p>
        </div>
        <div class="col-md-4">
            <br/>
            <br/>
            <br/>
            <br/>
            <ul class="media-list">
                <li>IMDB Rating: ${movie.imdbRating}(IMDB votes - ${movie.imdbVotes})</li>
                <li>Country: ${movie.country}</li>
                <li>Genres:
                    <c:forEach var="genre" items="${requestScope.genres}">
                        ${genre} &nbsp;
                    </c:forEach>
                </li>
                <li>Release Date: ${date}</li>
                <li>Runtime: ${movie.runtime}</li>
                <c:if test="${requestScope.directors.size() != 0}">
                    <li>
                        Directors:
                        <c:forEach var="director" items="${requestScope.directors}">
                            <a href="#">${director.name}</a> &nbsp;
                        </c:forEach>
                    </li>
                </c:if>
                <c:if test="${requestScope.actors.size() != 0}">
                    <li>
                        Actors:
                        <c:forEach var="actor" items="${requestScope.actors}">
                            <a href="#">${actor.name}</a> &nbsp
                        </c:forEach>
                    </li>
                </c:if>
                <c:if test="${requestScope.producers.size() != 0}">
                    <li>
                        Producers:
                        <c:forEach var="producer" items="${requestScope.producers}">
                            <a href="#">${producer.name}</a> &nbsp
                        </c:forEach>
                    </li>
                </c:if>
                <c:if test="${requestScope.screenWriters.size() != 0}">
                    <li>
                        Screen Writers:
                        <c:forEach var="screenWriter" items="${requestScope.screenWriters}">
                            <a href="#">${screenWriter.name}</a> &nbsp
                        </c:forEach>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="<c:url value="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/bootstrap-3.3.7-dist/js/bootstrap.js"/>"></script>
<%@include file="/WEB-INF/pages/fragments/footer.jspf" %>
</body>
</html>
