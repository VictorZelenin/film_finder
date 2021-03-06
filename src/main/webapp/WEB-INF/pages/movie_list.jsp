<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${movies.size() > 0}">
        <c:forEach var="movie" items="${requestScope.movies}">
            <div class="movie_box">
                <div class="text_container">
            <span class="movie_title">
                <a href="<c:url value="/controller?command=show_movie&movie_id=${movie.id}"/>">${movie.title}
                </a>
            </span>
                    <span class="movie_mark">${movie.imdbRating}</span>
                </div>
                <div class="movie_image">
                    <img src="<c:url value="${movie.posterURL}"/>" class="img-responsive">
                </div>
            </div>
        </c:forEach>
        <c:if test="${moviesList_size > per_page}">
            <ul class="media-list movie-pager">
                <c:if test="${requestScope.previous_page != null}">
                    <li><a href="<c:url value="/controller?command=main&page=${previous_page}"/>">
                        <span aria-hidden="true">&larr;</span>
                    </a></li>
                </c:if>
                <c:if test="${requestScope.next_page != null}">
                    <li><a href="<c:url value="/controller?command=main&page=${next_page}"/>">
                        <span aria-hidden="true">&rarr;</span>
                    </a></li>
                </c:if>
            </ul>
        </c:if>
    </c:when>
    <c:otherwise>
        <h1>Фильмов за такими параметрами не найдено!!</h1>
    </c:otherwise>
</c:choose>

