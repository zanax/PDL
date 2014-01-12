<%@include file="/includes/header.jsp" %>

<div class="content-header">
    Disenroll Course
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

<c:if test="${success != null && success}">
    <div class="course">
        <div id="course-content">
            <div class="success_message">
                U have successfully disenrolled the course.
            </div>
            <a href="courseDetails?id=${courseID}" class="button cancel" id="button">Go To Course</a>
        </div>
    </div>
</c:if>

<c:if test="${show != null && show}">
    <div class="course">
        <div id="course-content">
            <form method="post" action="disenrollCourse">
                <label class="label">
                    <input name="courseID" type="hidden" value="${course.id}">
                    <span class="form-span">Course:</span>
                    ${course.name}
                </label>
                <label class="label">
                    <input type="submit" value="Disenroll Course" class="button" id="button">
                </label>
                <label class="label">
                    <a href="courseDetails?id=${course.id}" class="button cancel" id="button">Cancel</a>
                </label>
            </form>
        </div>
    </div>   
</c:if>

<%@include file="/includes/footer.jsp" %>