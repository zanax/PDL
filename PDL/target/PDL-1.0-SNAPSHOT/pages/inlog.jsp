<%@include file="/includes/header.jsp" %>       

<div class="content-header">
    Log in
</div>
<div class="course">
    <div id="course-content" style="text-align: center;">
        <form method="post" action="">
            <div id="course-content-title">
                <h4>Username</h4>
                <input type="text" placeholder="Username" style="border: 1px solid #00B711; border-radius: 5px 5px 5px 5px; height: 25px; text-align: center; width: 215px;">
            </div><br/>
            <div id="course-content-title">
                <h4>Password</h4>
                <input type="password" placeholder="Password" style="border: 1px solid #00B711; border-radius: 5px 5px 5px 5px; height: 25px; text-align: center; width: 215px;">
            </div>
            <div class="course-button info" style="left: 654px;">
                <a href="register" class="button" id="button">Register</a>
            </div>
            <div class="course-button info" style="left: 745px;">
                <a href="test.html" class="button" id="button">Log in</a>
            </div> <br/>
        </form>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>