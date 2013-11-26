<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Edit Course - Chapters
</div>

<div class="course">
    <div id="course-content">
        <form method="post" action="editCourseChapters">
            <c:forEach var="chapter" items="${course.chapters}">
                <label class="label">
                    <span class="form-span">${chapter.name}</span>
                    <a href="editCourseChaptersEdit?id=${chapter.id}" class="button" id="button">Edit Chapter</a>
                    <input name="removeChapter" type="submit" value="Remove Chapter" class="form-input">
                    <!-- hoe stuur ik de chapterID mee naar de post zodat ie weggehaald wordt-->
                </label>
            </c:forEach>
        </form>
        <label class="label">
            <a href="editCourseChapterCreate?id=${course.id}" class="button" id="button">Create Chapter</a>
        </label>
        <label class="label">
            <a href="editCourse?id=${course.id}" class="button" id="button">Back</a>
        </label>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>
