<%--
  Created by IntelliJ IDEA.
  User: Alexey Chernyavsky
  Date: 10/10/2019
  Time: 11:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Sign Up</title>
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
            <input type="hidden" name="command" value="register">


            <p class="h4 mb-4">
                <fmt:message key="content.register.label.signup" bundle="${BundleContent}"></fmt:message>
            </p>

            <div class="form-row mb-4">
                <div class="col">
                    <!-- First name -->
                    <fmt:message key="content.register.placeholder.firstname" var="placeholderFirstName" bundle="${BundleContent}"/>
                    <input type="text" class="form-control" name="first-name" placeholder="${placeholderFirstName}">
                </div>
                <div class="col">
                    <!-- Last name -->
                    <fmt:message key="content.register.placeholder.lastname" var="placeholderLastName" bundle="${BundleContent}"/>
                    <input type="text" class="form-control" name="last-name" placeholder="${placeholderLastName}">
                </div>
            </div>

            <!-- E-mail - login -->
            <fmt:message key="content.register.placeholder.email" var="placeholderEmail" bundle="${BundleContent}"/>
            <input type="email" class="form-control mb-4" name="login" placeholder="${placeholderEmail}">

            <!-- Password -->
            <fmt:message key="content.register.placeholder.password" var="placeholderPassword" bundle="${BundleContent}"/>
            <input type="password" class="form-control mb-4" name="password" placeholder="${placeholderPassword}"
                   aria-describedby="defaultRegisterFormPasswordHelpBlock">

            <!-- Phone number -->
            <fmt:message key="content.register.placeholder.phone" var="placeholderPhone" bundle="${BundleContent}"/>
            <input type="text" name="phone-number" id="defaultRegisterPhonePassword" class="form-control mb-4"
                   placeholder="${placeholderPhone}"
                   aria-describedby="defaultRegisterFormPhoneHelpBlock">

            <div class="form-row mb-4">
                <div class="col">
                    <!-- Document number -->
                    <fmt:message key="content.register.placeholder.documentnumber" var="placeholderDocumentNumber" bundle="${BundleContent}"/>
                    <input type="text" class="form-control" name="document-number" placeholder="${placeholderDocumentNumber}">
                </div>
<%--                <div class="col">--%>
<%--                    <!-- Validity Period -->--%>
<%--                    <input type="date" class="form-control" name="validity-period" aria-describedby="validityFormHelpBlock">--%>
<%--                    <small id="validityFormHelpBlock" class="form-text text-muted mb-4">--%>
<%--                        <fmt:message key="content.register.small.validityperiod" bundle="${BundleContent}"></fmt:message>--%>
<%--                    </small>--%>
<%--                </div>--%>
            </div>
            <div class="form-row mb-4">
<%--                <div class="col">--%>
<%--                    <!-- Country -->--%>
<%--                    <fmt:message key="content.register.placeholder.country" var="placeholderCountry" bundle="${BundleContent}"/>--%>
<%--                    <input type="text" class="form-control" name="country" placeholder="${placeholderCountry}">--%>
<%--                </div>--%>
                <div class="col">
                    <!-- Address -->
                    <fmt:message key="content.register.placeholder.address" var="placeholderAddress" bundle="${BundleContent}"/>
                    <input type="text" class="form-control" name="address" placeholder="${placeholderAddress}">
                </div>
            </div>

            <button class="btn btn-info my-4 btn-block" type="submit">
                <fmt:message key="content.register.button.signup" bundle="${BundleContent}"></fmt:message>
            </button>

            <hr>

        </form>
        <!-- Default form register -->

    </div>
    <div class="col">

    </div>
</div>


<%@ include file="parts/footer.jsp" %>

</body>
</html>
