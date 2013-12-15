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
        </div>
    </div>
    <div class="course">
        <div id="course-content">

            <a href="enrollCourse">
                <div class="button full-width">
                    Enroll in this course
                </div>
            </a>
            <br>
            <a href="disenrollCourse">
                <div class="button full-width">
                    Disenroll from this course
                </div>
            </a>

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