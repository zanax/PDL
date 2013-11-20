<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Course Config
</div>

<div class="course">
    <div id="course-content" style="text-align: center;">
        <form method="post" action="">
            <div class="course-button info" style="left: 550px;">
                <a href="CourseEditTeacher.jsp" class="button" id="button">Teacher</a>
            </div>
            <div class="course-button info" style="left: 550px;">
                <a href="CourseEditTests.jsp" class="button" id="button">Test</a>
            </div>
            <div class="course-button info" style="left: 550px;">
                <a href="CourseEditStudents.jsp" class="button" id="button">Students</a>
            </div>
            <div class="course-button info" style="left: 550px;">
                <a href="CourseEditChapters.jsp" class="button" id="button">Chapters</a>
            </div>
            <div class="course-button info" style="left: 550px;">
                <a href="CourseEditConfig.jsp" class="button" id="button">Config</a>
            </div>
            <div class="course-button info" style="left: 550px;">
                <a class="button" id="button">Start Course</a>
            </div>
        </form>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>