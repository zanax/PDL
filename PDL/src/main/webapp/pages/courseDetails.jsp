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
            <div class="course-button info">
                <a href="enrollCourse" class="button" id="button">Enroll in this course</a>
            </div>
            <div class="course-button info">
                <a href="disenrollCourse" class="button" id="button">Disenroll from course</a>
            </div>
        </div>
    </div>

    <div class="content-header">
        Chapters in this course
    </div>

    <div class="course">
        <div id="course-content">
            <label class="label">
                <span class="form-span"><b>Select a chapter: </b></span>
                <select class="form-input-select" name="id" required>
                    <c:forEach var="chapter" items="${chapters}">
                        <option value="${chapter.title}">${chapter.title}</option>
                    </c:forEach>
                </select>
            </label>
            <div class="course-button info">
                <a href="" class="button" id="button">Show chapter</a>
            </div>
        </div>
    </div>

    <div class="content-header">
        Tests in this course
    </div>

    <div class="course">
        <div id="course-content">
            <label class="label">
                <span class="form-span"><b>Select a test: </b></span>
                <select class="form-input-select" name="id" required>
                    <c:forEach var="test" items="${tests}">
                        <option value="${test.title}">${test.title}</option>
                    </c:forEach>
                </select>
            </label>
            <div class="course-button info">
                <a href="" class="button" id="button">Show test</a>
            </div>
        </div>
    </div>

</c:if>

<%@include file="/includes/footer.jsp" %>