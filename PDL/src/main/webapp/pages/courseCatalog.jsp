<%@include file="/includes/header.jsp" %>       
<div class="content-header">
    Course Catalog
</div>

<c:forEach var="course" items="${courses}">
    <div class="course">
        <div id="course-content">

            <div ALIGN="center" id="course-content-image">
                <img src="img/${course.imgSrc}" height="200">
            </div>
            <div id="course-content-title">
                <h4>${course.name}</h4>
            </div>
            <div id="course-content-description">
                ${course.description} <br/>
                Subscriptions: ${course.numberOfStudents}
            </div>
            <div class="course-button info">
                <a href="courseDetails?id=${course.id}" class="button" id="button"><%= Helper.translateWord(language, "More info")%></a>
            </div>
        </div>
    </div>
</c:forEach>

<%@include file="/includes/footer.jsp" %>