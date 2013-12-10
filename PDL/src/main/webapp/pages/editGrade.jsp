<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Edit Grade
</div>

<div class="course">
    <div id="course-content">
        <c:forEach var="error" items="${errors}">
            <div class="error_message">
                ${error}
            </div>
        </c:forEach>
        <c:if test="${show != null && show}">

            <form method="get" action="editGrade">
                
               <input type="submit" value="Submit" class="button" id="button"> 
            </form>
            
            
        </c:if>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>