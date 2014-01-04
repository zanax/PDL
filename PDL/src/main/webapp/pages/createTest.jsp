<%@include file="/includes/header.jsp" %> 

<form method="post" action="createTest">


    <div class="content-header">
        Create Test
    </div>

    <div class="course">
        <div id="course-content">
            <c:forEach var="error" items="${errors}">
                <div class="error_message">
                    ${error}
                </div>
            </c:forEach>
            <c:if test="${databaseError != null && databaseError}">
                <div class="error_message">
                    Something went wrong, Please try again.
                </div>
            </c:if>
            <label class="label">
                <span class="form-span">Title:</span>
                <input name="title" type="text" placeholder="Title" value="${title}" class="form-input" required>
            </label>
            <label class="label">
                <span class="form-span">Description:</span>
                <textarea name="description" placeholder="Description" class="form-input-textarea" required>${description}</textarea>
            </label>
            <label class="label">
                <span class="form-span">Course:</span>
                <select class="form-input-select" name="course_id" required>
                    <!-- Courses ophalen en in select zetten -->
                    <option value="">Select course...</option>
                    <c:forEach items="${courses}" var="course">
                        <option value="${course.id}" <c:if test="${course_id != null && course_id == course.id}">selected</c:if>>${course.name}</option>
                    </c:forEach>
                </select>
            </label>
            <label class="label">
                <span class="form-span">Chapter:</span>
                <select class="form-input-select" name="chapter_id" required>
                    <!-- Chapters ophalen en in select zetten -->
                    <option value="0">None</option>
                    <option value="1">Chapter 1</option>
                </select>
            </label>
            <label class="label">
                <span class="form-span">Amount of questions:</span>
                <input name="question_amount" type="text" placeholder="10" value="${question_amount}" class="form-input" required>
            </label>
            <label class="label">
                <span class="form-span">Time (minutes):</span>
                <input name="time" type="text" placeholder="45" value="${time}" class="form-input" required>
            </label>
            <label class="label">
                <span class="form-span">Start date:</span>
                <input name="start_date" type="text" placeholder="dd-mm-yyyy" value="${start_date}" class="form-input" required>
            </label>
            <label class="label">
                <span class="form-span">End date:</span>
                <input name="end_date" type="text" placeholder="dd-mm-yyy" value="${end_date}" class="form-input" required>
            </label>
            <label class="label">
                <span class="form-span">Language:</span>
                <select class="form-input-select" name="language_id" required>
                    <option value="0" <c:if test="${language == 0}">selected</c:if>>English (UK)</option>
                    <option value="1" <c:if test="${language == 1}">selected</c:if>>Nederlands</option>
                </select>
            </label>
        </div>
    </div>

    <div class="course">
        <div id="course-content">
            <input type="submit" class="button" id="button" value="Submit">
            <a href="teacherPanel" class="button" id="button">Cancel</a>
        </div>        
    </div>

</form>


<%@include file="/includes/footer.jsp" %>