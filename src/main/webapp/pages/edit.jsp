<%--TODO tips if smth going wrong--%>
<!DOCTYPE html>
<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/fragments/header.jspf" %>
<body>
<jsp:include page="../WEB-INF/pages/navbar/navbar.jsp"/>

<c:if test="${sessionScope.client == null}">
    <c:redirect url="/controller"/>
</c:if>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-offset-4 col-md-6">
            <h2>Редактирование профиля</h2>
        </div>
    </div>
    <br/>
    <form action="/controller?command=accept_edit_form" enctype="multipart/form-data" method="post">
        <div class="row">
            <div class="form-group">
                <label class="control-label col-md-offset-1 col-md-1" for="first_name">Имя:</label>
                <div class="col-md-3">
                    <input type="text" class="form-control" id="first_name" name="first_name"
                           value="${sessionScope.client.getFirstName()}">
                </div>
                <label class="control-label col-md-offset-1 col-md-1" for="last_name">Фамилия:</label>
                <div class="col-md-3">
                    <input type="text" class="form-control" id="last_name" name="last_name"
                           value="${sessionScope.client.getLastName()}">
                </div>
            </div>
        </div>
        <br/>
        <div class="row">
            <div class="form-group">
                <label class="control-label col-md-offset-3 col-md-1" for="email">Email:</label>
                <div class="col-md-4 ">
                    <input type="email" class="form-control" id="email" name="email" value="
                    ${sessionScope.client.email}">
                </div>
            </div>
        </div>
        <br/>
        <br/>
        <div class="row">
            <c:choose>
                <c:when test="${sessionScope.client.gender == 'MALE'}">
                    <div class="form-group">
                        <label class="control-label col-md-offset-3 col-md-1">Пол:</label>
                        <div class="col-md-4">
                            <select class="form-control" name="gender">
                                <option value="male" selected>Мужской</option>
                                <option value="female">Женский</option>
                            </select>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="form-group">
                        <label class="control-label col-md-offset-3 col-md-1">Пол:</label>
                        <div class="col-md-4">
                            <select class="form-control" name="gender">
                                <option value="male">Мужской</option>
                                <option value="female" selected>Женский</option>
                            </select>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        <br/>
        <br/>
        <div class="row">
            <div class="form-group">
                <label class="col-md-offset-3 col-md-2">Загрузите фото:</label>
                <label class="col-md-1 btn btn-primary btn-file">
                    Upload photo
                    <input name="client_image" type="file" style="display: none;">
                </label>
            </div>
        </div>
        <br/>
        <br/>
        <br/>
        <br/>
        <div class="row">
            <div class="form-group">
                <label class="control-label col-md-offset-1 col-md-1" for="password">Пароль</label>
                <div class="col-md-3">
                    <input type="password" class="form-control" id="password" name="password">
                </div>
                <label class="control-label col-md-offset-1 col-md-2" for="password_confirmation">Подтвердите
                    пароль</label>
                <div class="col-md-3">
                    <input type="password" class="form-control" id="password_confirmation" name="password_confirmation">
                </div>
            </div>
        </div>
        <div class="row">
            <button type="submit" class="btn btn-primary col-md-offset-10">Изменить</button>
        </div>
    </form>
</div>
<br/>
<br/>

<%@ include file="/WEB-INF/pages/fragments/footer.jspf" %>
</body>
</html>