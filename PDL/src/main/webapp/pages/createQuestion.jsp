<%@include file="/includes/header.jsp" %>
<form method="post" action="createQuestion">

    <div class="content-header">
        Create Question
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
                    U have successfully added a Question.
                </div>
            </c:if>
            <c:if test="${show != null && show}">

                <label class="label">
                    <span class="form-span">Test: *</span>
                    <select class="form-input-select" name="test" required>
                        <option value="">Select Test...</option>
                        <c:forEach items="${tests}" var="test">
                            <option value="${test.id}" <c:if test="${test_id != null && test_id == test.id}">selected</c:if>>${test.title}</option>
                        </c:forEach>
                    </select>
                </label>
                <label class="label">
                    <span class="form-span">Question: *</span>
                    <input name="question" type="text" class="form-input" value="${questionObj.question}" required>
                </label>
                <label class="label">
                    <span class="form-span">Correct Answer: *</span>
                    <input name="correctAnswer" type="text" class="form-input" value="${questionObj.correctAnswer}" required>
                </label>
                <label class="label">
                    <span class="form-span">Other Answers:</span>
                    <input name="answer1" type="text" class="form-input" value="${questionObj.answer1}">
                </label>
                <label class="label">
                    <input name="answer2" type="text" class="form-input" value="${questionObj.answer2}">
                </label>
                <label class="label">
                    <input name="answer3" type="text" class="form-input" value="${questionObj.answer3}">
                </label>

            </c:if>
        </div>
    </div>

    <div class="course">
        <div id="course-content">
            <input type="submit" class="button" id="button" value="Add Question">
            <a href="teacherPanel" class="button" id="button">Cancel</a>
        </div>        
    </div>
</form>

<%@include file="/includes/footer.jsp" %>

<!-- <label class="label">
<span class="form-span">Course *</span>
<select class="form-input-select" name="test" required onchange="">
<option value="">Select Course...</option>
<C:forEach items="S{courses}" var="course">
<option value="S{course.id}">S{course.name}</option>
</C:forEach>
</select>
</label> -->