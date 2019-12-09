<%--
  Created by IntelliJ IDEA.
  User: Alexey Chernyavsky
  Date: 10/10/2019
  Time: 1:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.21.0/moment.min.js" type="text/javascript"></script>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en_US'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle var="BundleContent" basename="content"/>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
        <!-- Brand -->
        <a class="navbar-brand" href="/controller?command=main">Aircompany</a>

        <c:choose>
            <c:when test="${sessionScope.user.role.role == 'admin'}">
                <!-- Admin Links -->
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/controller?command=all-users&page=1">
                            <fmt:message key="content.header.navitem.users"
                                         bundle="${BundleContent}"></fmt:message>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/controller?command=all-flights&page=1">
                            <fmt:message key="content.header.navitem.flights"
                                         bundle="${BundleContent}"></fmt:message>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/controller?command=create-flight">
                            <fmt:message key="content.header.navitem.createflight"
                                         bundle="${BundleContent}"></fmt:message>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/controller?command=all-tickets&page=1">
                            <fmt:message key="content.header.navitem.tickets"
                                         bundle="${BundleContent}"></fmt:message>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/controller?command=track-flights&order=plane">
                            <fmt:message key="content.header.navitem.trackflights" bundle="${BundleContent}"></fmt:message>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/controller?command=export-import-xml">
                            <fmt:message key="content.header.navitem.exportimportxml" bundle="${BundleContent}"></fmt:message>
                        </a>
                    </li>
                </ul>
            </c:when>
            <c:when test="${sessionScope.user.role.role == 'user'}">
                <!-- User Links -->
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/controller?command=user-orders">My Orders</a>
                    </li>
<%--                    Unregister--%>
                    <li class="nav-item">
                        <a class="nav-link" href="/controller?command=track-flights&order=plane">
                            <fmt:message key="content.header.navitem.trackflights" bundle="${BundleContent}"></fmt:message>
                        </a>
                    </li>
                </ul>
            </c:when>
            <c:when test="${sessionScope.user eq null}">
                <ul class="navbar-nav">
                        <%--                    Unregister--%>
                    <li class="nav-item">
                        <a class="nav-link" href="/controller?command=track-flights&order=plane">
                            <fmt:message key="content.header.navitem.trackflights" bundle="${BundleContent}"></fmt:message>
                        </a>
                    </li>
                </ul>
            </c:when>
        </c:choose>
    </div>
    <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
        <ul class="navbar-nav ml-auto">
            <c:choose>
                <c:when test="${empty sessionScope.user}">
                    <li class="nav-item">
                        <a class="nav-link" href="/controller?command=login">
                            <fmt:message key="content.header.navitem.login" bundle="${BundleContent}"></fmt:message>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/controller?command=register">
                            <fmt:message key="content.header.navitem.signup"
                                         bundle="${BundleContent}"></fmt:message>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <a class="nav-link navbar-toggler-right"
                           href="/controller?command=edit-profile">${user.login}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=logout">
                            <fmt:message key="content.header.navitem.logout" bundle="${BundleContent}"></fmt:message>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown"><c:out
                        value="${not empty language ? language : 'en_US'}"/>
                </a>
                <div class="dropdown-menu">
                    <form action="${requestScope['javax.servlet.forward.request_uri']}" hidden id="langEn">
                        <c:forEach var="par" items="${paramValues}">
                            <c:if test="${par.key != 'language'}">
                                <input hidden name="${par.key}" value="${par.value[0]}">
                            </c:if>
                        </c:forEach>
                        <input hidden name="language" value="en_US">
                    </form>
                    <a class="dropdown-item" onclick="document.getElementById('langEn').submit()" href="#">EN</a>

                    <form action="${requestScope['javax.servlet.forward.request_uri']}" hidden id="langRu">
                        <c:forEach var="par" items="${paramValues}">
                            <c:if test="${par.key != 'language'}">
                                <input hidden name="${par.key}" value="${par.value[0]}">
                            </c:if>
                        </c:forEach>
                        <input hidden name="language" value="ru_RU">
                    </form>
                    <a class="dropdown-item" onclick="document.getElementById('langRu').submit()" href="#">RU</a>
                </div>
            </li>

        </ul>
    </div>
</nav>
<c:if test="${not empty sessionScope.successMessage}">
    <div class="alert alert-success alert-dismissible fade show">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <strong>Success!</strong> ${successMessage}
    </div>
    <c:remove var="successMessage" scope="session"/>
</c:if>
<c:if test="${not empty sessionScope.errorMessage}">
    <div class="alert alert-danger alert-dismissible fade show">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <strong>Danger!</strong> ${errorMessage}
    </div>
    <c:remove var="errorMessage" scope="session"/>
</c:if>
