<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Edit Course - Chapters
</div>

<div class="course">
    <div id="course-content" style="text-align: center;">
        <form method="post" action="">
            <div id="course-content-title">
                <h4>Chapters</h4>
            </div>

            <!-- TABLE with Teachers that are in the course-->

            <div class="course-button info" style="left: 550px;">
                <a class="button" id="button">Add Chapter</a>
            </div>
            <div class="course-button info" style="left: 550px;">
                <a class="button" id="button">Edit Chapter</a>
            </div>
            <div class="course-button info" style="left: 550px;">
                <a class="button" id="button">Remove Chapter</a>
            </div>
            <div class="course-button info" style="left: 550px;">
                <a href="editCourse?id=${id}" class="button" id="button">Back</a>
            </div>
        </form>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>
