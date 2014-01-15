<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Grade Test
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

<c:if test="${success != null && success}">
    <div class="course">
        <div id="course-content">
            <div class="success_message">
                U have successfully graded the Test. 
            </div>
        </div>
    </div>
</c:if>

<c:if test="${show != null && show}">
    <form method="post" action="gradeTest">
        <div class="course">
            <div id="course-content">
                <label class="label">
                    <span class="form-span">Test:</span>
                    ${test.title}
                </label>
                <br>
                <label class="label">
                    <span class="form-span">Student:</span>
                    ${student.firstname} ${student.surname}
                </label>
                <input type="submit" value="Grade Test" class="button" id="button">
            </div>
        </div>

        <input name="testID" type="hidden" value="${test.id}">
        <input name="studentID" type="hidden" value="${student.id}">

        <c:forEach var="question" items="${questions}">
            <div class="course">
                <div id="course-content">
                    <label class="label">
                        <span class="form-span">Question</span>
                        ${question.question}
                    </label>
                    <label class="label">
                        <span class="form-span">Answer</span>
                        ${answers.get(question.id)}
                        <br>
                    </label>
                    <label class="label">
                        <span class="form-span">Correct Answer</span>
                        ${question.correctAnswer}
                    </label>
                    <label class="label">
                        <span class="form-span">Correct</span>
                        <input name="${question.id}" value="true" type="radio" class="form-input" required>
                    </label>
                    <label class="label">
                        <span class="form-span">False</span>
                        <input name="${question.id}" value="false" type="radio" class="form-input" required>
                    </label>
                </div>
            </div>
        </c:forEach>
    </form>

</c:if>


<%@include file="/includes/footer.jsp" %>
