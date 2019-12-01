<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexey Chernyavsky
  Date: 10/23/2019
  Time: 6:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<html>
<head>
    <%@ include file="parts/meta.jsp" %>
    <style>
        <%@include file="/resources/css/group-list.css" %>
    </style>
    <title>Search Flights</title>

</head>
<body>
<%@ include file="parts/header.jsp" %>

<div class="row">
    <div class="col">

    </div>
    <div class="col-6">

        <form action="/controller" method="post" name="requestOrder">
            <input type="hidden" name="command" value="request-order">

            <p class="h4 mb-4">
                <fmt:message key="content.flightsearch.label.findedflights" bundle="${BundleContent}"></fmt:message>
            </p>

            <div class="list-group">

                <c:forEach var="flight" items="${findedFlights}">
                    <input type="radio" name="selected-flight" value="${flight.id}" id="Radio${flight.id}"/>
                    <label class="list-group-item" for="Radio${flight.id}">
                        <div>
                            <h4 class="list-group-item-heading">
                                <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium"
                                                value="${flight.departureDate}"/>
                            </h4>
                            <p class="list-group-item-text">
                                <fmt:message key="content.flightsearch.label.cost" bundle="${BundleContent}"></fmt:message>
                                : ${flight.priceFlight}</p>
                            <p class="list-group-item-text">
                                <fmt:message key="content.flightsearch.label.luggagecost" bundle="${BundleContent}"></fmt:message>
                                : ${flight.priceLuggage}</p>
                        </div>
                    </label>
                </c:forEach>

            </div>
            <div class="custom-control custom-checkbox">
                <input type="checkbox" value="true" class="custom-control-input" id="customCheck" name="luggage">
                <label class="custom-control-label" for="customCheck">
                    <fmt:message key="content.flightsearch.label.conditions" bundle="${BundleContent}"></fmt:message>
                </label>
            </div>

            <button class="btn btn-info btn-block my-4" type="submit">
                <fmt:message key="content.flightsearch.button.order" bundle="${BundleContent}"></fmt:message>
            </button>
        </form>

    </div>

    <div class="col">

    </div>
</div>
<%@ include file="parts/footer.jsp" %>

</body>
</html>

