<%@include file="/includes/header.jsp" %> 

<c:if test="${show == null}">
    <div class="course">
        <div id="course-content">
            <c:forEach var="error" items="${errors}">
                <div class="error_message">
                    ${error}
                </div>
            </c:forEach>
        </div>
    </div>
</c:if>

<c:if test="${show != null}">

    <div class="content-header">
        Welcome to: ${course.name}!
    </div>

    <div class="course">
        <div id="course-content">
            <div id="course-content-image">
                <img src="http://blog.ecollegefinder.org/wp-content/uploads/2012/01/101474454.jpg">
            </div>
            <div id="course-content-description">
                <b>Course description:</b><br>
                ${course.description}
            </div>
            <c:if test="${logged_in == false}">
                <div class="course-button info">
                    <a href="login" class="button" id="button">Log in to enroll</a>
                </div>
            </c:if>
            <c:if test="${enrolled == false}">
                <div class="course-button info">
                    <a href="enrollCourse" class="button" id="button">Enroll in this course</a>
                </div>
            </c:if>
            <c:if test="${enrolled == true}">
                <div class="course-button info">
                    <a href="disenrollCourse" class="button" id="button">Disenroll from this course</a>
                </div>
            </c:if>

        </div>
    </div>

    <div class="content-header">
        Chapters in this course
    </div>

    <div class="course">
        <div id="course-content">
            <c:forEach var="chapter" items="${chapters}">
                <a href="viewChapter?id=${chapter.id}">
                    <div class="button full-width">
                        ${chapter.title}
                    </div>
                </a>
                <br>
            </c:forEach>
        </div>
    </div>

    <div class="content-header">
        Tests in this course
    </div>

    <div class="course">
        <div id="course-content">
            <c:forEach var="test" items="${tests}">
                <a href=makeTest?id=${test.id}">
                    <div class="button full-width">
                        ${test.title}
                    </div>
                </a>
                <br>
            </c:forEach>
        </div>
    </div>

</c:if>

<%@include file="/includes/footer.jsp" %>