<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Edit Course - Tests
</div>

<div class="course">
    <div id="course-content" style="text-align: center;">
        <form method="post" action="">
            <div id="course-content-title">
                <h4>Tests</h4>
            </div>

            <!-- TABLE with Teachers that are in the course-->

            <div class="course-button info" style="left: 100px;">
                <a class="button" id="button">Add Test</a>
            </div>
            <div class="course-button info" style="left: 200px;">
                <a class="button" id="button">Edit Test</a>
            </div>
            <div class="course-button info" style="left: 300px;">
                <a class="button" id="button">Remove Test</a>
            </div>
            <div class="course-button info" style="left: 400px;">
                <a href="editCourse?id=${id}" class="button" id="button">Back</a>
            </div>
        </form>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>
