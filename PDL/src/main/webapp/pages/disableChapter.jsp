<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Disable Chapter
</div>
<div class="course">
    <div id="course-content" style="text-align: center;">
        <c:if test="${success != null}">
            <c:if test="${success != false}">
                <div class="success_message">
                    Chapter is disabled
                </div>
            </c:if>
        </c:if>
        <c:forEach var="error" items="${errors}">
            <div class="error_message">
                ${error}
            </div>
        </c:forEach>
        <form method="post" action="disableChapter">
            <div id="course-content-title">
                <div class="error_message">
                    <div>  
                        Are you sure you want to disable this chapter: '${chapter.chapterName}'?      
                    </div>                   
                </div>
            </div>
            <div class="course-button info" style="left: 725px;">

                <input type="hidden" name="id" value="${chapter.id}">
                <input type="submit" value="Disable chapter" class="button">
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
            </div>
        </form>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>