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
            <div ALIGN="center" id="course-content-image">
                <img src="img/${course.imgSrc}"  height="200">
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
                    <a href="enrollCourse?courseID=${course.id}" class="button" id="button">Enroll in this course</a>
                </div>
            </c:if>
            <c:if test="${enrolled == true}">
                <div class="course-button info">
                    <a href="disenrollCourse?courseID=${course.id}" class="button" id="button">Disenroll from this course</a>
                </div>
            </c:if>

        </div>
    </div>

    <div class="content-header">
        Chapters in this course
    </div>
    <div class="course">
        <div id="course-content">
            <c:if test="${enrolled == true}">

                <c:forEach var="chapter" items="${chapters}" >
                    <a href="viewChapter?id=${chapter.id}">
                        <div class="button full-width">
                            ${chapter.chapterName}
                        </div>
                    </a>
                    <br>
                </c:forEach>
            </c:if>

                <c:if test="${enrolled != true}">
                    <c:forEach var="chapter" items="${chapters}" end="0" >
                    <a href="viewChapter?id=${chapter.id}">
                        <div class="button full-width">
                            ${chapter.chapterName}
                        </div>
                    </a>
                    <br>
                </c:forEach>
            </c:if>
            
        </div>
    </div>

    <div class="content-header">
        Tests in this course
    </div>
    <div class="course">
        <div id="course-content">
            <c:forEach var="test" items="${tests}">
                <a href="makeTest?id=${test.id}">
                    <div class="button full-width">
                        ${test.title}
                    </div>
                </a>
                <br>
            </c:forEach>
        </div>
    </div>

    <!--Alleen aan studenten/leraren laten zien-->
    <% if (user != null) {%>
    <div class="content-header">
        Livestreams
    </div>
    <div class="course">
        <div id="course-content">
            <a href="livestreamDetail?id=${course.id}">
                <div class="button full-width">
                    Go to page
                </div>
            </a>
        </div>
    </div>
    <% }%>

</c:if>

<%@include file="/includes/footer.jsp" %>