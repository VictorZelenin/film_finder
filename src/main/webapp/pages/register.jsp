<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@ include file="/WEB-INF/pages/fragments/header.jspf" %>
<body>
<div class="container">
    <div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Registration</h3>
                </div>
                <div class="panel-body">
                    <form action="<c:url value="/controller?command=accept_signup_form"/>" role="form" method="post"
                          enctype="multipart/form-data">
                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <label>First Name</label>
                                    <input type="text" name="first_name" id="first_name" class="form-control input-sm"
                                           placeholder="First Name">
                                </div>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <label>Last Name</label>
                                    <input type="text" name="last_name" id="last_name" class="form-control input-sm"
                                           placeholder="Last Name">
                                </div>
                            </div>
                        </div>
                        <label class="btn btn-primary btn-file">
                            Upload photo
                            <input name="client_image" type="file" style="display: none;">
                        </label>
                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" name="email" id="email" class="form-control input-sm"
                                   placeholder="Email Address">
                        </div>
                        <div class="form-group">
                            <label>Gender</label>
                            <div class="radio">
                                <label><input type="radio" name="optradio" value="male">Male</label>
                            </div>
                            <div class="radio">
                                <label><input type="radio" name="optradio" value="female">Female</label>
                            </div>
                        </div>


                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <label>Password</label>
                                    <input type="password" name="password" id="password" class="form-control input-sm"
                                           placeholder="Password">
                                </div>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <label>Confirm Password</label>
                                    <input type="password" name="password_confirmation" id="password_confirmation"
                                           class="form-control input-sm" placeholder="Confirm Password">
                                </div>
                            </div>
                        </div>

                        <input type="submit" value="Register" class="btn btn-primary btn-block">
                        <c:choose>
                            <c:when test="${requestScope.isRegistered != null}">
                                <span class="login_error_msg">Already exists!</span>
                            </c:when>
                            <c:when test="${requestScope.samePassword != null}">
                                <span class="login_error_msg">Different passwords!</span>
                            </c:when>
                        </c:choose>
                        <%--<c:otherwise>--%>
                            <%--<span class="login_error_msg">Error</span>--%>
                        <%--</c:otherwise>--%>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<link rel="stylesheet" href="<c:url value="/resources/css/register.css"/>"/>
<script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
</body>
</html>
