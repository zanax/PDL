<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Page not available
</div>

<div class="course">
    <div id="course-content">
        The page you requested was not available due to the following reason(s):
        <c:forEach var="error" items="${errors}">
            <div class="error_message">
                ${error}
            </div>
        </c:forEach>
    </div>
</div>
<%@include file="/includes/footer.jsp" %>