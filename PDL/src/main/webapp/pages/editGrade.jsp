<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Edit Grade
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
                U have successfully edited the Grade.
            </div>
        </div>
    </div>
</c:if>

<<<<<<< HEAD
            <form method="get" action="editGrade">

                <input type="submit" value="Submit" class="button" id="button"> 
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


        </c:if>
=======
<c:if test="${show != null && show}">
    <div class="course">
        <div id="course-content">
            <form method="post" action="editGrade">
                <label class="label">
                    <input name="testID" type="hidden" value="${test.id}">
                    <span class="form-span">Test:</span>
                    ${test.title}
                </label>
                <label class="label">
                    <input name="studentID" type="hidden" value="${student.id}">
                    <span class="form-span">Student:</span>
                    ${student.firstname} ${student.surname}
                </label>
                <label class="label">
                    <span class="form-span">Grade:</span>
                    <input type="text" name="grade" value="${grade.grade}"> 
                </label>
                <input type="submit" value="Submit" class="button" id="button"> 
            </form>

            <label class="label">
                <% if (Helper.isTeacher(user)) {%>
                <a href="teacherPanel" class="button cancel" id="button"><%= Helper.translateWord(language, "Back")%></a>
                <% } else if (Helper.isAdmin(user)) {%>
                <a href="adminPanel" class="button cancel" id="button"><%= Helper.translateWord(language, "Back")%></a>
                <% }%>
            </label>
        </div>
>>>>>>> ad9ac49bb45e6f95e4afec0da997b53b77e39091
    </div>
</c:if>

<%@include file="/includes/footer.jsp" %>