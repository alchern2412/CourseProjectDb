<%--
  Created by IntelliJ IDEA.
  User: Alexey Chernyavsky
  Date: 10/8/2019
  Time: 2:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <%@ include file="parts/meta.jsp" %>
    <link rel="stylesheet"
          href="https://www.jqueryscript.net/demo/Date-Time-Picker-Bootstrap-4/build/css/bootstrap-datetimepicker.min.css">
    <title>Aircompany</title>

</head>
<body>
<%@ include file="parts/header.jsp" %>

<div>
    <div class="row">
        <div class="col">

        </div>
        <div class="col-6">
            <form action="/controller" method="get" name="findFlight">
                <input type="hidden" name="command" value="find-flight">

                <div class="row">
                    <div class="col">
                        <label for="sel-place-from">
                            <fmt:message key="content.main.label.from"
                                                                 bundle="${BundleContent}"></fmt:message>
                            :
                        </label>
                        <select name="from-place" class="form-control" id="sel-place-from">
                            <c:forEach var="place" items="${sessionScope.allPlaces}">
                                <option value="${place.id}"><c:out value="${place.name}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col">

                        <label for="sel-place-to"><fmt:message key="content.main.label.to"
                                                               bundle="${BundleContent}"></fmt:message>:</label>
                        <select name="to-place" class="form-control" id="sel-place-to">
                            <c:forEach var="place" items="${sessionScope.allPlaces}">
                                <option value="${place.id}"><c:out value="${place.name}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <br/>
                <div class="row">
                    <div class="col">
                        <input type='text' name="departure-date" class="form-control" id='datePickerFrom'/>
                    </div>
                </div>

                <button class="btn btn-info btn-block my-4" type="submit">
                    <fmt:message key="content.main.label.search" bundle="${BundleContent}"></fmt:message>
                </button>
            </form>
        </div>

        <div class="col ">

        </div>
    </div>
</div>

<%@ include file="parts/footer.jsp" %>

<script src="https://www.jqueryscript.net/demo/Date-Time-Picker-Bootstrap-4/build/js/bootstrap-datetimepicker.min.js"></script>
<script>
    $(function () {
        $('#datePickerFrom').datetimepicker();
    });
</script>
</body>
</html>
