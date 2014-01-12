<%@include file="/includes/header.jsp" %>
<form method="post" action="enrollCourse">

    <div class="content-header">
        Enroll Course
    </div>

<<<<<<< HEAD
<c:if test="${not empty errors}">
=======
>>>>>>> f3720d4781b5fb8d80d276579e16d1f9927438fb
    <div class="course">
        <div id="course-content">
            <c:forEach var="error" items="${errors}">
                <div class="error_message">
                    ${error}
                </div>
            </c:forEach>
<<<<<<< HEAD
        </div>
    </div>
</c:if>

<c:if test="${success != null && success}">
    <div class="course">
        <div id="course-content">
            <div class="success_message">
                U have successfully enrolled the course.
            </div>
            <a href="courseDetails?id=${courseID}" class="button cancel" id="button">Go To Course</a>
        </div>
    </div>
</c:if>

<c:if test="${show != null && show}">
    <div class="course">
        <div id="course-content">
            <form method="post" action="enrollCourse">
=======
            <c:if test="${success != null && success}">
                <div class="success_message">
                    U have successfully enrolled to the Course.
                </div>
            </c:if>
            <c:if test="${show != null && show}">
>>>>>>> f3720d4781b5fb8d80d276579e16d1f9927438fb
                <label class="label">
                    <input name="courseID" type="hidden" value="${course.id}">
                    <span class="form-span">Course:</span>
                    ${course.name}
                </label>
                <label class="label">
                    <input type="submit" value="Enroll Course" class="button" id="button">
                </label>
                <label class="label">
                    <a href="courseDetails?id=${course.id}" class="button cancel" id="button">Cancel</a>
                </label>
                <label class="label">
                    <span class="form-span">Payment Method: *</span>
                    <select class="form-input-select" name="paymentMethod" required>
                        <option value="">Select Payment Method...</option>
                        <option value="ideal">Ideal</option>
                        <option value="paypal">Paypal</option>
                        <option value="creditcard">Credit Card</option>
                    </select>
                </label>
<<<<<<< HEAD
       
                <input type="submit" value="Enroll Course" class="button" id="button">
                <a href="courseDetails?id=${course.id}" class="button cancel" id="button">Cancel</a>
            </form>
        </div>
    </div>
</c:if>
=======


            </c:if>
        </div>
    </div>
    <c:if test="${show != null && show}">

        <div class="course">
            <div id="course-content">
                <input type="submit" value="Pay for this Course" class="button" id="button">
                <a href="courseDetails?id=${course.id}" class="button cancel" id="button">Cancel</a>
            </div>
        </div>

    </c:if>
    <c:if test="${success != null && success}">

        <div class="course">
            <div id="course-content">
                <a href="courseDetails?id=${course.id}" class="button cancel" id="button"><%= Helper.translateWord(language, "Go to course")%></a>
            </div>
        </div>

    </c:if>

</form>

>>>>>>> f3720d4781b5fb8d80d276579e16d1f9927438fb

<%@include file="/includes/footer.jsp" %>