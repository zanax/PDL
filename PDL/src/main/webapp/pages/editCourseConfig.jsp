<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Edit Course - Config
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
        <form method="post" action="editCourseConfig">
            <label class="label">
                <span class="form-span">Name:</span>
                <input name="name" type="text" placeholder="Name" value="${course.name}" class="form-input" required>
            </label>
            <label class="label">
                <span class="form-span">Maximum Students:</span>
                <input name="maximumStudents" type="text" placeholder="0" value="${course.maximumStudents}" class="form-input" required>
            </label>
            <label class="label">
                <span class="form-span">Description:</span>
                <textarea name="description" placeholder="Description" class="form-input-textarea" required>${course.description}</textarea>
            </label>
            <label class="label">
                <span class="form-span">Category:</span>
                <select class="form-input-select" name="category" required>
                    <!-- TODO ${course.category} Set selected if category != null -->
                    <option value="">Select category...</option>
                    <option value="Art">Art</option>
                    <option value="Geography">Geography</option>
                    <option value="History">History</option>
                    <option value="Languages">Languages</option>
                    <option value="Literacy">Literacy</option>
                    <option value="Music">Music</option>
                    <option value="Mathematics">Mathematics</option>
                    <option value="Science">Science</option>
                </select>
            </label>   
            <input type="submit" class="button" id="button" name="id" value="${id}">
            <a href="editCourse?id=${course.id}" class="button" id="button">Back</a>
        </form>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>
