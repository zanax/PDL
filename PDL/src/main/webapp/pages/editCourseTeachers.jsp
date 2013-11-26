<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Edit Course - Teachers
</div>

<div class="course">
    <div id="course-content">
        <form method="post" action="editCourseTeachers">
            <c:forEach var="teacher" items="${course.teachers}">
                <label class="label">
                    <span class="form-span">${teacher.name}</span>
                    <input name="removeTeacher" type="submit" value="Remove Teacher" class="form-input">
                    <!-- hoe stuur ik de chapterID mee naar de post zodat ie weggehaald wordt-->
                </label>
            </c:forEach>
            <a class="button" id="button">Add Teacher</a>
        </form>
        <label class="label">
            <a href="editCourse?id=${course.id}" class="button" id="button">Back</a>
        </label>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>
