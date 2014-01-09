<%@include file="/includes/header.jsp" %> 


<c:if test="${show == null}">
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

<div class="course">
    <div id="course-content">

        <div ALIGN="center" id="course-content-image">
            <img src="img/${chapter.imgSrc}"  height="200">
        </div>
        <div class="content-header">
            ${chapter.chapterName}
        </div>
        <div id="course-content-description">
            ${chapter.chapter_description}
        </div>
        <div class="course-button info">
            <a href="courseDetails?id=${chapter.course_id}" class="button" id="button">Back to course home</a>
        </div>


    </div>
</div>

<c:if test="${show != null}">



    <div class="course">
        <div id="course-content">
            ${chapter.chapter_content}           
        </div>
        <div  >
            <iframe width="640" height="390" src="//www.youtube.com/embed/IhgtPXvRtX8" frameborder="0" allowfullscreen></iframe>

        </div>

    </div>


</c:if>

<%@include file="/includes/footer.jsp" %>