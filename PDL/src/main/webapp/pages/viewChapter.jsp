<%@include file="/includes/header.jsp" %> 


<c:if test="${show == null}">
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

<c:if test="${show != null}">

    <div class="content-header">
        ${chapter.title}
    </div>

    <div class="course">
        <div id="course-content">
            ${chapter.description}           
        </div>
    </div>

</c:if>

<%@include file="/includes/footer.jsp" %>