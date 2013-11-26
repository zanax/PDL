<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Edit Course - Students
</div>

<div class="course">
    <div id="course-content">
        <c:forEach var="student" items="${course.students}">
            <label class="label">
                <span class="form-span">${student.name}</span>
            </label>
        </c:forEach>
        <label class="label">
            <a href="editCourse?id=${course.id}" class="button" id="button">Back</a>
        </label>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>
