<%@include file="/includes/header.jsp" %>
<div class="content-header">
    Found courses
</div>

<c:forEach var="error" items="${errors}">
    <div class="error_message">
        ${error}
    </div>
</c:forEach>

<c:forEach var="course" items="${courses}">
    <div class="course">
        <div id="course-content">

            <div ALIGN="center" id="course-content-image">
                <img src="img/${course.imgSrc}" height="200">
            </div>
            <div id="course-content-title">
                <h4><a href="course_economics.html">${course.name}</a></h4>
            </div>
            <div id="course-content-description">
                ${course.description}
            </div>
            <div class="course-button info">
                <a href="courseDetails?id=${course.id}" class="button" id="button">Go to course</a>
            </div>

        </div>
    </div>
</c:forEach>

<%@include file="/includes/footer.jsp" %>