<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Disenroll Course
</div>

<div class="course">
    <div id="course-content">
        <c:forEach var="error" items="${errors}">
            <div class="error_message">
                ${error}
            </div>
        </c:forEach>
        <c:if test="${show != null && show}">
            <c:if test="${success != null && success}">
                <a href="myCourses" >
                    <div class="success_message">
                        U have successfully disenrolled from the Course. Go to My Courses      
                    </div>
                </a>
            </c:if>
            <form method="post" action="disenrollCourse">
                <label class="label">
                    <span class="form-span">Course: *</span>
                    <select class="form-input-select" name="id" required>
                        <option value="">Select Course...</option>
                        <c:forEach var="course" items="${courses}">
                            <option value="${course.id}">${course.name}t</option>
                        </c:forEach>
                    </select>
                </label>
                <label class="label">
                    <span class="form-span">Are you sure?</span>
                    <input type="radio" name="agree" value="agree">
                </label>
                <input type="submit" value="Disenroll" class="button" id="button">
            </form>
        </c:if>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>