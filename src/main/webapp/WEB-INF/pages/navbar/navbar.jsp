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
                        <li><a href="<c:url value="/"/>">Поиск фильмов</a></li>
                        <li><a href="<c:url value="/"/>">Список фильмов</a></li>
                        <li><a href="<c:url value="/controller?command=personal_cabinet"/>">Личный кабинет</a></li>
                    </ul>
                    <c:choose>
                        <c:when test="${sessionScope.client == null}">
                            <%--TODO hide link style--%>
                            <button class="btn btn-primary"><a class="login" href="/controller?command=login">Log in</a></button>
                            <button class="btn btn-primary"><a class="signup" href="/controller?command=signup">Sign up</a></button>
                            <%--<button type="submit" name="signup" class="btn btn-primary">Sign up</button>--%>
                            <%--<ul class="collapse navbar-collapse navbar-right">--%>
                            <%--<li><a href="<c:url value="/controller?command=login"/>">LOG IN</a></li>--%>
                            <%--<li><a href="<c:url value="/controller?command=signup"/>">SIGN UP</a></li>--%>
                            <%--</ul>--%>

                        </c:when>
                        <c:otherwise>
                            <form method="post" action="/controller?command=log_out">
                                <button name="exit" type="submit" class="btn-primary">EXIT</button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                    <%--<c:choose>--%>
                    <%--<c:when test="${sessionScope.client == null}">--%>
                    <%--<form class="navbar-form navbar-right hidden-sm log-in" method="post">--%>
                    <%--<div class="form-group">--%>
                    <%--<input type="text" class="form-control" placeholder="E-mail" name="email">--%>
                    <%--<div class="form-group">--%>
                    <%--<input type="password" class="form-control" placeholder="Пароль"--%>
                    <%--name="password">--%>
                    <%--</div>--%>
                    <%--<button type="submit" name="btn" value="login" class="btn btn-primary">--%>
                    <%--<i class="fa fa-sign-in"></i> Войти--%>
                    <%--</button>--%>

                    <%--</div>--%>
                    <%--</form>--%>
                    <%--<a class="navbar-right" href="<c:url value="/registration"/>">Регистрация</a>--%>
                    <%--<c:if test="${requestScope.flag == false}">--%>
                    <%--<span class="login_error_msg">Error</span>--%>
                    <%--</c:if>--%>

                    <%--</c:when>--%>
                    <%--&lt;%&ndash; else option, set up different icons&ndash;%&gt;--%>
                    <%--</c:choose>--%>

                </div>
            </div>
        </div>
    </div>
</div>
