<%@include file="/includes/header.jsp" %> 



<div class="content-header">
    Edit Chapter
</div>

<div class="course">
    <div id="course-content">


        <c:if test="${success != null && success}">
            <div class="success_message">
                The chapter has been successfully edited.
            </div>
        </c:if>
        <c:if test="${create_success != null && create_success}">
            <div class="success_message">
                The chapter has been successfully created.
            </div>
        </c:if>
        <c:forEach var="error" items="${errors}">
            <div class="error_message">
                ${error}
            </div>
        </c:forEach>
        <c:if test="${databaseError != null && databaseError}">
            <div class="error_message">
                Something went wrong, Please try again.
            </div>
        </c:if>


        <form method="post" action="editChapter">

            <label class="label">
                <span class="form-span">Course:*</span>
                <select class="form-input-select" name="course_id" required>
                    <!-- Courses ophalen en in select zetten -->
                    <option value="">Select course...</option>
                    <c:forEach items="${courses}" var="course">
                        <option value="${course.id}" <c:if test="${course_id != null && course_id == course.id}">selected</c:if>>${course.name}</option>
                    </c:forEach>
                </select>
            </label>            
            <label class="label">
                <span class="form-span">Language:</span>
                <select class="form-input-select" name="language_id" required>
                    <option value="0" <c:if test="${language == 0}">selected</c:if>>English (UK)</option>
                    <option value="1" <c:if test="${language == 1}">selected</c:if>>Nederlands</option>
                    </select>
                </label>
        </div>
    </div>

    <div class="course">
        <div id="course-content">


            <div id="course-content-description">

                <label class="label">
                    <span class="form-span">Chapter name:*</span>
                    <input name="chapterName" type="text" placeholder="Name" value="${chapter.chapterName}" class="form-input" required>
            </label>
            <label class="label">
                <span class="form-span">Chapter description:*</span>
                <textarea name="chapter_description" placeholder="description" class="form-input-textarea" required>${chapter.chapter_description}</textarea>
            </label> 
        </div>
    </div>
</div>

<div class="course">
    <div id="course-content">

        <label class="label">
            <span class="form-span">Chapter content:*</span>
            <textarea name="chapter_content" placeholder="content" class="form-input-textarea" required>${chapter.chapter_content}</textarea>
        </label>
    </div>
</div>

<div class="content-header">
    Video
</div>             
<div class="course">
    <div id="course-content">

        <label class="label">
            <span class="form-span">Video:</span>
            <input name="videoUrl" placeholder="Put the youtube video *url here..." value="${chapter.videoUrl}" class="form-input" >
        </label>
        <br><br><br>
        *use only the part of the youtube url after the "watch?v=" as shown in the image
        <div ALIGN="center" id="course-content-image">
            <img src="img/url_ex.png"  height="25">
        </div>
    </div>
</div>

<div class="course">
    <div id="course-content">
        <input type="submit" class="button" id="button" value="Submit">

        <% if (Helper.isTeacher(user)) {%>
        <li>
            <a href="teacherPanel" class="button cancel" id="button"><%= Helper.translateWord(language, "Back")%></a>
        </li>
        <% }%>
        <% if (Helper.isAdmin(user)) {%>
        <li>
            <a href="adminPanel" class="button cancel" id="button"><%= Helper.translateWord(language, "Back")%></a>
        </li>
        <%}%>  
        </form>

    </div>
</div>

<%@include file="/includes/footer.jsp" %>