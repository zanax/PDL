<%@include file="/includes/header.jsp" %>       
<div class="content-header">
    Course Catalog
</div>

<c:forEach var="course" items="${courses}">
    <div class="course">
        <div id="course-content">

            <div id="course-content-image">
                <img src="http://blog.ecollegefinder.org/wp-content/uploads/2012/01/101474454.jpg"></a>
            </div>
            <div id="course-content-title">
                <h4>${course.name}</h4>
            </div>
            <div id="course-content-description">
                ${course.description}
            </div>
            <div class="course-button info">
                <a href="courseDetails?id=${course.id}" class="button" id="button">More info</a>
            </div>
        </div>
    </div>
</c:forEach>

<%@include file="/includes/footer.jsp" %>