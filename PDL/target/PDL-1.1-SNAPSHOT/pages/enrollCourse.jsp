<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Create Course
</div>

<div class="course">
    <div id="course-content">
        <c:forEach var="error" items="${errors}">
            <div class="error_message">
                ${error}
            </div>
        </c:forEach>
        <c:if test="${show != null && show}">
            <form method="post" action="enrollCourse">
                <label class="label">
                    <span class="form-span">Course: *</span>
                    <select class="form-input-select" name="id" required>
                        <option value="">Select Course...</option>
                        <c:forEach var="course" items="${courses}">
                            <option value="${course.id}">${course.name}t</option>
                        </c:forEach>
                    </select>
                </label>
                <label class="label">
                    <span class="form-span">Payment Method: *</span>
                    <select class="form-input-select" name="paymentMethod" required>
                        <option value="">Select Payment Method...</option>
                        <option value="paypal">Paypal</option>
                        <option value="creditcard">Credit Card</option>
                    </select>
                </label>
                <input type="submit" value="Pay for this Course" class="button" id="button">
            </form>
        </c:if>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>