<%@include file="/includes/header.jsp" %>

<div class="content-header">
    Enroll Course
</div>

<div class="course">
    <div id="course-content">
        <c:forEach var="error" items="${errors}">
            <div class="error_message">
                ${error}
            </div>
        </c:forEach>
        <c:if test="${success != null && success}">
            <div class="success_message">
                U have successfully enrolled to the Course.
            </div>
        </c:if>
        <c:if test="${show != null && show}">
            <form method="post" action="enrollCourse">
                <label class="label">
                    <span class="form-span">Course: *</span>
                    <select class="form-input-select" name="id" required>
                        <option value="">Select Course...</option>
                        <c:forEach var="course" items="${courses}">
                            <option value="${course.id}">${course.name}</option>
                        </c:forEach>
                    </select>
                </label>
                <input type="submit" value="Enroll Course" class="button" id="button">
                <a href="courseDetails?id=${course.id}" class="button cancel" id="button">Cancel</a>
            </form>
        </c:if>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>