<%@include file="/includes/header.jsp" %>

<div class="content-header">
    Change permission - ${user.firstname} ${user.surname}
</div>

<div class="course">
    <div id="course-content">
        <c:forEach var="error" items="${errors}">
            <div class="error_message">
                ${error}
            </div>
        </c:forEach>
        <c:if test="${success != null && success}">
            <a href="adminPanel" >
            <div class="success_message">
                Succes! Click to get back to the Admin Panel.
            </div>
            </a>
        </c:if>
        <c:if test="${show != null && show}">
            <c:if test="${user.kindOfUser.equals('Student')}">
                <form method="post" action="changeUser">
                    <label class="label">
                        <span class="form-span">From ${user.kindOfUser} to:</span>
                        <select class="form-input-select" name="permission" required>
                            <option value="teacher">Teacher</option>
                            <option value="admin">Admin</option>
                        </select>
                    </label>
                    <input type="submit" value="Change user" class="button" id="button">
                </form>   
            </c:if>
            <c:if test="${user.kindOfUser.equals('Admin')}">
                <form method="post" action="changeUser">
                    <label class="label">
                        <span class="form-span">From ${user.kindOfUser} to:</span>
                        <select class="form-input-select" name="permission" required>
                            <option value="teacher">Teacher</option>
                            <option value="student">Student</option>
                        </select>
                    </label>
                    <input type="submit" value="Change user" class="button" id="button">
                </form>   
            </c:if>
            <c:if test="${user.kindOfUser.equals('Teacher')}">
                <form method="post" action="changeUser">
                    <label class="label">
                        <span class="form-span">From ${user.kindOfUser} to:</span>
                        <select class="form-input-select" name="permission" required>
                            <option value="student">Student</option>
                            <option value="admin">Admin</option>
                        </select>
                    </label>
                    <input type="submit" value="Change user" class="button" id="button">
                </form>   
            </c:if>
            </c:if>
        </div>
    </div>

    <%@include file="/includes/footer.jsp" %>