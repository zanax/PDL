<%@include file="/includes/header.jsp" %>       

<div class="content-header">
    Log in
</div>
<div class="course">
    <div id="course-content" style="text-align: center;">
        <c:if test="${success != null}">
            <c:if test="${success != false}">
                <div class="success_message">
                    You have been successfully registered!
                </div>
            </c:if>
        </c:if>
        <c:forEach var="error" items="${errors}">
            <div class="error_message">
                ${error}
            </div>
        </c:forEach>
        <form method="post" action="login">
            <div id="course-content-title">
                <h4>E-mail</h4>
                <input type="text" name="email" placeholder="you@mail.com" required class="form-input" style="text-align: center;">
            </div><br/>
            <div id="course-content-title">
                <h4>Password</h4>
                <input name="password" type="password" placeholder="Password" required class="form-input" style="text-align: center;">
            </div>
            
            <input class="button middle right" type="submit" value="Log in" class="button">
        </form>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>