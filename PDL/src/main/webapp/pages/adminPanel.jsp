<%@include file="/includes/header.jsp" %>

<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

<script language=JavaScript>
    var edit_normal = new Image();
    edit_normal.src = "edit_normal.png";

    var edit_green = new Image();
    edit_green.src = "edit_green.png";

    var delete_normal = new Image();
    delete_normal.src = "delete_normal.png";

    var delete_red = new Image();
    delete_red.src = "delete_red.png";
</script>


<div class="content-header">
    Administrator options
</div>

<div class="content-inner">
    <div id="content-inner-block">
        <div class="content-inner title">
            Users
        </div>       

        <table class="table" id="userTable">
            <input type="text" id="searchUsers" placeholder="Type to search">
            <tr>
                <th style="width: 25px; ">
                    #ID
                </th>
                <th style="width: 120px;">
                    Email
                </th>
                <th style="width: auto;">
                    Name
                </th>
                <th style="width: auto;">
                    Type of user
                </th>
                <th style="width: auto;">
                    Banned
                </th>
                <th style="width: 30px;">
                    Actions
                </th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.email}</td>
                    <td>${user.firstname} ${user.surname}</td>
                    <td>${user.kindOfUser}</td>
                    <td>${user.isBanned}</td>
                    <td>
                        <a href="changeUser?id=${user.id}"><img id="edit" name="edit" alt="Edit" src="img/edit_green.png"></a>
                        <a href="javascript:if(confirm('Weet u het zeker dat u ${user.firstname} ${user.surname} wilt bannen/unbannen?'))
                           window.location='setBanned?id=${user.id}';"><img id="delete" name="delete" alt="Delete" src="img/delete_red.png"></a>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </div>
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
                <th style="width: auto;">
                    Active
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
                    <td>${course.isActive}</td>
                    <td>
                        <a href="editCourse?id=${course.id}">
                            <img id="edit" name="edit" alt="Edit" src="img/edit_green.png">
                        </a>
                        <a href="disableCourse?id=${course.id}">
                            <img id="delete" name="delete" alt="Delete" src="img/delete_red.png">
                        </a>
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
            Chapters
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
            <th style="width: auto;">
                Active
            </th>
            <th style="width: 30px;">
                Actions
            </th>
            <c:forEach items="${chapters}" var="chapter">
                <tr>
                    <td style="text-align: center;">${chapter.id}</td>
                    <td>${chapter.chapterName}</td>
                    <td>${chapter.chapter_description}</td>
                    <td>${chapter.isActive}</td>
                    <td style="text-align: center;">
                        <a href="editChapter?id=${chapter.id}"><img src="img/edit_green.png"></a>
                        <a href="disableChapter?id=${chapter.id}"><img src="img/delete_red.png"></a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <a href="createChapter">
            <div class="button full-width middle">
                Create chapter
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
                    <td>${test.isActive}</td>
                    <td style="text-align: center;">
                        <a href="editTest?id=${test.id}"><img src="img/edit_green.png"></a>
                        <a href="reviewTest?id=${test.id}"><img src="img/edit_normal.png"></a>
                        <a href="deleteTest?id=${test.id}"><img src="img/delete_red.png"></a>
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
                <th style="width: auto;">
                    Active
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
                    <td>${question.isActive}</td>

                    <td>
                        <a href="editQuestion?id=${question.id}"><img src="img/edit_green.png"></a>
                        <a href="disableQuestion?id=${question.id}"><img src="img/delete_red.png"></a>
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

<div class="content-inner">
    <div id="content-inner-block">
        <div class="content-inner title">
            Grade Test
        </div>
        <table class="table">
            <th style="width: auto;">
                Test
            </th>
            <th style="width: auto;">
                Student
            </th>
            <th style="width: 30px;">
                Actions
            </th>
            <c:forEach items="${madedTests}" var="madedTest">
                 <c:forEach items="${madedTest.value}" var="Test">
                    <tr>
                        <td>${Test}</td>
                        <td>${madedTest.key}</td>
                        <td style="text-align: center;">
                            <a href="gradeTest?testID=${Test}&studentID=${madedTest.key}"><img src="img/edit_green.png"></a>
                        </td>
                    </tr>
                 </c:forEach>
            </c:forEach>
        </table>
    </div>
</div>

<div class="content-inner">
    <div id="content-inner-block">
        <div class="content-inner title">
            Edit Grade
        </div>
        <table class="table">
            <th style="width: 25px; ">
                #ID
            </th>
            <th style="width: auto;">
                Test
            </th>
            <th style="width: auto;">
                Student
            </th>
            <th style="width: 30px;">
                Actions
            </th>
            <c:forEach items="${grades}" var="grade">
                <tr>
                    <td>${grade.id}</td>
                    <td>${grade.testId}</td>
                    <td>${grade.userId}</td>
                    <td style="text-align: center;">
                        <a href="reviewTest?id=${grade.testId}&student=${grade.userId}"><img src="img/edit_normal.png"></a>
                        <a href="editGrade?testID=${grade.testId}&studentID=${grade.userId}"><img src="img/edit_green.png"></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
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

<script type="text/javascript">
    var $rows = $('#userTable tr');
    $('#searchUsers').keyup(function() {
        var val = $.trim($(this).val()).replace(/ +/g, ' ').toLowerCase();

        $rows.show().filter(function() {
            var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
            return !~text.indexOf(val);
        }).hide();
    });
</script>

<%@include file="/includes/footer.jsp" %>