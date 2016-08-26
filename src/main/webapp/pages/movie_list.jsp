<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<%@ include file="/WEB-INF/pages/fragments/header.jspf" %>

<body>
<jsp:include page="../WEB-INF/pages/navbar/navbar.jsp"/>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-offset-4 col-md-4">
            <jsp:include page="/WEB-INF/pages/movie_list.jsp"/>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/pages/fragments/footer.jspf" %>
</body>
</html>
