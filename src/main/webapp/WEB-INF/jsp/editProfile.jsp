<%--
  Created by IntelliJ IDEA.
  User: Alexey Chernyavsky
  Date: 10/10/2019
  Time: 11:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Edit Profile</title>
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
            <input type="hidden" name="command" value="edit-profile">


            <p class="h4 mb-4">EditProfile</p>

            <div class="form-row mb-4">
                <div class="col">
                    <!-- First name -->
                    <input type="text" class="form-control" name="first-name" placeholder="First name"
                           value="${user.userData.firstName}">
                </div>
                <div class="col">
                    <!-- Second name -->
                    <input type="text" class="form-control" name="last-name" placeholder="Last name"
                           value="${user.userData.lastName}">
                </div>
            </div>

            <!-- E-mail - login -->
            <input type="email" class="form-control mb-4" name="login" placeholder="E-mail" value="${user.login}">

            <!-- Password -->
            <input type="password" class="form-control" name="password" placeholder="Password" value="${user.password}"
                   aria-describedby="defaultRegisterFormPasswordHelpBlock">
            <small id="defaultRegisterFormPasswordHelpBlock" class="form-text text-muted mb-4">
                At least 8 characters and 1 digit
            </small>

            <!-- Phone number -->
            <input type="text" name="phone-number" value="${user.userData.phoneNumber}"
                   id="defaultRegisterPhonePassword"
                   class="form-control" placeholder="Phone number"
                   aria-describedby="defaultRegisterFormPhoneHelpBlock">
            <small id="defaultRegisterFormPhoneHelpBlock" class="form-text text-muted mb-4">
                Optional - for two step authentication
            </small>

            <div class="form-row mb-4">
                <div class="col">
                    <!-- Document number -->
                    <input type="text" class="form-control" name="document-number"
                           value="${user.userData.documentNumber}"
                           placeholder="Document number">
                </div>
                <div class="col">
                    <!-- Validity Period -->
                    <input type="date" class="form-control" name="validity-period"
                           value="${user.userData.validityPeriod}"
                           placeholder="Validity period">
                </div>
            </div>
            <div class="form-row mb-4">
                <div class="col">
                    <!-- Country -->
                    <input type="text" class="form-control" name="country" value="${user.userData.country}"
                           placeholder="Country">
                </div>
                <div class="col">
                    <!-- Address -->
                    <input type="text" class="form-control" name="address" value="${user.userData.address}"
                           placeholder="address">
                </div>
            </div>

            <!-- Newsletter -->
            <%--    <div class="custom-control custom-checkbox">--%>
            <%--        <input type="checkbox" class="custom-control-input" id="defaultRegisterFormNewsletter">--%>
            <%--        <label class="custom-control-label" for="defaultRegisterFormNewsletter">Subscribe to our newsletter</label>--%>
            <%--    </div>--%>

            <!-- Sign up button -->
            <button class="btn btn-info my-4 btn-block" type="submit">Save</button>

            <hr>

            <!-- Terms of service -->
            <p>By clicking
                <em>Sign up</em> you agree to our
                <a href="" target="_blank">terms of service</a>

        </form>
        <!-- Default form register -->

    </div>
    <div class="col">

    </div>
</div>

<%@ include file="parts/footer.jsp" %>

</body>
</html>
