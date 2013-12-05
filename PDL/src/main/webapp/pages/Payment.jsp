<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Payment
</div>

<div class="course">
    <div id="course-content">
        <c:forEach var="error" items="${errors}">
            <div class="error_message">
                ${error}
            </div>
        </c:forEach>
        <c:if test="${show != null && show}">
            ${id}
            ${paymentMethod}
        </c:if>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>