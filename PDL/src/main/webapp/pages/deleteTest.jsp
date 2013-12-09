<%@include file="/includes/header.jsp" %>       

<div class="content-header">
    Delete Test
</div>
<div class="course">
    <div id="course-content" style="text-align: center;">
        <c:if test="${success != null}">
            <c:if test="${success != false}">
                <div class="success_message">
                    Test is deleted
                </div>
            </c:if>
        </c:if>
        <c:forEach var="error" items="${errors}">
            <div class="error_message">
                ${error}
            </div>
        </c:forEach>
        <form method="post" action="deleteTest">
            <div id="course-content-title">
                <div class="error_message">
                        Are you sure you want to delete this test: '${test.title}'?      
                    </div>
                
                <input type="hidden" name="test_id" placeholder="test id here..." value="${test.id}" required class="form-input" style="text-align: center;">
            </div>
            <div class="course-button info" style="left: 725px;">

                <input type="hidden" name="id" value="${test.id}">
                <input type="submit" value="Delete test" class="button">
            </div><br/><br/><br/><br/>

        </form>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>