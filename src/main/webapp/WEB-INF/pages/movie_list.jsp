<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
</c:forEach>

