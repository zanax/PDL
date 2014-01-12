<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Edit Grade
</div>

<c:if test="${not empty errors}">
    <div class="course">
        <div id="course-content">
            <c:forEach var="error" items="${errors}">
                <div class="error_message">
                    ${error}
                </div>
            </c:forEach>
        </div>
    </div>
</c:if>

<c:if test="${show != null && show}">
    <div class="course">
        <div id="course-content">
            <form method="get" action="editGrade">
                <input type="submit" value="Submit" class="button" id="button"> 
            </form>
        </div>
    </div>
</c:if>

<%@include file="/includes/footer.jsp" %>