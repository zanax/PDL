<%@include file="/includes/header.jsp" %>

<div class="content-header">
    Make Test
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
                U have successfully submitted the Test.
            </div>
        </div>
    </div>
</c:if>

<script>
var mins = 0;
var secs = 0;

function cd() {
    mins = ${test.time};
    redo();
}

function dis(mins, secs) {
    var disp;
    if(mins <= 9) {
        disp = " 0";
    } else {
        disp = " ";
    }
    disp += mins + ":";
    if(secs <= 9) {
        disp += "0" + secs;
    } else {
        disp += secs;
    }
    return(disp);
}

function redo() {
    secs--;
    if(secs === -1) {
        secs = 59;
        mins--;
    }
    document.makeTest.disp.value = dis(mins,secs); // setup additional displays here.
    if((mins === 0) && (secs === 0)) {
        window.alert("Time is up. Press OK to continue."); // change timeout message as required
        window.location = " "; // redirects to specified page once timer ends and ok button is pressed
    } else {
        cd = setTimeout("redo()",1000);
    }
}

function init() {
  cd();
}

window.onload = init;

</script>

<c:if test="${show != null && show}">
    <form name="makeTest" method="post" action="makeTest">
        <div class="course">
            <div id="course-content">
                <label class="label">
                    <span class="form-span">Test:</span>
                    ${test.title}
                </label>
                <br>
                <label class="label">
                    <span class="form-span">Time:</span>
                    <input id="txt" type="text" name="disp">
                </label>
                <input type="submit" value="Submit Test" class="button" id="button">
            </div>
        </div>

        <input name="id" type="hidden" value="${test.id}">

        <c:forEach var="question" items="${questions}">
            <div class="course">
                <div id="course-content">
                    <label class="label">
                        <span class="form-span">Question</span>
                        ${question.question}
                    </label>
                    <c:choose>
                        <c:when test="${not empty question.answer1 or not empty question.answer2 or not empty question.answer3}">
                            <c:if test="${not empty question.answer1}">
                                <label class="label">
                                    <span class="form-span">A:</span>
                                    <input name="${question.id}" type="radio" value="${question.answer1}" class="form-input">
                                    ${question.answer1}
                                </label>
                            </c:if>
                            <c:if test="${not empty question.answer2}">
                                <label class="label">
                                    <span class="form-span">B:</span>
                                    <input name="${question.id}" type="radio" value="${question.answer2}" class="form-input">
                                    ${question.answer2}
                                </label>
                            </c:if>
                            <c:if test="${not empty question.answer3}">
                                <label class="label">
                                    <span class="form-span">C:</span>
                                    <input name="${question.id}" type="radio" value="${question.answer3}" class="form-input">
                                    ${question.answer3}
                                </label>
                            </c:if>
                            <label class="label">
                                <span class="form-span">D:</span>
                                <input name="${question.id}" type="radio" value="${question.correctAnswer}" class="form-input">
                                ${question.correctAnswer}
                            </label>
                        </c:when>
                        <c:otherwise>
                            <label class="label">
                                <span class="form-span">Answer:</span>
                                <input name="${question.id}" type="text" class="form-input">
                            </label>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </c:forEach>
    </form>

</c:if>


<%@include file="/includes/footer.jsp" %>
