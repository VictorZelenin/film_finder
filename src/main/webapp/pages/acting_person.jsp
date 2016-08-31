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
            <%--<br/>--%>
            <%--<br/>--%>
            <c:choose>
                <c:when test="${acting_person.averageClientMark != 'NaN'}">
                    <h4>Ср. оценка - ${acting_person.averageClientMark}</h4>
                </c:when>
                <c:otherwise>
                    <h4>Ср. оценка - 0</h4>
                </c:otherwise>
            </c:choose>

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
        <form method="post" action="/controller?command=add_mark&person_id=${acting_person.id}">
            <div class="row">
                <div class="form-group">
                    <label class="control-label col-md-offset-2 col-md-2" for="mark">Ваша оценка:</label>
                    <div class="col-md-2">
                        <c:choose>
                            <c:when test="${mark != null}">
                                <input type="number" min="0" max="10" class="form-control" id="mark" name="mark"
                                       value="${mark.mark}">
                            </c:when>
                            <c:otherwise>
                                <input type="number" min="0" max="10" class="form-control" id="mark" name="mark"
                                       value="">
                            </c:otherwise>
                        </c:choose>

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
                        <c:choose>
                            <c:when test="${mark != null}">
                                <textarea cols="15" rows="5" class=" form-control" id="comment"
                                          name="comment"
                                          placeholder="comment here...">${mark.description}</textarea>
                            </c:when>
                            <c:otherwise>
                                <textarea cols="15" rows="5" class=" form-control" id="comment"
                                          name="comment"
                                          placeholder="comment here..."></textarea>
                            </c:otherwise>
                        </c:choose>
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
    <c:forEach var="person_mark" items="${requestScope.marks}">
        <div class="row">
            <div class="col-md-offset-1">
                <h3>${person_mark.client.name}, Оценка - ${person_mark.mark}</h3>
                <h5>${person_mark.date}</h5>
            </div>
        </div>
        <div class="row">
            <div class="col-md-offset-2 col-md-6">
                <textarea cols="15" rows="5" class=" form-control"
                          readonly="readonly">${person_mark.description}</textarea>
            </div>
        </div>
        <br/>
    </c:forEach>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="<c:url value="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/bootstrap-3.3.7-dist/js/bootstrap.js"/>"></script>
<%@include file="/WEB-INF/pages/fragments/footer.jspf" %>
</body>
</html>
