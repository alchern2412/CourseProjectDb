<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <link rel="stylesheet"
          href="https://www.jqueryscript.net/demo/Date-Time-Picker-Bootstrap-4/build/css/bootstrap-datetimepicker.min.css">
    <title>
        Edit Flight
    </title>
    <%@ include file="parts/meta.jsp" %>
</head>
<body>

<%@ include file="parts/header.jsp" %>

<div class="row">
    <div class="col">

    </div>
    <div class="col-6">

        <!-- Default form register -->
        <form method="post" class="text-center border border-light p-5" action="/controller">
            <input type="hidden" name="command" value="edit-flight">
            <input hidden name="id-flight" value="${flight.id}">


            <p class="h4 mb-4">
                <fmt:message key="content.editflight.label.editflight" bundle="${BundleContent}"></fmt:message>
            </p>

            <div class="form-row mb-4">
                <div class="col">
                    <!-- From -->
                    <select name="from-place" class="form-control" aria-describedby="fromHelpBlock">
                        <c:forEach var="place" items="${allPlaces}">
                            <c:if test="${place.name eq flight.fromPlace.name}">
                                <option selected value="${place.id}"><c:out value="${place.name}"/></option>
                            </c:if>
                            <c:if test="${place.name ne flight.fromPlace.name}">
                                <option value="${place.id}"><c:out value="${place.name}"/></option>
                            </c:if>
                        </c:forEach>
                    </select>

                    <small id="fromHelpBlock" class="form-text text-muted mb-4">
                        <fmt:message key="content.createflight.label.from" bundle="${BundleContent}"/>
                    </small>
                </div>
                <div class="col">
                    <!-- To -->
                    <select name="to-place" aria-describedby="toHelpBlock" class="form-control">
                        <c:forEach var="place" items="${sessionScope.allPlaces}">
                            <c:if test="${place.name eq flight.toPlace.name}">
                                <option selected value="${place.id}"><c:out value="${place.name}"/></option>
                            </c:if>
                            <c:if test="${place.name ne flight.toPlace.name}">
                                <option value="${place.id}"><c:out value="${place.name}"/></option>
                            </c:if>
                        </c:forEach>
                    </select>
                    <small id="toHelpBlock" class="form-text text-muted mb-4">
                        <fmt:message key="content.createflight.label.to" bundle="${BundleContent}"/>
                    </small>
                </div>
            </div>
            <div class="form-row mb-4">
                <div class="col">
                    <!-- Departure Date -->
                    <fmt:formatDate pattern="MM/dd/yyyy hh:mm a" type="both" dateStyle="medium" timeStyle="medium"
                                    value="${flight.departureDate}" var="varDepartureDate"/>
                    <input type='text' name="departure-date" value="${varDepartureDate}"
                           aria-describedby="departureDateHelpBlock" class="form-control" id='datePickerDeparture'/>
                    <%--                    <input type="text" class="form-control" aria-describedby="departureDateHelpBlock" name="departure-date">--%>
                    <small id="departureDateHelpBlock" class="form-text text-muted mb-4">
                        <fmt:message key="content.createflight.label.departuredate" bundle="${BundleContent}"/>
                    </small>
                </div>
                <div class="col">
                    <!-- Arrival Date -->
                    <fmt:formatDate pattern="MM/dd/yyyy hh:mm a" type="both" dateStyle="medium" timeStyle="medium"
                                    value="${flight.arrivalDate}" var="varArrivalDate"/>
                    <input type='text' name="arrival-date" value="${varArrivalDate}"
                           aria-describedby="arrivalDateHelpBlock" class="form-control" id='datePickerArrival'/>

                    <%--                    <input type="text" class="form-control" aria-describedby="arrivalDateHelpBlock" name="arrival-date">--%>
                    <small id="arrivalDateHelpBlock" class="form-text text-muted mb-4">
                        <fmt:message key="content.createflight.label.arrivaldate" bundle="${BundleContent}"/>
                    </small>
                </div>
            </div>

            <div class="form-row mb-4">
                <div class="col">
                    <!-- Passengers -->
                    <fmt:message key="content.createflight.label.passengers" var="placeholderPassengers"
                                 bundle="${BundleContent}"/>
                    <input value="${flight.passengers}" type="number" class="form-control" name="passengers"
                           placeholder="${placeholderPassengers}">
                </div>
                <div class="col">
                    <!-- All Passengers -->
                    <fmt:message key="content.createflight.label.allpassengers" var="placeholderAllPassengers"
                                 bundle="${BundleContent}"/>
                    <input type="number" value="${flight.allPassengers}" class="form-control" name="all-passengers"
                           placeholder="${placeholderAllPassengers}">
                </div>
            </div>

            <div class="form-row mb-4">
                <div class="col">
                    <!-- Price Flight -->
                    <fmt:message key="content.createflight.label.priceflight" var="placeholderPriceFlight"
                                 bundle="${BundleContent}"/>
                    <input type="number" value="${flight.priceFlight}" step="0.01" class="form-control"
                           name="price-flight" placeholder="${placeholderPriceFlight}">
                </div>
                <div class="col">
                    <!-- Price Luggage -->
                    <fmt:message key="content.createflight.label.priceluggage" var="placeholderPriceLuggage"
                                 bundle="${BundleContent}"/>
                    <input type="number" value="${flight.priceLuggage}" step="0.01" class="form-control"
                           name="price-luggage" placeholder="${placeholderPriceLuggage}">
                </div>
            </div>


            <!-- Description -->
            <fmt:message key="content.createflight.label.description" var="placeholderDescription"
                         bundle="${BundleContent}"/>
            <input type="text" value="${flight.description}" class="form-control mb-4" name="description"
                   placeholder="${placeholderDescription}">

            <button class="btn btn-info my-4 btn-block" type="submit">
                <fmt:message key="content.editflight.button.editflight" bundle="${BundleContent}"></fmt:message>
            </button>

            <hr>

        </form>
        <!-- Default form register -->

    </div>
    <div class="col">

    </div>
</div>


<%@ include file="parts/footer.jsp" %>

<script src="https://www.jqueryscript.net/demo/Date-Time-Picker-Bootstrap-4/build/js/bootstrap-datetimepicker.min.js"></script>
<script>
    $(function () {
        $('#datePickerDeparture').datetimepicker();
    });
    $(function () {
        $('#datePickerArrival').datetimepicker();
    });
</script>

</body>
</html>
