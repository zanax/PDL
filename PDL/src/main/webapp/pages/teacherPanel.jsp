<%@include file="/includes/header.jsp" %>       

<div class="content-header">
    Teacher
</div>
<div class="content-inner">
    <div id="content-inner-block">
        <div class="content-inner title">
            Courses
        </div>
        
        <table class="table">
            <tr>
                <th style="width: 25px; ">
                    #ID
                </th>
                <th style="width: 120px;">
                    Title
                </th>
                <th style="width: auto;">
                    Description
                </th>
                <th style="width: 30px;">
                    Actions
                </th>
            </tr>
            <c:forEach items="${courses}" var="course">
                <tr>
                    <td>${course.id}</td>
                    <td>${course.name}</td>
                    <td>${course.description}</td>
                    <td>
                        <a href="editCourse?id=${course.id}"><img src="img/edit.png"></a>
                        <a href="disableCourse?id=${course.id}"><img src="img/delete.png"></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <a href="createCourse">
            <div class="button full-width middle">
                Create course
            </div>
        </a>
    </div>
</div>
<div class="content-inner">
    <div id="content-inner-block">
        <div class="content-inner title">
            Tests
        </div>
        <table class="table">
            <th style="width: 25px;">
                #ID
            </th>
            <th style="width: 120px;">
                Title
            </th>
            <th style="width: auto;">
                Description
            </th>
            <th style="width: 30px;">
                Actions
            </th>
            <c:forEach items="${tests}" var="test">
                <tr>
                    <td style="text-align: center;">${test.id}</td>
                    <td>${test.title}</td>
                    <td>${test.description}</td>
                    <td style="text-align: center;">
                        <a href="editTest?id=${test.id}"><img src="img/edit.png"></a>
                        <a href="deleteTest?id=${test.id}"><img src="img/delete.png"></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <a href="createTest">
            <div class="button full-width middle">
                Create test
            </div>
        </a>
    </div>
    
</div>

<div class="content-inner">
    <div id="content-inner-block">
        <div class="content-inner title">
            Questions
        </div>
        
        <table class="table">
            <tr>
                <th style="width: 25px; ">
                    #ID
                </th>
                <th style="width: 120px;">
                    Test
                </th>
                <th style="width: auto;">
                    Description
                </th>
                <th style="width: 30px;">
                    Actions
                </th>
            </tr>
            <c:forEach items="${questions}" var="question">
                <tr>
                    <td>${question.id}</td>
                    <td>${question.testTitle}</td>
                    <td>${question.description}</td>
                    <td>
                        <a href="editQuestion?id=${question.id}"><img src="img/edit.png"></a>
                        <a href="disableCourse?id=${question.id}"><img src="img/delete.png"></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <a href="createQuestion">
            <div class="button full-width middle">
                Create question
            </div>
        </a>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>