<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Edit Course
</div>

<div class="course">
    <div id="course-content">
        <c:forEach var="error" items="${errors}">
            <div class="error_message">
                ${error}
            </div>
        </c:forEach>
        <c:if test="${show != null && show}">
            <c:if test="${success != null && success}">
                <a href="myCourses" >
                    <div class="success_message">
                        The course has been successfully edited.     
                    </div>
                </a>
            </c:if>
            <form method="post" action="editCourse">
                <label class="label">
                    <span class="form-span">Name: *</span>
                    <input name="name" type="text" placeholder="Name" value="${course.name}" class="form-input" required>
                </label>
                <label class="label">
                    <span class="form-span">Maximum Students:</span>
                    <input name="maximumStudents" type="text" placeholder="0" value="${course.maximumStudents}" class="form-input">
                </label>
                <label class="label">
                    <span class="form-span">Start Date:</span>
                    <input name="startDate" type="text" placeholder="DD-MM-YYYY" value="${course.startDate}" class="form-input">
                </label>
                <label class="label">
                    <span class="form-span">End Date:</span>
                    <input name="endDate" type="text" placeholder="DD-MM-YYYY" value="${course.endDate}" class="form-input">
                </label>
                <label class="label">
                    <span class="form-span">Description: *</span>
                    <textarea name="description" placeholder="Description" class="form-input-textarea" required>${course.description}</textarea>
                </label>
                <label class="label">
                    <span class="form-span">Category: *</span>
                    <select class="form-input-select" name="category" required>
                        <option value="">Select category...</option>
                        <option value="Art">Art</option>
                        <option value="Geography">Geography</option>
                        <option value="History">History</option>
                        <option value="Languages">Languages</option>
                        <option value="Literacy">Literacy</option>
                        <option value="Music">Music</option>
                        <option value="Mathematics">Mathematics</option>
                        <option value="Science">Science</option>
                        <option value="${course.category}" selected>${course.category}</option>
                        <!-- Tijdelijk totdat wel DB ervoor hebben -->
                    </select>
                </label>
                <!-- <label class="label">
                    <input type="file" name="file" id="file">
                </label> -->
                <input type="hidden" name="id" value="${course.id}">
                <input type="submit" value="Save" class="button" id="button">
            </form>
        </c:if>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>