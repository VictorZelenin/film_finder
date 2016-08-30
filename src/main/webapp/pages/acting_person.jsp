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
        <div class="col-md-4">
            <h1>${acting_person.name}</h1>
            <div class="movie_image">
                <img src="${acting_person.photoURL}" class="img-responsive" width="300" height="400">
            </div>
            <%--<p>${requestScope.movie.plot}</p>--%>
        </div>
        <div class="col-md-offset-1 col-md-3">
            <br/>
            <br/>
            <br/>
            <br/>
            <ul class="media-list">
                <c:if test="${roles.size() > 0}">
                    <li>Амплуа:
                        <c:forEach var="role" items="${roles}">
                            ${role} &nbsp;
                        </c:forEach>
                    </li>
                </c:if>
                <li>Страна: ${acting_person.country}</li>
                <li>Возраст: ${acting_person.age}</li>
                <li>Рост: ${acting_person.height}</li>
                <c:if test="${acting_person.deathDate != null}">
                    <li>Дата смерти: ${acting_person.deathDate}</li>
                </c:if>
                <c:if test="${person_genres.size() > 0}">
                    <li>Жанры:
                        <c:forEach var="genre" items="${person_genres}">
                            ${genre} &nbsp;
                        </c:forEach>
                    </li>
                </c:if>
                <li>Количество фильмов: ${acting_person.totalMoviesNumber}</li>
            </ul>
        </div>
    </div>
    <br/>
    <c:if test="${client != null}">
        <form method="post" action="/controller?command=add_mark?...">
            <div class="row">
                <div class="form-group">
                    <label class="control-label col-md-offset-2 col-md-2" for="mark">Ваша оценка:</label>
                    <div class="col-md-2">
                        <input type="number" min="0" max="10" class="form-control" id="mark" name="mark"
                               value="">
                    </div>
                </div>
            </div>
            <br/>
                <%--foreach mark show comments--%>
            <div class="row">
                <div class="form-group">
                    <label class="control-label col-md-offset-2 col-md-4 col-md-offset-4" for="comment">Оставьте
                        комментарий:</label>
                    <div class="col-md-offset-2 col-md-6">
                    <textarea cols="15" rows="5" class=" form-control" id="comment"
                              placeholder="comment here..."></textarea>
                    </div>
                </div>
            </div>
            <br/>
            <div class="row">
                <div class="col-md-offset-7">
                    <button type="submit" class="btn-primary">Оставить</button>
                </div>
            </div>
        </form>
    </c:if>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="<c:url value="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/bootstrap-3.3.7-dist/js/bootstrap.js"/>"></script>
<%@include file="/WEB-INF/pages/fragments/footer.jspf" %>
</body>
</html>
