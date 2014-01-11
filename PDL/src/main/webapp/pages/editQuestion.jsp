<%@include file="/includes/header.jsp" %>

<div class="content-header">
    Edit Question
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

        <form method="post" action="editQuestion">
            <input type="hidden" value="${questionObj.id}" name="id">

            <label class="label">
                <span class="form-span">Test: *</span>
                <select class="form-input-select" name="test" required>
                    <option value="">Select Test...</option>
                    <c:forEach items="${tests}" var="test">
                        <option value="${test.id}" ${test.id == questionObj.testId ? 'selected' : ''}>${test.title}</option>
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
            <input type="submit" value="Save Question" class="button" id="button">
            <% if (Helper.isTeacher(user)) {%>
            <li>
                <a href="teacherPanel" class="button cancel" id="button"><%= Helper.translateWord(language, "Back")%></a>
            </li>
            <% }%>
            <% if (Helper.isAdmin(user)) {%>
            <li>
                <a href="adminPanel" class="button cancel" id="button"><%= Helper.translateWord(language, "Back")%></a>
            </li>
            <% } else {%>
            <li>
            </li>
            <%}%>
        </form>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>