<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Edit Course - Students
</div>

<div class="course">
    <div id="course-content" style="text-align: center;">
        <form method="post" action="">
            <div id="course-content-title">
                <h4>Students</h4>
            </div>
            
            <!-- TABLE with Students that are in the course-->

            <div class="course-button info" style="left: 100px;">
                <a href="editCourse?id=${id}" class="button" id="button">Back</a>
            </div>
        </form>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>
