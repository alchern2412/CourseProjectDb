<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="users" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <%@ include file="parts/meta.jsp" %>
    <style>
        <%@include file="/resources/css/group-list.css" %>
    </style>
    <title>Orders</title>

</head>
<body>
<%@ include file="parts/header.jsp" %>

<users:allUsers users="${users}"/>

<nav aria-label="Users pagination">

    <ul class="pagination justify-content-center">

        <c:if test="${param.page gt 1}">
            <li class="page-item">
                <a class="page-link" href="http://localhost:8080/controller?command=all-users&page=${param.page - 1}"
                   tabindex="-1">
                    <fmt:message key="content.pagination.label.prev" bundle="${BundleContent}"></fmt:message>
                </a>
            </li>
        </c:if>
        <c:if test="${param.page <= 1}">
            <li class="page-item disabled">
                <a class="page-link" href="http://localhost:8080/controller?command=all-users&page=${param.page - 1}"
                   tabindex="-1">
                    <fmt:message key="content.pagination.label.prev" bundle="${BundleContent}"></fmt:message>
                </a>
            </li>
        </c:if>
        <c:if test="${param.page gt 1}">
            <li class="page-item">
                <a class="page-link"
                   href="http://localhost:8080/controller?command=all-users&page=${param.page - 1}">${param.page - 1}</a>
            </li>
        </c:if>
        <li class="page-item active">
            <a class="page-link"
               href="http://localhost:8080/controller?command=all-users&page=${param.page}">${param.page}<span
                    class="sr-only">(current)</span></a>
        </li>
        <li class="page-item"><a class="page-link"
                                 href="http://localhost:8080/controller?command=all-users&page=${param.page + 1}">${param.page + 1}</a>
        </li>
        <li class="page-item">
            <a class="page-link"
               href="http://localhost:8080/controller?command=all-users&page=${param.page + 1}">
                <fmt:message key="content.pagination.label.next" bundle="${BundleContent}"></fmt:message>
            </a>
        </li>
    </ul>
</nav>

<%@ include file="parts/footer.jsp" %>

</body>
</html>
