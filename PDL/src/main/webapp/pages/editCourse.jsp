<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Edit Course
</div>

<div class="course">
    <div id="course-content">
        <c:if test="${databaseError != null && databaseError}">
            <div class="error_message">
                Something went wrong, Please try again.
            </div>
        </c:if>
        <!-- TODO: Dit is gewoon een voorbeeld voor hoe het er ongeveer uitziet, De Css klopt voor geen meter (kwa buttons iig) -->
        <div id="course-content-title">
            <h4>Name: ${course.name}</h4>
            <h5>Description: ${course.description}</h5>
            <h5>Category: ${course.category}</h5>
        </div>
        <label class="label">
            <a href="editCourseTeachers?id=${course.id}" class="button" id="button">Teachers</a>
        </label>
        <label class="label">
            <a href="editCourseTests?id=${course.id}" class="button" id="button">Tests</a>
        </label>
        <label class="label">
            <a href="editCourseStudents?id=${course.id}" class="button" id="button">Students</a>
        </label>
        <label class="label">
            <a href="editCourseChapters?id=${course.id}" class="button" id="button">Chapters</a>
        </label>
        <label class="label">
            <a href="editCourseConfig?id=${course.id}" class="button" id="button">Config</a>
        </label>

    </div>
</div>

<%@include file="/includes/footer.jsp" %>