<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Review Test
</div>

<c:if test="${not empty errors}">
    <div class="course">
        <div id="course-content">
            <c:forEach var="error" items="${errors}">
                <div class="error_message">
                    ${error}
                </div>
            </c:forEach>
        </div>
    </div>
</c:if>

<c:if test="${show != null && show}">
    <div class="course">
        <div id="course-content">
            <label class="label">
                <span class="form-span">Test:</span>
                ${test.title}
            </label>
            <label class="label">
                <span class="form-span">Student:</span>
                ${student.firstname} ${student.surname}
            </label>
        </div>
    </div>

    <c:forEach var="question" items="${questions}">
        <div class="course">
            <div id="course-content">
                <label class="label">
                    <span class="form-span">Question:</span>
                    ${question.question}
                </label>
                <br>
                <c:if test="${showAnswer != null && showAnswer}">
                    <label class="label">
                        <span class="form-span">Given Answer:</span>
                        ${answers.get(question.id)}
                        <br>
                    </label>
                    <br>
                </c:if>
                <label class="label">
                    <span class="form-span">Correct Answer:</span>
                    ${question.correctAnswer}
                </label>
            </div>
        </div>
    </c:forEach>

</c:if>


<%@include file="/includes/footer.jsp" %>
