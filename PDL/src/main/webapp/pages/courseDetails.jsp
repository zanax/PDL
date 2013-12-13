<%@include file="/includes/header.jsp" %>


<div class="course">
    <div id="course-content">
        <c:forEach var="error" items="${errors}">
            <div class="error_message">
                ${error}
            </div>
        </c:forEach>

        <c:if test="${show != null}">

            <div class="content-header">
                Welcome to: ${course.name}!
            </div>

            <div class="course">
                <div id="course-content">

                    <div id="course-content-image">
                        <img src="http://images.ctrustnetwork.com/static_pages/business_investment/stock_market/market_analysis/Stock.Line.chart.jpg"></a>
                    </div>
                    <div id="course-content-description">
                        <b>Course description:</b><br>
                        ${course.description}
                    </div>
                    <div class="course-button info">
                        <a href="" class="button" id="button">Enroll in this course</a>
                    </div>
                </div>
            </div>

            <div class="content-header">
                Chapters in this course
            </div>

            <div class="content-header">
                Course Tests
            </div>


        </c:if>

        <%@include file="/includes/footer.jsp" %>