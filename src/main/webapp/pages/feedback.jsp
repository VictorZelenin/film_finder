<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/fragments/header.jspf" %>
<body>
<jsp:include page="../WEB-INF/pages/navbar/navbar.jsp"/>
<c:choose>
    <c:when test="${sessionScope.client == null}">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-offset-3 col-md-6">
                    <p>Только зарегистрированные пользователи могут оставить сообщение администратору!!</p>
                </div>
            </div>
            <br/>
            <div class="row">
                <div class="col-md-offset-3 col-md-2">
                    <a href="<c:url value="/controller"/>">Назад на главную</a>
                </div>
                <div class="col-md-1">
                    <a href="<c:url value="/controller?command=login"/>">Войти</a>
                </div>
            </div>

        </div>
    </c:when>
    <c:otherwise>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-offset-3 col-md-6">
                    <h1>Оставьте сообщение администратору:</h1>
                </div>
            </div>
            <form action="/controller?command=accept_feedback" id="feedback_form" method="post">
                <div class="row">
                    <div class="col-md-offset-3 col-md-5">
                        <c:choose>
                            <c:when test="${message == null}">
                                <textarea class="form-control" name="feedback_message"
                                          placeholder="Enter text here..." cols="30" rows="10"></textarea>
                            </c:when>
                            <c:otherwise>
                                <textarea class="form-control" name="feedback_message"
                                          cols="30" rows="10">${message}</textarea>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <br/>
                <div class="row">
                    <div class="col-md-offset-7">
                        <button type="submit" class="btn-primary">Отправить</button>
                    </div>
                </div>
            </form>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>
