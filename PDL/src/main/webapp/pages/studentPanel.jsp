<%@include file="/includes/header.jsp" %>  

<div class="content-header">
    <%= Helper.translateWord(language, "Student Panel")%>  
</div>

<div class="course">
    <div id="course-content">
        <div id="course-content-image">
            <a href="myCourses"><img src="http://2.bp.blogspot.com/_opS9Z5vqQYQ/TTYrMHXNiRI/AAAAAAAAAVA/H0gJ2oCoBTg/s1600/html5_bg_no_icons.png"></a>
        </div>
        <div id="course-content-title">
            <h4><a href="myCourses"><%= Helper.translateWord(language, "My Courses")%></a></h4>
        </div>
        <div id="course-content-description">
            <%= Helper.translateWord(language, "My Courses")%>  
        </div>
        <div class="course-button info">
            <a href="myCourses" class="button" id="button">Ga naar</a>
        </div>
    </div>
</div>

<div class="course">
    <div id="course-content">
        <div id="course-content-image">
            <a href="myGrades"><img src="http://images.ctrustnetwork.com/static_pages/business_investment/stock_market/market_analysis/Stock.Line.chart.jpg"></a>
        </div>
        <div id="course-content-title">
            <h4><a href="myGrades"><%= Helper.translateWord(language, "My Grades")%></a></h4>
        </div>
        <div id="course-content-description">
            <%= Helper.translateWord(language, "My Grades")%>  

        </div>
        <div class="course-button info">
            <a href="myGrades" class="button" id="button">Ga naar</a>
        </div>
    </div>
</div>

<div class="course">
    <div id="course-content">
        <div id="course-content-image">
            <a href="editUser"><img src="img/default_picture.png"></a>
        </div>
        <div id="course-content-title">
            <h4><a href="editUser"><%= Helper.translateWord(language, "Profile")%></a></h4>
        </div>
        <div id="course-content-description">
            <%= Helper.translateWord(language, "Profile")%>  

        </div>
        <div class="course-button info">

            <a href="editUser" class="button" id="button">Ga naar</a>
        </div>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>