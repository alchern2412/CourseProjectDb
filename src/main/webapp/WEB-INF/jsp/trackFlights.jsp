<%--
  Created by IntelliJ IDEA.
  User: Alexey Chernyavsky
  Date: 12/2/2019
  Time: 6:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html>
<head>
    <%@ include file="parts/meta.jsp" %>
    <style>
        <%@include file="/resources/css/group-list.css" %>
    </style>
    <title>Track Flights</title>

</head>
<body>
<%@ include file="parts/header.jsp" %>


<div class="tab-pane container active">
    <div class="container mt-3">
        <h2>
            <fmt:message key="content.trackflights.inway" bundle="${BundleContent}"></fmt:message>
        </h2>
        <p>
            <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>
                    <a href="/controller?command=track-flights&order=plane">
                        <fmt:message key="content.allflights.table.plane" bundle="${BundleContent}"></fmt:message>
                    </a>
                </th>
                <th>
                    <a href="/controller?command=track-flights&order=from-airport">
                        <fmt:message key="content.allflights.table.fromairport" bundle="${BundleContent}"></fmt:message>
                    </a>
                </th>
                <th>
                    <a href="/controller?command=track-flights&order=to-airport">
                        <fmt:message key="content.allflights.table.toairport" bundle="${BundleContent}"></fmt:message>
                    </a>
                </th>
                <th>
                    <a href="/controller?command=track-flights&order=departure">
                        <fmt:message key="content.allflights.table.departuredate"
                                     bundle="${BundleContent}"></fmt:message>
                    </a>
                </th>
                <th>
                    <a href="/controller?command=track-flights&order=arrival">
                        <fmt:message key="content.allflights.table.arrivaldate" bundle="${BundleContent}"></fmt:message>
                    </a>
                </th>
                <%--                <th>--%>
                <%--                    <fmt:message key="content.allflights.table.allpassengers" bundle="${BundleContent}"></fmt:message>--%>
                <%--                </th>--%>
                <%--                <th>--%>
                <%--                    <fmt:message key="content.allflights.table.passengers" bundle="${BundleContent}"></fmt:message>--%>
                <%--                </th>--%>
                <%--                <th>--%>
                <%--                    <fmt:message key="content.allflights.table.priceflight" bundle="${BundleContent}"></fmt:message>--%>
                <%--                </th>--%>
                <th>
                    <a href="/controller?command=track-flights&order=will-arrive">
                        <fmt:message key="content.trackflights.willarrive" bundle="${BundleContent}"></fmt:message>
                    </a>
                </th>

                <%--                <th>--%>
                <%--                    <fmt:message key="content.allflights.table.description" bundle="${BundleContent}"></fmt:message>--%>
                <%--                </th>--%>
                <th>
                    <fmt:message key="content.allflights.table.actions" bundle="${BundleContent}"></fmt:message>
                </th>
            </tr>
            </thead>

            <tbody id="myTable">
            <c:forEach var="curFlight" items="${planesOnTheWay}">
                <form id="formFlightUsers${curFlight.id}" action="/controller" method="get">
                    <input hidden name="command" value="flight-users">
                    <input hidden name="id-flight" value="${curFlight.id}">
                </form>
                <form id="formEditFlight${curFlight.id}" action="/controller" method="get">
                    <input hidden name="command" value="edit-flight">
                    <input hidden name="id-flight" value="${curFlight.id}">
                </form>
                <tr>
                    <td>${curFlight.plane.number}</td>
                    <td>${curFlight.from_airport.name}</td>
                    <td>${curFlight.to_airport.name}</td>
                    <td>
                        <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium"
                                        value="${curFlight.departure}"/>
                    </td>
                    <td>
                        <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium"
                                        value="${curFlight.arrival}"/>
                    </td>
                        <%--                    <td>${curFlight.plane.capacity}</td>--%>
                        <%--                        &lt;%&ndash;                        <td>${curFlight.passengers}</td>&ndash;%&gt;--%>
                        <%--                    <td>???</td>--%>
                        <%--                    <td>${curFlight.price}</td>--%>
                    <td>???</td>

                        <%--                        <td>${curFlight.description}</td>--%>
                    <td>
                        <a class="dropdown-item"
                           onclick="document.getElementById('formFlightUsers${curFlight.id}').submit()" href="#">
                            <fmt:message key="content.allflights.table.flightspassengers" bundle="${BundleContent}"/>
                        </a>
                        <a class="dropdown-item"
                           onclick="document.getElementById('formEditFlight${curFlight.id}').submit()" href="#">
                            <fmt:message key="content.allflights.table.editflight" bundle="${BundleContent}"/>
                        </a>
                    </td>
                </tr>

            </c:forEach>

            </tbody>
        </table>
    </div>
</div>

<div class="tab-pane container active">
    <div class="container mt-3">
        <h2>
            <fmt:message key="content.trackflights.willbeleaving" bundle="${BundleContent}"></fmt:message>
        </h2>
        <p>
            <br>
        <table class="table table-bordered">
            <thead>
            <tr>

                <th>
                    <fmt:message key="content.allflights.table.plane" bundle="${BundleContent}"></fmt:message>
                </th>

                <th>
                    <fmt:message key="content.allflights.table.fromairport" bundle="${BundleContent}"></fmt:message>
                </th>
                <th>
                    <fmt:message key="content.allflights.table.toairport" bundle="${BundleContent}"></fmt:message>
                </th>
                <th>
                    <fmt:message key="content.allflights.table.departuredate" bundle="${BundleContent}"></fmt:message>
                </th>
                <th>
                    <fmt:message key="content.allflights.table.arrivaldate" bundle="${BundleContent}"></fmt:message>
                </th>
                <%--                <th>--%>
                <%--                    <fmt:message key="content.allflights.table.allpassengers" bundle="${BundleContent}"></fmt:message>--%>
                <%--                </th>--%>
                <%--                <th>--%>
                <%--                    <fmt:message key="content.allflights.table.passengers" bundle="${BundleContent}"></fmt:message>--%>
                <%--                </th>--%>
                <%--                <th>--%>
                <%--                    <fmt:message key="content.allflights.table.priceflight" bundle="${BundleContent}"></fmt:message>--%>
                <%--                </th>--%>
                <th>
                    <fmt:message key="content.trackflights.leavein" bundle="${BundleContent}"></fmt:message>
                </th>

                <%--                <th>--%>
                <%--                    <fmt:message key="content.allflights.table.description" bundle="${BundleContent}"></fmt:message>--%>
                <%--                </th>--%>
                <th>
                    <fmt:message key="content.allflights.table.actions" bundle="${BundleContent}"></fmt:message>
                </th>
            </tr>
            </thead>

            <tbody id="will-be-leaving-table">
            <c:forEach var="curFlight" items="${departingPlanes}">
                <form id="formFlightUsers${curFlight.id}" action="/controller" method="get">
                    <input hidden name="command" value="flight-users">
                    <input hidden name="id-flight" value="${curFlight.id}">
                </form>
                <form id="formEditFlight${curFlight.id}" action="/controller" method="get">
                    <input hidden name="command" value="edit-flight">
                    <input hidden name="id-flight" value="${curFlight.id}">
                </form>
                <tr>
                    <td>${curFlight.plane.number}</td>
                    <td>${curFlight.from_airport.name}</td>
                    <td>${curFlight.to_airport.name}</td>
                    <td>
                        <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium"
                                        value="${curFlight.departure}"/>
                    </td>
                    <td>
                        <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium"
                                        value="${curFlight.arrival}"/>
                    </td>
                        <%--                    <td>${curFlight.plane.capacity}</td>--%>
                        <%--                        &lt;%&ndash;                        <td>${curFlight.passengers}</td>&ndash;%&gt;--%>
                        <%--                    <td>???</td>--%>
                        <%--                    <td>${curFlight.price}</td>--%>
                    <td>???</td>

                        <%--                        <td>${curFlight.description}</td>--%>
                    <td>
                        <a class="dropdown-item"
                           onclick="document.getElementById('formFlightUsers${curFlight.id}').submit()" href="#">
                            <fmt:message key="content.allflights.table.flightspassengers" bundle="${BundleContent}"/>
                        </a>
                        <a class="dropdown-item"
                           onclick="document.getElementById('formEditFlight${curFlight.id}').submit()" href="#">
                            <fmt:message key="content.allflights.table.editflight" bundle="${BundleContent}"/>
                        </a>
                    </td>
                </tr>

            </c:forEach>

            </tbody>
        </table>
    </div>
</div>


<%--<nav aria-label="Flights pagination">--%>

<%--    <ul class="pagination justify-content-center">--%>

<%--        <c:if test="${param.page gt 1}">--%>
<%--            <li class="page-item">--%>
<%--                <a class="page-link" href="/controller?command=all-flights&page=${param.page - 1}"--%>
<%--                   tabindex="-1">--%>
<%--                    <fmt:message key="content.pagination.label.prev" bundle="${BundleContent}"></fmt:message>--%>
<%--                </a>--%>
<%--            </li>--%>
<%--        </c:if>--%>
<%--        <c:if test="${param.page <= 1}">--%>
<%--            <li class="page-item disabled">--%>
<%--                <a class="page-link" href="/controller?command=all-flights&page=${param.page - 1}"--%>
<%--                   tabindex="-1">--%>
<%--                    <fmt:message key="content.pagination.label.prev" bundle="${BundleContent}"></fmt:message>--%>
<%--                </a>--%>
<%--            </li>--%>
<%--        </c:if>--%>
<%--        <c:if test="${param.page gt 1}">--%>
<%--            <li class="page-item"><a class="page-link"--%>
<%--                                     href="/controller?command=all-flights&page=${param.page - 1}">${param.page - 1}</a>--%>
<%--            </li>--%>
<%--        </c:if>--%>
<%--        <li class="page-item active">--%>
<%--            <a class="page-link"--%>
<%--               href="/controller?command=all-flights&page=${param.page}">${param.page}<span--%>
<%--                    class="sr-only">(current)</span></a>--%>
<%--        </li>--%>
<%--        <li class="page-item"><a class="page-link"--%>
<%--                                 href="/controller?command=all-flights&page=${param.page + 1}">${param.page + 1}</a>--%>
<%--        </li>--%>
<%--        <li class="page-item">--%>
<%--            <a class="page-link"--%>
<%--               href="/controller?command=all-flights&page=${param.page + 1}">--%>
<%--                <fmt:message key="content.pagination.label.next" bundle="${BundleContent}"></fmt:message>--%>
<%--            </a>--%>
<%--        </li>--%>
<%--    </ul>--%>
<%--</nav>--%>

<%@ include file="parts/footer.jsp" %>

</body>
</html>
