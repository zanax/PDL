<%@include file="/includes/header.jsp" %>
<form method="post" action="enrollCourse">

    <div class="content-header">
        Enroll Course
    </div>

    <div class="course">
        <div id="course-content">
            <c:forEach var="error" items="${errors}">
                <div class="error_message">
                    ${error}
                </div>
            </c:forEach>
            <c:if test="${success != null && success}">
                <div class="success_message">
                    U have successfully enrolled to the Course.
                </div>
            </c:if>
            <c:if test="${show != null && show}">
                <label class="label">
                    <span class="form-span">Course: *</span>
                    <select class="form-input-select" name="id" required>
                        <option value="">Select Course...</option>
                        <c:forEach var="course" items="${courses}">
                            <option value="${course.id}">${course.name}</option>
                        </c:forEach>
                    </select>
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


<%@include file="/includes/footer.jsp" %>