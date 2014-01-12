<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Course Ban User
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
                U have successfully banned an User.
            </div>
        </div>
    </div>
</c:if>

<c:if test="${show != null && show}">
    <div class="course">
        <div id="course-content">
            <div class="content-inner title">
                ${course.name}
            </div>

            <table class="table">
                <tr>
                    <th style="width: 25px; ">
                        #ID
                    </th>
                    <th style="width: auto;">
                        Firstname
                    </th>
                    <th style="width: auto;">
                        Surname
                    </th>
                    <th style="width: 30px;">
                        Ban
                    </th>
                </tr>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.firstname}</td>
                        <td>${user.surname}</td>
                        <td>
                             <form method="post" action="courseBanUser">
                                <input name="userID" type="hidden" value="${user.id}">
                                <input name="courseID" type="hidden" value="${course.id}">
                                <input type="image" src="img/delete_red.png" alt="Submit Form" />
                             </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</c:if>

<%@include file="/includes/footer.jsp" %>