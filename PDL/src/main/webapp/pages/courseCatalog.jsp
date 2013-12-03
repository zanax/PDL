<%@include file="/includes/header.jsp" %>       
<div class="content-header">
    Courses
</div>

<c:forEach var="course" items="${courses}">
    <div class="course">
        <div id="course-content">

            <div id="course-content-image">
                <a href="course_economics.html"><img src="http://images.ctrustnetwork.com/static_pages/business_investment/stock_market/market_analysis/Stock.Line.chart.jpg"></a>
            </div>
            <div id="course-content-title">
                <h4><a href="course_economics.html">${course.name}</a></h4>
            </div>
            <div id="course-content-description">
                ${course.description}
            </div>
           <div class="course-button info">
                <a href="course_economics.html" class="button" id="button">Enroll</a>
            </div>
            <div class="course-button info">
                <a href="course_economics.html" class="button" id="button">More info</a>
                <a href="course_economics.html" class="button" id="button">Enroll</a>
            </div>
        </div>
    </div>
</c:forEach>

<%@include file="/includes/footer.jsp" %>