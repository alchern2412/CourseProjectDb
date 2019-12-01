<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="users" required="true" type="java.util.List<by.belstu.alchern.db.courseproject.model.impl.User>" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en_US'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle var="BundleContent" basename="content"/>

<div class="tab-pane container active" id="orders">
    <div class="container mt-3">
        <h2>
                        <fmt:message key="content.allusers.label.users" bundle="${BundleContent}"></fmt:message>
        </h2>
        <p>
            <br>
        <table class="table table-bordered">
<%--        Header--%>
            <thead>
            <tr>
                <th>
                    <fmt:message key="content.allusers.table.login" bundle="${BundleContent}"></fmt:message>
                </th>
                <th>
                    <fmt:message key="content.allusers.table.name" bundle="${BundleContent}"></fmt:message>
                </th>
                <th>
                    <fmt:message key="content.allusers.table.lastname" bundle="${BundleContent}"></fmt:message>
                </th>
                <th>
                    <fmt:message key="content.allusers.table.role" bundle="${BundleContent}"></fmt:message>
                </th>
                <th>
                    <fmt:message key="content.allusers.table.documentnumber" bundle="${BundleContent}"></fmt:message>
                </th>
                <th>
                    <fmt:message key="content.allusers.table.address" bundle="${BundleContent}"></fmt:message>
                </th>
                <th>
                    <fmt:message key="content.allusers.table.tel" bundle="${BundleContent}"></fmt:message>
                </th>
                <th>
                    <fmt:message key="content.allusers.table.actions" bundle="${BundleContent}"></fmt:message>
                </th>
            </tr>
            </thead>
            <%--Body--%>
            <tbody id="myTable">
            <c:forEach var="curUser" items="${users}">
                <form action="/controller" method="post">
                    <input hidden name="command" value="delete-order">
                    <input hidden name="user-orders" value="${curUser.id}">

                    <tr>
                        <td>${curUser.login}</td>
                        <td>${curUser.firstName}</td>
                        <td>${curUser.lastName}</td>
                        <td>${curUser.role.role}</td>
                        <td>${curUser.documentNumber}</td>
                        <td>${curUser.address}</td>
                        <td>${curUser.tel}</td>
                        <td>
                            <fmt:message key="content.allusers.table.usertickets" bundle="${BundleContent}" var="inputUserTickets"/>
                            <input type="submit" value="${inputUserTickets}">
                        </td>
                    </tr>

                </form>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>