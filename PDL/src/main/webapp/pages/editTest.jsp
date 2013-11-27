<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Edit Test
</div>

<div class="course">
    <div id="course-content">
        <c:if test="${success != null && success}">
            <div class="success_message">
                U have successfully created a test      
            </div>
        </c:if>
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
        <form method="post" action="editTest">
            <label class="label">
                <span class="form-span">Title:</span>
                <input name="title" type="text" placeholder="Title" value="${test.title}" class="form-input" required>
            </label>
            <label class="label">
                <span class="form-span">Description:</span>
                <textarea name="description" placeholder="Description" class="form-input-textarea" required>${test.description}</textarea>
            </label>
            <label class="label">
                <span class="form-span">Course:</span>
                <select class="form-input-select" name="course_id" required>
                    <!-- Courses ophalen en in select zetten -->
                    <option value="">Select course...</option>
                    <option value="1">HTML 5</option>
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
                <input name="question_amount" type="text" placeholder="10" value="${test.amount_of_questions}" class="form-input" required>
            </label>
            <label class="label">
                <span class="form-span">Time (minutes):</span>
                <input name="time" type="text" placeholder="45" value="${test.time}" class="form-input" required>
            </label>
            <label class="label">
                <span class="form-span">Start date:</span>
                <input name="start_date" type="text" placeholder="dd-mm-yyyy" value="${test.start_date}" class="form-input" required>
            </label>
            <label class="label">
                <span class="form-span">End date:</span>
                <input name="end_date" type="text" placeholder="dd-mm-yyy" value="${test.end_date}" class="form-input" required>
            </label>
            
            <input type="submit" class="button" id="button">
        </form>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>