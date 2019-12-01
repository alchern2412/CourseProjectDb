<%--
  Created by IntelliJ IDEA.
  User: Alexey Chernyavsky
  Date: 10/8/2019
  Time: 3:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"%>
<html>
<head>
    <title>Login</title>
    <%@ include file="parts/meta.jsp" %>
</head>
<body>
<%@ include file="parts/header.jsp" %>

<div class="row">
    <div class="col">
    </div>

    <div class="col-6">

        <!-- Default form login -->
        <form class="text-center border border-light p-5" name="LoginForm" action="/controller" method="post">
            <input type="hidden" name="command" value="login">

            <p class="h4 mb-4">
                <fmt:message key="content.login.button.signin" bundle="${BundleContent}"></fmt:message>
            </p>
            <!-- Email -->
            <fmt:message key="content.login.placeholder.login" var="placeholderLogin" bundle="${BundleContent}"/>
            <input type="login" name="login" class="form-control mb-4" placeholder="${placeholderLogin}">
            <!-- Password -->
            <fmt:message key="content.login.placeholder.password" var="placeholderPassword" bundle="${BundleContent}" />
            <input type="password" name="password" class="form-control mb-4" placeholder="${placeholderPassword}">

            <!-- Sign in button -->
            <button class="btn btn-info btn-block my-4" type="submit">
                <fmt:message key="content.login.button.signin" bundle="${BundleContent}" />
            </button>

            <!-- Register -->
            <p>
                <fmt:message key="content.login.label.notamember" bundle="${BundleContent}" />
                <a href="/register">
                    <fmt:message key="content.login.a.register" bundle="${BundleContent}" />
                </a>
            </p>
        </form>

    </div>
    <div class="col">
    </div>
</div>
<%@ include file="parts/footer.jsp" %>
</body>
</html>
