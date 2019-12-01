<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexey Chernyavsky
  Date: 10/25/2019
  Time: 1:51 AM
  To change this template use File | Settings | File Templates.
--%>
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

<!-- Nav tabs -->
<ul class="nav nav-tabs">
    <li class="nav-item">
        <a class="nav-link active" data-toggle="tab" href="#orders">
            <fmt:message key="content.myorders.label.orders" bundle="${BundleContent}"></fmt:message>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-toggle="tab" href="#finishedOrders">
            <fmt:message key="content.myorders.label.finishedorders" bundle="${BundleContent}"></fmt:message>
        </a>
    </li>
</ul>

<!-- Tab panes -->
<div class="tab-content">
    <div class="tab-pane container active" id="orders">
        <div class="container mt-3">
            <h2>
                <fmt:message key="content.myorders.label.flights" bundle="${BundleContent}"></fmt:message>
            </h2>
            <p>
                <fmt:message key="content.myorders.label.findflight" bundle="${BundleContent}"></fmt:message>
                :</p>
            <fmt:message key="content.myorders.placeholder.search" var="placeholderSearch" bundle="${BundleContent}"></fmt:message>
            <input class="form-control" id="searchOrder" type="text" placeholder="${placeholderSearch}">
            <br>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>
                        <fmt:message key="content.myorders.table.departuredate" bundle="${BundleContent}"></fmt:message>
                    </th>
                    <th>
                        <fmt:message key="content.myorders.table.arrivaldate" bundle="${BundleContent}"></fmt:message>
                    </th>
                    <th>
                        <fmt:message key="content.myorders.table.from" bundle="${BundleContent}"></fmt:message>
                    </th>
                    <th>
                        <fmt:message key="content.myorders.table.to" bundle="${BundleContent}"></fmt:message>
                    </th>
<%--                    <th>--%>
<%--                        <fmt:message key="content.myorders.table.luggage" bundle="${BundleContent}"></fmt:message>--%>
<%--                    </th>--%>
                    <th>
                        <fmt:message key="content.myorders.table.price" bundle="${BundleContent}"></fmt:message>
                    </th>
                    <th>
                        <fmt:message key="content.myorders.table.status" bundle="${BundleContent}"></fmt:message>
                    </th>
                    <th>
                        <fmt:message key="content.myorders.table.actions" bundle="${BundleContent}"></fmt:message>
                    </th>
                </tr>
                </thead>

                <tbody id="myTable">
                <c:forEach var="ticket" items="${userTickets}">
                    <form action="/controller" method="post">
                        <input hidden name="command" value="delete-order">
                        <input hidden name="delete-ticket" value="${ticket.id}">

                        <tr>
                            <td>
                                <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium"
                                                value="${ticket.flight.departure}"/>
                            </td>
                            <td>
                                <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium"
                                                value="${ticket.flight.arrival}"/>
                            </td>
                            <td>${ticket.flight.from_airport.name}</td>
                            <td>${ticket.flight.to_airport.name}</td>
<%--                            <td>${ticket.luggage}</td>--%>
                            <td>${ticket.flight.price}</td>
                            <td>${ticket.confirmed}</td>
                            <td>
                                <input type="submit" value="Cancel Order">
                            </td>
                        </tr>

                    </form>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
    <div class="tab-pane container fade" id="finishedOrders">
        <div class="container mt-3">
            <h2>
                <fmt:message key="content.myorders.label.finishedorders" bundle="${BundleContent}"></fmt:message>
            </h2>
            <p>
                <fmt:message key="content.myorders.label.findfinishedflight" bundle="${BundleContent}"></fmt:message>
                :</p>
            <input class="form-control" id="searchFinishedOrder" type="text" placeholder="${placeholderSearch}">
            <br>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>
                        <fmt:message key="content.myorders.table.departuredate" bundle="${BundleContent}"></fmt:message>
                    </th>
                    <th>
                        <fmt:message key="content.myorders.table.arrivaldate" bundle="${BundleContent}"></fmt:message>
                    </th>
                    <th>
                        <fmt:message key="content.myorders.table.from" bundle="${BundleContent}"></fmt:message>
                    </th>
                    <th>
                        <fmt:message key="content.myorders.table.to" bundle="${BundleContent}"></fmt:message>
                    </th>
                    <th>
                        <fmt:message key="content.myorders.table.luggage" bundle="${BundleContent}"></fmt:message>
                    </th>
                    <th>
                        <fmt:message key="content.myorders.table.price" bundle="${BundleContent}"></fmt:message>
                    </th>
                    <th>
                        <fmt:message key="content.myorders.table.status" bundle="${BundleContent}"></fmt:message>
                    </th>
                </tr>
                </thead>

                <tbody id="finishedOrdersTable">
                <c:forEach var="ticket" items="${userFinishedTickets}">
                        <form action="/controller" method="post">
                            <input hidden name="command" value="delete-order">
                            <input hidden name="delete-ticket" value="${ticket.id}">

                            <tr>
                                <td>
                                    <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium"
                                                    value="${ticket.flight.departure}"/>
                                </td>
                                <td>
                                    <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium"
                                                    value="${ticket.flight.arrival}"/>
                                </td>
                                <td>${ticket.flight.from_airport.name}</td>
                                <td>${ticket.flight.to_airport.name}</td>
<%--                                <td>${ticket.luggage}</td>--%>
                                <td>${ticket.flight.price}</td>
                                <td>${ticket.confirmed}</td>
                            </tr>

                        </form>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file="parts/footer.jsp" %>

<script>
    $(document).ready(function () {
        $("#searchOrder").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#myTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
    $(document).ready(function () {
        $("#searchFinishedOrder").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#finishedOrdersTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>

</body>
</html>
