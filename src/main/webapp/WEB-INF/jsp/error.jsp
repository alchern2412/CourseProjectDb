<%--
  Created by IntelliJ IDEA.
  User: Alexey Chernyavsky
  Date: 10/8/2019
  Time: 4:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>
    <%@ include file="parts/meta.jsp" %>
</head>
<body>
<%@ include file="parts/header.jsp" %>

<h2>
    <fmt:message key="content.error.label.oops" bundle="${BundleContent}"></fmt:message>
</h2>

<%--    Request from ${pageContext.errorData.requestURI} is failed--%>
<%--    <br/>--%>
<%--    Servlet name or type: ${pageContext.errorData.servletName}--%>
<%--    <br/>--%>
<%--    Status code: ${pageContext.errorData.statusCode}--%>
<%--    <br/>--%>
<%--    Exception: ${pageContext.errorData.throwable}--%>

<%@ include file="parts/footer.jsp" %>
</body>
</html>
