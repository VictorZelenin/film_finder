<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-fluid">
    <div class="row">
        <div class="navbar navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#responsive-menu">
                        <span class="sr-only">Открыть навигацию</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a id="logo" href="<c:url value="/"/>" class="navbar-left"><img
                            src="${pageContext.request.contextPath}/resources/logo.png"/></a>
                </div>
                <div class="collapse navbar-collapse" id="responsive-menu">
                    <ul class="nav navbar-nav">
                        <li><a href="<c:url value="/controller"/>">Главная</a></li>
                        <li><a href="<c:url value="/controller?command=search"/>">Поиск фильмов</a></li>
                        <li><a href="<c:url value="/controller?command=show_movie_list&default=true"/>">Список фильмов</a></li>
                        <li><a href="<c:url value="/controller?command=personal_cabinet"/>">Личный кабинет</a></li>
                    </ul>
                    <form role="search" action="/controller?command=accept_search_form" method="post">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Search" name="title">
                            <div class="input-group-btn">
                                <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                    <c:choose>
                        <c:when test="${sessionScope.client == null}">
                            <%--TODO hide link style--%>
                            <div class="control-button navbar-right">
                                <button class="btn btn-primary"><a class="login" href="/controller?command=login">Log
                                    in</a></button>
                                <button class="btn btn-primary"><a class="signup" href="/controller?command=signup">Sign
                                    up</a></button>
                            </div>

                        </c:when>
                        <c:otherwise>
                            <form method="post" action="/controller?command=log_out">
                                <div class="control-button navbar-right">
                                    <button name="exit" type="submit" class="btn-primary">EXIT</button>
                                </div>

                            </form>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>
