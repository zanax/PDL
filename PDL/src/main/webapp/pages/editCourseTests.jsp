<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Edit Course - Tests
</div>

<div class="course">
    <div id="course-content">
        <form method="post" action="editCourseTests">
            <c:forEach var="test" items="${course.tests}">
                <label class="label">
                    <span class="form-span">${test.name}</span>
                    <a href="editCourseTestEdit?id=${test.id}" class="button" id="button">Edit Test</a>
                    <input name="removeTest" type="submit" value="Remove Test" class="form-input">
                    <!-- hoe stuur ik de chapterID mee naar de post zodat ie weggehaald wordt-->
                </label>
            </c:forEach>
        </form>
        <label class="label">
            <a href="editCourseTestsCreate?id=${course.id}" class="button" id="button">Create Test</a>
        </label>
        <label class="label">
            <a href="editCourse?id=${course.id}" class="button" id="button">Back</a>
        </label>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>
