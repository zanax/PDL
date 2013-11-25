<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Edit Course - Teachers
</div>

<div class="course">
    <div id="course-content" style="text-align: center;">
        <div id="course-content-title">
            <h4>Teachers</h4>
        </div>
        <br>
        <br>
        <br>
        <form method="post" action="">

            <!-- TABLE with Teachers that are in the course-->

            <div class="course-button info" style="left: 15px;">
                <a class="button" id="button">Add Teacher</a>
            </div>
            <div class="course-button info" style="left: 135px;">
                <a href="editCourse?id=${id}" class="button" id="button">Back</a>
            </div>
        </form>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>
