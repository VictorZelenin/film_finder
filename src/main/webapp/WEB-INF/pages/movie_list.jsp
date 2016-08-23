<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="movie" items="${requestScope.movies}">
    <div class="movie_box">
        <div class="text_container">
            <span class="movie_title"><a href="#">${movie.title}</a></span>
            <span class="movie_mark">${movie.imdbRating}</span>
        </div>
        <div class="movie_image">
            <img src="<c:url value="${movie.posterURL}"/>" class="img-responsive">
        </div>
    </div>
</c:forEach>

