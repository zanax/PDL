<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Create Course
</div>

<div class="course">
    <div id="course-content">
        <c:if test="${success != null && success}">
            <a href="editCourse?id=${id}" >
                <div class="success_message">
                    U have successfully created the Course '${name}'. Go to Course      
                </div>
            </a>
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
        <form method="post" action="createCourse">
            <label class="label">
                <span class="form-span">Name:</span>
                <input name="name" type="text" placeholder="Name" value="${name}" style="border: 1px solid #00B711; border-radius: 5px 5px 5px 5px; height: 25px; text-align: center; width: 215px;">
            </label>
            <label class="label">
                <span class="form-span">Description:</span>
                <input name="description" type="text" placeholder="Description" value="${description}" style="border: 1px solid #00B711; border-radius: 5px 5px 5px 5px; height: 75px; text-align: center; width: 215px;">
            </label>
            <label class="label">
                <span class="form-span">Category:</span>
                <select class="form-input-select" name="category">
                    <!-- TODO ${category} Set selected if category != null -->
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
            <label class="label">
                <input type="submit" class="button" id="button" name="Create" value="Create">
            </label>
        </form>
    </div>
</div>

<!--

TODO: CSS van inputs naar een format

-->

<%@include file="/includes/footer.jsp" %>