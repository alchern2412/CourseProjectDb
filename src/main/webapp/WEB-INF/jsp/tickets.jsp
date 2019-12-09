<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html>
<head>
    <%@ include file="parts/meta.jsp" %>
    <style>
        <%@include file="/resources/css/group-list.css" %>
    </style>
    <title>Tickets</title>

</head>
<body>
<%@ include file="parts/header.jsp" %>


<div class="tab-pane container active">
    <div class="container mt-3">
        <h2>
            <fmt:message key="content.alltickets.label.tickets" bundle="${BundleContent}"></fmt:message>
        </h2>
        <p>
            <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>
                    <fmt:message key="content.alltickets.table.fromplace" bundle="${BundleContent}"></fmt:message>
                </th>
                <th>
                    <fmt:message key="content.alltickets.table.toplace" bundle="${BundleContent}"></fmt:message>
                </th>
                <th>
                    <fmt:message key="content.alltickets.table.departuredate" bundle="${BundleContent}"></fmt:message>
                </th>
                <th>
                    <fmt:message key="content.alltickets.table.firstname" bundle="${BundleContent}"></fmt:message>
                </th>
                <th>
                    <fmt:message key="content.alltickets.table.lastname" bundle="${BundleContent}"></fmt:message>
                </th>
                <th>
                    <fmt:message key="content.alltickets.table.price" bundle="${BundleContent}"></fmt:message>
                </th>
                <th>
                    <fmt:message key="content.alltickets.table.status" bundle="${BundleContent}"></fmt:message>
                </th>
            </tr>
            </thead>

            <tbody id="myTable">
            <c:forEach var="curTicket" items="${tickets}">
                <form id="fromConfirmTicket${curTicket.id}" action="/controller" method="get">
                    <input hidden name="command" value="confirm-ticket">
                    <input hidden name="id-ticket" value="${curTicket.id}">
                </form>
                <tr>
                    <td>${curTicket.flight.from_airport.name}</td>
                    <td>${curTicket.flight.to_airport.name}</td>
                    <td>
                        <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium"
                                        value="${curTicket.flight.departure}"/>
                    </td>
                        <%--                        <td>--%>
                        <%--                            <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium"--%>
                        <%--                                            value="${curTicket.arrivalDate}"/>--%>
                        <%--                        </td>--%>

                    <td>${curTicket.user.firstName}</td>
                    <td>${curTicket.user.lastName}</td>
                    <td>${curTicket.flight.price}</td>

                    <td>
                        <c:if test="${curTicket.confirmed}">
                            <fmt:message key="content.alltickets.table.confrimed" bundle="${BundleContent}"/>
                        </c:if>
                        <c:if test="${not curTicket.confirmed}">
                            <a class="dropdown-item"
                               onclick="document.getElementById('fromConfirmTicket${curTicket.id}').submit()" href="#">
                                <fmt:message key="content.alltickets.table.confirm" bundle="${BundleContent}"/>
                            </a>
                        </c:if>

                    </td>
                </tr>

            </c:forEach>

            </tbody>
        </table>
    </div>
</div>
<nav aria-label="Tickets pagination">

    <ul class="pagination justify-content-center">

        <c:if test="${param.page gt 1}">
            <li class="page-item">
                <a class="page-link" href="/controller?command=all-tickets&page=${param.page - 1}"
                   tabindex="-1">
                    <fmt:message key="content.pagination.label.prev" bundle="${BundleContent}"></fmt:message>
                </a>
            </li>
        </c:if>
        <c:if test="${param.page <= 1}">
            <li class="page-item disabled">
                <a class="page-link" href="/controller?command=all-tickets&page=${param.page - 1}"
                   tabindex="-1">
                    <fmt:message key="content.pagination.label.prev" bundle="${BundleContent}"></fmt:message>
                </a>
            </li>
        </c:if>
        <c:if test="${param.page gt 1}">
            <li class="page-item"><a class="page-link"
                                     href="/controller?command=all-tickets&page=${param.page - 1}">${param.page - 1}</a>
            </li>
        </c:if>
        <li class="page-item active">
            <a class="page-link"
               href="/controller?command=all-tickets&page=${param.page}">${param.page}<span
                    class="sr-only">(current)</span></a>
        </li>
        <li class="page-item"><a class="page-link"
                                 href="/controller?command=all-tickets&page=${param.page + 1}">${param.page + 1}</a>
        </li>
        <li class="page-item">
            <a class="page-link"
               href="/controller?command=all-tickets&page=${param.page + 1}">
                <fmt:message key="content.pagination.label.next" bundle="${BundleContent}"></fmt:message>
            </a>
        </li>
    </ul>
</nav>

<%@ include file="parts/footer.jsp" %>

</body>
</html>
