<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Disable Question
</div>
<div class="course">
    <div id="course-content" style="text-align: center;">
        <c:if test="${success != null}">
            <c:if test="${success != false}">
                <div class="success_message">
                    Question is disabled
                </div>
            </c:if>
        </c:if>
        <c:forEach var="error" items="${errors}">
            <div class="error_message">
                ${error}
            </div>
        </c:forEach>
        <form method="post" action="disableQuestion">
            <div id="course-content-title">
                <div class="error_message">
                    <div>  
                        Are you sure you want to disable this question '${question.question}'?      
                    </div>                   
                </div>
            </div>
            <div class="course-button info" style="left: 725px;">
                <input type="hidden" name="id" value="${question.id}">
                <input type="submit" value="Disable question" class="button">
                <% if (Helper.isTeacher(user)) {%>
                    <a href="teacherPanel" class="button cancel" id="button"><%= Helper.translateWord(language, "Back")%></a>
                <% }%>
                <% if (Helper.isAdmin(user)) {%>
                    <a href="adminPanel" class="button cancel" id="button"><%= Helper.translateWord(language, "Back")%></a>
                <% } %>
            </div>
        </form>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>