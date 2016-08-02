<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="st" tagdir="/WEB-INF/tags" %>
<html>
<body>
<c:forEach items="${list}" var="listItem">
    <c:out value="${listItem}"/> <br/>
</c:forEach>
<st:helloworld name="Victor"/>
</body>
</html>