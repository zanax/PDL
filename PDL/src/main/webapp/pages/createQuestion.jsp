<<<<<<< HEAD
<%@include file="/includes/header.jsp" %>       
<div class="content-header">
    Courses
</div>

<c:forEach var="course" items="${questions}">
    <div class="course">
        <div id="course-content">

            <div id="course-content-image">
                <a href="course_economics.html"><img src="http://images.ctrustnetwork.com/static_pages/business_investment/stock_market/market_analysis/Stock.Line.chart.jpg"></a>
            </div>
            <div id="course-content-title">
                <h4><a href="course_economics.html">${course.name}</a></h4>
            </div>
            <div id="course-content-description">
                ${course.description}
            </div>
           <div class="course-button info">
                <a href="course_economics.html" class="button" id="button">Enroll</a>
            </div>
            <div class="course-button info">
                <a href="course_economics.html" class="button" id="button">More info</a>
                <a href="course_economics.html" class="button" id="button">Enroll</a>
            </div>
        </div>
    </div>
</c:forEach>

<%@include file="/includes/footer.jsp" %>
=======
<%@include file="/includes/header.jsp" %> 

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
            <form method="post" action="createQuestion">
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
                <input type="submit" value="Add Question" class="button" id="button">
            </form>
        </c:if>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>

<!--                <label class="label">
                    <span class="form-span">Course *</span>
                    <select class="form-input-select" name="test" required onchange="">
                    <option value="">Select Course...</option>
                        <C:forEach items="S{courses}" var="course">
                            <option value="S{course.id}">S{course.name}</option>
                        </C:forEach>
                    </select>
                </label> -->
>>>>>>> e23038c8f3b4ffab997154379561dcbbeba445c6
