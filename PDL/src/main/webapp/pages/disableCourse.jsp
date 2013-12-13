<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Disable Course
</div>
<div class="course">
    <div id="course-content" style="text-align: center;">
        <c:if test="${success != null}">
            <c:if test="${success != false}">
                <div class="success_message">
                    Course is deleted
                </div>
            </c:if>
        </c:if>
        <c:forEach var="error" items="${errors}">
            <div class="error_message">
                ${error}
            </div>
        </c:forEach>
        <form method="post" action="disableCourse">
            <div id="course-content-title">
                <div class="error_message">
                    <div>  
                        Are you sure you want to disable this course: '${course.name}'?      
                    </div>                   
                </div>

                <input type="hidden" name="course_id" placeholder="test id here..." value="${course.id}" required class="form-input" style="text-align: center;">
            </div>
            <div class="course-button info" style="left: 725px;">

                <input type="hidden" name="id" value="${course.id}">
                <input type="submit" value="Disable course" class="button">
            </div>
                </form>
            </div>
    </div>

    <%@include file="/includes/footer.jsp" %>