<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Export/import XML</title>
    <%@ include file="parts/meta.jsp" %>
</head>
<body>

<%@ include file="parts/header.jsp" %>

<form method="get" class="text-center border border-light p-5" action="/controller">
    <input type="hidden" name="command" value="export-xml">

    <p class="h4 mb-4">
        <fmt:message key="content.exportimportxml.label.exportxml" bundle="${BundleContent}"></fmt:message>
    </p>

    <div class="row">
        <input type="textarea" name="export-textarea" value="${sessionScope.xmlExportText}" class="form-control">
    </div>
    <div class="row">
        <button class="btn btn-info my-4 btn-block" type="submit">
            <fmt:message key="content.exportimportxml.button.export"
                         bundle="${BundleContent}"></fmt:message>
        </button>
    </div>
</form>
<form method="post" class="text-center border border-light p-5" action="/controller"  enctype="multipart/form-data">
    <input type="hidden" name="command" value="import-xml">

    <p class="h4 mb-4">
        <fmt:message key="content.exportimportxml.label.importxml" bundle="${BundleContent}"></fmt:message>
    </p>

    <div class="row">
        <input name="xml-import-file" type="file" class="form-control">
    </div>
    <div class="row">
        <button class="btn btn-info my-4 btn-block" type="submit">
            <fmt:message key="content.exportimportxml.button.import"
                         bundle="${BundleContent}"></fmt:message>
        </button>
    </div>
</form>

<%@ include file="parts/footer.jsp" %>

</body>
</html>
