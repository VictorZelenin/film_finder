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
            <h1>${movie.title}</h1>
            <div class="movie_image">
                <img src="${movie.posterURL}" class="img-responsive" width="300" height="400">
            </div>
        </div>
        <div class="col-md-offset-1 col-md-3">
            <br/>
            <c:choose>
                <c:when test="${movie.averageClientMark != 'NaN'}">
                    <h4>Ср. оценка - ${movie.averageClientMark}</h4>
                </c:when>
                <c:otherwise>
                    <h4>Ср. оценка - 0</h4>
                </c:otherwise>
            </c:choose>

            <%--<h4>Ср. оценка - ${movie.averageClientMark}</h4>--%>
            <br/>
            <br/>
            <%--<br/>--%>

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
                            <a href="/controller?command=show_acting_person&person_id=${director.id}">${director.name}</a> &nbsp;
                        </c:forEach>
                    </li>
                </c:if>
                <c:if test="${requestScope.actors.size() != 0}">
                    <li>
                        Actors:
                        <c:forEach var="actor" items="${requestScope.actors}">
                            <a href="/controller?command=show_acting_person&person_id=${actor.id}">${actor.name}</a> &nbsp
                        </c:forEach>
                    </li>
                </c:if>
                <c:if test="${requestScope.producers.size() != 0}">
                    <li>
                        Producers:
                        <c:forEach var="producer" items="${requestScope.producers}">
                            <a href="/controller?command=show_acting_person&person_id=${producer.id}">${producer.name}</a> &nbsp
                        </c:forEach>
                    </li>
                </c:if>
                <c:if test="${requestScope.screenWriters.size() != 0}">
                    <li>
                        Screen Writers:
                        <c:forEach var="screenWriter" items="${requestScope.screenWriters}">
                            <a href="/controller?command=show_acting_person&person_id=${screenWriter.id}">${screenWriter.name}</a> &nbsp
                        </c:forEach>
                    </li>
                </c:if>
            </ul>
            <br/>
            <p>${requestScope.movie.plot}</p>
            <br/>
            <c:if test="${client != null}">
                <form method="post" action="/controller?command=add_movie&movie_id=${movie.id}">
                    <button class="btn-primary" type="submit">Add to favourites</button>
                </form>
            </c:if>
        </div>
    </div>
    <br/>
    <br/>
    <c:if test="${client != null}">
        <form id="mark_form" method="post" action="/controller?command=add_mark&movie_id=${movie.id}">
            <div class="row">
                <div class="form-group">
                    <label class="control-label col-md-offset-2 col-md-2" for="mark">Ваша оценка:</label>
                    <div class="col-md-2">
                        <c:choose>
                            <c:when test="${mark != null}">
                                <input type="number" min="0" max="10" class="form-control" id="mark"
                                       name="mark" value="${mark.mark}">
                            </c:when>
                            <c:otherwise>
                                <input type="number" min="0" max="10" class="form-control" id="mark"
                                       name="mark" value="">
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
            <br/>
        </form>
    </c:if>
    <br/>
    <c:if test="${client != null}">
        <div class="row">
            <div class="form-group">
                <label class="control-label col-md-offset-2 col-md-4 col-md-offset-4" for="comment">Оставьте
                    комментарий:</label>
                <div class="col-md-offset-2 col-md-6">
                    <c:choose>
                        <c:when test="${mark != null}">
                                <textarea cols="15" rows="5" class=" form-control" id="comment"
                                          name="comment" form="mark_form"
                                          placeholder="comment here...">${mark.description}</textarea>
                        </c:when>
                        <c:otherwise>
                                <textarea form="mark_form" cols="15" rows="5" class=" form-control"
                                          id="comment" name="comment" placeholder="comment here..."></textarea>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
        <br/>
        <div class="row">
            <%--<div class="col-md-offset-3">--%>
                <%--<input type="button" value="delete profile">--%>
            <%--</div>--%>
            <div class="col-md-offset-7">
                <button form="mark_form" type="submit" class="btn-primary">Оставить</button>
            </div>
        </div>
        <br/>
    </c:if>
    <c:forEach var="movie_mark" items="${requestScope.marks}">
        <div class="row">
            <div class="col-md-offset-1">
                <h3>${movie_mark.client.name}, Оценка - ${movie_mark.mark}</h3>
                <h5>${movie_mark.date}</h5>
            </div>
        </div>
        <div class="row">
            <div class="col-md-offset-2 col-md-6">
                <textarea cols="15" rows="5" class=" form-control"
                          readonly="readonly">${movie_mark.description}</textarea>
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
