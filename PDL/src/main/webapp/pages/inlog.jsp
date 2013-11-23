<%@include file="/includes/header.jsp" %>       

<div class="content-header">
    Log in
</div>
<div class="course">
    <div id="course-content" style="text-align: center;">
        <c:if test="${success}">
            <div class="success_message">
                Welcome ${user.getFirstname()}
            </div>
        </c:if>
        <c:forEach var="error" items="${errors}">
            <div class="error_message">
                ${error}
            </div>
        </c:forEach>
        <form method="post" action="/PDL/inlog">
            <div id="course-content-title">
                <h4>E-mail</h4>
                <input type="text" name="email" placeholder="you@mail.com" required class="form-input" style="text-align: center;">
            </div><br/>
            <div id="course-content-title">
                <h4>Password</h4>
                <input name="password" type="password" placeholder="Password" required class="form-input" style="text-align: center;">
            </div>
            <div class="course-button info" style="left: 654px;">
                <a href="/PDL/register" class="button" id="button">Register</a>
            </div>
            <div class="course-button info" style="left: 745px;">
                <input type="submit" value="Log in" class="button">
            </div> <br/>
        </form>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>