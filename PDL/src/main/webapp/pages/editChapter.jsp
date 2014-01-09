<%@include file="/includes/header.jsp" %>

<div class="content-header">
    Edit Chapter
</div>

<div class="course">
    <div id="course-content">
        <c:if test="${success != null && success}">
            <div class="success_message">
                The Chapter has been successfully edited.
            </div>
        </c:if>
        <c:if test="${create_success != null && create_success}">
            <div class="success_message">
                The chapter has been successfully created.
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
       <form method="post" action="editChapter">
            <input type="hidden" value="${chapter.id}" name="id">                        
            <label class="label">
                <span class="form-span">Course:</span>
                <select class="form-input-select" name="course_id" required>
                    <option value="">Select course...</option>
                    <c:forEach items="${courses}" var="course">
                        <option value="${course.id}" ${course.id == chapter.course_id ? 'selected' : ''}>${course.name}</option>
                    </c:forEach>
                </select>
            </label>
            <label class="label">
                <span class="form-span">Chapter name:</span>
                <textarea name="chapterName" placeholder="Chapter name" class="form-input-textarea" required>${chapter.chapterName}</textarea>
            </label>
            <label class="label">
                <span class="form-span">Description:</span>
                <textarea name="chapter_description" placeholder="Description" class="form-input-textarea" required>${chapter.chapter_description}</textarea>
            </label>
           <label class="label">
                <span class="form-span">Content:</span>
                <textarea name="chapter_content" placeholder="Content" class="form-input-textarea" required>${chapter.chapter_content}</textarea>
            </label>
            
            <input type="submit" class="button" id="button" value="Save">
        </form>
        </div>
    </div>

<%@include file="/includes/footer.jsp" %>